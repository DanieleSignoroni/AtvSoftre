package it.softre.thip.base.attivita;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.Set;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.sun.mail.util.MailSSLSocketFactory;
import com.thera.thermfw.ad.ClassAD;
import com.thera.thermfw.ad.ClassADCollection;
import com.thera.thermfw.ad.ClassADCollectionManager;
import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.ssd.SSDConfiguration;
import com.thera.thermfw.type.EnumType;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.base.partner.RubricaEstesa;
import it.thera.thip.base.profilo.UtenteAzienda;

/**
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 21/05/2024 <br>
 *         <br>
 *         <b>71543 DSSOF3 21/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 * <b>71558	DSSOF3	20/06/2024</b>
 * <p>
 * Implementazione notifiche prima versione.<br>
 * </p>
 */
public class AttivitaSoftre extends AttivitaSoftrePO {

	//Per evitare buchi non usiamo THERA.NUMERATOR ma usiamo una nostra query
	public static final String STMT_NEXT_PROGR = "SELECT (COALESCE(MAX("+AttivitaSoftreTM.ID+"),0)+1) AS NEXT_VALUE FROM "+AttivitaSoftreTM.TABLE_NAME+" ";
	public static final CachedStatement cNextProgressivo = new CachedStatement(STMT_NEXT_PROGR);

	protected AttivitaSoftre iOldAttivita;

	private static Session session = null;

	protected List<Dipendente> relatedEmployees = null;

	public List<Dipendente> getRelatedEmployees() {
		return relatedEmployees;
	}

	public void setRelatedEmployees(List<Dipendente> relatedEmployees) {
		this.relatedEmployees = relatedEmployees;
	}

	public AttivitaSoftre getOldAttivita() {
		return iOldAttivita;
	}

	public void setOldAttivita(AttivitaSoftre oldAttivita) {
		iOldAttivita = oldAttivita;
	}

	public ErrorMessage checkDelete() {
		return null;
	}

	@Override
	public boolean initializeOwnedObjects(boolean result) {
		boolean ret = super.initializeOwnedObjects(result);
		if (isOnDB()){
			creaOldTestata();
			copiaValoriInOldTestata();
			retrieveRelatedEmployees(); //recupero i dipendenti relazionati che saranno poi da notificare
		}
		return ret;
	}

	protected void copiaValoriInOldTestata() {
		iOldAttivita.setId(getId());
		iOldAttivita.setDataCompletamento(getDataCompletamento());
		iOldAttivita.setDataFine(getDataFine());
		iOldAttivita.setDataCompletamento(getDataCompletamento());
		iOldAttivita.setDataInizio(getDataInizio());
		iOldAttivita.setDataPrevistaConsegna(getDataPrevistaConsegna());
		iOldAttivita.setQuotazioneGg(getQuotazioneGg());
		iOldAttivita.setQuotazioneOre(getQuotazioneOre());
		iOldAttivita.setTipoFatturazione(getTipoFatturazione());
		iOldAttivita.setStatoCnfCliente(getStatoCnfCliente());
		iOldAttivita.setIdClienteSoftre(getClientesoftre() != null ? getClientesoftre().getIdAnagrafico() : null);
		iOldAttivita.setIdIncaricato(getIncaricato() != null ? getIncaricato().getIdUtente() : null);
		iOldAttivita.setTicketSisthema(getTicketSisthema());
		iOldAttivita.setRichiedenteCliente(getRichiedenteCliente());
		iOldAttivita.setRichiedenteSoftre(getRichiedenteSoftre());
		iOldAttivita.setPriorita(getPriorita());
		iOldAttivita.setCommessaSmeup(getCommessaSmeup());
		iOldAttivita.setStatoAttivita(getStatoAttivita());

	}

	protected void creaOldTestata(){
		iOldAttivita =(AttivitaSoftre)Factory.createObject(AttivitaSoftre.class);
	}

	public int save() throws SQLException {
		if (!isOnDB()) {
			setId(getNextIdProgressivo());
		}
		if(isOnDB()) {
			controllaIncaricatoSeCollaboratore();
		}
		calcolaGiorniQuotazione();
		if(getIdIncaricato() != null && 
				(getOldAttivita().getIncaricato() == null  
				|| !(getIdIncaricato().equals(getOldAttivita().getIdIncaricato()))
						)){
			//allora devo notificare all'incaricato l'assegnazione della attività
			notificaIncaricatoAssegnazioneAttivita();
		}
		return super.save();
	}

	protected void notificaIncaricatoAssegnazioneAttivita() {
		ClassADCollection hdr = null;
		try {
			hdr = ClassADCollectionManager.collectionWithName("AttivitaSoftre");
			UtenteAzienda utente = (UtenteAzienda) UtenteAzienda.elementWithKey(UtenteAzienda.class, 
					KeyHelper.buildObjectKey(new String[] {
							Azienda.getAziendaCorrente(),
							getIdIncaricato()
					}), PersistentObject.NO_LOCK);
			Dipendente assegnatario = UtenteAzienda.getUtenteAziendaConnesso().getDipendente();
			if(utente.getDipendente() != null && assegnatario != null) {
				Dipendente assegnato = utente.getDipendente();
				Message message = new MimeMessage(sessionForSendMail());
				message.setFrom(new InternetAddress("info@softre.it"));
				message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(utente.getDipendente().getEmail()));
				message.setSubject(assegnatario.getNome() + " " +assegnatario.getCognome()+ " ti ha assegnato un'attività : "+getNomeAttivita());
				ClassAD tipoFatturazione = hdr.getAttribute("TipoFatturazione");
				ClassAD statoCnfCliente = hdr.getAttribute("StatoCnfCliente");
				String dataConsegnaPrevista = "Nessuna data scadenza";
				if(getDataPrevistaConsegna() != null) {
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					dataConsegnaPrevista = format.format(getDataPrevistaConsegna());
				}
				String htmlContent = "<!DOCTYPE html>" +
						"<html>" +
						"<head>" +
						"<meta charset='UTF-8'>" +
						"<title>Task Assignment Notification</title>" +
						"<style>" +
						"body { font-family: Arial, sans-serif; margin: 0; padding: 0; background-color: #f8f9fa; }" +
						".container { width: 100%; max-width: 600px; margin: 0 auto; padding: 20px; background-color: #ffffff; border: 1px solid #dddddd; }" +
						".header { text-align: center; padding: 20px; background-color: #f8f9fa; border-bottom: 1px solid #dddddd; }" +
						".header h1 { font-size: 24px; margin: 0; }" +
						".content { padding: 20px; }" +
						".content p { font-size: 16px; line-height: 1.5; }" +
						".button { display: inline-block; padding: 10px 20px; margin: 20px 0; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px; }" +
						".details { padding: 20px; background-color: #f8f9fa; border: 1px solid #dddddd; }" +
						".details p { margin: 10px 0; }" +
						".footer { text-align: center; padding: 20px; background-color: #f8f9fa; border-top: 1px solid #dddddd; }" +
						".footer p { font-size: 14px; color: #888888; }" +
						"</style>" +
						"</head>" +
						"<body>" +
						"<div class='container'>" +
						"<div class='header'>" +
						"<h1>"+assegnatario.getNome()+" ti ha assegnato un'attività</h1>" +
						"</div>" +
						"<div class='content'>" +
						"<p>"+getNomeAttivita()+"</p>" +
						"<div class='details'>" +
						"<p><strong>Cliente:</strong> "+(getClientesoftre() != null ? getClientesoftre().getRagioneSociale() : "")+"</p>" +
						"<p><strong>Assegnata a:</strong> "+(assegnato.getNome() + " "+assegnato.getCognome())+"</p>" +
						"<p><strong>Quotazione ore:</strong> "+(getQuotazioneOre() != null ? getQuotazioneOre().setScale(2) : "")+"</p>" +
						"<p><strong>Tipo Fatturazione:</strong> "+(((EnumType)tipoFatturazione.getType()).descriptionFromValue(String.valueOf(getTipoFatturazione())))+"</p>" +
						"<p><strong>Stato conferma cliente:</strong> "+(((EnumType)statoCnfCliente.getType()).descriptionFromValue(String.valueOf(getStatoCnfCliente())))+"</p>";
				RubricaEstesa rubrica = null;
				String richiedente = null;
				//Inizio a scrivere la sezione dei richiedenti
				if(getRichiedenteSoftre() != null) {
					rubrica = getRubricaSoftre();
					if(rubrica != null) {
						richiedente = rubrica.getCognome() + " " + rubrica.getNome();
						htmlContent += 
								"<p><strong>Richiedente Softre:</strong> "+richiedente+" </p>";
					}
				}
				if(getRichiedenteCliente() != null) {
					rubrica = getRubricaCliente();
					if(rubrica != null) {
						richiedente = rubrica.getCognome() + " " + rubrica.getNome();
						htmlContent += 
								"<p><strong>Richiedente Cliente:</strong> "+richiedente+" </p>";
					}
				}
				//Fine sezione richiedenti
				//Data scadenza
				htmlContent += 
						"<p><strong>Data di scadenza:</strong> "+dataConsegnaPrevista+" </p>" +
								"<p><strong>Collaboratori:</strong> "+(getCollaboratoriConcatenati(assegnato, "e"))+"</p>";
				//Eventuale commessa Smeup
				if(getCommessaSmeup() != null) {
					htmlContent += 
							"<p><strong>Commessa SMEUP:</strong> "+getCommessaSmeup()+" </p>";
				}
				//Allego l'analisi (per poterla 'Allegare' devo scrivere qualcosa nell' <a>xxx</a> e poi lo nascondo)
				htmlContent +=
						"<div style='display:none;'><a href="+getLinkAnalisi()+">Analisi</a></div>";
				//End
				htmlContent += "</div>" +
						"</div>" +
						"</div>" +
						"</body>" +
						"</html>";
				message.setContent(htmlContent, "text/html; charset=utf-8");
				sendMessage(message);
			}
		} catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		} catch (MessagingException e) {
			e.printStackTrace(Trace.excStream);
		} catch (NoSuchElementException e) {
			e.printStackTrace(Trace.excStream);
		} catch (NoSuchFieldException e) {
			e.printStackTrace(Trace.excStream);
		}

	}

	@SuppressWarnings("unchecked")
	protected String getCollaboratoriConcatenati(Dipendente incaricato,String concatenazione) {
		if(!isOnDB()) {
			if(incaricato == null)
				return "";
			return incaricato.getNome() + " " +incaricato.getCognome();
		}else {
			String result = "";
			Iterator<AttivitaCollaboratore> collaboratori = getAttivitaCollaboratori().iterator();
			while(collaboratori.hasNext()) {
				AttivitaCollaboratore collaboratore = collaboratori.next();
				Dipendente dip = collaboratore.getDipendente();
				if(dip == null) {
					UtenteAzienda utenteAzienda = null;
					try {
						utenteAzienda = (UtenteAzienda) 
								UtenteAzienda.elementWithKey(UtenteAzienda.class,
										KeyHelper.buildObjectKey(new String[] {
												getIdAzienda(),
												collaboratore.getIdUtente()
										}), PersistentObject.NO_LOCK);
					} catch (Exception e) {
						//
					}
					dip = utenteAzienda.getDipendente();
				}
				result = dip.getNome() + " " + dip.getCognome();
				if(collaboratori.hasNext()) {
					result += " "+concatenazione+" ";
				}
			}
			return result;
		}
	}

	/**
	 * @author Daniele Signoroni 30/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Si occupa di gestire la relazione tra incaricato e la collezione Collaboratori.<br></br>
	 * 
	 * Se ho l'incaricato e questo non e' presente tra i collaboratori lo codifico.<br>
	 * 
	 * Se avevo l'incaricato e poi lo svuoto e questo era tra i collaboratori lo elimino.<br>
	 * </p>
	 * @throws SQLException
	 */
	@SuppressWarnings("unchecked")
	protected void controllaIncaricatoSeCollaboratore() throws SQLException {
		if(getIncaricato() != null) {
			List<AttivitaCollaboratore> collaboratori = getAttivitaCollaboratori();
			boolean daCodificare = true;
			for(AttivitaCollaboratore collaboratore : collaboratori) {
				if(collaboratore.getUtente().equals(getIncaricato())) {
					daCodificare = false;
				}
			}
			if(daCodificare) {
				AttivitaCollaboratore collaboratore = (AttivitaCollaboratore) Factory.createObject(AttivitaCollaboratore.class);
				collaboratore.setParent(this);
				collaboratore.setUtente(getIncaricato());
				getAttivitaCollaboratori().add(collaboratore);
			}
		}else {
			List<AttivitaCollaboratore> collaboratori = getAttivitaCollaboratori();
			String idOldIncaricato = getOldAttivita().getIdIncaricato();
			for(AttivitaCollaboratore collaboratore : collaboratori) {
				if(collaboratore.getUtente().getIdUtente().equals(idOldIncaricato)) {
					getAttivitaCollaboratori().remove(collaboratore);
				}
			}
		}
		//Facciamo che il campo e' usato solo per comodo, ad ogni salvataggio se l'incaricato indicato non e'
		//presente nei collaboratori allora lo codifico
		//Sara' poi l'utente a video a rimuovere nel caso i collaboratori indesiderati
		//setIncaricato(null); 
	}

	protected void calcolaGiorniQuotazione() {
		if(getQuotazioneOre() != null && getQuotazioneOre().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal quotazioneGiorni = getQuotazioneOre().divide(new BigDecimal(8)).setScale(2, RoundingMode.HALF_UP); //up perche' js fa up
			setQuotazioneGg(quotazioneGiorni);
		}
	}

	public static Integer getNextIdProgressivo() {
		ResultSet rs = null;
		try{
			PreparedStatement ps = cNextProgressivo.getStatement();
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace(Trace.excStream);
		}finally{
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return -1;
	}

	@SuppressWarnings("unchecked")
	public void retrieveRelatedEmployees(){
		List<Dipendente> employees = new ArrayList<>();
		Set<String> addedEmployeeIds = new HashSet<>();

		// Iterate over AttivitaCollaboratore objects and add unique Dipendente instances
		Iterator<AttivitaCollaboratore> iterCollaboratori = getAttivitaCollaboratori().iterator();
		while (iterCollaboratori.hasNext()) {
			AttivitaCollaboratore collaboratore = iterCollaboratori.next();
			if (!collaboratore.getIdUtente().equals(UtenteAzienda.getUtenteAziendaConnesso().getIdUtente())) {
				Dipendente dipendente = collaboratore.getDipendente();
				if (dipendente != null && addedEmployeeIds.add(dipendente.getIdDipendente())) {
					employees.add(dipendente); // Add only if the ID was not already in the set
				}
			}
		}

		// Check if the creating user is different from the connected user and add their Dipendente if unique
		String idUtenteCreazione = getDatiComuni().getIdUtenteCrz().substring(0, getDatiComuni().getIdUtenteCrz().indexOf("_"));
		if (!idUtenteCreazione.equals(UtenteAzienda.getUtenteAziendaConnesso().getIdUtente())) {
			try {
				UtenteAzienda utenteAzienda = (UtenteAzienda)
						UtenteAzienda.elementWithKey(UtenteAzienda.class,
								KeyHelper.buildObjectKey(new String[]{
										Azienda.getAziendaCorrente(),
										idUtenteCreazione
								}), PersistentObject.NO_LOCK);
				if (utenteAzienda != null) {
					Dipendente dipendente = utenteAzienda.getDipendente();
					if (dipendente != null && addedEmployeeIds.add(dipendente.getIdDipendente())) {
						employees.add(dipendente); // Add only if the ID was not already in the set
					}
				}
			} catch (Exception e) {
				e.printStackTrace(Trace.excStream);
			}
		}

		setRelatedEmployees(employees);
	}

	public Session sessionForSendMail() {
		if(session == null) {
			try {
				final SSDConfiguration conf = (SSDConfiguration) SSDConfiguration.elementWithKey("0", PersistentObject.NO_LOCK);
				Properties props = new Properties();  
				props.put("mail.smtp.host", conf.getSMTPServer());
				props.put("mail.from", "info@softre.it");
				props.put("mail.smtp.port", 25);

				MailSSLSocketFactory trustAllSocketFactory = null;
				try {
					trustAllSocketFactory = new MailSSLSocketFactory();
					trustAllSocketFactory.setTrustAllHosts(true);		
				}
				catch (Exception e) {
					e.printStackTrace(Trace.excStream);
				}
				Authenticator auth = null;
				if (conf.getSMTPAccount() != null && conf.getSMTPPassword() != null && conf.getSMTPPassword().length() != 0 && conf.getSMTPPassword().length() != 0){//Fix 30398 
					auth = new javax.mail.Authenticator() {protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(conf.getSMTPAccount(),conf.getSMTPPassword());}};
				}
				if (auth != null) { 
					props.put("mail.smtp.auth", "true");
					session = Session.getInstance(props, auth);
				} else {
					props.put("mail.smtp.auth", "false");
					session = Session.getInstance(props, null); 
				};
			}catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		}
		return session;
	}

	public void sendMessage(Message message) {
		try {
			Transport.send(message);
		} catch (MessagingException mex) {
			String errorMessage = mex.getLocalizedMessage();

			if(errorMessage != null) {
				if(errorMessage.indexOf("Unknown SMTP host") != -1) {
					Trace.println("*** Host SMTP non conosciuto: verificare indirizzo e porta del server SMTP.");
					Trace.println(errorMessage);
				} else if(errorMessage.indexOf("554") != -1) {
					Trace.println("*** Si sta inviando un messaggio con un indirizzo email non accettato dalla policy di quest'ultimo (errore 554).");
					Trace.println(errorMessage);
				} else if(errorMessage.indexOf("530") != -1) {
					Trace.println("*** L'indirizzo email del destinatario non è riconosciuto come valido dal server di posta (errore 530).");
					Trace.println(errorMessage);
				} else if(errorMessage.indexOf("Could not connect to SMTP host") != -1) {
					Trace.println("*** Could not connect to SMTP host: verificare l'indirizzo del server SMTP e la porta sulla quale si sta tentando di comunicare.");
					Trace.println(errorMessage);
				} else if(errorMessage.indexOf("AuthenticationFailedException") != -1) {
					Trace.println("*** Autenticazione fallita: verificare user e password utilizzati per la connessione al server SMTP.");
					Trace.println(errorMessage);
				} else {
					Trace.println("*** Errore generico");
					Trace.println(errorMessage);
				}
			} else {
			}
			mex.printStackTrace(Trace.excStream);
		}
	}

}

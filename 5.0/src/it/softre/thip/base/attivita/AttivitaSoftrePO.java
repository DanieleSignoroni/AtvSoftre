package it.softre.thip.base.attivita;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.ErrorCodes;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.OneToMany;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.softre.thip.base.cliente.ClienteSoftre;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.profilo.Utente;
import it.thera.thip.cs.EntitaAzienda;

/**
 * 
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 21/05/2024 <br>
 *         <br>
 *         <b>71543 DSSOF3 21/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 */

public abstract class AttivitaSoftrePO extends EntitaAzienda
implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static AttivitaSoftre cInstance;

	protected Integer iId;

	protected String iNomeAttivita;

	protected String iDescrizioneAttivita;

	protected String iRichiedenteSoftre;

	protected String iRichiedenteCliente;

	protected String iLinkAnalisi;

	protected String iProprietarioAnalisi;

	protected BigDecimal iQuotazioneOre;

	protected BigDecimal iQuotazioneGg;

	protected char iTipoFatturazione = '0';

	protected char iStatoCnfCliente = '0';

	protected String iPriorita;

	protected String iCommessaSmeup;

	protected char iStatoAttivita = '0';

	protected String iLinkDocumentazione;

	protected String iLinkFolderAllegati;

	protected String iTicketSisthema;

	protected java.sql.Date iDataPrevistaConsegna;

	protected java.sql.Date iDataIncontroCliente;

	protected java.sql.Date iDataCompletamento;

	protected java.sql.Date iDataInizio;

	protected java.sql.Date iDataFine;

	protected Proxy iClientesoftre = new Proxy(it.softre.thip.base.cliente.ClienteSoftre.class);

	protected Proxy iIncaricato = new Proxy(it.thera.thip.base.profilo.Utente.class);

	protected OneToMany iAttivitaCollaboratori = new OneToMany(it.softre.thip.base.attivita.AttivitaCollaboratore.class,
			this, 3, false);

	protected OneToMany iAttivitaChat = new OneToMany(it.softre.thip.base.attivita.AttivitaChat.class, this, 3, false);

	protected OneToMany iAttivitaFixes = new OneToMany(it.softre.thip.base.attivita.AttivitaFix.class, this, 3, false);

	protected boolean iSalvaChat = true;

	protected boolean iSalvaFix = true;

	protected boolean iSalvaCollaboratori = true;

	// Enumerato TipoFatturazione
	public static final char CONSUMO = '0';
	public static final char CORPO = '1';

	// Enumerato StatoCnfCliente
	public static final char NO = '0';
	public static final char IN_ATTESA = '1';
	public static final char SI = '2';

	// Enumerato StatoAttivita
	public static final char DA_ANALIZZARE = '0';
	public static final char DA_QUOTARE = '1';
	public static final char IN_ATTESA_CONFERMA_CLIENTE = '2';
	public static final char NON_CONFERMATO_CLIENTE = '3';
	public static final char CONFERMATO_CLIENTE = '4';
	public static final char IN_CORSO = '5';
	public static final char CONFEZIONATA = '6';
	public static final char FASE_DI_TEST = '7';
	public static final char COMPLETATA = '8';
	public static final char RIAPERTA = '9';

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (AttivitaSoftre) Factory.createObject(AttivitaSoftre.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static AttivitaSoftre elementWithKey(String key, int lockType) throws SQLException {
		return (AttivitaSoftre) PersistentObject.elementWithKey(AttivitaSoftre.class, key, lockType);
	}

	public AttivitaSoftrePO() {
		setId(new Integer(0));
		setTipoFatturazione('0');
		setStatoCnfCliente('0');
		setStatoAttivita('0');
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setId(Integer id) {
		this.iId = id;
		setDirty();
		setOnDB(false);
		iAttivitaCollaboratori.setFatherKeyChanged();
		iAttivitaChat.setFatherKeyChanged();
		iAttivitaFixes.setFatherKeyChanged();
	}

	public Integer getId() {
		return iId;
	}

	public void setNomeAttivita(String nomeAttivita) {
		this.iNomeAttivita = nomeAttivita;
		setDirty();
	}

	public String getNomeAttivita() {
		return iNomeAttivita;
	}

	public void setDescrizioneAttivita(String descrizioneAttivita) {
		this.iDescrizioneAttivita = descrizioneAttivita;
		setDirty();
	}

	public String getDescrizioneAttivita() {
		return iDescrizioneAttivita;
	}

	public void setRichiedenteSoftre(String richiedenteSoftre) {
		this.iRichiedenteSoftre = richiedenteSoftre;
		setDirty();
	}

	public String getRichiedenteSoftre() {
		return iRichiedenteSoftre;
	}

	public void setRichiedenteCliente(String richiedenteCliente) {
		this.iRichiedenteCliente = richiedenteCliente;
		setDirty();
	}

	public String getRichiedenteCliente() {
		return iRichiedenteCliente;
	}

	public void setLinkAnalisi(String linkAnalisi) {
		this.iLinkAnalisi = linkAnalisi;
		setDirty();
	}

	public String getLinkAnalisi() {
		return iLinkAnalisi;
	}

	public void setProprietarioAnalisi(String proprietarioAnalisi) {
		this.iProprietarioAnalisi = proprietarioAnalisi;
		setDirty();
	}

	public String getProprietarioAnalisi() {
		return iProprietarioAnalisi;
	}

	public void setQuotazioneOre(BigDecimal quotazioneOre) {
		this.iQuotazioneOre = quotazioneOre;
		setDirty();
	}

	public BigDecimal getQuotazioneOre() {
		return iQuotazioneOre;
	}

	public void setQuotazioneGg(BigDecimal quotazioneGg) {
		this.iQuotazioneGg = quotazioneGg;
		setDirty();
	}

	public BigDecimal getQuotazioneGg() {
		return iQuotazioneGg;
	}

	public void setTipoFatturazione(char tipoFatturazione) {
		this.iTipoFatturazione = tipoFatturazione;
		setDirty();
	}

	public char getTipoFatturazione() {
		return iTipoFatturazione;
	}

	public void setStatoCnfCliente(char statoCnfCliente) {
		this.iStatoCnfCliente = statoCnfCliente;
		setDirty();
	}

	public char getStatoCnfCliente() {
		return iStatoCnfCliente;
	}

	public void setPriorita(String priorita) {
		this.iPriorita = priorita;
		setDirty();
	}

	public String getPriorita() {
		return iPriorita;
	}

	public void setCommessaSmeup(String commessaSmeup) {
		this.iCommessaSmeup = commessaSmeup;
		setDirty();
	}

	public String getCommessaSmeup() {
		return iCommessaSmeup;
	}

	public void setStatoAttivita(char statoAttivita) {
		this.iStatoAttivita = statoAttivita;
		setDirty();
	}

	public char getStatoAttivita() {
		return iStatoAttivita;
	}

	public void setLinkDocumentazione(String linkDocumentazione) {
		this.iLinkDocumentazione = linkDocumentazione;
		setDirty();
	}

	public String getLinkDocumentazione() {
		return iLinkDocumentazione;
	}

	public void setLinkFolderAllegati(String linkFolderAllegati) {
		this.iLinkFolderAllegati = linkFolderAllegati;
		setDirty();
	}

	public String getLinkFolderAllegati() {
		return iLinkFolderAllegati;
	}

	public void setTicketSisthema(String ticketSisthema) {
		this.iTicketSisthema = ticketSisthema;
		setDirty();
	}

	public String getTicketSisthema() {
		return iTicketSisthema;
	}

	public void setDataPrevistaConsegna(java.sql.Date dataPrevistaConsegna) {
		this.iDataPrevistaConsegna = dataPrevistaConsegna;
		setDirty();
	}

	public java.sql.Date getDataPrevistaConsegna() {
		return iDataPrevistaConsegna;
	}

	public void setDataIncontroCliente(java.sql.Date dataIncontroCliente) {
		this.iDataIncontroCliente = dataIncontroCliente;
		setDirty();
	}

	public java.sql.Date getDataIncontroCliente() {
		return iDataIncontroCliente;
	}

	public void setDataCompletamento(java.sql.Date dataCompletamento) {
		this.iDataCompletamento = dataCompletamento;
		setDirty();
	}

	public java.sql.Date getDataCompletamento() {
		return iDataCompletamento;
	}

	public void setDataInizio(java.sql.Date dataInizio) {
		this.iDataInizio = dataInizio;
		setDirty();
	}

	public java.sql.Date getDataInizio() {
		return iDataInizio;
	}

	public void setDataFine(java.sql.Date dataFine) {
		this.iDataFine = dataFine;
		setDirty();
	}

	public java.sql.Date getDataFine() {
		return iDataFine;
	}

	public void setClientesoftre(ClienteSoftre clientesoftre) {
		String oldObjectKey = getKey();
		String idAzienda = getIdAzienda();
		if (clientesoftre != null) {
			idAzienda = KeyHelper.getTokenObjectKey(clientesoftre.getKey(), 1);
		}
		setIdAziendaInternal(idAzienda);
		this.iClientesoftre.setObject(clientesoftre);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
			iAttivitaCollaboratori.setFatherKeyChanged();
			iAttivitaChat.setFatherKeyChanged();
			iAttivitaFixes.setFatherKeyChanged();
		}
	}

	public ClienteSoftre getClientesoftre() {
		return (ClienteSoftre) iClientesoftre.getObject();
	}

	public void setClientesoftreKey(String key) {
		String oldObjectKey = getKey();
		iClientesoftre.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
			setOnDB(false);
			iAttivitaCollaboratori.setFatherKeyChanged();
			iAttivitaChat.setFatherKeyChanged();
			iAttivitaFixes.setFatherKeyChanged();
		}
	}

	public String getClientesoftreKey() {
		return iClientesoftre.getKey();
	}

	public void setIdClienteSoftre(java.math.BigDecimal idClienteSoftre) {
		String key = iClientesoftre.getKey();
		iClientesoftre.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idClienteSoftre));
		setDirty();
	}

	public java.math.BigDecimal getIdClienteSoftre() {
		String key = iClientesoftre.getKey();
		String objIdClienteSoftre = KeyHelper.getTokenObjectKey(key, 2);
		return KeyHelper.stringToBigDecimal(objIdClienteSoftre);
	}

	public void setIdAzienda(String idAzienda) {
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
		iAttivitaCollaboratori.setFatherKeyChanged();
		iAttivitaChat.setFatherKeyChanged();
		iAttivitaFixes.setFatherKeyChanged();
	}

	public String getIdAzienda() {
		String key = iClientesoftre.getKey();
		String objIdAzienda = KeyHelper.getTokenObjectKey(key, 1);
		return objIdAzienda;

	}

	public void setIncaricato(Utente incaricato) {
		this.iIncaricato.setObject(incaricato);
		setDirty();
	}

	public Utente getIncaricato() {
		return (Utente) iIncaricato.getObject();
	}

	public void setIncaricatoKey(String key) {
		iIncaricato.setKey(key);
		setDirty();
	}

	public String getIncaricatoKey() {
		return iIncaricato.getKey();
	}

	public void setIdIncaricato(String idIncaricato) {
		iIncaricato.setKey(idIncaricato);
		setDirty();
	}

	public String getIdIncaricato() {
		String key = iIncaricato.getKey();
		return key;
	}

	public boolean isSalvaChat() {
		return iSalvaChat;
	}

	public void setSalvaChat(boolean iSalvaChat) {
		this.iSalvaChat = iSalvaChat;
	}

	public boolean isSalvaFix() {
		return iSalvaFix;
	}

	public void setSalvaFix(boolean iSalvaFix) {
		this.iSalvaFix = iSalvaFix;
	}

	public boolean isSalvaCollaboratori() {
		return iSalvaCollaboratori;
	}

	public void setSalvaCollaboratori(boolean iSalvaCollaboratori) {
		this.iSalvaCollaboratori = iSalvaCollaboratori;
	}

	@SuppressWarnings("rawtypes")
	public List getAttivitaCollaboratori() {
		return getAttivitaCollaboratoriInternal();
	}

	@SuppressWarnings("rawtypes")
	public List getAttivitaChat() {
		return getAttivitaChatInternal();
	}

	@SuppressWarnings("rawtypes")
	public List getAttivitaFixes() {
		return getAttivitaFixesInternal();
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		AttivitaSoftrePO attivitaSoftrePO = (AttivitaSoftrePO) obj;
		if (attivitaSoftrePO.iDataPrevistaConsegna != null)
			iDataPrevistaConsegna = (java.sql.Date) attivitaSoftrePO.iDataPrevistaConsegna.clone();
		if (attivitaSoftrePO.iDataIncontroCliente != null)
			iDataIncontroCliente = (java.sql.Date) attivitaSoftrePO.iDataIncontroCliente.clone();
		if (attivitaSoftrePO.iDataCompletamento != null)
			iDataCompletamento = (java.sql.Date) attivitaSoftrePO.iDataCompletamento.clone();
		if (attivitaSoftrePO.iDataInizio != null)
			iDataInizio = (java.sql.Date) attivitaSoftrePO.iDataInizio.clone();
		if (attivitaSoftrePO.iDataFine != null)
			iDataFine = (java.sql.Date) attivitaSoftrePO.iDataFine.clone();
		iClientesoftre.setEqual(attivitaSoftrePO.iClientesoftre);
		iIncaricato.setEqual(attivitaSoftrePO.iIncaricato);
		iAttivitaCollaboratori.setEqual(attivitaSoftrePO.iAttivitaCollaboratori);
		iAttivitaChat.setEqual(attivitaSoftrePO.iAttivitaChat);
		iAttivitaFixes.setEqual(attivitaSoftrePO.iAttivitaFixes);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		if (!isOnDB()) {
			setId(new Integer(0));
		}
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Integer id = getId();
		Object[] keyParts = { idAzienda, id };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}
	
	public void setSalvaSoloTestata(boolean salvaSoloTestata) {
		setSalvaChat(!salvaSoloTestata);
		setSalvaFix(!salvaSoloTestata);
		setSalvaCollaboratori(!salvaSoloTestata);
	}

	public int saveOwnedObjects(int rc) throws SQLException {
		if(isSalvaCollaboratori())
			rc = iAttivitaCollaboratori.save(rc);
		if(isSalvaChat())
			rc = iAttivitaChat.save(rc);
		if(isSalvaFix())
			rc = iAttivitaFixes.save(rc);
		return rc;
	}

	public int deleteOwnedObjects() throws SQLException {
		int rcAttivitaCollaboratori = getAttivitaCollaboratoriInternal().delete();
		if (rcAttivitaCollaboratori < ErrorCodes.NO_ROWS_UPDATED)
			return rcAttivitaCollaboratori;
		int rcAttivitaChat = getAttivitaChatInternal().delete();
		if (rcAttivitaChat < ErrorCodes.NO_ROWS_UPDATED)
			return rcAttivitaChat;
		int rcAttivitaFixes = getAttivitaFixesInternal().delete();
		if (rcAttivitaFixes < ErrorCodes.NO_ROWS_UPDATED)
			return rcAttivitaFixes;
		return rcAttivitaCollaboratori + rcAttivitaChat + rcAttivitaFixes;
	}

	public boolean initializeOwnedObjects(boolean result) {
		result = iAttivitaCollaboratori.initialize(result);
		result = iAttivitaChat.initialize(result);
		result = iAttivitaFixes.initialize(result);
		return result;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return AttivitaSoftreTM.getInstance();
	}

	protected OneToMany getAttivitaCollaboratoriInternal() {
		if (iAttivitaCollaboratori.isNew())
			iAttivitaCollaboratori.retrieve();
		return iAttivitaCollaboratori;
	}

	protected OneToMany getAttivitaChatInternal() {
		if (iAttivitaChat.isNew())
			iAttivitaChat.retrieve();
		return iAttivitaChat;
	}

	protected OneToMany getAttivitaFixesInternal() {
		if (iAttivitaFixes.isNew())
			iAttivitaFixes.retrieve();
		return iAttivitaFixes;
	}

	protected void setIdAziendaInternal(String idAzienda) {
		String key1 = iClientesoftre.getKey();
		iClientesoftre.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
		iAzienda.setKey(idAzienda);
	}

}

package it.softre.thip.base.attivita;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.Factory;

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
 */

public class AttivitaSoftre extends AttivitaSoftrePO {

	//Per evitare buchi non usiamo THERA.NUMERATOR ma usiamo una nostra query
	public static final String STMT_NEXT_PROGR = "SELECT (COALESCE(MAX("+AttivitaSoftreTM.ID+"),0)+1) AS NEXT_VALUE FROM "+AttivitaSoftreTM.TABLE_NAME+" ";
	public static final CachedStatement cNextProgressivo = new CachedStatement(STMT_NEXT_PROGR);

	protected AttivitaSoftre iOldAttivita;

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
		controllaIncaricatoSeCollaboratore();
		if (!isOnDB()) {
			setId(getNextIdProgressivo());
		}
		calcolaGiorniQuotazione();
		return super.save();
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
		List<AttivitaCollaboratore> collaboratori = getAttivitaCollaboratori();
		if(getIncaricato() != null) {
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
		setIncaricato(null); 
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
}

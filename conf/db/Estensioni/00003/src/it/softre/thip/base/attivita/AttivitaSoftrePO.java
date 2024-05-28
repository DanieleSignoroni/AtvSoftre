/*
 * @(#)AttivitaSoftrePO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 22/05/2024 at 12:51:02
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 22/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.attivita;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.softre.thip.base.cliente.ClienteSoftre;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.profilo.Utente;
import java.math.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class AttivitaSoftrePO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static AttivitaSoftre cInstance;

  /**
   * Attributo iId
   */
  protected Integer iId;

  /**
   * Attributo iNomeAttivita
   */
  protected String iNomeAttivita;

  /**
   * Attributo iDescrizioneAttivita
   */
  protected String iDescrizioneAttivita;

  /**
   * Attributo iRichiedenteSoftre
   */
  protected String iRichiedenteSoftre;

  /**
   * Attributo iRichiedenteCliente
   */
  protected String iRichiedenteCliente;

  /**
   * Attributo iLinkAnalisi
   */
  protected String iLinkAnalisi;

  /**
   * Attributo iProprietarioAnalisi
   */
  protected String iProprietarioAnalisi;

  /**
   * Attributo iQuotazioneOre
   */
  protected BigDecimal iQuotazioneOre;

  /**
   * Attributo iQuotazioneGg
   */
  protected BigDecimal iQuotazioneGg;

  /**
   * Attributo iTipoFatturazione
   */
  protected char iTipoFatturazione = '0';

  /**
   * Attributo iStatoCnfCliente
   */
  protected char iStatoCnfCliente = '0';

  /**
   * Attributo iPriorita
   */
  protected String iPriorita;

  /**
   * Attributo iCommessaSmeup
   */
  protected String iCommessaSmeup;

  /**
   * Attributo iStatoAttivita
   */
  protected char iStatoAttivita = '0';

  /**
   * Attributo iLinkDocumentazione
   */
  protected String iLinkDocumentazione;

  /**
   * Attributo iLinkFolderAllegati
   */
  protected String iLinkFolderAllegati;

  /**
   * Attributo iTicketSisthema
   */
  protected String iTicketSisthema;

  /**
   * Attributo iDataPrevistaConsegna
   */
  protected java.sql.Date iDataPrevistaConsegna;

  /**
   * Attributo iDataIncontroCliente
   */
  protected java.sql.Date iDataIncontroCliente;

  /**
   * Attributo iDataCompletamento
   */
  protected java.sql.Date iDataCompletamento;

  /**
   * Attributo iDataInizio
   */
  protected java.sql.Date iDataInizio;

  /**
   * Attributo iDataFine
   */
  protected java.sql.Date iDataFine;

  /**
   * Attributo iClientesoftre
   */
  protected Proxy iClientesoftre = new Proxy(it.softre.thip.base.cliente.ClienteSoftre.class);

  /**
   * Attributo iIncaricato
   */
  protected Proxy iIncaricato = new Proxy(it.thera.thip.base.profilo.Utente.class);

  /**
   * Attributo iAttivitaCollaboratori
   */
  protected OneToMany iAttivitaCollaboratori = new OneToMany(it.softre.thip.base.attivita.AttivitaCollaboratore.class, this, 3, false);

  /**
   * Attributo iAttivitaChat
   */
  protected OneToMany iAttivitaChat = new OneToMany(it.softre.thip.base.attivita.AttivitaChat.class, this, 3, false);

  
  /**
   *  retrieveList
   * @param where
   * @param orderBy
   * @param optimistic
   * @return Vector
   * @throws SQLException
   * @throws ClassNotFoundException
   * @throws InstantiationException
   * @throws IllegalAccessException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (AttivitaSoftre)Factory.createObject(AttivitaSoftre.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return AttivitaSoftre
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static AttivitaSoftre elementWithKey(String key, int lockType) throws SQLException {
    return (AttivitaSoftre)PersistentObject.elementWithKey(AttivitaSoftre.class, key, lockType);
  }

  /**
   * AttivitaSoftrePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AttivitaSoftrePO() {
    setId(new Integer(0));
    setTipoFatturazione('0');
    setStatoCnfCliente('0');
    setStatoAttivita('0');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param id
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setId(Integer id) {
    this.iId = id;
    setDirty();
    setOnDB(false);
    iAttivitaCollaboratori.setFatherKeyChanged();
    iAttivitaChat.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getId() {
    return iId;
  }

  /**
   * Valorizza l'attributo. 
   * @param nomeAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setNomeAttivita(String nomeAttivita) {
    this.iNomeAttivita = nomeAttivita;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getNomeAttivita() {
    return iNomeAttivita;
  }

  /**
   * Valorizza l'attributo. 
   * @param descrizioneAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDescrizioneAttivita(String descrizioneAttivita) {
    this.iDescrizioneAttivita = descrizioneAttivita;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getDescrizioneAttivita() {
    return iDescrizioneAttivita;
  }

  /**
   * Valorizza l'attributo. 
   * @param richiedenteSoftre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRichiedenteSoftre(String richiedenteSoftre) {
    this.iRichiedenteSoftre = richiedenteSoftre;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRichiedenteSoftre() {
    return iRichiedenteSoftre;
  }

  /**
   * Valorizza l'attributo. 
   * @param richiedenteCliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setRichiedenteCliente(String richiedenteCliente) {
    this.iRichiedenteCliente = richiedenteCliente;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getRichiedenteCliente() {
    return iRichiedenteCliente;
  }

  /**
   * Valorizza l'attributo. 
   * @param linkAnalisi
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setLinkAnalisi(String linkAnalisi) {
    this.iLinkAnalisi = linkAnalisi;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getLinkAnalisi() {
    return iLinkAnalisi;
  }

  /**
   * Valorizza l'attributo. 
   * @param proprietarioAnalisi
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setProprietarioAnalisi(String proprietarioAnalisi) {
    this.iProprietarioAnalisi = proprietarioAnalisi;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getProprietarioAnalisi() {
    return iProprietarioAnalisi;
  }

  /**
   * Valorizza l'attributo. 
   * @param quotazioneOre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQuotazioneOre(BigDecimal quotazioneOre) {
    this.iQuotazioneOre = quotazioneOre;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQuotazioneOre() {
    return iQuotazioneOre;
  }

  /**
   * Valorizza l'attributo. 
   * @param quotazioneGg
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setQuotazioneGg(BigDecimal quotazioneGg) {
    this.iQuotazioneGg = quotazioneGg;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public BigDecimal getQuotazioneGg() {
    return iQuotazioneGg;
  }

  /**
   * Valorizza l'attributo. 
   * @param tipoFatturazione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setTipoFatturazione(char tipoFatturazione) {
    this.iTipoFatturazione = tipoFatturazione;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getTipoFatturazione() {
    return iTipoFatturazione;
  }

  /**
   * Valorizza l'attributo. 
   * @param statoCnfCliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setStatoCnfCliente(char statoCnfCliente) {
    this.iStatoCnfCliente = statoCnfCliente;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoCnfCliente() {
    return iStatoCnfCliente;
  }

  /**
   * Valorizza l'attributo. 
   * @param priorita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPriorita(String priorita) {
    this.iPriorita = priorita;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPriorita() {
    return iPriorita;
  }

  /**
   * Valorizza l'attributo. 
   * @param commessaSmeup
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setCommessaSmeup(String commessaSmeup) {
    this.iCommessaSmeup = commessaSmeup;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getCommessaSmeup() {
    return iCommessaSmeup;
  }

  /**
   * Valorizza l'attributo. 
   * @param statoAttivita
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setStatoAttivita(char statoAttivita) {
    this.iStatoAttivita = statoAttivita;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getStatoAttivita() {
    return iStatoAttivita;
  }

  /**
   * Valorizza l'attributo. 
   * @param linkDocumentazione
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setLinkDocumentazione(String linkDocumentazione) {
    this.iLinkDocumentazione = linkDocumentazione;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getLinkDocumentazione() {
    return iLinkDocumentazione;
  }

  /**
   * Valorizza l'attributo. 
   * @param linkFolderAllegati
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setLinkFolderAllegati(String linkFolderAllegati) {
    this.iLinkFolderAllegati = linkFolderAllegati;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getLinkFolderAllegati() {
    return iLinkFolderAllegati;
  }

  /**
   * Valorizza l'attributo. 
   * @param ticketSisthema
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setTicketSisthema(String ticketSisthema) {
    this.iTicketSisthema = ticketSisthema;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getTicketSisthema() {
    return iTicketSisthema;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataPrevistaConsegna
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataPrevistaConsegna(java.sql.Date dataPrevistaConsegna) {
    this.iDataPrevistaConsegna = dataPrevistaConsegna;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataPrevistaConsegna() {
    return iDataPrevistaConsegna;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataIncontroCliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataIncontroCliente(java.sql.Date dataIncontroCliente) {
    this.iDataIncontroCliente = dataIncontroCliente;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataIncontroCliente() {
    return iDataIncontroCliente;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataCompletamento
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataCompletamento(java.sql.Date dataCompletamento) {
    this.iDataCompletamento = dataCompletamento;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataCompletamento() {
    return iDataCompletamento;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataInizio
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataInizio(java.sql.Date dataInizio) {
    this.iDataInizio = dataInizio;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataInizio() {
    return iDataInizio;
  }

  /**
   * Valorizza l'attributo. 
   * @param dataFine
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDataFine(java.sql.Date dataFine) {
    this.iDataFine = dataFine;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.sql.Date
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.sql.Date getDataFine() {
    return iDataFine;
  }

  /**
   * Valorizza l'attributo. 
   * @param clientesoftre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
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
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return ClienteSoftre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public ClienteSoftre getClientesoftre() {
    return (ClienteSoftre)iClientesoftre.getObject();
  }

  /**
   * setClientesoftreKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
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
    }
  }

  /**
   * getClientesoftreKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getClientesoftreKey() {
    return iClientesoftre.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idClienteSoftre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdClienteSoftre(java.math.BigDecimal idClienteSoftre) {
    String key = iClientesoftre.getKey();
    iClientesoftre.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idClienteSoftre));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return java.math.BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.math.BigDecimal getIdClienteSoftre() {
    String key = iClientesoftre.getKey();
    String objIdClienteSoftre = KeyHelper.getTokenObjectKey(key,2);
    return KeyHelper.stringToBigDecimal(objIdClienteSoftre);
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
    iAttivitaCollaboratori.setFatherKeyChanged();
    iAttivitaChat.setFatherKeyChanged();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iClientesoftre.getKey();
    String objIdAzienda = KeyHelper.getTokenObjectKey(key,1);
    return objIdAzienda;
    
  }

  /**
   * Valorizza l'attributo. 
   * @param incaricato
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIncaricato(Utente incaricato) {
    this.iIncaricato.setObject(incaricato);
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return Utente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Utente getIncaricato() {
    return (Utente)iIncaricato.getObject();
  }

  /**
   * setIncaricatoKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIncaricatoKey(String key) {
    iIncaricato.setKey(key);
    setDirty();
  }

  /**
   * getIncaricatoKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIncaricatoKey() {
    return iIncaricato.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idIncaricato
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdIncaricato(String idIncaricato) {
    iIncaricato.setKey(idIncaricato);
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdIncaricato() {
    String key = iIncaricato.getKey();
    return key;
  }

  /**
   * getAttivitaCollaboratori
   * @return List
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public List getAttivitaCollaboratori() {
    return getAttivitaCollaboratoriInternal();
  }

  /**
   * getAttivitaChat
   * @return List
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public List getAttivitaChat() {
    return getAttivitaChatInternal();
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    AttivitaSoftrePO attivitaSoftrePO = (AttivitaSoftrePO)obj;
    if (attivitaSoftrePO.iDataPrevistaConsegna != null)
        iDataPrevistaConsegna = (java.sql.Date)attivitaSoftrePO.iDataPrevistaConsegna.clone();
    if (attivitaSoftrePO.iDataIncontroCliente != null)
        iDataIncontroCliente = (java.sql.Date)attivitaSoftrePO.iDataIncontroCliente.clone();
    if (attivitaSoftrePO.iDataCompletamento != null)
        iDataCompletamento = (java.sql.Date)attivitaSoftrePO.iDataCompletamento.clone();
    if (attivitaSoftrePO.iDataInizio != null)
        iDataInizio = (java.sql.Date)attivitaSoftrePO.iDataInizio.clone();
    if (attivitaSoftrePO.iDataFine != null)
        iDataFine = (java.sql.Date)attivitaSoftrePO.iDataFine.clone();
    iClientesoftre.setEqual(attivitaSoftrePO.iClientesoftre);
    iIncaricato.setEqual(attivitaSoftrePO.iIncaricato);
    iAttivitaCollaboratori.setEqual(attivitaSoftrePO.iAttivitaCollaboratori);
    iAttivitaChat.setEqual(attivitaSoftrePO.iAttivitaChat);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    if (!isOnDB()) {
      setId(new Integer(0));
    }
    components.runAllChecks(errors);
    return errors;
  }

  /**
   *  setKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    Integer id = getId();
    Object[] keyParts = {idAzienda, id};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * saveOwnedObjects
   * @param rc
   * @return int
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public int saveOwnedObjects(int rc) throws SQLException {
    rc = iAttivitaCollaboratori.save(rc);
    rc = iAttivitaChat.save(rc);
    return rc;
  }

  /**
   * deleteOwnedObjects
   * @return int
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public int deleteOwnedObjects() throws SQLException {
    int rcAttivitaCollaboratori = getAttivitaCollaboratoriInternal().delete();
    if (rcAttivitaCollaboratori < ErrorCodes.NO_ROWS_UPDATED)
      return rcAttivitaCollaboratori;
    int rcAttivitaChat = getAttivitaChatInternal().delete();
    if (rcAttivitaChat < ErrorCodes.NO_ROWS_UPDATED)
      return rcAttivitaChat;
    return rcAttivitaCollaboratori + rcAttivitaChat;
  }

  /**
   * initializeOwnedObjects
   * @param result
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean initializeOwnedObjects(boolean result) {
    result = iAttivitaCollaboratori.initialize(result);
    result = iAttivitaChat.initialize(result);
    return result;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  /**
   *  getTableManager
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return AttivitaSoftreTM.getInstance();
  }

  /**
   * getAttivitaCollaboratoriInternal
   * @return OneToMany
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected OneToMany getAttivitaCollaboratoriInternal() {
    if (iAttivitaCollaboratori.isNew())
        iAttivitaCollaboratori.retrieve();
    return iAttivitaCollaboratori;
  }

  /**
   * getAttivitaChatInternal
   * @return OneToMany
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected OneToMany getAttivitaChatInternal() {
    if (iAttivitaChat.isNew())
        iAttivitaChat.retrieve();
    return iAttivitaChat;
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    String key1 = iClientesoftre.getKey();
    iClientesoftre.setKey(KeyHelper.replaceTokenObjectKey(key1, 1, idAzienda));
    iAzienda.setKey(idAzienda);
  }

}


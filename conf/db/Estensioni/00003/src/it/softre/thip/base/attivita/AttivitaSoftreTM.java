/*
 * @(#)AttivitaSoftreTM.java
 */

/**
 * AttivitaSoftreTM
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
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class AttivitaSoftreTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

  /**
   * Attributo ID
   */
  public static final String ID = "ID";

  /**
   * Attributo NOME_ATTIVITA
   */
  public static final String NOME_ATTIVITA = "NOME_ATTIVITA";

  /**
   * Attributo DESCRIZIONE_ATTIVITA
   */
  public static final String DESCRIZIONE_ATTIVITA = "DESCRIZIONE_ATTIVITA";

  /**
   * Attributo RICHIEDENTE_SOFTRE
   */
  public static final String RICHIEDENTE_SOFTRE = "RICHIEDENTE_SOFTRE";

  /**
   * Attributo RICHIEDENTE_CLIENTE
   */
  public static final String RICHIEDENTE_CLIENTE = "RICHIEDENTE_CLIENTE";

  /**
   * Attributo LINK_ANALISI
   */
  public static final String LINK_ANALISI = "LINK_ANALISI";

  /**
   * Attributo PROPRIETARIO_ANALISI
   */
  public static final String PROPRIETARIO_ANALISI = "PROPRIETARIO_ANALISI";

  /**
   * Attributo R_INCARICATO
   */
  public static final String R_INCARICATO = "R_INCARICATO";

  /**
   * Attributo R_CLIENTE_SOFTRE
   */
  public static final String R_CLIENTE_SOFTRE = "R_CLIENTE_SOFTRE";

  /**
   * Attributo QUOTAZIONE_ORE
   */
  public static final String QUOTAZIONE_ORE = "QUOTAZIONE_ORE";

  /**
   * Attributo QUOTAZIONE_GG
   */
  public static final String QUOTAZIONE_GG = "QUOTAZIONE_GG";

  /**
   * Attributo TIPO_FATTURAZIONE
   */
  public static final String TIPO_FATTURAZIONE = "TIPO_FATTURAZIONE";

  /**
   * Attributo STATO_CNF_CLIENTE
   */
  public static final String STATO_CNF_CLIENTE = "STATO_CNF_CLIENTE";

  /**
   * Attributo PRIORITA
   */
  public static final String PRIORITA = "PRIORITA";

  /**
   * Attributo COMMESSA_SMEUP
   */
  public static final String COMMESSA_SMEUP = "COMMESSA_SMEUP";

  /**
   * Attributo STATO_ATTIVITA
   */
  public static final String STATO_ATTIVITA = "STATO_ATTIVITA";

  /**
   * Attributo LINK_DOCUMENTAZIONE
   */
  public static final String LINK_DOCUMENTAZIONE = "LINK_DOCUMENTAZIONE";

  /**
   * Attributo LINK_FOLDER_ALLEGATI
   */
  public static final String LINK_FOLDER_ALLEGATI = "LINK_FOLDER_ALLEGATI";

  /**
   * Attributo TICKET_SISTHEMA
   */
  public static final String TICKET_SISTHEMA = "TICKET_SISTHEMA";

  /**
   * Attributo DATA_PREVISTA_CONSEGNA
   */
  public static final String DATA_PREVISTA_CONSEGNA = "DATA_PREVISTA_CONSEGNA";

  /**
   * Attributo DATA_INCONTRO_CLIENTE
   */
  public static final String DATA_INCONTRO_CLIENTE = "DATA_INCONTRO_CLIENTE";

  /**
   * Attributo DATA_COMPLETAMENTO
   */
  public static final String DATA_COMPLETAMENTO = "DATA_COMPLETAMENTO";

  /**
   * Attributo DATA_INIZIO
   */
  public static final String DATA_INIZIO = "DATA_INIZIO";

  /**
   * Attributo DATA_FINE
   */
  public static final String DATA_FINE = "DATA_FINE";

  /**
   * Attributo STATO
   */
  public static final String STATO = "STATO";

  /**
   * Attributo R_UTENTE_CRZ
   */
  public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

  /**
   * Attributo TIMESTAMP_CRZ
   */
  public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

  /**
   * Attributo R_UTENTE_AGG
   */
  public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

  /**
   * Attributo TIMESTAMP_AGG
   */
  public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "ATTIVITA_SOFTRE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.attivita.AttivitaSoftre.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(AttivitaSoftreTM.class);
    }
    return cInstance;
  }

  /**
   *  AttivitaSoftreTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public AttivitaSoftreTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  /**
   *  initializeRelation
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("Id", ID, "getIntegerObject");
    addAttribute("NomeAttivita", NOME_ATTIVITA);
    addAttribute("DescrizioneAttivita", DESCRIZIONE_ATTIVITA);
    addAttribute("RichiedenteSoftre", RICHIEDENTE_SOFTRE);
    addAttribute("RichiedenteCliente", RICHIEDENTE_CLIENTE);
    addAttribute("LinkAnalisi", LINK_ANALISI);
    addAttribute("ProprietarioAnalisi", PROPRIETARIO_ANALISI);
    addAttribute("QuotazioneOre", QUOTAZIONE_ORE);
    addAttribute("QuotazioneGg", QUOTAZIONE_GG);
    addAttribute("TipoFatturazione", TIPO_FATTURAZIONE);
    addAttribute("StatoCnfCliente", STATO_CNF_CLIENTE);
    addAttribute("Priorita", PRIORITA);
    addAttribute("CommessaSmeup", COMMESSA_SMEUP);
    addAttribute("StatoAttivita", STATO_ATTIVITA);
    addAttribute("LinkDocumentazione", LINK_DOCUMENTAZIONE);
    addAttribute("LinkFolderAllegati", LINK_FOLDER_ALLEGATI);
    addAttribute("TicketSisthema", TICKET_SISTHEMA);
    addAttribute("DataPrevistaConsegna", DATA_PREVISTA_CONSEGNA);
    addAttribute("DataIncontroCliente", DATA_INCONTRO_CLIENTE);
    addAttribute("DataCompletamento", DATA_COMPLETAMENTO);
    addAttribute("DataInizio", DATA_INIZIO);
    addAttribute("DataFine", DATA_FINE);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdClienteSoftre", R_CLIENTE_SOFTRE);
    addAttribute("IdIncaricato", R_INCARICATO);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  /**
   *  init
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(ID + ", " + NOME_ATTIVITA + ", " + DESCRIZIONE_ATTIVITA + ", " + RICHIEDENTE_SOFTRE
         + ", " + RICHIEDENTE_CLIENTE + ", " + LINK_ANALISI + ", " + PROPRIETARIO_ANALISI + ", " + QUOTAZIONE_ORE
         + ", " + QUOTAZIONE_GG + ", " + TIPO_FATTURAZIONE + ", " + STATO_CNF_CLIENTE + ", " + PRIORITA
         + ", " + COMMESSA_SMEUP + ", " + STATO_ATTIVITA + ", " + LINK_DOCUMENTAZIONE + ", " + LINK_FOLDER_ALLEGATI
         + ", " + TICKET_SISTHEMA + ", " + DATA_PREVISTA_CONSEGNA + ", " + DATA_INCONTRO_CLIENTE + ", " + DATA_COMPLETAMENTO
         + ", " + DATA_INIZIO + ", " + DATA_FINE + ", " + ID_AZIENDA + ", " + R_CLIENTE_SOFTRE
         + ", " + R_INCARICATO + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ
         + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
  }

}


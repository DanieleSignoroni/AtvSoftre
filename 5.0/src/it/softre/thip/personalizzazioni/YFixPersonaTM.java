package it.softre.thip.personalizzazioni;

import com.thera.thermfw.persist.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

/**
 * 
 * @author Administrator
 *
 *	71042	TBSOF3	11/04/2023	Gestione informazioni cliente
 *
 */

public class YFixPersonaTM extends TableManager {

  public static final String ID_AZIENDA = "ID_AZIENDA";

  public static final String STATO = "STATO";

  public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

  public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

  public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

  public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

  public static final String ID_NUMERO_FIX = "ID_NUMERO_FIX";

  public static final String ID_CLIENTE = "R_CLIENTE";

  public static final String DESCRIZIONE = "DESCRIZIONE";

  public static final String DATA_RILASCIO = "DATA_RILASCIO";

  public static final String FLAG_MODIFICHE_STD = "FLAG_MODIFICHE_STD";

  public static final String FLAG_RILASCIO = "FLAG_RILASCIO";

  public static final String LINK_ANALISI = "LINK_ANALISI";

  public static final String LINK_DOCUMENTAZIONE = "LINK_DOCUMENTAZIONE";

  public static final String TABLE_NAME = SystemParam.getSchema("THIPPERS") + "YFIX_PERSONALIZZAZIONI";

  private static TableManager cInstance;

  private static final String CLASS_NAME = it.softre.thip.personalizzazioni.YFixPersona.class.getName();

  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(YFixPersonaTM.class);
    }
    return cInstance;
  }

  public YFixPersonaTM() throws SQLException {
    super();
  }

  protected void initialize() throws SQLException {
    setTableName(TABLE_NAME);
    setObjClassName(CLASS_NAME);
    init();
  }

  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("IdNumeroFix", ID_NUMERO_FIX, "getIntegerObject");
    addAttribute("Descrizione", DESCRIZIONE);
    addAttribute("DataRilascio", DATA_RILASCIO);
    addAttribute("FlagModificheStd", FLAG_MODIFICHE_STD);
    addAttribute("FlagRilascio", FLAG_RILASCIO);
    addAttribute("LinkAnalisi", LINK_ANALISI);
    addAttribute("LinkDocumentazione", LINK_DOCUMENTAZIONE);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdCliente", ID_CLIENTE);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID_NUMERO_FIX);

    setTimestampColumn("TIMESTAMP_AGG");
    ((it.thera.thip.cs.DatiComuniEstesiTTM)getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
  }

  private void init() throws SQLException {
    configure();
  }

}


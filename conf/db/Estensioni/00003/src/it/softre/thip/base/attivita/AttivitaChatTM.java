/*
 * @(#)AttivitaChatTM.java
 */

/**
 * AttivitaChatTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 22/05/2024 at 12:51:03
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

public class AttivitaChatTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

  /**
   * Attributo ID
   */
  public static final String ID = "ID";

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
   * Attributo MESSAGE
   */
  public static final String MESSAGE = "MESSAGE";

  /**
   * Attributo MESSAGE_ID
   */
  public static final String MESSAGE_ID = "MESSAGE_ID";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "ATTIVITA_CHAT";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.attivita.AttivitaChat.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(AttivitaChatTM.class);
    }
    return cInstance;
  }

  /**
   *  AttivitaChatTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public AttivitaChatTM() throws SQLException {
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
    addAttribute("Message", MESSAGE);
    addAttribute("MessageId", MESSAGE_ID, "getIntegerObject");
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("Id", ID, "getIntegerObject");
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID + "," + MESSAGE_ID);

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
    configure(MESSAGE + ", " + MESSAGE_ID + ", " + ID_AZIENDA + ", " + ID
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}


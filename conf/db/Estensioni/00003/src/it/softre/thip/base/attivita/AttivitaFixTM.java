/*
 * @(#)AttivitaFixTM.java
 */

/**
 * AttivitaFixTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 21/05/2024 at 12:10:48
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 21/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.attivita;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class AttivitaFixTM extends TableManager {

  
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
   * Attributo R_FIX
   */
  public static final String R_FIX = "R_FIX";

  /**
   * Attributo NOTE
   */
  public static final String NOTE = "NOTE";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "ATTIVITA_FIX";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.attivita.AttivitaFix.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(AttivitaFixTM.class);
    }
    return cInstance;
  }

  /**
   *  AttivitaFixTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public AttivitaFixTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
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
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("Note", NOTE);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdFix", R_FIX, "getIntegerObject");
    addAttribute("Id", ID, "getIntegerObject");
    
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
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(NOTE + ", " + ID_AZIENDA + ", " + R_FIX + ", " + ID
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}


/*
 * @(#)AttivitaCollaboratoreTM.java
 */

/**
 * AttivitaCollaboratoreTM
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

public class AttivitaCollaboratoreTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

  /**
   * Attributo ID
   */
  public static final String ID = "ID";

  /**
   * Attributo R_UTENTE
   */
  public static final String R_UTENTE = "R_UTENTE";

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
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "ATTIVITA_COLLABORATORE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.attivita.AttivitaCollaboratore.class.getName();

  
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
      cInstance = (TableManager)Factory.createObject(AttivitaCollaboratoreTM.class);
    }
    return cInstance;
  }

  /**
   *  AttivitaCollaboratoreTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public AttivitaCollaboratoreTM() throws SQLException {
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
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("Id", ID, "getIntegerObject");
    addAttribute("IdUtente", R_UTENTE);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID + "," + R_UTENTE);

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
    configure(ID_AZIENDA + ", " + ID + ", " + R_UTENTE + ", " + STATO
         + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG
        );
  }

}


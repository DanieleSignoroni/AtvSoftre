/*
 * @(#)ClienteSoftreTM.java
 */

/**
 * ClienteSoftreTM
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 20/05/2024 at 12:17:41
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 20/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.cliente;
import com.thera.thermfw.persist.*;
import com.thera.thermfw.common.*;
import java.sql.*;
import com.thera.thermfw.base.*;
import it.thera.thip.cs.*;

public class ClienteSoftreTM extends TableManager {

  
  /**
   * Attributo ID_AZIENDA
   */
  public static final String ID_AZIENDA = "ID_AZIENDA";

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
   * Attributo ID_ANAGRAFICO
   */
  public static final String ID_ANAGRAFICO = "ID_ANAGRAFICO";

  /**
   * Attributo AMBIENTE_SVILUPPO
   */
  public static final String AMBIENTE_SVILUPPO = "AMBIENTE_SVILUPPO";

  /**
   * Attributo DATABASE_VENDOR
   */
  public static final String DATABASE_VENDOR = "DATABASE_VENDOR";

  /**
   * Attributo PTH_VRM_01
   */
  public static final String PTH_VRM_01 = "PTH_VRM_01";

  /**
   * Attributo PTH_VRM_02
   */
  public static final String PTH_VRM_02 = "PTH_VRM_02";

  /**
   * Attributo JVM_APPLICATION_01
   */
  public static final String JVM_APPLICATION_01 = "JVM_APPLICATION_01";

  /**
   * Attributo JVM_APPLICATION_02
   */
  public static final String JVM_APPLICATION_02 = "JVM_APPLICATION_02";

  /**
   * Attributo JVM_BATCH_01
   */
  public static final String JVM_BATCH_01 = "JVM_BATCH_01";

  /**
   * Attributo JVM_BATCH_02
   */
  public static final String JVM_BATCH_02 = "JVM_BATCH_02";

  /**
   * Attributo SIRIO_VRM_01
   */
  public static final String SIRIO_VRM_01 = "SIRIO_VRM_01";

  /**
   * Attributo SIRIO_VRM_02
   */
  public static final String SIRIO_VRM_02 = "SIRIO_VRM_02";

  /**
   * Attributo CRYSTAL_VRM_01
   */
  public static final String CRYSTAL_VRM_01 = "CRYSTAL_VRM_01";

  /**
   * Attributo CRYSTAL_VRM_02
   */
  public static final String CRYSTAL_VRM_02 = "CRYSTAL_VRM_02";

  /**
   * Attributo APPLICATION_SVRV_TYPE
   */
  public static final String APPLICATION_SVRV_TYPE = "APPLICATION_SVRV_TYPE";

  /**
   * Attributo PASSWORD_ADMIN_01
   */
  public static final String PASSWORD_ADMIN_01 = "PASSWORD_ADMIN_01";

  /**
   * Attributo PASSWORD_ADMIN_02
   */
  public static final String PASSWORD_ADMIN_02 = "PASSWORD_ADMIN_02";

  /**
   * Attributo NOTE_SERVER_SVIL
   */
  public static final String NOTE_SERVER_SVIL = "NOTE_SERVER_SVIL";

  /**
   * Attributo MANAGER_PROJECT
   */
  public static final String MANAGER_PROJECT = "MANAGER_PROJECT";

  /**
   * Attributo PROJECT_NOTE
   */
  public static final String PROJECT_NOTE = "PROJECT_NOTE";

  /**
   * Attributo NOTE_CLIENTE
   */
  public static final String NOTE_CLIENTE = "NOTE_CLIENTE";

  /**
   * Attributo GIT_PROJECT
   */
  public static final String GIT_PROJECT = "GIT_PROJECT";

  /**
   * Attributo LINK_VPN_CONFIGURATION
   */
  public static final String LINK_VPN_CONFIGURATION = "LINK_VPN_CONFIGURATION";

  /**
   * Attributo LINK_PANTHERA_EXT
   */
  public static final String LINK_PANTHERA_EXT = "LINK_PANTHERA_EXT";

  /**
   *  TABLE_NAME
   */
  public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "CLIENTE_SOFTRE";

  /**
   *  instance
   */
  private static TableManager cInstance;

  /**
   *  CLASS_NAME
   */
  private static final String CLASS_NAME = it.softre.thip.base.cliente.ClienteSoftre.class.getName();

  
  /**
   *  getInstance
   * @return TableManager
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public synchronized static TableManager getInstance() throws SQLException {
    if (cInstance == null) {
      cInstance = (TableManager)Factory.createObject(ClienteSoftreTM.class);
    }
    return cInstance;
  }

  /**
   *  ClienteSoftreTM
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public ClienteSoftreTM() throws SQLException {
    super();
  }

  /**
   *  initialize
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
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
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void initializeRelation() throws SQLException {
    super.initializeRelation();
    addAttribute("AmbienteSviluppo", AMBIENTE_SVILUPPO);
    addAttribute("DatabaseVendor", DATABASE_VENDOR);
    addAttribute("PthVrm01", PTH_VRM_01);
    addAttribute("PthVrm02", PTH_VRM_02);
    addAttribute("JvmApplication01", JVM_APPLICATION_01);
    addAttribute("JvmApplication02", JVM_APPLICATION_02);
    addAttribute("JvmBatch01", JVM_BATCH_01);
    addAttribute("JvmBatch02", JVM_BATCH_02);
    addAttribute("SirioVrm01", SIRIO_VRM_01);
    addAttribute("SirioVrm02", SIRIO_VRM_02);
    addAttribute("CrystalVrm01", CRYSTAL_VRM_01);
    addAttribute("CrystalVrm02", CRYSTAL_VRM_02);
    addAttribute("ApplicationSvrvType", APPLICATION_SVRV_TYPE);
    addAttribute("PasswordAdmin01", PASSWORD_ADMIN_01);
    addAttribute("PasswordAdmin02", PASSWORD_ADMIN_02);
    addAttribute("NoteServerSvil", NOTE_SERVER_SVIL);
    addAttribute("ManagerProject", MANAGER_PROJECT);
    addAttribute("ProjectNote", PROJECT_NOTE);
    addAttribute("NoteCliente", NOTE_CLIENTE);
    addAttribute("GitProject", GIT_PROJECT);
    addAttribute("LinkVpnConfiguration", LINK_VPN_CONFIGURATION);
    addAttribute("LinkPantheraExt", LINK_PANTHERA_EXT);
    addAttribute("IdAzienda", ID_AZIENDA);
    addAttribute("IdAnagrafico", ID_ANAGRAFICO);
    
    addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
    setKeys(ID_AZIENDA + "," + ID_ANAGRAFICO);

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
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  private void init() throws SQLException {
    configure(AMBIENTE_SVILUPPO + ", " + DATABASE_VENDOR + ", " + PTH_VRM_01 + ", " + PTH_VRM_02
         + ", " + JVM_APPLICATION_01 + ", " + JVM_APPLICATION_02 + ", " + JVM_BATCH_01 + ", " + JVM_BATCH_02
         + ", " + SIRIO_VRM_01 + ", " + SIRIO_VRM_02 + ", " + CRYSTAL_VRM_01 + ", " + CRYSTAL_VRM_02
         + ", " + APPLICATION_SVRV_TYPE + ", " + PASSWORD_ADMIN_01 + ", " + PASSWORD_ADMIN_02 + ", " + NOTE_SERVER_SVIL
         + ", " + MANAGER_PROJECT + ", " + PROJECT_NOTE + ", " + NOTE_CLIENTE + ", " + GIT_PROJECT
         + ", " + LINK_VPN_CONFIGURATION + ", " + LINK_PANTHERA_EXT + ", " + ID_AZIENDA + ", " + ID_ANAGRAFICO
         + ", " + STATO + ", " + R_UTENTE_CRZ + ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG
         + ", " + TIMESTAMP_AGG);
  }

}


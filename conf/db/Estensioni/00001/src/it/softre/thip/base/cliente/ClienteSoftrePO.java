/*
 * @(#)ClienteSoftrePO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.partner.AnagraficoDiBasePrimrose;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class ClienteSoftrePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  
  /**
   *  instance
   */
  private static ClienteSoftre cInstance;

  /**
   * Attributo iAmbienteSviluppo
   */
  protected char iAmbienteSviluppo = '-';

  /**
   * Attributo iDatabaseVendor
   */
  protected char iDatabaseVendor = '1';

  /**
   * Attributo iPthVrm01
   */
  protected String iPthVrm01;

  /**
   * Attributo iPthVrm02
   */
  protected String iPthVrm02;

  /**
   * Attributo iJvmApplication01
   */
  protected String iJvmApplication01;

  /**
   * Attributo iJvmApplication02
   */
  protected String iJvmApplication02;

  /**
   * Attributo iJvmBatch01
   */
  protected String iJvmBatch01;

  /**
   * Attributo iJvmBatch02
   */
  protected String iJvmBatch02;

  /**
   * Attributo iSirioVrm01
   */
  protected String iSirioVrm01;

  /**
   * Attributo iSirioVrm02
   */
  protected String iSirioVrm02;

  /**
   * Attributo iCrystalVrm01
   */
  protected String iCrystalVrm01;

  /**
   * Attributo iCrystalVrm02
   */
  protected String iCrystalVrm02;

  /**
   * Attributo iApplicationSvrvType
   */
  protected String iApplicationSvrvType;

  /**
   * Attributo iPasswordAdmin01
   */
  protected String iPasswordAdmin01;

  /**
   * Attributo iPasswordAdmin02
   */
  protected String iPasswordAdmin02;

  /**
   * Attributo iNoteServerSvil
   */
  protected String iNoteServerSvil;

  /**
   * Attributo iManagerProject
   */
  protected char iManagerProject = '0';

  /**
   * Attributo iProjectNote
   */
  protected String iProjectNote;

  /**
   * Attributo iNoteCliente
   */
  protected String iNoteCliente;

  /**
   * Attributo iGitProject
   */
  protected String iGitProject;

  /**
   * Attributo iLinkVpnConfiguration
   */
  protected String iLinkVpnConfiguration;

  /**
   * Attributo iLinkPantheraExt
   */
  protected String iLinkPantheraExt;

  /**
   * Attributo iAnagraficodibase
   */
  protected Proxy iAnagraficodibase = new Proxy(it.thera.thip.base.partner.AnagraficoDiBasePrimrose.class);

  
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
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (ClienteSoftre)Factory.createObject(ClienteSoftre.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return ClienteSoftre
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static ClienteSoftre elementWithKey(String key, int lockType) throws SQLException {
    return (ClienteSoftre)PersistentObject.elementWithKey(ClienteSoftre.class, key, lockType);
  }

  /**
   * ClienteSoftrePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public ClienteSoftrePO() {
    setAmbienteSviluppo('-');
    setDatabaseVendor('1');
    setManagerProject('0');
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param ambienteSviluppo
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setAmbienteSviluppo(char ambienteSviluppo) {
    this.iAmbienteSviluppo = ambienteSviluppo;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getAmbienteSviluppo() {
    return iAmbienteSviluppo;
  }

  /**
   * Valorizza l'attributo. 
   * @param databaseVendor
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setDatabaseVendor(char databaseVendor) {
    this.iDatabaseVendor = databaseVendor;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getDatabaseVendor() {
    return iDatabaseVendor;
  }

  /**
   * Valorizza l'attributo. 
   * @param pthVrm01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPthVrm01(String pthVrm01) {
    this.iPthVrm01 = pthVrm01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPthVrm01() {
    return iPthVrm01;
  }

  /**
   * Valorizza l'attributo. 
   * @param pthVrm02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPthVrm02(String pthVrm02) {
    this.iPthVrm02 = pthVrm02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPthVrm02() {
    return iPthVrm02;
  }

  /**
   * Valorizza l'attributo. 
   * @param jvmApplication01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setJvmApplication01(String jvmApplication01) {
    this.iJvmApplication01 = jvmApplication01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getJvmApplication01() {
    return iJvmApplication01;
  }

  /**
   * Valorizza l'attributo. 
   * @param jvmApplication02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setJvmApplication02(String jvmApplication02) {
    this.iJvmApplication02 = jvmApplication02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getJvmApplication02() {
    return iJvmApplication02;
  }

  /**
   * Valorizza l'attributo. 
   * @param jvmBatch01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setJvmBatch01(String jvmBatch01) {
    this.iJvmBatch01 = jvmBatch01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getJvmBatch01() {
    return iJvmBatch01;
  }

  /**
   * Valorizza l'attributo. 
   * @param jvmBatch02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setJvmBatch02(String jvmBatch02) {
    this.iJvmBatch02 = jvmBatch02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getJvmBatch02() {
    return iJvmBatch02;
  }

  /**
   * Valorizza l'attributo. 
   * @param sirioVrm01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setSirioVrm01(String sirioVrm01) {
    this.iSirioVrm01 = sirioVrm01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getSirioVrm01() {
    return iSirioVrm01;
  }

  /**
   * Valorizza l'attributo. 
   * @param sirioVrm02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setSirioVrm02(String sirioVrm02) {
    this.iSirioVrm02 = sirioVrm02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getSirioVrm02() {
    return iSirioVrm02;
  }

  /**
   * Valorizza l'attributo. 
   * @param crystalVrm01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setCrystalVrm01(String crystalVrm01) {
    this.iCrystalVrm01 = crystalVrm01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getCrystalVrm01() {
    return iCrystalVrm01;
  }

  /**
   * Valorizza l'attributo. 
   * @param crystalVrm02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setCrystalVrm02(String crystalVrm02) {
    this.iCrystalVrm02 = crystalVrm02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getCrystalVrm02() {
    return iCrystalVrm02;
  }

  /**
   * Valorizza l'attributo. 
   * @param applicationSvrvType
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setApplicationSvrvType(String applicationSvrvType) {
    this.iApplicationSvrvType = applicationSvrvType;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getApplicationSvrvType() {
    return iApplicationSvrvType;
  }

  /**
   * Valorizza l'attributo. 
   * @param passwordAdmin01
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPasswordAdmin01(String passwordAdmin01) {
    this.iPasswordAdmin01 = passwordAdmin01;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPasswordAdmin01() {
    return iPasswordAdmin01;
  }

  /**
   * Valorizza l'attributo. 
   * @param passwordAdmin02
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setPasswordAdmin02(String passwordAdmin02) {
    this.iPasswordAdmin02 = passwordAdmin02;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getPasswordAdmin02() {
    return iPasswordAdmin02;
  }

  /**
   * Valorizza l'attributo. 
   * @param noteServerSvil
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setNoteServerSvil(String noteServerSvil) {
    this.iNoteServerSvil = noteServerSvil;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getNoteServerSvil() {
    return iNoteServerSvil;
  }

  /**
   * Valorizza l'attributo. 
   * @param managerProject
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setManagerProject(char managerProject) {
    this.iManagerProject = managerProject;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return char
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public char getManagerProject() {
    return iManagerProject;
  }

  /**
   * Valorizza l'attributo. 
   * @param projectNote
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setProjectNote(String projectNote) {
    this.iProjectNote = projectNote;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getProjectNote() {
    return iProjectNote;
  }

  /**
   * Valorizza l'attributo. 
   * @param noteCliente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setNoteCliente(String noteCliente) {
    this.iNoteCliente = noteCliente;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getNoteCliente() {
    return iNoteCliente;
  }

  /**
   * Valorizza l'attributo. 
   * @param gitProject
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setGitProject(String gitProject) {
    this.iGitProject = gitProject;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getGitProject() {
    return iGitProject;
  }

  /**
   * Valorizza l'attributo. 
   * @param linkVpnConfiguration
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setLinkVpnConfiguration(String linkVpnConfiguration) {
    this.iLinkVpnConfiguration = linkVpnConfiguration;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getLinkVpnConfiguration() {
    return iLinkVpnConfiguration;
  }

  /**
   * Valorizza l'attributo. 
   * @param linkPantheraExt
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setLinkPantheraExt(String linkPantheraExt) {
    this.iLinkPantheraExt = linkPantheraExt;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getLinkPantheraExt() {
    return iLinkPantheraExt;
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    iAzienda.setKey(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param anagraficodibase
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setAnagraficodibase(AnagraficoDiBasePrimrose anagraficodibase) {
    this.iAnagraficodibase.setObject(anagraficodibase);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return AnagraficoDiBasePrimrose
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AnagraficoDiBasePrimrose getAnagraficodibase() {
    return (AnagraficoDiBasePrimrose)iAnagraficodibase.getObject();
  }

  /**
   * setAnagraficodibaseKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setAnagraficodibaseKey(String key) {
    iAnagraficodibase.setKey(key);
    setDirty();
    setOnDB(false);
  }

  /**
   * getAnagraficodibaseKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getAnagraficodibaseKey() {
    return iAnagraficodibase.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAnagrafico
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAnagrafico(java.math.BigDecimal idAnagrafico) {
    iAnagraficodibase.setKey(KeyHelper.objectToString(idAnagrafico));
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return java.math.BigDecimal
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public java.math.BigDecimal getIdAnagrafico() {
    String key = iAnagraficodibase.getKey();
    return KeyHelper.stringToBigDecimal(key);
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    ClienteSoftrePO clienteSoftrePO = (ClienteSoftrePO)obj;
    iAnagraficodibase.setEqual(clienteSoftrePO.iAnagraficodibase);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
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
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setIdAnagrafico(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
  }

  /**
   *  getKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getKey() {
    String idAzienda = getIdAzienda();
    java.math.BigDecimal idAnagrafico = getIdAnagrafico();
    Object[] keyParts = {idAzienda, idAnagrafico};
    return KeyHelper.buildObjectKey(keyParts);
  }

  /**
   * isDeletable
   * @return boolean
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 20/05/2024    Wizard     Codice generato da Wizard
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
   * 20/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return ClienteSoftreTM.getInstance();
  }

}


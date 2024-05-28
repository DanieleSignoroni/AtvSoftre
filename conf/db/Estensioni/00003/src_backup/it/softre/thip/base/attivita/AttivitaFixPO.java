/*
 * @(#)AttivitaFixPO.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera SpA</b>
 * @author Wizard 21/05/2024 at 11:29:14
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 21/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.attivita;
import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import it.softre.thip.personalizzazioni.YFixPersona;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class AttivitaFixPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

  
  /**
   *  instance
   */
  private static AttivitaFix cInstance;

  /**
   * Attributo iNote
   */
  protected String iNote;

  /**
   * Attributo iFix
   */
  protected Proxy iFix = new Proxy(it.softre.thip.personalizzazioni.YFixPersona.class);

  /**
   * Attributo iParent
   */
  protected Proxy iParent = new Proxy(it.softre.thip.base.attivita.AttivitaSoftre.class);

  
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
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (AttivitaFix)Factory.createObject(AttivitaFix.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return AttivitaFix
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static AttivitaFix elementWithKey(String key, int lockType) throws SQLException {
    return (AttivitaFix)PersistentObject.elementWithKey(AttivitaFix.class, key, lockType);
  }

  /**
   * AttivitaFixPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AttivitaFixPO() {
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param note
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setNote(String note) {
    this.iNote = note;
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getNote() {
    return iNote;
  }

  /**
   * Valorizza l'attributo. 
   * @param fix
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setFix(YFixPersona fix) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (fix != null) {
      idAzienda = KeyHelper.getTokenObjectKey(fix.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iFix.setObject(fix);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * Restituisce l'attributo. 
   * @return YFixPersona
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public YFixPersona getFix() {
    return (YFixPersona)iFix.getObject();
  }

  /**
   * setFixKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setFixKey(String key) {
    String oldObjectKey = getKey();
    iFix.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  /**
   * getFixKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getFixKey() {
    return iFix.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idFix
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdFix(Integer idFix) {
    String key = iFix.getKey();
    iFix.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idFix));
    setDirty();
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getIdFix() {
    String key = iFix.getKey();
    String objIdFix = KeyHelper.getTokenObjectKey(key,2);
    return KeyHelper.stringToIntegerObj(objIdFix);
  }

  /**
   * Valorizza l'attributo. 
   * @param parent
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setParent(AttivitaSoftre parent) {
    String idAzienda = getIdAzienda();
    if (parent != null) {
      idAzienda = KeyHelper.getTokenObjectKey(parent.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iParent.setObject(parent);
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return AttivitaSoftre
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AttivitaSoftre getParent() {
    return (AttivitaSoftre)iParent.getObject();
  }

  /**
   * setParentKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setParentKey(String key) {
    iParent.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  /**
   * getParentKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getParentKey() {
    return iParent.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
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
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  /**
   * Valorizza l'attributo. 
   * @param id
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setId(Integer id) {
    String key = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key , 2, id));
    setDirty();
    setOnDB(false);
  }

  /**
   * Restituisce l'attributo. 
   * @return Integer
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getId() {
    String key = iParent.getKey();
    String objId = KeyHelper.getTokenObjectKey(key,2);
    return KeyHelper.stringToIntegerObj(objId);
  }

  /**
   * setEqual
   * @param obj
   * @throws CopyException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    AttivitaFixPO attivitaFixPO = (AttivitaFixPO)obj;
    iFix.setEqual(attivitaFixPO.iFix);
    iParent.setEqual(attivitaFixPO.iParent);
  }

  /**
   * checkAll
   * @param components
   * @return Vector
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
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
   * 21/05/2024    Wizard     Codice generato da Wizard
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
   * 21/05/2024    Wizard     Codice generato da Wizard
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
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public boolean isDeletable() {
    return checkDelete() == null;
  }

  /**
   * getFatherKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getFatherKey() {
    return getParentKey();
  }

  /**
   * setFatherKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setFatherKey(String key) {
    setParentKey(key);
  }

  /**
   * setFather
   * @param father
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setFather(PersistentObject father) {
    iParent.setObject(father);
  }

  /**
   * getOrderByClause
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getOrderByClause() {
    return "";
  }

  /**
   * toString
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
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
   * 21/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  protected TableManager getTableManager() throws SQLException {
    return AttivitaFixTM.getInstance();
  }

  /**
   * setIdAziendaInternal
   * @param idAzienda
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iFix.getKey();
    iFix.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
    String key3 = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
  }

}


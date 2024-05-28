/*
 * @(#)AttivitaChatPO.java
 */

/**
 * null
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
import java.sql.*;
import java.util.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class AttivitaChatPO extends PersistentObject implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

  
  /**
   *  instance
   */
  private static AttivitaChat cInstance;

  /**
   * Attributo iMessage
   */
  protected String iMessage;

  /**
   * Attributo iMessageId
   */
  protected Integer iMessageId;

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
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (AttivitaChat)Factory.createObject(AttivitaChat.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return AttivitaChat
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static AttivitaChat elementWithKey(String key, int lockType) throws SQLException {
    return (AttivitaChat)PersistentObject.elementWithKey(AttivitaChat.class, key, lockType);
  }

  /**
   * AttivitaChatPO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AttivitaChatPO() {
    setMessageId(new Integer(0));
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  /**
   * Valorizza l'attributo. 
   * @param message
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setMessage(String message) {
    this.iMessage = message;
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
  public String getMessage() {
    return iMessage;
  }

  /**
   * Valorizza l'attributo. 
   * @param messageId
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setMessageId(Integer messageId) {
    this.iMessageId = messageId;
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
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public Integer getMessageId() {
    return iMessageId;
  }

  /**
   * Valorizza l'attributo. 
   * @param parent
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    AttivitaChatPO attivitaChatPO = (AttivitaChatPO)obj;
    iParent.setEqual(attivitaChatPO.iParent);
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
    setMessageId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 3)));
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
    Integer messageId = getMessageId();
    Object[] keyParts = {idAzienda, id, messageId};
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
   * getFatherKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
   * 22/05/2024    Wizard     Codice generato da Wizard
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
    return AttivitaChatTM.getInstance();
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
    iAzienda.setKey(idAzienda);
        String key2 = iParent.getKey();
    iParent.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}


/*
 * @(#)AttivitaCollaboratorePO.java
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
import it.thera.thip.base.azienda.AziendaEstesa;
import it.thera.thip.base.profilo.Utente;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

public abstract class AttivitaCollaboratorePO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

  
  /**
   *  instance
   */
  private static AttivitaCollaboratore cInstance;

  /**
   * Attributo iParent
   */
  protected Proxy iParent = new Proxy(it.softre.thip.base.attivita.AttivitaSoftre.class);

  /**
   * Attributo iUtente
   */
  protected Proxy iUtente = new Proxy(it.thera.thip.base.profilo.Utente.class);

  
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
      cInstance = (AttivitaCollaboratore)Factory.createObject(AttivitaCollaboratore.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  /**
   *  elementWithKey
   * @param key
   * @param lockType
   * @return AttivitaCollaboratore
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    CodeGen     Codice generato da CodeGenerator
   *
   */
  public static AttivitaCollaboratore elementWithKey(String key, int lockType) throws SQLException {
    return (AttivitaCollaboratore)PersistentObject.elementWithKey(AttivitaCollaboratore.class, key, lockType);
  }

  /**
   * AttivitaCollaboratorePO
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public AttivitaCollaboratorePO() {
    setIdAzienda(Azienda.getAziendaCorrente());
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
   * Valorizza l'attributo. 
   * @param utente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setUtente(Utente utente) {
    this.iUtente.setObject(utente);
    setDirty();
    setOnDB(false);
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
  public Utente getUtente() {
    return (Utente)iUtente.getObject();
  }

  /**
   * setUtenteKey
   * @param key
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setUtenteKey(String key) {
    iUtente.setKey(key);
    setDirty();
    setOnDB(false);
  }

  /**
   * getUtenteKey
   * @return String
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public String getUtenteKey() {
    return iUtente.getKey();
  }

  /**
   * Valorizza l'attributo. 
   * @param idUtente
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public void setIdUtente(String idUtente) {
    iUtente.setKey(idUtente);
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
  public String getIdUtente() {
    String key = iUtente.getKey();
    return key;
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
    AttivitaCollaboratorePO attivitaCollaboratorePO = (AttivitaCollaboratorePO)obj;
    iParent.setEqual(attivitaCollaboratorePO.iParent);
    iUtente.setEqual(attivitaCollaboratorePO.iUtente);
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
    setIdUtente(KeyHelper.getTokenObjectKey(key, 3));
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
    String idUtente = getIdUtente();
    Object[] keyParts = {idAzienda, id, idUtente};
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
    return AttivitaCollaboratoreTM.getInstance();
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


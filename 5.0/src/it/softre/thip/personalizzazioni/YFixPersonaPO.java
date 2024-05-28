package it.softre.thip.personalizzazioni;

import com.thera.thermfw.persist.*;
import java.sql.*;
import java.util.*;
import it.thera.thip.cs.*;
import com.thera.thermfw.common.*;

import it.softre.thip.base.cliente.ClienteSoftre;
import it.thera.thip.base.azienda.Azienda;
import com.thera.thermfw.security.*;

/**
 * 
 * @author Administrator
 *
 *	71042	TBSOF3	11/04/2023	Gestione informazioni cliente
 *
 */

public abstract class YFixPersonaPO extends EntitaAzienda implements BusinessObject, Authorizable, Deletable, Conflictable {

  private static YFixPersona cInstance;

  protected Integer iIdNumeroFix;

  protected String iDescrizione;

  protected java.sql.Date iDataRilascio;

  protected boolean iFlagModificheStd = false;

  protected boolean iFlagRilascio = false;

  protected String iLinkAnalisi;

  protected String iLinkDocumentazione;

  protected Proxy iRelcliente = new Proxy(ClienteSoftre.class);

  @SuppressWarnings("rawtypes")
public static Vector retrieveList(String where, String orderBy, boolean optimistic) throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
    if (cInstance == null)
      cInstance = (YFixPersona)Factory.createObject(YFixPersona.class);
    return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
  }

  public static YFixPersona elementWithKey(String key, int lockType) throws SQLException {
    return (YFixPersona)PersistentObject.elementWithKey(YFixPersona.class, key, lockType);
  }

  public YFixPersonaPO() {
    setIdNumeroFix(new Integer(0));
    setFlagModificheStd(false);
    setFlagRilascio(false);
    setIdAzienda(Azienda.getAziendaCorrente());
  }

  public void setIdNumeroFix(Integer idNumeroFix) {
    this.iIdNumeroFix = idNumeroFix;
    setDirty();
    setOnDB(false);
  }

  public Integer getIdNumeroFix() {
    return iIdNumeroFix;
  }

  public void setDescrizione(String descrizione) {
    this.iDescrizione = descrizione;
    setDirty();
  }

  public String getDescrizione() {
    return iDescrizione;
  }

  public void setDataRilascio(java.sql.Date dataRilascio) {
    this.iDataRilascio = dataRilascio;
    setDirty();
  }

  public java.sql.Date getDataRilascio() {
    return iDataRilascio;
  }

  public void setFlagModificheStd(boolean flagModificheStd) {
    this.iFlagModificheStd = flagModificheStd;
    setDirty();
  }

  public boolean getFlagModificheStd() {
    return iFlagModificheStd;
  }

  public void setFlagRilascio(boolean flagRilascio) {
    this.iFlagRilascio = flagRilascio;
    setDirty();
  }

  public boolean getFlagRilascio() {
    return iFlagRilascio;
  }

  public void setLinkAnalisi(String linkAnalisi) {
    this.iLinkAnalisi = linkAnalisi;
    setDirty();
  }

  public String getLinkAnalisi() {
    return iLinkAnalisi;
  }

  public void setLinkDocumentazione(String linkDocumentazione) {
    this.iLinkDocumentazione = linkDocumentazione;
    setDirty();
  }

  public String getLinkDocumentazione() {
    return iLinkDocumentazione;
  }

  public void setRelcliente(ClienteSoftre relcliente) {
    String oldObjectKey = getKey();
    String idAzienda = getIdAzienda();
    if (relcliente != null) {
      idAzienda = KeyHelper.getTokenObjectKey(relcliente.getKey(), 1);
    }
    setIdAziendaInternal(idAzienda);
    this.iRelcliente.setObject(relcliente);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public ClienteSoftre getRelcliente() {
    return (ClienteSoftre)iRelcliente.getObject();
  }

  public void setRelclienteKey(String key) {
    String oldObjectKey = getKey();
    iRelcliente.setKey(key);
    String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
    setIdAziendaInternal(idAzienda);
    setDirty();
    if (!KeyHelper.areEqual(oldObjectKey, getKey())) {
      setOnDB(false);
    }
  }

  public String getRelclienteKey() {
    return iRelcliente.getKey();
  }

  public void setIdAzienda(String idAzienda) {
    setIdAziendaInternal(idAzienda);
    setDirty();
    setOnDB(false);
  }

  public String getIdAzienda() {
    String key = iAzienda.getKey();
    return key;
  }

  public void setIdCliente(Integer idCliente) {
    String key = iRelcliente.getKey();
    iRelcliente.setKey(KeyHelper.replaceTokenObjectKey(key , 2, idCliente));
    setDirty();
  }

  public Integer getIdCliente() {
    String key = iRelcliente.getKey();
    Integer objIdCliente = KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key,2));
    return objIdCliente;
  }

  public void setEqual(Copyable obj) throws CopyException {
    super.setEqual(obj);
    YFixPersonaPO yFixPersonaPO = (YFixPersonaPO)obj;
    if (yFixPersonaPO.iDataRilascio != null)
        iDataRilascio = (java.sql.Date)yFixPersonaPO.iDataRilascio.clone();
    iRelcliente.setEqual(yFixPersonaPO.iRelcliente);
  }

  @SuppressWarnings("rawtypes")
public Vector checkAll(BaseComponentsCollection components) {
    Vector errors = new Vector();
    if (!isOnDB()) {
      setIdNumeroFix(new Integer(0));
    }
    components.runAllChecks(errors);
    return errors;
  }

  public void setKey(String key) {
    setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
    setIdNumeroFix(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
  }

  public String getKey() {
    String idAzienda = getIdAzienda();
    Integer idNumeroFix = getIdNumeroFix();
    Object[] keyParts = {idAzienda, idNumeroFix};
    return KeyHelper.buildObjectKey(keyParts);
  }

  public boolean isDeletable() {
    return checkDelete() == null;
  }

  public String toString() {
    return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
  }

  protected TableManager getTableManager() throws SQLException {
    return YFixPersonaTM.getInstance();
  }

  protected void setIdAziendaInternal(String idAzienda) {
    iAzienda.setKey(idAzienda);
        String key2 = iRelcliente.getKey();
    iRelcliente.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
  }

}


package it.softre.thip.base.attivita;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
import com.thera.thermfw.persist.Child;
import com.thera.thermfw.persist.CopyException;
import com.thera.thermfw.persist.Copyable;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.persist.Proxy;
import com.thera.thermfw.persist.TableManager;
import com.thera.thermfw.security.Authorizable;
import com.thera.thermfw.security.Conflictable;

import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;

public abstract class AttivitaChatPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

	private static AttivitaChat cInstance;

	protected String iMessage;

	protected Integer iMessageId;

	protected Proxy iParent = new Proxy(it.softre.thip.base.attivita.AttivitaSoftre.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (AttivitaChat) Factory.createObject(AttivitaChat.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static AttivitaChat elementWithKey(String key, int lockType) throws SQLException {
		return (AttivitaChat) PersistentObject.elementWithKey(AttivitaChat.class, key, lockType);
	}

	public AttivitaChatPO() {
		setMessageId(new Integer(0));
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setMessage(String message) {
		this.iMessage = message;
		setDirty();
	}

	public String getMessage() {
		return iMessage;
	}

	public void setMessageId(Integer messageId) {
		this.iMessageId = messageId;
		setDirty();
		setOnDB(false);
	}

	public Integer getMessageId() {
		return iMessageId;
	}

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

	public AttivitaSoftre getParent() {
		return (AttivitaSoftre) iParent.getObject();
	}

	public void setParentKey(String key) {
		iParent.setKey(key);
		String idAzienda = KeyHelper.getTokenObjectKey(key, 1);
		setIdAziendaInternal(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getParentKey() {
		return iParent.getKey();
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

	public void setId(Integer id) {
		String key = iParent.getKey();
		iParent.setKey(KeyHelper.replaceTokenObjectKey(key, 2, id));
		setDirty();
		setOnDB(false);
	}

	public Integer getId() {
		String key = iParent.getKey();
		String objId = KeyHelper.getTokenObjectKey(key, 2);
		return KeyHelper.stringToIntegerObj(objId);
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		AttivitaChatPO attivitaChatPO = (AttivitaChatPO) obj;
		iParent.setEqual(attivitaChatPO.iParent);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 2)));
		setMessageId(KeyHelper.stringToIntegerObj(KeyHelper.getTokenObjectKey(key, 3)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Integer id = getId();
		Integer messageId = getMessageId();
		Object[] keyParts = { idAzienda, id, messageId };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String getFatherKey() {
		return getParentKey();
	}

	public void setFatherKey(String key) {
		setParentKey(key);
	}

	public void setFather(PersistentObject father) {
		iParent.setObject(father);
	}

	public String getOrderByClause() {
		return AttivitaChatTM.MESSAGE_ID + " ASC";
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return AttivitaChatTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iParent.getKey();
		iParent.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

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
import it.thera.thip.base.profilo.Utente;
import it.thera.thip.cs.EntitaAzienda;

public abstract class AttivitaCollaboratorePO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

	private static AttivitaCollaboratore cInstance;

	protected Proxy iParent = new Proxy(it.softre.thip.base.attivita.AttivitaSoftre.class);

	protected Proxy iUtente = new Proxy(it.thera.thip.base.profilo.Utente.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (AttivitaCollaboratore) Factory.createObject(AttivitaCollaboratore.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static AttivitaCollaboratore elementWithKey(String key, int lockType) throws SQLException {
		return (AttivitaCollaboratore) PersistentObject.elementWithKey(AttivitaCollaboratore.class, key, lockType);
	}

	public AttivitaCollaboratorePO() {
		setIdAzienda(Azienda.getAziendaCorrente());
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

	public void setUtente(Utente utente) {
		this.iUtente.setObject(utente);
		setDirty();
		setOnDB(false);
	}

	public Utente getUtente() {
		return (Utente) iUtente.getObject();
	}

	public void setUtenteKey(String key) {
		iUtente.setKey(key);
		setDirty();
		setOnDB(false);
	}

	public String getUtenteKey() {
		return iUtente.getKey();
	}

	public void setIdUtente(String idUtente) {
		iUtente.setKey(idUtente);
		setDirty();
		setOnDB(false);
	}

	public String getIdUtente() {
		String key = iUtente.getKey();
		return key;
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		AttivitaCollaboratorePO attivitaCollaboratorePO = (AttivitaCollaboratorePO) obj;
		iParent.setEqual(attivitaCollaboratorePO.iParent);
		iUtente.setEqual(attivitaCollaboratorePO.iUtente);
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
		setIdUtente(KeyHelper.getTokenObjectKey(key, 3));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Integer id = getId();
		String idUtente = getIdUtente();
		Object[] keyParts = { idAzienda, id, idUtente };
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
		return "";
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return AttivitaCollaboratoreTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iParent.getKey();
		iParent.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
	}

}

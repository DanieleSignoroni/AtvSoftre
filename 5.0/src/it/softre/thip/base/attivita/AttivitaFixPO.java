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

import it.softre.thip.personalizzazioni.YFixPersona;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.cs.EntitaAzienda;

/**
 * 
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 21/05/2024 <br>
 *         <br>
 *         <b>71XXX DSSOF3 21/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 */

public abstract class AttivitaFixPO extends EntitaAzienda
		implements BusinessObject, Authorizable, Deletable, Child, Conflictable {

	private static AttivitaFix cInstance;

	protected String iNote;

	protected Proxy iFix = new Proxy(it.softre.thip.personalizzazioni.YFixPersona.class);

	protected Proxy iParent = new Proxy(it.softre.thip.base.attivita.AttivitaSoftre.class);

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (AttivitaFix) Factory.createObject(AttivitaFix.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static AttivitaFix elementWithKey(String key, int lockType) throws SQLException {
		return (AttivitaFix) PersistentObject.elementWithKey(AttivitaFix.class, key, lockType);
	}

	public AttivitaFixPO() {
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setNote(String note) {
		this.iNote = note;
		setDirty();
	}

	public String getNote() {
		return iNote;
	}

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

	public YFixPersona getFix() {
		return (YFixPersona) iFix.getObject();
	}

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

	public String getFixKey() {
		return iFix.getKey();
	}

	public void setIdFix(Integer idFix) {
		String key = iFix.getKey();
		iFix.setKey(KeyHelper.replaceTokenObjectKey(key, 2, idFix));
		setDirty();
	}

	public Integer getIdFix() {
		String key = iFix.getKey();
		String objIdFix = KeyHelper.getTokenObjectKey(key, 2);
		return KeyHelper.stringToIntegerObj(objIdFix);
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
		AttivitaFixPO attivitaFixPO = (AttivitaFixPO) obj;
		iFix.setEqual(attivitaFixPO.iFix);
		iParent.setEqual(attivitaFixPO.iParent);
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
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		Integer id = getId();
		Object[] keyParts = { idAzienda, id };
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
		return AttivitaFixTM.getInstance();
	}

	protected void setIdAziendaInternal(String idAzienda) {
		iAzienda.setKey(idAzienda);
		String key2 = iFix.getKey();
		iFix.setKey(KeyHelper.replaceTokenObjectKey(key2, 1, idAzienda));
		String key3 = iParent.getKey();
		iParent.setKey(KeyHelper.replaceTokenObjectKey(key3, 1, idAzienda));
	}

}

package it.softre.thip.base.cliente;

import java.sql.SQLException;
import java.util.Vector;

import com.thera.thermfw.common.BaseComponentsCollection;
import com.thera.thermfw.common.BusinessObject;
import com.thera.thermfw.common.Deletable;
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
import it.thera.thip.base.partner.AnagraficoDiBasePrimrose;
import it.thera.thip.cs.EntitaAzienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 20/05/2024
 * <br><br>
 * <b>71543	DSSOF3	20/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public abstract class ClienteSoftrePO extends EntitaAzienda
implements BusinessObject, Authorizable, Deletable, Conflictable {

	private static ClienteSoftre cInstance;

	protected char iAmbienteSviluppo = '-';

	protected char iDatabaseVendor = '1';

	protected String iPthVrm01;

	protected String iPthVrm02;

	protected String iJvmApplication01;

	protected String iJvmApplication02;

	protected String iJvmBatch01;

	protected String iJvmBatch02;

	protected String iSirioVrm01;

	protected String iSirioVrm02;

	protected String iCrystalVrm01;

	protected String iCrystalVrm02;

	protected String iApplicationSvrvType;

	protected String iPasswordAdmin01;

	protected String iPasswordAdmin02;

	protected String iNoteServerSvil;

	protected char iManagerProject = '0';

	protected String iProjectNote;

	protected String iNoteCliente;

	protected String iGitProject;

	protected String iLinkVpnConfiguration;

	protected String iLinkPantheraExt;

	protected Proxy iAnagraficodibase = new Proxy(it.thera.thip.base.partner.AnagraficoDiBasePrimrose.class);
	
	//Database vendor
	public static final char SQL_SERVER = '1';
	public static final char DB2 = '2';
	public static final char DB2_AS400 = '3';

	//Gestore progetto
	public static final char SOFTRE = '0';
	public static final char SISTHEMA = '1';
	public static final char PARTNER_TERZO = '2';

	//Tipo ambiente
	public static final char NON_SIGNIFICATIVO = '-';
	public static final char ALTRO = 'A';
	public static final char LOCALE = 'L';
	public static final char SERVER = 'S';

	@SuppressWarnings("rawtypes")
	public static Vector retrieveList(String where, String orderBy, boolean optimistic)
			throws SQLException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		if (cInstance == null)
			cInstance = (ClienteSoftre) Factory.createObject(ClienteSoftre.class);
		return PersistentObject.retrieveList(cInstance, where, orderBy, optimistic);
	}

	public static ClienteSoftre elementWithKey(String key, int lockType) throws SQLException {
		return (ClienteSoftre) PersistentObject.elementWithKey(ClienteSoftre.class, key, lockType);
	}

	public ClienteSoftrePO() {
		setAmbienteSviluppo(NON_SIGNIFICATIVO);
		setDatabaseVendor(SQL_SERVER);
		setManagerProject(SOFTRE);
		setIdAzienda(Azienda.getAziendaCorrente());
	}

	public void setAmbienteSviluppo(char ambienteSviluppo) {
		this.iAmbienteSviluppo = ambienteSviluppo;
		setDirty();
	}

	public char getAmbienteSviluppo() {
		return iAmbienteSviluppo;
	}

	public void setDatabaseVendor(char databaseVendor) {
		this.iDatabaseVendor = databaseVendor;
		setDirty();
	}

	public char getDatabaseVendor() {
		return iDatabaseVendor;
	}

	public void setPthVrm01(String pthVrm01) {
		this.iPthVrm01 = pthVrm01;
		setDirty();
	}

	public String getPthVrm01() {
		return iPthVrm01;
	}

	public void setPthVrm02(String pthVrm02) {
		this.iPthVrm02 = pthVrm02;
		setDirty();
	}

	public String getPthVrm02() {
		return iPthVrm02;
	}

	public void setJvmApplication01(String jvmApplication01) {
		this.iJvmApplication01 = jvmApplication01;
		setDirty();
	}

	public String getJvmApplication01() {
		return iJvmApplication01;
	}

	public void setJvmApplication02(String jvmApplication02) {
		this.iJvmApplication02 = jvmApplication02;
		setDirty();
	}

	public String getJvmApplication02() {
		return iJvmApplication02;
	}

	public void setJvmBatch01(String jvmBatch01) {
		this.iJvmBatch01 = jvmBatch01;
		setDirty();
	}

	public String getJvmBatch01() {
		return iJvmBatch01;
	}

	public void setJvmBatch02(String jvmBatch02) {
		this.iJvmBatch02 = jvmBatch02;
		setDirty();
	}

	public String getJvmBatch02() {
		return iJvmBatch02;
	}

	public void setSirioVrm01(String sirioVrm01) {
		this.iSirioVrm01 = sirioVrm01;
		setDirty();
	}

	public String getSirioVrm01() {
		return iSirioVrm01;
	}

	public void setSirioVrm02(String sirioVrm02) {
		this.iSirioVrm02 = sirioVrm02;
		setDirty();
	}

	public String getSirioVrm02() {
		return iSirioVrm02;
	}

	public void setCrystalVrm01(String crystalVrm01) {
		this.iCrystalVrm01 = crystalVrm01;
		setDirty();
	}

	public String getCrystalVrm01() {
		return iCrystalVrm01;
	}

	public void setCrystalVrm02(String crystalVrm02) {
		this.iCrystalVrm02 = crystalVrm02;
		setDirty();
	}

	public String getCrystalVrm02() {
		return iCrystalVrm02;
	}

	public void setApplicationSvrvType(String applicationSvrvType) {
		this.iApplicationSvrvType = applicationSvrvType;
		setDirty();
	}

	public String getApplicationSvrvType() {
		return iApplicationSvrvType;
	}

	public void setPasswordAdmin01(String passwordAdmin01) {
		this.iPasswordAdmin01 = passwordAdmin01;
		setDirty();
	}

	public String getPasswordAdmin01() {
		return iPasswordAdmin01;
	}

	public void setPasswordAdmin02(String passwordAdmin02) {
		this.iPasswordAdmin02 = passwordAdmin02;
		setDirty();
	}

	public String getPasswordAdmin02() {
		return iPasswordAdmin02;
	}

	public void setNoteServerSvil(String noteServerSvil) {
		this.iNoteServerSvil = noteServerSvil;
		setDirty();
	}

	public String getNoteServerSvil() {
		return iNoteServerSvil;
	}

	public void setManagerProject(char managerProject) {
		this.iManagerProject = managerProject;
		setDirty();
	}

	public char getManagerProject() {
		return iManagerProject;
	}

	public void setProjectNote(String projectNote) {
		this.iProjectNote = projectNote;
		setDirty();
	}

	public String getProjectNote() {
		return iProjectNote;
	}

	public void setNoteCliente(String noteCliente) {
		this.iNoteCliente = noteCliente;
		setDirty();
	}

	public String getNoteCliente() {
		return iNoteCliente;
	}

	public void setGitProject(String gitProject) {
		this.iGitProject = gitProject;
		setDirty();
	}

	public String getGitProject() {
		return iGitProject;
	}

	public void setLinkVpnConfiguration(String linkVpnConfiguration) {
		this.iLinkVpnConfiguration = linkVpnConfiguration;
		setDirty();
	}

	public String getLinkVpnConfiguration() {
		return iLinkVpnConfiguration;
	}

	public void setLinkPantheraExt(String linkPantheraExt) {
		this.iLinkPantheraExt = linkPantheraExt;
		setDirty();
	}

	public String getLinkPantheraExt() {
		return iLinkPantheraExt;
	}

	public void setIdAzienda(String idAzienda) {
		iAzienda.setKey(idAzienda);
		setDirty();
		setOnDB(false);
	}

	public String getIdAzienda() {
		String key = iAzienda.getKey();
		return key;
	}

	public void setAnagraficodibase(AnagraficoDiBasePrimrose anagraficodibase) {
		this.iAnagraficodibase.setObject(anagraficodibase);
		setDirty();
		setOnDB(false);
	}

	public AnagraficoDiBasePrimrose getAnagraficodibase() {
		return (AnagraficoDiBasePrimrose) iAnagraficodibase.getObject();
	}

	public void setAnagraficodibaseKey(String key) {
		iAnagraficodibase.setKey(key);
		setDirty();
		setOnDB(false);
	}

	public String getAnagraficodibaseKey() {
		return iAnagraficodibase.getKey();
	}

	public void setIdAnagrafico(java.math.BigDecimal idAnagrafico) {
		iAnagraficodibase.setKey(KeyHelper.objectToString(idAnagrafico));
		setDirty();
		setOnDB(false);
	}

	public java.math.BigDecimal getIdAnagrafico() {
		String key = iAnagraficodibase.getKey();
		return KeyHelper.stringToBigDecimal(key);
	}

	public void setEqual(Copyable obj) throws CopyException {
		super.setEqual(obj);
		ClienteSoftrePO clienteSoftrePO = (ClienteSoftrePO) obj;
		iAnagraficodibase.setEqual(clienteSoftrePO.iAnagraficodibase);
	}

	@SuppressWarnings("rawtypes")
	public Vector checkAll(BaseComponentsCollection components) {
		Vector errors = new Vector();
		components.runAllChecks(errors);
		return errors;
	}

	public void setKey(String key) {
		setIdAzienda(KeyHelper.getTokenObjectKey(key, 1));
		setIdAnagrafico(KeyHelper.stringToBigDecimal(KeyHelper.getTokenObjectKey(key, 2)));
	}

	public String getKey() {
		String idAzienda = getIdAzienda();
		java.math.BigDecimal idAnagrafico = getIdAnagrafico();
		Object[] keyParts = { idAzienda, idAnagrafico };
		return KeyHelper.buildObjectKey(keyParts);
	}

	public boolean isDeletable() {
		return checkDelete() == null;
	}

	public String toString() {
		return getClass().getName() + " [" + KeyHelper.formatKeyString(getKey()) + "]";
	}

	protected TableManager getTableManager() throws SQLException {
		return ClienteSoftreTM.getInstance();
	}

}

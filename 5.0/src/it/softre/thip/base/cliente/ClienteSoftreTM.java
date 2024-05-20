package it.softre.thip.base.cliente;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 20/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	20/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class ClienteSoftreTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String ID_ANAGRAFICO = "ID_ANAGRAFICO";

	public static final String AMBIENTE_SVILUPPO = "AMBIENTE_SVILUPPO";

	public static final String DATABASE_VENDOR = "DATABASE_VENDOR";

	public static final String PTH_VRM_01 = "PTH_VRM_01";

	public static final String PTH_VRM_02 = "PTH_VRM_02";

	public static final String JVM_APPLICATION_01 = "JVM_APPLICATION_01";

	public static final String JVM_APPLICATION_02 = "JVM_APPLICATION_02";

	public static final String JVM_BATCH_01 = "JVM_BATCH_01";

	public static final String JVM_BATCH_02 = "JVM_BATCH_02";

	public static final String SIRIO_VRM_01 = "SIRIO_VRM_01";

	public static final String SIRIO_VRM_02 = "SIRIO_VRM_02";

	public static final String CRYSTAL_VRM_01 = "CRYSTAL_VRM_01";

	public static final String CRYSTAL_VRM_02 = "CRYSTAL_VRM_02";

	public static final String APPLICATION_SVRV_TYPE = "APPLICATION_SVRV_TYPE";

	public static final String PASSWORD_ADMIN_01 = "PASSWORD_ADMIN_01";

	public static final String PASSWORD_ADMIN_02 = "PASSWORD_ADMIN_02";

	public static final String NOTE_SERVER_SVIL = "NOTE_SERVER_SVIL";

	public static final String MANAGER_PROJECT = "MANAGER_PROJECT";

	public static final String PROJECT_NOTE = "PROJECT_NOTE";

	public static final String NOTE_CLIENTE = "NOTE_CLIENTE";

	public static final String GIT_PROJECT = "GIT_PROJECT";

	public static final String LINK_VPN_CONFIGURATION = "LINK_VPN_CONFIGURATION";

	public static final String LINK_PANTHERA_EXT = "LINK_PANTHERA_EXT";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "CLIENTE_SOFTRE";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.base.cliente.ClienteSoftre.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(ClienteSoftreTM.class);
		}
		return cInstance;
	}

	public ClienteSoftreTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

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
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure();
	}

}

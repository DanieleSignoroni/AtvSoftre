package it.softre.thip.base.attivita;

import java.sql.SQLException;

import com.thera.thermfw.base.SystemParam;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.TableManager;

import it.thera.thip.cs.DatiComuniEstesiTTM;

/**
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 22/05/2024 <br>
 *         <br>
 *         <b>71543 DSSOF3 22/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 */

public class AttivitaChatTM extends TableManager {

	public static final String ID_AZIENDA = "ID_AZIENDA";

	public static final String ID = "ID";

	public static final String STATO = "STATO";

	public static final String R_UTENTE_CRZ = "R_UTENTE_CRZ";

	public static final String TIMESTAMP_CRZ = "TIMESTAMP_CRZ";

	public static final String R_UTENTE_AGG = "R_UTENTE_AGG";

	public static final String TIMESTAMP_AGG = "TIMESTAMP_AGG";

	public static final String MESSAGE = "MESSAGE";

	public static final String MESSAGE_ID = "MESSAGE_ID";

	public static final String TABLE_NAME = SystemParam.getSchema("SOFTRE") + "ATTIVITA_CHAT";

	private static TableManager cInstance;

	private static final String CLASS_NAME = it.softre.thip.base.attivita.AttivitaChat.class.getName();

	public synchronized static TableManager getInstance() throws SQLException {
		if (cInstance == null) {
			cInstance = (TableManager) Factory.createObject(AttivitaChatTM.class);
		}
		return cInstance;
	}

	public AttivitaChatTM() throws SQLException {
		super();
	}

	protected void initialize() throws SQLException {
		setTableName(TABLE_NAME);
		setObjClassName(CLASS_NAME);
		init();
	}

	protected void initializeRelation() throws SQLException {
		super.initializeRelation();
		addAttribute("Message", MESSAGE);
		addAttribute("MessageId", MESSAGE_ID, "getIntegerObject");
		addAttribute("IdAzienda", ID_AZIENDA);
		addAttribute("Id", ID, "getIntegerObject");

		addComponent("DatiComuniEstesi", DatiComuniEstesiTTM.class);
		setKeys(ID_AZIENDA + "," + ID + "," + MESSAGE_ID);

		setTimestampColumn("TIMESTAMP_AGG");
		((it.thera.thip.cs.DatiComuniEstesiTTM) getTransientTableManager("DatiComuniEstesi")).setExcludedColums();
	}

	private void init() throws SQLException {
		configure(MESSAGE + ", " + MESSAGE_ID + ", " + ID_AZIENDA + ", " + ID + ", " + STATO + ", " + R_UTENTE_CRZ
				+ ", " + TIMESTAMP_CRZ + ", " + R_UTENTE_AGG + ", " + TIMESTAMP_AGG);
	}

}

package it.softre.thip.base.api;

import java.sql.SQLException;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.Factory;

import it.softre.thip.base.cliente.ClienteSoftre;
import it.thera.thip.vendite.proposteEvasione.CreaMessaggioErrore;

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

public class ClienteSoftreService {

	private static ClienteSoftreService instance = null;

	public static ClienteSoftreService getInstance() {
		if(instance == null) {
			instance = (ClienteSoftreService) Factory.createObject(ClienteSoftreService.class);
		}
		return instance;
	}

	public JSONObject aggiornaClienteSoftre(String bodyAsString) {
		JSONObject result = new JSONObject();
		JSONObject body = new JSONObject(bodyAsString);
		Status status = null;
		String errors = null;
		String message = null;
		if(body == null || body.isEmpty() || !body.has("PartitaIVA") || body.has("instance")) {
			status = Status.BAD_REQUEST;
			errors = "Body non fornito o partita iva non fornita o istanza non fornita";
		}else {
			ClienteSoftre cliente = ClienteSoftre.clienteSoftreDaPartitaIVA(body.getString("PartitaIVA"));
			if(cliente == null) {
				status = Status.INTERNAL_SERVER_ERROR;
				errors = "Non e' stato trovato nessun cliente con partita IVA: "+body.get("PartitaIVA");
			}else if(!isInstanceOk(body.get("instance"))){
				status = Status.INTERNAL_SERVER_ERROR;
				errors = "Istanza specificata non corretta: "+body.get("instance");
			}else {
				int rc = -1;
				try {
					rc = aggiornaIstanzaCliente(cliente,body);
				} catch (SQLException e) {
					ErrorMessage em = CreaMessaggioErrore.daRcAErrorMessage(rc, e);
					if(em != null)
						errors = em.getLongText();
					e.printStackTrace(Trace.excStream);
				}
				if(rc != BODataCollector.OK) {
					status = Status.INTERNAL_SERVER_ERROR;
					if(errors == null)
						errors = CreaMessaggioErrore.daRcAErrorMessage(rc, null).getLongText();
				}else {
					status = Status.OK;
					message = "Cliente aggiornato correttamente";
				}
			}
		}
		result.put("status", status);
		result.put("errors", errors);
		result.put("message", message);
		return result;
	}

	protected int aggiornaIstanzaCliente(ClienteSoftre cliente, JSONObject body) throws SQLException {
		int rc = -1;
		String istanza = body.getString("instance");
		if(istanza.equals("PANTH01")) {
			//aggiorniamo i dati di panth01
			String panthVrm01 = body.has("panth.vrm.01") ? body.getString("panth.vrm.01") : null;
			String jvmApplication01 = body.has("jvm.application.01") ? body.getString("jvm.application.01") : null;
			String jvmBatch01 = body.has("jvm.batch.01") ? body.getString("jvm.batch.01") : null;
			String sirioVrm01 = body.has("sirio.vrm.01") ? body.getString("sirio.vrm.01") : null;
			String crystalVrm01 = body.has("crystal.vrm.01") ? body.getString("crystal.vrm.01") : null;
			if(panthVrm01 != null) {
				cliente.setPthVrm01(panthVrm01);
			}
			if(jvmApplication01 != null) {
				cliente.setJvmApplication01(jvmApplication01);
			}
			if(jvmBatch01 != null) {
				cliente.setJvmBatch01(jvmBatch01);
			}
			if(sirioVrm01 != null) {
				cliente.setSirioVrm01(sirioVrm01);
			}
			if(crystalVrm01 != null) {
				cliente.setCrystalVrm01(crystalVrm01);
			}
		}else {
			//aggiorniamo i dati di panth02
			String panthVrm02 = body.has("panth.vrm.02") ? body.getString("panth.vrm.02") : null;
			String jvmApplication02 = body.has("jvm.application.02") ? body.getString("jvm.application.02") : null;
			String jvmBatch02 = body.has("jvm.batch.02") ? body.getString("jvm.batch.02") : null;
			String sirioVrm02 = body.has("sirio.vrm.02") ? body.getString("sirio.vrm.02") : null;
			String crystalVrm02 = body.has("crystal.vrm.02") ? body.getString("crystal.vrm.02") : null;
			if(panthVrm02 != null) {
				cliente.setPthVrm02(panthVrm02);
			}
			if(jvmApplication02 != null) {
				cliente.setJvmApplication02(jvmApplication02);
			}
			if(jvmBatch02 != null) {
				cliente.setJvmBatch02(jvmBatch02);
			}
			if(sirioVrm02 != null) {
				cliente.setSirioVrm02(sirioVrm02);
			}
			if(crystalVrm02 != null) {
				cliente.setCrystalVrm02(crystalVrm02);
			}
		}
		rc = cliente.save();
		return rc;
	}

	protected boolean isInstanceOk(Object istanza) {
		if(istanza.toString().toUpperCase().equals("PANTH01") || istanza.toString().toUpperCase().equals("PANTH02")) {
			return true;
		}
		return false;
	}

	public Object getValueFromJson(JSONObject json, String key) {
		Object value = null;
		if(json.has(key)) {
			value = json.get(key);
		}
		return value;
	}
}

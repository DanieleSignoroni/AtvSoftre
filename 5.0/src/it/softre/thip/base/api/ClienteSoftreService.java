package it.softre.thip.base.api;

import java.sql.SQLException;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;

import it.softre.thip.base.cliente.ClienteSoftre;
import it.thera.thip.cs.DatiComuniEstesi;
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
		if(body == null || body.isEmpty() || !body.has("partita.iva") || !body.has("instance")) {
			status = Status.BAD_REQUEST;
			errors = "Body non fornito o partita iva non fornita o istanza non fornita";
		}else {
			ClienteSoftre cliente = ClienteSoftre.clienteSoftreDaPartitaIVA(body.getString("partita.iva"));
			if(cliente == null) {
				status = Status.INTERNAL_SERVER_ERROR;
				errors = "Non e' stato trovato nessun cliente con partita.iva: "+body.get("partita.iva");
			}else if(!isInstanceOk(body.get("instance"))){
				status = Status.INTERNAL_SERVER_ERROR;
				errors = "Istanza specificata non corretta: "+body.get("instance");
			}else if(cliente.getStato() == DatiComuniEstesi.ANNULLATO){
				status = Status.INTERNAL_SERVER_ERROR;
				errors = "Il cliente si trova in stato annullato, non verrano aggiornati i dati";
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
				if(rc < BODataCollector.OK) {
					status = Status.INTERNAL_SERVER_ERROR;
					if(errors == null)
						errors = CreaMessaggioErrore.daRcAErrorMessage(rc, null).getLongText();
				}else {
					status = Status.OK;
					message = "Cliente aggiornato correttamente";
				}
				try {
					if(status == Status.OK) {
						ConnectionManager.commit();
					}else {
						ConnectionManager.rollback();
					}
				} catch (SQLException e) {
					e.printStackTrace(Trace.excStream);
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
		String applicationServerType = (String) (body.has("applycation.server.type") ? body.get("applycation.server.type") : null);
		String panthVrm = body.has("panth.vrm") ? body.getString("panth.vrm") : null;
		String jvmApplication = body.has("jvm.application") ? body.getString("jvm.application") : null;
		String jvmBatch = body.has("jvm.batch") ? body.getString("jvm.batch") : null;
		String sirioVrm = body.has("sirio.vrm") ? body.getString("sirio.vrm") : null;
		String crystalVrm = body.has("crystal.vrm") ? body.getString("crystal.vrm") : null;
		if(istanza.equals("PANTH01")) {
			//aggiorniamo i dati di panth01
			if(panthVrm != null) {
				cliente.setPthVrm01(panthVrm);
			}
			if(jvmApplication != null) {
				cliente.setJvmApplication01(jvmApplication);
			}
			if(jvmBatch != null) {
				cliente.setJvmBatch01(jvmBatch);
			}
			if(sirioVrm != null) {
				cliente.setSirioVrm01(sirioVrm);
			}
			if(crystalVrm != null) {
				cliente.setCrystalVrm01(crystalVrm);
			}
		}else {
			//aggiorniamo i dati di panth02
			if(panthVrm != null) {
				cliente.setPthVrm02(panthVrm);
			}
			if(jvmApplication != null) {
				cliente.setJvmApplication02(jvmApplication);
			}
			if(jvmBatch != null) {
				cliente.setJvmBatch02(jvmBatch);
			}
			if(sirioVrm != null) {
				cliente.setSirioVrm02(sirioVrm);
			}
			if(crystalVrm != null) {
				cliente.setCrystalVrm02(crystalVrm);
			}
		}
		if(applicationServerType != null) {
			cliente.setApplicationSvrvType(applicationServerType);
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

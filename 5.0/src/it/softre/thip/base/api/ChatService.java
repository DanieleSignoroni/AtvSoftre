package it.softre.thip.base.api;

import java.sql.SQLException;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.attivita.AttivitaChat;
import it.softre.thip.base.attivita.AttivitaSoftre;
import it.thera.thip.base.azienda.Azienda;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 28/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	28/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class ChatService {

	private static ChatService instance = null;

	public static ChatService getInstance() {
		if(instance == null) {
			instance = (ChatService) Factory.createObject(ChatService.class);
		}
		return instance;
	}

	@SuppressWarnings("unchecked")
	public JSONObject ottieniConversazione(Integer idAttivita) {
		Status status = Status.INTERNAL_SERVER_ERROR;
		JSONObject response = new JSONObject();
		AttivitaSoftre attivita = (AttivitaSoftre) Factory.createObject(AttivitaSoftre.class);
		attivita.setKey(KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(),idAttivita.toString()}));
		attivita.setDeepRetrieveEnabled(true);
		String result = null;
		try {
			boolean exist = attivita.retrieve(PersistentObject.NO_LOCK);
			if(exist) {
				List<AttivitaChat> conversations = attivita.getAttivitaChat();
				StringBuilder html = new StringBuilder();
				html.append("<div class=\"container-fluid mt-2\">")
				.append("    <div class=\"row d-flex\">")
				.append("        <div>")
				.append("            <div class=\"card\" id=\"chat2\">")
				.append("                <div class=\"card-body chat-container fs-5\">");

				for(AttivitaChat conversation : conversations) { // Replace with your actual messages loop
					boolean isSent = conversation.isSent(); // Example condition for sent/received
					String idUtenteCrz = conversation.getDatiComuni().getIdUtenteCrz();
					String message = conversation.getMessage();
					String timestamp = conversation.getDatiComuni().getTimestampCrz().toString();

					html.append("                    <div class=\"")
					.append(isSent ? "d-flex flex-row justify-content-end" : "d-flex flex-row justify-content-start")
					.append("\">")
					.append("                        <img src=\"https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp\" alt=\"avatar 1\" style=\"width: 45px; height: 100%;\">")
					.append("                        <div>")
					.append("                            <p class=\"ms-3 mb-3 rounded-3 text-muted\">")
					.append(idUtenteCrz)
					.append("</p>")
					.append("                            <p class=\"p-2 ms-3 mb-1 rounded-3 message ")
					.append(isSent ? "sent" : "received")
					.append("\">")
					.append(message)
					.append("</p>")
					.append("                            <p class=\"ms-3 mb-3 rounded-3 text-muted\">")
					.append(timestamp)
					.append("</p>")
					.append("                        </div>")
					.append("                    </div>")
					.append("                    <div class=\"divider d-flex align-items-center mb-4\"></div>");
				}

				html.append("                </div>")
				.append("                <div class=\"card-footer text-muted d-flex justify-content-start align-items-center p-3\">")
				.append("                    <img src=\"https://mdbcdn.b-cdn.net/img/Photos/new-templates/bootstrap-chat/ava3-bg.webp\" alt=\"avatar 3\" style=\"width: 40px; height: 100%;\">")
				.append("                    <input type=\"text\" class=\"form-control form-control-lg\" id=\"exampleFormControlInput1\" placeholder=\"Type message\">")
				.append("                    <a class=\"ms-1 text-muted\" href=\"#\"><i class=\"fas fa-paperclip\"></i></a>")
				.append("                    <a class=\"ms-3 text-muted\" href=\"#\"><i class=\"fas fa-smile\"></i></a>")
				.append("                    <a class=\"ms-3\" href=\"#\"><i class=\"fas fa-paper-plane\"></i></a>")
				.append("                </div>")
				.append("            </div>")
				.append("        </div>")
				.append("    </div>")
				.append("</div>");
				
				status = Status.OK;
				result = html.toString();
			}
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace(Trace.excStream);
		}
		response.put("status", status);
		response.put("response", result);
		return response;
	}
}

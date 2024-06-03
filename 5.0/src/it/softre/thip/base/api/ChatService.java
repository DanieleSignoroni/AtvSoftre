package it.softre.thip.base.api;

import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.softre.thip.base.attivita.AttivitaChat;
import it.softre.thip.base.attivita.AttivitaSoftre;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.base.profilo.UtenteAzienda;

/**
 * Servizio per la gestione della chat nelle {@link AttivitaSoftre}.<br>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 28/05/2024
 * <br><br>
 * <b>71543	DSSOF3	28/05/2024</b>
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
				.append("            <div class=\"card\" id=\"chat2\">")
				.append("                <div class=\"card-body chat-container fs-6\" id=\"chatBody\">");

				for(AttivitaChat conversation : conversations) { 
					UtenteAzienda utenteAzienda = null;
					try {
						utenteAzienda = (UtenteAzienda) 
								UtenteAzienda.elementWithKey(UtenteAzienda.class,
										KeyHelper.buildObjectKey(new String[] {
												Azienda.getAziendaCorrente(),
												conversation.getDatiComuni().getIdUtenteCrz().substring(0, conversation.getDatiComuni().getIdUtenteCrz().indexOf("_"))
										}), PersistentObject.NO_LOCK);
					} catch (Exception e) {
						//
					}

					String image = null;
					String nome = null;
					if(utenteAzienda != null) {
						Dipendente dipendente = utenteAzienda.getDipendente();
						if(dipendente != null) {
							image = dipendente.getURLImmagineDipendente();
							nome = dipendente.getNome() + " " + dipendente.getCognome();
						}
					}
					if(image == null)
						image = "https://tacm.com/wp-content/uploads/2018/01/no-image-available.jpeg";
					if(nome == null)
						nome = conversation.getDatiComuni().getIdUtenteCrz();

					boolean isSent = conversation.isSent(); 
					String message = conversation.getMessage();
					String timestamp = getRelativeTime(conversation.getDatiComuni().getTimestampCrz().toString());
					html.append("<div class=\"row ")
					.append(isSent ? "text-right" : "")
					.append("\">")
					.append(isSent ? "<div class=\"col-6\"></div>" : "")
					.append("<div class=\"col-6\">")
					.append("<div class=\"row\">")
					.append("<div class=\"col\">")
					.append("<img src=\"")
					.append(image)
					.append("\" class=\"img-fluid \" style=\"width: 48px; display: inline;border-radius:500px;\">")
					.append("<p class=\"font-weight-bold ml-2\" style=\"display: inline;\">")
					.append(nome)
					.append("</p>")
					.append("</div>")
					.append("</div>")
					.append("<div class=\"row mt-2\">")
					.append("<div class=\"col\">")
					.append("<p class=\"messaggio "+(isSent ? "sent" : "received")+" p-2 \" style=\"width:fit-content;"+(isSent ? "float:right;" : "float:left;")+"\">")
					.append(message)
					.append("</p>")
					.append("</div>")
					.append("<div class=\"row mt-2\">")
					.append("<p class=\"small\" style=\"display: inline;\">")
					.append(timestamp)
					.append("</p>")
					.append("</div>")
					.append("</div>")
					.append("</div>")
					.append("</div>")
					.append("<div class=\"divider d-flex align-items-center mb-4\"></div>");
				}
				html.append("</div>")
				.append("</div>")
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

	/**
	 * Dato un timestamp indica quanto tempo fa e' passato.<br>
	 * Serve per indicare la data di un messaggio.<br>
	 * @author Daniele Signoroni 29/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param timestampStr
	 * @return una stringa composta di:<br>
	 * <list>
	 *  <li>Se il timestamp e' > di 31 giorni allora indica il giorno del mese es: {29 Aprile} </li>
	 *  <li>Se il timestamp e' >= 1 giorni indica { N giorni fa } </li>
	 *  <li>Se il timestamp e' >= 1 ora indica {N ore fa } </li>
	 *  <li>Se il timestamp e' >= 1 minuti indica {N minuti fa } </li>
	 *  <li>Se il timestamp e' >= 1 secondi indica {N secondi fa } </li>
	 * </list>
	 */
	public static String getRelativeTime(String timestampStr) {
		if(timestampStr.length() > 19) { 
			//a volte il timestamp mi arrivava HH:mm:ss.SSS oppure HH:mm:ss.SS oppure HH:mm:ss.S , quindi ho deciso di trimmare
			timestampStr = timestampStr.substring(0,19);
		}
		LocalDateTime messageDateTime = LocalDateTime.parse(timestampStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime now = LocalDateTime.now();

		Duration duration = Duration.between(messageDateTime, now);
		long days = duration.toDays();

		if (days >= 31) { // More than a month
			return messageDateTime.format(DateTimeFormatter.ofPattern("dd MMMM"));
		} else if (days >= 1) { // More than a day
			return days + " giorn" + (days > 1 ? "i" : "o") + " fa";
		} else { // Less than a day
			long hours = duration.toHours();
			long minutes = duration.toMinutes();
			long seconds = duration.getSeconds();

			if (hours >= 1) {
				return hours + " or" + (hours > 1 ? "e" : "a") + " fa";
			} else if (minutes >= 1) {
				return minutes + " minut" + (minutes > 1 ? "i" : "o") + " fa";
			} else {
				return seconds + " second" + (seconds > 1 ? "i" : "o") + " fa";
			}
		}
	}

	/**
	 * Riceve un messaggio.<br> 
	 * @author Daniele Signoroni 29/05/2024
	 * <p>
	 * Prima stesura.<br>
	 *
	 * </p>
	 * @param idAttivita
	 * @param message
	 * @return
	 */
	public JSONObject riceviMessaggio(Integer idAttivita, String message) {
		Status status = Status.INTERNAL_SERVER_ERROR;
		JSONObject response = new JSONObject();
		AttivitaSoftre attivita = (AttivitaSoftre) Factory.createObject(AttivitaSoftre.class);
		attivita.setKey(KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(),idAttivita.toString()}));
		String result = null;
		try {
			boolean exist = attivita.retrieve(PersistentObject.NO_LOCK);
			if(exist) {
				AttivitaChat mex = (AttivitaChat) Factory.createObject(AttivitaChat.class);
				mex.setParent(attivita);
				mex.setMessage(message);
				int rc = mex.save();
				if(rc >= BODataCollector.OK) {
					ConnectionManager.commit();
					status = Status.OK;
					result = "Message received";
				}else {
					ConnectionManager.rollback();
				}
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

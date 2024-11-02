package it.softre.thip.base.api;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.json.JSONObject;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.base.Utils;
import com.thera.thermfw.collector.BODataCollector;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;
import com.thera.thermfw.pref.ApplicationPreferences;

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
 * <b>71558	DSSOF3	20/06/2024</b>
 * <p>
 * Implementazione notifiche prima versione.<br>
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
				if(conversations.size() > 0) {
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
						.append("<div class=\"col messaggio "+(isSent ? "sent" : "received")+" p-2 \" style=\"width:fit-content;"+(isSent ? "float:right;" : "float:left;")+"\">");
						byte[] bytes = conversation.getAttachment().getBytes();
						String fileType = conversation.getFileType();
						if(bytes != null && fileType != null) {
							if(isImage(bytes)) {
								String dataUrl = "data:image/png;base64," + Base64.getEncoder().encodeToString(bytes);
								html.append("<div class=\"row\" style=\"display:flow-root;\">");
								if(!message.isEmpty()) {
									html.append("<p>")
									.append(message)
									.append("</p>");
								}
								html.append("<div class=\"attachment mt-2\" style=\"width:fit-content;"+(isSent ? "float:right;" : "float:left;")+"\">")
								.append("<img src=\"")
								.append(dataUrl)
								.append("\" class=\"img-fluid attachment-image\" style=\"max-width: 200px; cursor: pointer;\">")
								.append("</div>")
								.append("</div>");
							}
							String fileContent = new String(bytes, StandardCharsets.UTF_8);
							String fileCardHtml = "<div class='file-card' style='" + (isSent ? "float:right;" : "float:left; ")+"'>" +
									"<img src='" + "it/softre/thip/base/attivita/img/generic_file.png" + "' alt='File Icon' class='file-icon'>" +
									"<span class='file-name'>" + conversation.getFileName() + "</span>" +
									"</div>";
							html.append("<div class=\"attachment mt-2\" style=\"width:fit-content;" + (isSent ? "float:right;" : "float:left;") + "\">")
							.append(fileCardHtml)
							.append("<input type=\"hidden\" class=\"attachment-content\" value=\"")
							.append(Base64.getEncoder().encodeToString(fileContent.getBytes()))
							.append("\" data-filename="+conversation.getFileName()+" >")
							.append("</div>");
						}else {
							html.append("<div class=\"row\">");
							html.append("<p>");
							html.append(message);
							html.append("</p>");
							html.append("</div>");
						}
						String comandi = "<div class='"+(isSent ? "comandi-end" : "comandi-start")+"'>" +
								"<i title=\"Cancella messaggio\" class=\"fa fa-solid fa-trash fa-1x mt-2\" style=\"cursor:pointer;\" onclick=\"deleteMessage('"+conversation.getKey()+"')\"></i>" +
								"<i title=\"Download file\" class=\"fa fa-solid fa-download fa-1x mt-2\" style=\"cursor:pointer;margin-left:0.75rem;\" onclick=\"downloadAttachment(this)\"></i>" +
								"</div>";
						html.append("</div>")
						.append(comandi)
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
					result = html.toString();
				}
			}

			status = Status.OK;
		} catch (SQLException e) {
			result = e.getMessage();
			e.printStackTrace(Trace.excStream);
		}
		response.put("status", status);
		response.put("response", result);
		return response;
	}

	public static boolean isImage(byte[] fileBytes) {
		try (ByteArrayInputStream bais = new ByteArrayInputStream(fileBytes)) {
			BufferedImage image = ImageIO.read(bais);
			return image != null;
		} catch (IOException e) {
			return false;
		}
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
	 * @param fileMetaData 
	 * @param fileInputStream 
	 * @return
	 */
	public JSONObject riceviMessaggio(Integer idAttivita, String message, InputStream fileInputStream, FormDataContentDisposition fileMetaData) {
		Status status = Status.INTERNAL_SERVER_ERROR;
		JSONObject response = new JSONObject();
		AttivitaSoftre attivita = (AttivitaSoftre) Factory.createObject(AttivitaSoftre.class);
		attivita.setKey(KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(),idAttivita.toString()}));
		String result = null;
		try {
			boolean exist = attivita.retrieve(PersistentObject.OPTIMISTIC_LOCK);
			if(exist) {
				AttivitaChat mex = (AttivitaChat) Factory.createObject(AttivitaChat.class);
				mex.setParent(attivita);
				mex.setMessage(message);
				if(fileMetaData != null) { //Meta Dati file
					mex.setFileName(fileMetaData.getFileName());
					mex.setFileType(fileMetaData.getFileName().substring(fileMetaData.getFileName().lastIndexOf("."), fileMetaData.getFileName().length()));
				}
				int rc = mex.save();
				if(fileInputStream != null) { //Aggiungere attachment file
					ByteArrayOutputStream os = new ByteArrayOutputStream();
					try {
						Utils.copyStream(fileInputStream, os);
						mex.setBytes(mex.getAttachment(), os.toByteArray());
					} catch (IOException e) {
						e.printStackTrace(Trace.excStream);
					}
				}
				if(rc >= BODataCollector.OK) {
					Dipendente sender = UtenteAzienda.getUtenteAziendaConnesso().getDipendente();
					//Va inviata la notifica via mail ai collaboratori dell'attivita
					if(sender != null) {
						List<Dipendente> adressees = attivita.getRelatedEmployees();
						for(Dipendente adressee : adressees) {
							try {
								inviaMailACollaboratore(attivita.sessionForSendMail(), adressee, attivita, sender, message);
							} catch (MessagingException e) {
								e.printStackTrace(Trace.excStream);
							}
						}
					}
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

	public void inviaMailACollaboratore(Session session,
			Dipendente destinatario,
			AttivitaSoftre attivita, Dipendente mittente, String messaggio) throws MessagingException {
		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("info@softre.it"));
		message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario.getEmail()));
		message.setSubject("Attivita Softre ["+attivita.getNomeAttivita()+"]");
		String mittenteId = mittente.getNome() + " " + mittente.getCognome();
		String htmlContent = "<!DOCTYPE html>" +
				"<html>" +
				"<head>" +
				"<style>" +
				"body { font-family: Arial, sans-serif; margin: 0; padding: 0; }" +
				".container { width: 100%; max-width: 600px; margin: 0 auto; padding: 20px; }" +
				".header { background-color: #f8f9fa; padding: 20px; text-align: center; }" +
				".content { padding: 20px; background-color: #ffffff; }" +
				".footer { background-color: #f8f9fa; padding: 20px; text-align: center; }" +
				".button { display: inline-block; padding: 10px 20px; margin: 20px 0; font-size: 16px; color: #ffffff; background-color: #007bff; text-decoration: none; border-radius: 5px; }" +
				"</style>" +
				"</head>" +
				"<body>" +
				"<div class='container'>" +
				"<div class='header'>" +
				"<h1>"+(attivita.getClientesoftre() != null ? attivita.getClientesoftre().getAnagraficodibase().getRagioneSociale() : "")+"</h1>" +
				"<h2>"+attivita.getNomeAttivita()+"</h2>" +
				"</div>" +
				"<div class='content' style='text-align:center;'>" +
				"<p><img style='width:24px;height:24px;margin-right:1rem;' src='"+getUrlImmagineCollaboratorePerEsterno(mittente)+"'></img><b>"+mittenteId+" ha aggiunto un nuovo commento all'attivita </b></p><br></br>" +
				"<p>"+messaggio+" " +
				"</div>" +
				"<div class='footer'>" +
				"</div>" +
				"</div>" +
				"</body>" +
				"</html>";
		message.setContent(htmlContent, "text/html; charset=utf-8");
		attivita.sendMessage(message);
	}

	public String getUrlImmagineCollaboratorePerEsterno(Dipendente dipendente) {
		String urlImmagine = "";
		try {
			String urlPub = null;
			ApplicationPreferences appPref = (ApplicationPreferences) ApplicationPreferences.elementWithKey(ApplicationPreferences.class, "0", PersistentObject.NO_LOCK);
			if(appPref != null)
				urlPub = appPref.getURLPubblico();

			if(urlPub != null && !urlPub.equals("") && !urlPub.startsWith("http://") && !urlPub.startsWith("https://"))
				urlPub = "http://" + urlPub;

			String webAppPath = "panth03";
			if(urlPub != null && !urlPub.equals("") && !urlPub.endsWith("/"+webAppPath) && !urlPub.endsWith("/"+webAppPath+"/"))
				urlPub = urlPub + "/" + "panth03";

			if(urlPub != null && !urlPub.equals("") && urlPub.endsWith("/"))
				urlPub = urlPub.substring(0, urlPub.length()-1);
			urlImmagine = urlPub + "/" + dipendente.getURLImmagineDipendente();
		}catch (SQLException e) {
			e.printStackTrace(Trace.excStream);
		}
		return urlImmagine;
	}

	public JSONObject cancellaMessaggio(String body) {
		Status status = Status.INTERNAL_SERVER_ERROR;
		JSONObject response = new JSONObject();
		String result = null;
		try {
			JSONObject bodyAsJson = new JSONObject(body);
			if(bodyAsJson.has("ChiaveMessaggio")) {
				AttivitaChat chat = (AttivitaChat) AttivitaChat.elementWithKey(AttivitaChat.class, bodyAsJson.getString("ChiaveMessaggio"), PersistentObject.NO_LOCK);
				if(chat != null) {
					int rcDel = chat.delete();
					if(rcDel > 0) {
						status = Status.OK;
						ConnectionManager.commit();
						result = "Messaggio cancellato correttamente";
					}else {
						ConnectionManager.rollback();	
					}
				}
			}else {
				status = Status.BAD_REQUEST;
				result = "Message key not provided";
			}
		}catch (Exception e) {
			result = e.getMessage();
			e.printStackTrace(Trace.excStream);
		}
		response.put("status", status);
		response.put("response", result);
		return response;
	}
}

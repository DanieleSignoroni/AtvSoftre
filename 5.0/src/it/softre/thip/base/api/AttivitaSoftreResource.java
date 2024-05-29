package it.softre.thip.base.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.rs.BaseResource;

import it.softre.thip.base.attivita.AttivitaChat;
import it.softre.thip.base.attivita.AttivitaSoftre;

/**
 * Endpoint per la gestione di un {@link AttivitaSoftre} e delle sue collezioni.<br>
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 28/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	28/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

@Path("softre/attivita")
public class AttivitaSoftreResource extends BaseResource {

	private ChatService chatService = ChatService.getInstance();
	
	/**
	 * Si occupa di ricevere un messaggio e salvare l'opportuno persistent object {@link AttivitaChat}.<br>
	 * L'utente di creazione e' quello del jwt.<br>
	 * @author Daniele Signoroni 29/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * </p>
	 * @param body
	 * @return
	 */
	@POST
	@Path("/chat/ricevi")
	public Response receiveMessage(String body) {
		JSONObject bodyJSON = new JSONObject(body);
		Integer idAttivita = bodyJSON.getInt("IdAttivita");
		String message = bodyJSON.getString("Message");
		JSONObject result = chatService.riceviMessaggio(idAttivita,message);
		Status stato = (Status) result.get("status");
		Object entity = result.get("response");
		return buildResponse(stato,entity);
	}

	/**
	 * Si occupa di ritornare l'html della conversazione di un'attivita'.<br>
	 * Questo verra' poi mostrato dove l'utente preferisce.<br>
	 * @author Daniele Signoroni 29/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * </p>
	 * @param idAttivita
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_HTML)
	@Path("/chat/html") 
	public Response obtainChat(@QueryParam("IdAttivita") Integer idAttivita){
		JSONObject result = chatService.ottieniConversazione(idAttivita);
		Status stato = (Status) result.get("status");
		Object entity = result.get("response");
		return buildResponse(stato,entity);

	}
}

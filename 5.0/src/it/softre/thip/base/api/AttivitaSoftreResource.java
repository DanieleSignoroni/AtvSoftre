package it.softre.thip.base.api;

import javax.annotation.security.PermitAll;
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

@Path("softre/attivita")
public class AttivitaSoftreResource extends BaseResource {

	private ChatService chatService = ChatService.getInstance();
	
	@POST
	@Path("/chat/ricevi")
	public Response receiveMessage(@QueryParam("IdAttivita") Integer idAttivita,
			@QueryParam("Message") String message) {
		return null;
	}

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

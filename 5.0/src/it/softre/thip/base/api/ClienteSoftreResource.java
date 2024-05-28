package it.softre.thip.base.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import com.thera.thermfw.rs.BaseResource;

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

@Path("softre/cliente")
@Consumes(MediaType.APPLICATION_JSON)
public class ClienteSoftreResource extends BaseResource {
	
	private ClienteSoftreService clienteService = ClienteSoftreService.getInstance();
	
	@POST
	@Path("/aggiorna")
	public Response aggiornaClienteSoftre(String body) {
		JSONObject result = clienteService.aggiornaClienteSoftre(body);
		Status status = (Status) result.get("status");
		if(status.equals(Status.OK)) {
			String message = result.getString("message");
			return buildResponse(status, message);
		}else {
			String errors = result.getString("errors");
			return buildResponse(status, errors);
		}
	}
}

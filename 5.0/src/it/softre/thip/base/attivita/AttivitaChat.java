package it.softre.thip.base.attivita;

import com.thera.thermfw.persist.*;

import it.thera.thip.base.profilo.UtenteAzienda;

import java.sql.*;
import com.thera.thermfw.common.*;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 22/05/2024
 * <br><br>
 * <b>71543	DSSOF3	22/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class AttivitaChat extends AttivitaChatPO {

	public static final String QUERY_SAVE = "SELECT COALESCE(MAX(MESSAGE_ID)+1,1) AS MAXI_VAL FROM SOFTRE.ATTIVITA_CHAT WHERE ID_AZIENDA = ? AND ID = ?";

	public static CachedStatement querySave = new CachedStatement(QUERY_SAVE);

	public ErrorMessage checkDelete() {
		return null;
	}

	public int save() throws SQLException {
		if (!isOnDB()) {
			ResultSet rs = null;
			int value = 1;
			Database db = ConnectionManager.getCurrentDatabase();
			synchronized(querySave){
				PreparedStatement ps = querySave.getStatement();
				db.setString(ps, 1, getIdAzienda());
				ps.setInt(2, getId() == null ? 0 : getId().intValue());
				rs = ps.executeQuery();
				if (rs.next())
					value = rs.getInt("MAXI_VAL");
				rs.close();
				setMessageId(new Integer(value));
			}
		}
		return super.save();
	}
	
	public boolean isSent() {
		UtenteAzienda utenteConnesso = UtenteAzienda.getUtenteAziendaConnesso();
		if(utenteConnesso != null) {
			if(getDatiComuniEstesi().getIdUtenteCrz().equals(utenteConnesso.getUtenteTherm().getId())) {
				return true;
			}
		}
		return false;
	}

}


/*
 * @(#)AttivitaChat.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera s.p.a.</b>
 * @author Wizard 22/05/2024 at 12:51:03
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 22/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.attivita;
import com.thera.thermfw.persist.*;
import java.sql.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import com.thera.thermfw.common.*;
import com.thera.thermfw.base.*;

public class AttivitaChat extends AttivitaChatPO {

  
public static final String QUERY_SAVE = "SELECT COALESCE(MAX(MESSAGE_ID)+1,1) AS MAXI_VAL FROM SOFTRE.ATTIVITA_CHAT WHERE ID_AZIENDA = ? AND ID = ?";

public static CachedStatement querySave = new CachedStatement(QUERY_SAVE);

  
  /**
   * checkDelete
   * @return ErrorMessage
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public ErrorMessage checkDelete() {
    /**@todo*/
    return null;
  }

  /**
   * save
   * @return int
   * @throws SQLException
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 22/05/2024    Wizard     Codice generato da Wizard
   *
   */
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

}


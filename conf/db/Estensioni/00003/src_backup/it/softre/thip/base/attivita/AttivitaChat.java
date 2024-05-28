/*
 * @(#)AttivitaChat.java
 */

/**
 * null
 *
 * <br></br><b>Copyright (C) : Thera s.p.a.</b>
 * @author Wizard 21/05/2024 at 12:10:48
 */
/*
 * Revisions:
 * Date          Owner      Description
 * 21/05/2024    Wizard     Codice generato da Wizard
 *
 */
package it.softre.thip.base.attivita;
import com.thera.thermfw.persist.*;
import java.sql.*;
import it.thera.thip.base.azienda.AziendaEstesa;
import com.thera.thermfw.common.*;
import com.thera.thermfw.base.*;

public class AttivitaChat extends AttivitaChatPO {

  
  
  /**
   * checkDelete
   * @return ErrorMessage
   */
  /*
   * Revisions:
   * Date          Owner      Description
   * 21/05/2024    Wizard     Codice generato da Wizard
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
   * 21/05/2024    Wizard     Codice generato da Wizard
   *
   */
  public int save() throws SQLException {
    if (!isOnDB()) {
      try {
        setId(new Integer(Numerator.getNextInt("AttivitaChat")));
      }
      catch(NumeratorException e) {e.printStackTrace(Trace.excStream);}
    }
    return super.save();
  }

}


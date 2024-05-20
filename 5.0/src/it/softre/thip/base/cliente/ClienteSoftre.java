package it.softre.thip.base.cliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.*;
import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Database;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.partner.AnagraficoDiBaseTM;

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

public class ClienteSoftre extends ClienteSoftrePO {
	
	public static final String SELECT_CLIENTE_FROM_PIVA = "SELECT C."+ClienteSoftreTM.ID_ANAGRAFICO+" FROM "+ClienteSoftreTM.TABLE_NAME+" C "
			+ "INNER JOIN "+AnagraficoDiBaseTM.TABLE_NAME+" A "
			+ "ON C."+ClienteSoftreTM.ID_ANAGRAFICO+" = A."+AnagraficoDiBaseTM.ID_ANAGRAFICO+" "
			+ "WHERE A."+AnagraficoDiBaseTM.PARTITA_IVA+" = ? ";
	
	public static CachedStatement cClienteDaPIVA = new CachedStatement(SELECT_CLIENTE_FROM_PIVA);
	
	public ErrorMessage checkDelete() {
		return null;
	}
	
	public static ClienteSoftre clienteSoftreDaPartitaIVA(String partitaIVA) {
		if(partitaIVA == null)
			return null;
		ClienteSoftre cliente = null;
		 ResultSet rs = null;
		    try{
		      PreparedStatement ps = cClienteDaPIVA.getStatement();
		      Database db = ConnectionManager.getCurrentDatabase();
		      db.setString(ps, 1, partitaIVA);
		      rs = ps.executeQuery();
		      if (rs.next()){
		        cliente = (ClienteSoftre) ClienteSoftre.elementWithKey(ClienteSoftre.class, 
		        		Integer.valueOf(rs.getInt(AnagraficoDiBaseTM.PARTITA_IVA)).toString(), PersistentObject.NO_LOCK);
		      }
		    } catch (SQLException e) {
				e.printStackTrace(Trace.excStream);
			}
		    finally{
		      try{
		        rs.close();
		      }
		      catch(SQLException e){
		    	  e.printStackTrace(Trace.excStream);
		      }
		    }
		return cliente;
	}

}


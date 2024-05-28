package it.softre.thip.base.attivita;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.thera.thermfw.base.Trace;
import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.CachedStatement;

/**
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 21/05/2024 <br>
 *         <br>
 *         <b>71XXX DSSOF3 21/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 */

public class AttivitaSoftre extends AttivitaSoftrePO {

	//Per evitare buchi non usiamo THERA.NUMERATOR ma usiamo una nostra query
	public static final String STMT_NEXT_PROGR = "SELECT (COALESCE(MAX("+AttivitaSoftreTM.ID+"),0)+1) AS NEXT_VALUE FROM "+AttivitaSoftreTM.TABLE_NAME+" ";
	public static final CachedStatement cNextProgressivo = new CachedStatement(STMT_NEXT_PROGR);

	public ErrorMessage checkDelete() {
		return null;
	}

	public int save() throws SQLException {
		if (!isOnDB()) {
			setId(getNextIdProgressivo());
		}
		calcolaGiorniQuotazione();
		return super.save();
	}

	protected void calcolaGiorniQuotazione() {
		if(getQuotazioneOre() != null && getQuotazioneOre().compareTo(BigDecimal.ZERO) > 0) {
			BigDecimal quotazioneGiorni = getQuotazioneOre().divide(new BigDecimal(8)).setScale(2, RoundingMode.HALF_UP); //up perche' js fa up
			setQuotazioneGg(quotazioneGiorni);
		}
	}

	public static Integer getNextIdProgressivo() {
		ResultSet rs = null;
		try{
			PreparedStatement ps = cNextProgressivo.getStatement();
			rs = ps.executeQuery();
			if (rs.next())
				return rs.getInt(1);
		}
		catch (SQLException e){
			e.printStackTrace(Trace.excStream);
		}finally{
			try{
				rs.close();
			}catch(SQLException e){
				e.printStackTrace(Trace.excStream);
			}
		}
		return -1;
	}
}

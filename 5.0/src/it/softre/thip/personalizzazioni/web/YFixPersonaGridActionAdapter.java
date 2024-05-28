package it.softre.thip.personalizzazioni.web;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;

import com.thera.thermfw.persist.CachedStatement;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.web.ServletEnvironment;

import it.softre.thip.personalizzazioni.YFixPersona;
import it.thera.thip.base.azienda.Azienda;
import it.thera.thip.base.documentoDgt.DocumentoDgtOggetto;
import it.thera.thip.cs.web.AziendaGridActionAdapter;

/**
 * 
 * @author Administrator
 *
 *	71042	TBSOF3	11/04/2023	Gestione informazioni cliente. Invece di mostrare l'elenco dei docDgt collegati, vado a scaricarli direttamente 
 *
 */

public class YFixPersonaGridActionAdapter extends AziendaGridActionAdapter{

	/**
	 * 
	 */
	private static final long serialVersionUID = 109758625660194924L;

	@Override
	public void processAction(ServletEnvironment arg0) throws ServletException, IOException {
		String action = arg0.getRequest().getParameter(ACTION);
		if(action.equals("DOCDGT_COLLEG")) {
			String key = arg0.getRequest().getParameter("ObjectKey");
			CachedStatement cs = null;
			ResultSet rs = null;
			DocumentoDgtOggetto docDgtOgg = null;
			try {
				YFixPersona fix = (YFixPersona) YFixPersona.elementWithKey(YFixPersona.class, key, 0);
				if(fix != null) {
					String select = "SELECT * FROM THIP.DOC_DGT_ENTITA "
							+ "WHERE ID_AZIENDA = '" + Azienda.getAziendaCorrente() + "' AND "
							+ "ID_TIPO_DOC_DGT='YZIP' AND "
							+ "KEY_ENTITA='" + key + "'";
					cs = new CachedStatement(select);
					rs = cs.executeQuery();
					if(rs.next()) {
						String idDocDgt = rs.getString("ID_DOCUMENTO_DGT");
						key = KeyHelper.buildObjectKey(new String[] {Azienda.getAziendaCorrente(), idDocDgt, "1", "1"});
						docDgtOgg = (DocumentoDgtOggetto) DocumentoDgtOggetto.elementWithKey(DocumentoDgtOggetto.class, key , 0);
					}
					if(docDgtOgg != null) {
						String url = arg0.getServletPath()+ "it.thera.thip.base.documentoDgt.web.OpenAllOggettiDigitale";
						url += "?thKey="+docDgtOgg.getKey();
						arg0.sendRequest(getServletContext(),url, false);
					}
				}
			} catch (SQLException e1) {
				e1.printStackTrace();
			}finally {
				try {
					if(cs!= null)
						cs.free();
					if(rs != null)
						rs = null;
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
		super.processAction(arg0);
	}
}

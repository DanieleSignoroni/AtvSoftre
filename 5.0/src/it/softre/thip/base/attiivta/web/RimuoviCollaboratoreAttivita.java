package it.softre.thip.base.attiivta.web;

import java.io.PrintWriter;

import com.thera.thermfw.persist.ConnectionManager;
import com.thera.thermfw.persist.Factory;
import com.thera.thermfw.web.ServletEnvironment;
import com.thera.thermfw.web.servlet.BaseServlet;

import it.softre.thip.base.attivita.AttivitaCollaboratore;
import it.softre.thip.base.attivita.AttivitaSoftre;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 30/05/2024
 * <br><br>
 * <b>71XXX	DSSOF3	30/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class RimuoviCollaboratoreAttivita extends BaseServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void processAction(ServletEnvironment se) throws Exception {
		String key = getStringParameter(se.getRequest(), KEY);
		String thClassName = getStringParameter(se.getRequest(), CLASS_NAME);
		String keyCollab = getStringParameter(se.getRequest(), "KeyCollab");
		if(key != null && !key.isEmpty()) {
			PrintWriter out = se.getResponse().getWriter();
			out.print("<script>");
			AttivitaCollaboratore collaboratore = (AttivitaCollaboratore) Factory.createObject(AttivitaCollaboratore.class);
			collaboratore.setKey(keyCollab);
			collaboratore.retrieve();
			int rc = collaboratore.delete();
			AttivitaSoftre attivita = collaboratore.getParent();
			if(attivita.getIdIncaricato() != null && attivita.getIdIncaricato().equals(collaboratore.getIdUtente())) {
				attivita.setIncaricato(null);
				attivita.setSalvaSoloTestata(true);
				rc = attivita.save();
			}
			if(rc > 0) {
				ConnectionManager.commit();
			}else {
				ConnectionManager.rollback();
			}
			out.print("parent.runActionDirect('REFRESH','action_submit','"+thClassName+"','"+key+"','same','no')");
			out.print("</script>");
		}
	}

}

package it.softre.thip.base.attivita.web;

import java.util.Enumeration;

import com.thera.thermfw.collector.BaseBOComponent;
import com.thera.thermfw.collector.EnhBOComponentManager;

import it.softre.thip.base.attivita.AttivitaSoftre;
import it.thera.thip.base.documenti.TipoGestione;
import it.thera.thip.base.documenti.web.DocumentoDataCollector;

/**
 * <h1>Softre Solutions</h1>
 * <br>
 * @author Daniele Signoroni 21/05/2024
 * <br><br>
 * <b>71543	DSSOF3	21/05/2024</b>
 * <p>Prima stesura.<br>
 *  
 * </p>
 */

public class AttivitaSoftreDataCollector extends DocumentoDataCollector {

	@Override
	protected String getNavigatoreName() {
		return null;
	}

	@Override
	public void impostaSecondoCausale() {

	}

	/**
	 * @author Daniele Signoroni 21/05/2024
	 * <p>
	 * Prima stesura.<br>
	 * Se all'attivita e' stata assegnata una o piu fixes allora il cliente non puo' essere modificato, 
	 * vanno prima cancellate le fix.<br>
	 * </p>
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public void updateHandlingModeOnComponentManagers() {
		super.updateHandlingModeOnComponentManagers();
		AttivitaSoftre atv = (AttivitaSoftre) getBo();
		if(atv.getAttivitaFixes().size() > 0) {
			EnhBOComponentManager p0 = null;
			p0 = (EnhBOComponentManager)getComponentManager("IdClienteSoftre");
			impostaHandlingModeOnComponentManagers(p0, TipoGestione.SOLO_VISUALIZZ);
		}
		if(atv.getStatoAttivita() == AttivitaSoftre.COMPLETATA) {
			Enumeration e = getComponents();
			while (e.hasMoreElements()) {
				BaseBOComponent comp = (BaseBOComponent) e.nextElement();
				if (comp == null)
					continue;
				EnhBOComponentManager compManager = (EnhBOComponentManager) comp.getComponentManager();
				if (compManager == null || compManager.getClassADName().equals("StatoAttivita"))
					continue;
				compManager.setHandlingMode(EnhBOComponentManager.READ_ONLY);
			}
		}
	}

}

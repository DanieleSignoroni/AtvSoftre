package it.softre.thip.base.attivita;

import com.thera.thermfw.common.ErrorMessage;
import com.thera.thermfw.persist.KeyHelper;
import com.thera.thermfw.persist.PersistentObject;

import it.thera.thip.base.dipendente.Dipendente;
import it.thera.thip.base.profilo.UtenteAzienda;

/**
 * <h1>Softre Solutions</h1> <br>
 * 
 * @author Daniele Signoroni 22/05/2024 <br>
 *         <br>
 *         <b>71543 DSSOF3 22/05/2024</b>
 *         <p>
 *         Prima stesura.<br>
 * 
 *         </p>
 */

public class AttivitaCollaboratore extends AttivitaCollaboratorePO {

	protected Dipendente dipendente;

	public ErrorMessage checkDelete() {
		return null;
	}

	@Override
	public boolean initializeOwnedObjects(boolean retFather) {
		boolean ret = super.initializeOwnedObjects(retFather);
		if(ret && isOnDB()) {
			UtenteAzienda utenteAzienda = null;
			try {
				utenteAzienda = (UtenteAzienda) 
						UtenteAzienda.elementWithKey(UtenteAzienda.class,
								KeyHelper.buildObjectKey(new String[] {
										getIdAzienda(),
										getIdUtente()
								}), PersistentObject.NO_LOCK);
			} catch (Exception e) {
				//
			}
			dipendente = utenteAzienda.getDipendente();
		}
		return ret;
	}
	
	public String getNomeCollaboratore() {
		if(dipendente != null) {
			return dipendente.getNome() + " "+ dipendente.getCognome();
		}
		return getIdUtente();
	}

	public String getUrlImmagineCollaboratore() {
		String image = null;
		if(dipendente != null) {
			if(dipendente != null) {
				image = dipendente.getURLImmagineDipendente();
			}
		}
		if(image == null)
			image = "https://tacm.com/wp-content/uploads/2018/01/no-image-available.jpeg";
		return image;
	}

}

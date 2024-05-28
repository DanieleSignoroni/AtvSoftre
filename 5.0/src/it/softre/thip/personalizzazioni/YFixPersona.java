package it.softre.thip.personalizzazioni;

import java.sql.*;

import com.thera.thermfw.common.*;
import com.thera.thermfw.base.*;

/**
 * 
 * @author Administrator
 *
 *	71042	TBSOF3	11/04/2023	Gestione informazioni cliente
 *
 */

public class YFixPersona extends YFixPersonaPO {

	public ErrorMessage checkDelete() {
		return null;
	}

	public int save() throws SQLException {
		if (!isOnDB()) {
			try {
				setIdNumeroFix(new Integer(Numerator.getNextInt("YFixPersona")));
			}
			catch(NumeratorException e) {e.printStackTrace(Trace.excStream);}
		}
		int rc = super.save();
		return rc;
	}

}


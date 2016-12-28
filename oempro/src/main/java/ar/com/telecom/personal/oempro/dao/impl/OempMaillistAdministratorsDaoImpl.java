package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempMaillistAdministratorsDao;
import java.sql.PreparedStatement;

public class OempMaillistAdministratorsDaoImpl extends GenericDao implements OempMaillistAdministratorsDao {
	public void insertMaillistAdministrator(int id) {
		try {
			PreparedStatement insertMaillistAdmin = ConnectionManager.getConnection().prepareStatement("insert into oemp_maillist_administrators(RelMailListID,RelAdministratorID) values (?, ?)");
			insertMaillistAdmin.setInt(1, id);
			insertMaillistAdmin.setInt(2, OempAdministratorsDaoImpl.getAdministrator().getAdministratorID().intValue());
			insertMaillistAdmin.executeUpdate();
		} catch (Exception e) {
			logger.error("Error insertando relacion maillist administrator", e);
		}
	}
}

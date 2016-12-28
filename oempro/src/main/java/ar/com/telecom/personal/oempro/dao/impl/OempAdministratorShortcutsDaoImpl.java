package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempAdministratorShortcutsDao;
import java.sql.PreparedStatement;

public class OempAdministratorShortcutsDaoImpl extends GenericDao implements OempAdministratorShortcutsDao {
	public void updateCountList() {
		try {
			// TODO remover esta entrada
			PreparedStatement updateCount = ConnectionManager.getConnection().prepareStatement("update oemp_administrator_shortcuts set  ClickAmount = (ClickAmount + 1) where RelAdministratorID = ? and fileName = ? ");
			updateCount.setInt(1, OempAdministratorsDaoImpl.getAdministrator().getAdministratorID().intValue());
			updateCount.setString(2, "/admin/list_add.php");
			updateCount.executeUpdate();
		} catch (Exception e) {
			logger.error("Error actualizando el contador de la tabla oemp_administrator_shortcut", e);
		}
	}

	public void updateCountImportFlat() {
		try {
			PreparedStatement updateCount = ConnectionManager.getConnection().prepareStatement("update oemp_administrator_shortcuts set  ClickAmount = (ClickAmount + 4) where RelAdministratorID = ? and fileName = ? ");
			updateCount.setInt(1, OempAdministratorsDaoImpl.getAdministrator().getAdministratorID().intValue());
			updateCount.setString(2, "/admin/member_import_flat.php");
			updateCount.executeUpdate();
		} catch (Exception e) {
			logger.error("Error actualizando el contador de la tabla oemp_administrator_shortcut", e);
		}
	}
}

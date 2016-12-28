package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempMaillistBlacklistDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OempMaillistBlacklistDaoImpl extends GenericDao implements OempMaillistBlacklistDao {
	static PreparedStatement isBlacklisted = null;

	static {
		try {
			isBlacklisted = ConnectionManager.getConnection().prepareStatement("select * from oemp_maillist_blacklist where (RelMailListID = ? or RelMailListID = ?) and Email = ? ");
		} catch (Exception e) {
			logger.error("Error en el prepare statement de oemp maillist blacklist", e);
		}
	}

	public boolean isBlacklisted(int idMaillist, String email) {
		try {
			isBlacklisted.setInt(1, idMaillist);
			isBlacklisted.setInt(2, 0);
			isBlacklisted.setString(3, email);

			ResultSet rs = isBlacklisted.executeQuery();

			return rs.next();
		} catch (Exception e) {
			logger.error("Error controlando si el email: " + email + " se encuentra blacklisted.", e);
		}
		return false;
	}
}

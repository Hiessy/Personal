package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempMaillistBannedDao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OempMaillistBannedDaoImpl extends GenericDao implements OempMaillistBannedDao {
	static PreparedStatement isBanned = null;

	static {
		try {
			isBanned = ConnectionManager.getConnection().prepareStatement("select * from oemp_maillist_banned where (Type = ? or Type = ?) and (RelMailListID = ? or RelMailListID = ?) and ? like CONCAT('%',banned,'%')");
		} catch (Exception e) {
			logger.error("Error en el prepare statement de oemp maillist banned", e);
		}
	}

	public boolean isBanned(int mailId, String email) {
		try {
			isBanned.setString(1, "Email Address");
			isBanned.setString(2, "Domain");
			isBanned.setInt(3, mailId);
			isBanned.setInt(4, 0);
			isBanned.setString(5, email.toLowerCase());

			ResultSet rs = isBanned.executeQuery();

			return rs.next();
		} catch (Exception e) {
			logger.error("Error controlando si el email: " + email + " se encuentra banned.", e);
		}
		return false;
	}
}

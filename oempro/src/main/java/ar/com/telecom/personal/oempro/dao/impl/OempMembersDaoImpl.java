package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.OempMembersDao;
import ar.com.telecom.personal.oempro.model.OempMembers;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OempMembersDaoImpl extends GenericDao implements OempMembersDao {
	static PreparedStatement getMaillistId = null;
	static PreparedStatement memberPs = null;

	ResultSet rs;
	int lastInsertId = 0;
	int x = 2;

	public OempMembers getMember(String email) {
		try {
			memberPs.setString(1, email);
			this.rs = memberPs.executeQuery();

			if (this.rs.next()) {
				OempMembers oempMembers = new OempMembers();
				oempMembers.setMemberId(Integer.valueOf(this.rs.getInt("MemberID")));
				return oempMembers;
			}

			return null;

		} catch (Exception e) {
			logger.error("Error consultando si existe el member por email.");
		}
		return null;
	}

	public int getLastOempMemberId() {
		try {
			ResultSet rs = getMaillistId.executeQuery();

			if (rs.next()) {
				return rs.getInt("lastInsertId");
			}

		} catch (Exception e) {
			logger.error("Error obteniendo el último MemberID");
		}
		return 0;
	}
}

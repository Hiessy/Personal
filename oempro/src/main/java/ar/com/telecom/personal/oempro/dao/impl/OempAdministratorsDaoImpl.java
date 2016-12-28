package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempAdministratorsDao;
import ar.com.telecom.personal.oempro.exception.UsuarioAdminNotFoundException;
import ar.com.telecom.personal.oempro.model.OempAdministrators;
import ar.com.telecom.personal.oempro.service.util.PropertiesLoader;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

public class OempAdministratorsDaoImpl extends GenericDao implements OempAdministratorsDao {
	private static OempAdministrators administrator = null;
	private final static String ADMINQUERYSTR = "select AdminID from oempro_admins where username = ?";
	private Properties props;// PropertiesLoader.getConfigProperties();

	public OempAdministratorsDaoImpl() throws IOException {
		this.props = PropertiesLoader.getProperties();
	}

	public OempAdministrators obtenerUsuarioAdmin() throws Exception {
		if (administrator == null) {

			try {
				// original query:
				// "select AdministratorID from oemp_administrators where username = ?"
				PreparedStatement getAdministratorId = ConnectionManager.getConnection().prepareStatement(ADMINQUERYSTR);
				getAdministratorId.setString(1, this.props.getProperty("oempro.user.name"));
				ResultSet rs = getAdministratorId.executeQuery();

				if (rs.next()) {
					OempAdministrators oempAdministrators = new OempAdministrators();
					oempAdministrators.setAdministratorID(Integer.valueOf(rs.getInt("AdminID")));

					administrator = oempAdministrators;

				} else {
					throw new UsuarioAdminNotFoundException("No se pudo encontrar el administrador: " + this.props.getProperty("user.name"));
				}

			} catch (Exception e) {
				throw new UsuarioAdminNotFoundException();
			}
		}

		return administrator;
	}

	public static OempAdministrators getAdministrator() {
		return administrator;
	}

	public static void setAdministrator(OempAdministrators administrator) {
		OempAdministratorsDaoImpl.administrator = administrator;
	}
}

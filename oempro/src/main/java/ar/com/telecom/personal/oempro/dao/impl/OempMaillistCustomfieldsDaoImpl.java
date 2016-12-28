package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempMaillistCustomfieldsDao;
import java.sql.PreparedStatement;

public class OempMaillistCustomfieldsDaoImpl extends GenericDao implements OempMaillistCustomfieldsDao {
	public void insertMaillistcustomfield(int idMaillist, int idCustomField) {
		try {
			// TODO
			PreparedStatement insertMaillistCustomField = ConnectionManager.getConnection().prepareStatement("insert into oemp_maillist_customfields (RelMailListID, RelCustomFieldID, IsRequired, IsUnique) values (?,?,?,?)");
			insertMaillistCustomField.setInt(1, idMaillist);
			insertMaillistCustomField.setInt(2, idCustomField);
			insertMaillistCustomField.setString(3, "Not mandatory field");
			insertMaillistCustomField.setString(4, "Not unique");
			insertMaillistCustomField.executeUpdate();
		} catch (Exception e) {
			logger.error("Error insertando relacion maillist custom field", e);
		}
	}
}

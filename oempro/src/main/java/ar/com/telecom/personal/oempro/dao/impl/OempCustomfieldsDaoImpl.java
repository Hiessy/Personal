package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempCustomfieldsDao;
import ar.com.telecom.personal.oempro.model.OempCustomfields;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class OempCustomfieldsDaoImpl extends GenericDao implements OempCustomfieldsDao {
	public OempCustomfields getCustomFieldByName(String customField) {
		try {
			//TODO entender que es esto
			PreparedStatement getCustomField = ConnectionManager.getConnection().prepareStatement("select CustomFieldID from oemp_customfields where Title = ?");
			getCustomField.setString(1, customField);
			ResultSet rs = getCustomField.executeQuery();
			if (rs.next()) {
				OempCustomfields oempCustomfields = new OempCustomfields();
				oempCustomfields.setCustomFieldId(Integer.valueOf(rs.getInt("CustomFieldID")));

				return oempCustomfields;
			}

			return null;

		} catch (Exception e) {
			logger.error("Error insertando customfield", e);
		}
		return null;
	}
}

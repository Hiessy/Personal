package ar.com.telecom.personal.oempro.dao.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempMaillistsDao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;

public class OempMaillistsDaoImpl extends GenericDao implements OempMaillistsDao {
	public int insertMaillists(String maillistName) {
		try {

			PreparedStatement insertMaillist = ConnectionManager
					.getConnection()
					.prepareStatement(
							"insert into oempro_subscriber_lists (Name, RelOptInConfirmationEmailID, CreatedOn, RelOwnerUserID, SubscriptionConfirmationPendingPageURL, SubscriptionConfirmedPageURL, SubscriptionErrorPageURL, UnsubscriptionConfirmedPageURL, UnsubscriptionErrorPageURL, ReqByEmailSearchToAddress, ReqByEmailSubscriptionCommand, ReqByEmailUnsubscriptionCommand,  SyncMySQLHost, SyncMySQLPort, SyncMySQLUsername, SyncMySQLPassword, SyncMySQLDBName, SyncMySQLQuery, SyncFieldMapping, SyncLastDateTime, OptInSubscribeTo, OptInUnsubscribeFrom, OptOutSubscribeTo, OptOutUnsubscribeFrom) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

			insertMaillist.setString(1, maillistName);
			insertMaillist.setInt(2, 0);
			insertMaillist.setDate(3, new java.sql.Date(Calendar.getInstance().getTimeInMillis())); 
			insertMaillist.setInt(4, 1);
			insertMaillist.setString(5, "");
			insertMaillist.setString(6, "");
			insertMaillist.setString(7, "");
			insertMaillist.setString(8, "");
			insertMaillist.setString(9, "");
			insertMaillist.setString(10, "");
			insertMaillist.setString(11, "");
			insertMaillist.setString(12, "");
			insertMaillist.setString(13, "");
			insertMaillist.setInt(14, 3306);
			insertMaillist.setString(15, "");
			insertMaillist.setString(16, "");

			insertMaillist.setString(17, "");
			insertMaillist.setString(18, "");
			insertMaillist.setString(19, "");
			insertMaillist.setDate(20, new java.sql.Date(0));
			insertMaillist.setInt(21, 0);
			insertMaillist.setInt(22, 0);
			insertMaillist.setInt(23, 0);
			insertMaillist.setInt(24, 0);

			insertMaillist.executeUpdate();

			// Query vieja select LAST_INSERT_ID() as lastInsertId from
			// oemp_maillists
			PreparedStatement getMaillistId = ConnectionManager.getConnection().prepareStatement("select DISTINCT LAST_INSERT_ID() as lastInsertId from oempro_subscriber_lists");
			ResultSet rs = getMaillistId.executeQuery();

			if (rs.next()) {
			}
			return rs.getInt("lastInsertId");

		} catch (Exception e) {

			logger.error("Error insertando maillist", e);
		}
		return 0;
	}
}

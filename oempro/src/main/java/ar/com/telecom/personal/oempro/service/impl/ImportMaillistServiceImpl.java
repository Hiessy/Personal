package ar.com.telecom.personal.oempro.service.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import ar.com.telecom.personal.oempro.dao.OempAdministratorShortcutsDao;
import ar.com.telecom.personal.oempro.dao.OempCustomfieldsDao;
import ar.com.telecom.personal.oempro.dao.OempMaillistAdministratorsDao;
import ar.com.telecom.personal.oempro.dao.OempMaillistBannedDao;
import ar.com.telecom.personal.oempro.dao.OempMaillistBlacklistDao;
import ar.com.telecom.personal.oempro.dao.OempMaillistCustomfieldsDao;
import ar.com.telecom.personal.oempro.dao.OempMaillistsDao;
import ar.com.telecom.personal.oempro.dao.OempMembersDao;
import ar.com.telecom.personal.oempro.exception.LoadDataFileCreationException;
import ar.com.telecom.personal.oempro.model.Member;
import ar.com.telecom.personal.oempro.service.util.PropertiesLoader;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportMaillistServiceImpl implements ar.com.telecom.personal.oempro.service.ImportMaillistService {

	protected Logger logger = LoggerFactory.getLogger(getClass());
	private Properties props;// = PropertiesLoader.getConfigProperties();
	private OempAdministratorShortcutsDao oempAdministratorShortcutsDao = new ar.com.telecom.personal.oempro.dao.impl.OempAdministratorShortcutsDaoImpl();

	private OempMaillistsDao oempMaillistsDao = new ar.com.telecom.personal.oempro.dao.impl.OempMaillistsDaoImpl();
	private OempMembersDao oempMembersDao = new ar.com.telecom.personal.oempro.dao.impl.OempMembersDaoImpl();
	private OempMaillistAdministratorsDao oempMaillistAdministratorsDao = new ar.com.telecom.personal.oempro.dao.impl.OempMaillistAdministratorsDaoImpl();
	private OempMaillistBlacklistDao oempMaillistBlacklistDao = new ar.com.telecom.personal.oempro.dao.impl.OempMaillistBlacklistDaoImpl();
	private OempMaillistBannedDao oempMaillistBannedDao = new ar.com.telecom.personal.oempro.dao.impl.OempMaillistBannedDaoImpl();
	private OempMaillistCustomfieldsDao oempMaillistCustomfieldsDao = new ar.com.telecom.personal.oempro.dao.impl.OempMaillistCustomfieldsDaoImpl();
	private OempCustomfieldsDao oempCustomfieldsDao = new ar.com.telecom.personal.oempro.dao.impl.OempCustomfieldsDaoImpl();

	private HashMap<String, Member> membersMap = new HashMap<String, Member>();
	private File file;

	public ImportMaillistServiceImpl(File file) throws IOException {
		this.file = file;
		this.props = PropertiesLoader.getProperties();
	}

	public void importar() {
		this.logger.info("Inicio - Proceso importar maillist, archivo: " + this.file.getName());

		PrintWriter pwInsertMemberFile = null;
		File insertMemberFile = null;

		try {
			parseFile(this.file);

			this.logger.info("creando maillist");

			int idMaillist = this.oempMaillistsDao.insertMaillists(this.file.getName().substring(0, this.file.getName().lastIndexOf(".")));

			insertMemberFile = createLoadDataFile("_INSERT_MEMBERS");
			pwInsertMemberFile = new PrintWriter(insertMemberFile);
			pwInsertMemberFile.println("EmailAddress,SubscriptionDate,SubscriptionStatus,SubscriptionIP,UnsubscriptionDate,UnsubscriptionIP,OptInDate");

			String fieldsInsertMembers = "EmailAddress,SubscriptionDate,SubscriptionStatus,SubscriptionIP,UnsubscriptionDate,UnsubscriptionIP,OptInDate";
			String insertStatement = "LOAD DATA LOCAL INFILE '" + this.props.getProperty("dir.output") + insertMemberFile.getName() + "' INTO TABLE oempro_subscribers_" + idMaillist + " FIELDS TERMINATED BY ';'  IGNORE 1 LINES (" + fieldsInsertMembers + ")";

			PreparedStatement insertMembersPs = ConnectionManager.getConnection().prepareStatement(insertStatement);
			PreparedStatement createMembersPs = ConnectionManager.getConnection().prepareStatement(createTableSubscriberSQL(idMaillist));
			this.logger.info("recorriendo lista de members [ " + this.membersMap.size() + " registros ] ...");

			// TODO recorro la lista de miembros y si el miembro no esta banned
			// o blacklisted lo inserto en los archivos
			for (Member member : this.membersMap.values()) {

				pwInsertMemberFile.println(member.getEmailAddress() + ";" + member.getSubscriptionDate() + ";" + member.getSubscriptionStatus() + ";" + member.getSubscriptionIP() + ";" + member.getUnsubscriptionDate() + ";" + member.getUnsubscriptionIP() + ";"
						+ member.getOptInDate());
			}

			this.membersMap.clear();
			pwInsertMemberFile.flush();

			createMembersPs.executeUpdate();
			insertMembersPs.executeUpdate();

			renombrar(this.file);

			this.logger.info("Fin - Proceso importar maillist, archivo: " + this.file.getName());
			return;

		} catch (Exception e) {
			this.logger.error("Error", e);
		} finally {
			try {
				if (pwInsertMemberFile != null) {
					pwInsertMemberFile.close();
				}

			} catch (Exception e) {
				this.logger.error("Error", e);
			}
		}
	}

	private File createLoadDataFile(String append) throws Exception {
		try {
			int index = getFile().getName().lastIndexOf(".");
			String newFileName = getFile().getName().substring(0, index) + append + "." + this.props.getProperty("file.extension");
			File newFile = new File(this.props.getProperty("dir.output") + newFileName);

			if (newFile.exists()) {
				newFile.delete();
			}

			if (newFile.createNewFile()) {
				this.logger.info("Se creo el archivo: " + newFileName);
			} else {
				throw new Exception();
			}

			return newFile;

		} catch (Exception e) {
			throw new LoadDataFileCreationException("No se pudo crear el archivo para insertar los members.", e);
		}
	}

	private void parseFile(File file) {

		this.logger.info("-Inicio- Parseo archivo de clientes");
		String fechaHoy = obtenerHoy();
		String ipAdress = getIpAddress();

		try {
			DataInputStream dis = new DataInputStream(new java.io.FileInputStream(file));
			BufferedReader br = new BufferedReader(new InputStreamReader(dis));

			String strLine;

			while ((strLine = br.readLine()) != null) {
				this.logger.trace("Procesando registro: " + strLine);

				Member member = new Member();
				String[] tokens = strLine.split(String.valueOf(this.props.getProperty("file.separator")));

				if (tokens.length < 2) {
					this.logger.warn("el registro no posee los campos obligatorios, se ignorará el registro.");
					break;
				}

				member.setEmailAddress(tokens[1]);
				member.setSubscriptionDate(fechaHoy);
				member.setSubscriptionStatus("Subscribed");
				member.setSubscriptionIP(ipAdress);
				member.setUnsubscriptionDate("000-00-00");
				member.setUnsubscriptionIP("0.0.0.0");
				member.setOptInDate("000-00-00");

				if ((tokens[1].matches("^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,4}$")) && (this.membersMap.get(member.getEmailAddress()) == null)) {
					this.membersMap.put(member.getEmailAddress(), member);
				} else if (this.membersMap.get(member.getEmailAddress()) != null) {
					this.logger.warn("el registro se encuentra repetido dentro del archivo, se ignorará el registro.");
				} else {
					this.logger.warn("el registro no posee una dirección de mail válida, se ignorará el registro. Mail inválido: " + tokens[1]);
				}
			}

			dis.close();
			br.close();

			this.logger.info("-Fin- Parseo archivo de clientes");

		} catch (Exception e) {

			this.logger.error("Error parseando archivo de clientes: " + e.getMessage());
		}
	}

	private File renombrar(File archivo) {

		String newName = archivo.getPath().replace("_en_proceso", "_procesado");
		File archivoRenombrado = new File(newName);

		if (archivoRenombrado.exists()) {
			archivoRenombrado.delete();
		}

		boolean succeedRenamed = archivo.renameTo(archivoRenombrado);

		if (succeedRenamed) {
			this.logger.info("El archivo fue renombrado a: " + newName + " correctamente.");
		} else {
			this.logger.error("No pudo renombrarse el archivo.");
		}

		return archivoRenombrado;
	}

	public OempAdministratorShortcutsDao getOempAdministratorShortcutsDao() {
		return this.oempAdministratorShortcutsDao;
	}

	public void setOempAdministratorShortcutsDao(OempAdministratorShortcutsDao oempAdministratorShortcutsDao) {
		this.oempAdministratorShortcutsDao = oempAdministratorShortcutsDao;
	}

	public OempMaillistsDao getOempMaillistsDao() {
		return this.oempMaillistsDao;
	}

	public void setOempMaillistsDao(OempMaillistsDao oempMaillistsDao) {
		this.oempMaillistsDao = oempMaillistsDao;
	}

	public OempMembersDao getOempMembersDao() {
		return this.oempMembersDao;
	}

	public void setOempMembersDao(OempMembersDao oempMembersDao) {
		this.oempMembersDao = oempMembersDao;
	}

	public File getFile() {
		return this.file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public OempMaillistAdministratorsDao getOempMaillistAdministratorsDao() {
		return this.oempMaillistAdministratorsDao;
	}

	public void setOempMaillistAdministratorsDao(OempMaillistAdministratorsDao oempMaillistAdministratorsDao) {
		this.oempMaillistAdministratorsDao = oempMaillistAdministratorsDao;
	}

	public OempMaillistBlacklistDao getOempMaillistBlacklistDao() {
		return this.oempMaillistBlacklistDao;
	}

	public void setOempMaillistBlacklistDao(OempMaillistBlacklistDao oempMaillistBlacklistDao) {
		this.oempMaillistBlacklistDao = oempMaillistBlacklistDao;
	}

	public OempMaillistBannedDao getOempMaillistBannedDao() {
		return this.oempMaillistBannedDao;
	}

	public void setOempMaillistBannedDao(OempMaillistBannedDao oempMaillistBannedDao) {
		this.oempMaillistBannedDao = oempMaillistBannedDao;
	}

	public OempMaillistCustomfieldsDao getOempMaillistCustomfieldsDao() {
		return this.oempMaillistCustomfieldsDao;
	}

	public void setOempMaillistCustomfieldsDao(OempMaillistCustomfieldsDao oempMaillistCustomfieldsDao) {
		this.oempMaillistCustomfieldsDao = oempMaillistCustomfieldsDao;
	}

	public OempCustomfieldsDao getOempCustomfieldsDao() {
		return this.oempCustomfieldsDao;
	}

	public void setOempCustomfieldsDao(OempCustomfieldsDao oempCustomfieldsDao) {
		this.oempCustomfieldsDao = oempCustomfieldsDao;
	}

	private String createTableSubscriberSQL(int subscriber) {
		return "CREATE TABLE `oempro_subscribers_" + subscriber + "` (" + "`SubscriberID` int(11) NOT NULL AUTO_INCREMENT," + "`EmailAddress` varchar(250) COLLATE utf8_unicode_ci NOT NULL,"
				+ "`BounceType` enum('Not Bounced','Hard','Soft') COLLATE utf8_unicode_ci NOT NULL DEFAULT 'Not Bounced'," + "`SubscriptionStatus` enum('Opt-In Pending','Subscribed','Opt-Out Pending','Unsubscribed') COLLATE utf8_unicode_ci NOT NULL,"
				+ "`SubscriptionDate` date NOT NULL," + "`SubscriptionIP` varchar(250) COLLATE utf8_unicode_ci NOT NULL," + "`UnsubscriptionDate` date NOT NULL," + "`UnsubscriptionIP` varchar(250) COLLATE utf8_unicode_ci NOT NULL," + "`OptInDate` date NOT NULL,"
				+ "PRIMARY KEY (`SubscriberID`)," + "KEY `EmailAddress` (`EmailAddress`)" + ") ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;";
	}

	private String obtenerHoy() {
		DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-dd");
		Date date = new Date();
		return dateFormat.format(date).toString();
	}

	private String getIpAddress() {
		return "CAMP - 0.0.0.0 - Manual Import";

	}
}

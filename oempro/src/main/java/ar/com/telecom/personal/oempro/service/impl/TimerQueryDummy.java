package ar.com.telecom.personal.oempro.service.impl;

import ar.com.telecom.personal.oempro.dao.ConnectionManager;
import java.sql.PreparedStatement;
import java.util.TimerTask;
import org.apache.log4j.Logger;

public class TimerQueryDummy extends TimerTask {
	private static Logger logger = Logger.getLogger(TimerQueryDummy.class);
	private PreparedStatement preparedStatement = null;

	public void run() {
		try {
			this.preparedStatement = ConnectionManager.getConnection().prepareStatement("select 1 from dual");
			this.preparedStatement.executeQuery();
			logger.info("Se ejecuto el query dummy");
		} catch (Exception e) {
			logger.error("Error ejecutando query dummy", e);
		}
	}
}

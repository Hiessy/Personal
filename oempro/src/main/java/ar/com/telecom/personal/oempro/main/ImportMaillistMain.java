package ar.com.telecom.personal.oempro.main;

import ar.com.telecom.personal.oempro.dao.OempAdministratorsDao;
import ar.com.telecom.personal.oempro.dao.impl.OempAdministratorsDaoImpl;
import ar.com.telecom.personal.oempro.exception.ConfigDataException;
import ar.com.telecom.personal.oempro.exception.LoadDataDirectoryException;
import ar.com.telecom.personal.oempro.service.impl.ExecuteProcessServiceImpl;
import ar.com.telecom.personal.oempro.service.impl.TimerQueryDummy;
import ar.com.telecom.personal.oempro.service.util.PropertiesLoader;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.Properties;
import java.util.Timer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImportMaillistMain {
	private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
	protected static Logger logger = LoggerFactory.getLogger(ImportMaillistMain.class);
	private static Properties props;
	public static long checkNewFilesPeriod = 60000L;

	public static Calendar endCalendar;

	public static void main(String[] args) throws IOException {

		props = PropertiesLoader.getProperties();
		
		try {
			if (args.length == 0) {
				Locale.setDefault(new Locale("es", "ES"));

				logger.info("Hora Ejecucion Scheduler: " + new Date());
				logger.info("TimeZone: " + System.getProperty("user.timezone"));

				OempAdministratorsDao oempAdministratorsDao = new OempAdministratorsDaoImpl();
				oempAdministratorsDao.obtenerUsuarioAdmin();

				createLoadDataDir();
				String dummyQueryTime = props.getProperty("timer.dummy.query.time");

				long dummyQueryLong = Long.parseLong(dummyQueryTime) * 1000L * 60L;

				if ((dummyQueryTime != null) && (dummyQueryTime.matches("\\d+"))) {
					Timer timerDummyQuery = new Timer("Timer-QueryDummy");
					timerDummyQuery.schedule(new TimerQueryDummy(), dummyQueryLong, dummyQueryLong);
				} else {
					throw new ConfigDataException("El parémetro 'timer.dummy.query.time' no existe o no posee un formato válido  para que la aplicación funcione.");
				}
			}

			String initPeriod = props.getProperty("timer.init.hour");
			String endPeriod = props.getProperty("timer.end.hour");

			if ((initPeriod != null) && (endPeriod != null) && (initPeriod.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]")) && (endPeriod.matches("([01]?[0-9]|2[0-3]):[0-5][0-9]"))) {
				Calendar initProcessCalendar = initProcessDate(initPeriod);
				Calendar endProcessCalendar = endProcessDate(endPeriod, initProcessCalendar);

				Timer timerExecute = new Timer("Timer-ImportMaillist");
				timerExecute.scheduleAtFixedRate(new ExecuteProcessServiceImpl(timerExecute), initProcessCalendar.getTime(), checkNewFilesPeriod);

				endCalendar = endProcessCalendar;

			} else {
				throw new ConfigDataException("El parémetro de inicio/fin hora proceso no existe o no posee un formato válido  para que la aplicación funcione.");
			}

		} catch (Exception e) {
			logger.error("error iniciando el scheduler", e);
			System.exit(1);
		}
	}

	private static Calendar endProcessDate(String endPeriod, Calendar initCalendar) throws Exception {
		Date endDateFormatted = simpleDateFormat.parse(endPeriod);
		Calendar endCalendar = new GregorianCalendar();
		endCalendar.setTime(endDateFormatted);

		Date now = new Date();

		Calendar nowCalendar = new GregorianCalendar();
		nowCalendar.setTime(now);

		nowCalendar.set(11, endCalendar.get(11));
		nowCalendar.set(12, endCalendar.get(12));
		nowCalendar.set(13, 0);

		if ((nowCalendar.getTime().before(initCalendar.getTime())) || (nowCalendar.getTime().compareTo(initCalendar.getTime()) == 0)) {
			nowCalendar.add(5, 1);
		}
		if ((nowCalendar.getTime().before(initCalendar.getTime())) || (nowCalendar.getTime().compareTo(initCalendar.getTime()) == 0)) {
			nowCalendar.add(5, 1);
		}

		logger.info("Fecha Fin aplicación: " + nowCalendar.getTime());

		return nowCalendar;
	}

	public static Calendar initProcessDate(String period) throws Exception {
		Date dateFormatted = simpleDateFormat.parse(period);
		Calendar initCalendar = new GregorianCalendar();
		initCalendar.setTime(dateFormatted);

		Date now = new Date();

		Calendar nowCalendar = new GregorianCalendar();
		nowCalendar.setTime(now);

		nowCalendar.set(11, initCalendar.get(11));
		nowCalendar.set(12, initCalendar.get(12));
		nowCalendar.set(13, 0);

		if (nowCalendar.getTime().before(now)) {
			logger.trace("la fecha configurada es anterior a la actual, se suma un día");
			nowCalendar.add(5, 1);
		}

		logger.info("Fecha Inicio aplicación: " + nowCalendar.getTime());

		return nowCalendar;
	}

	private static void createLoadDataDir() throws LoadDataDirectoryException {
		File dirLoadData = new File("/controlm/integracion/load_data");

		if ((dirLoadData.exists()) && (dirLoadData.isDirectory())) {
			logger.debug("El directorio para los LOAD DATA ya existe");

		} else if (dirLoadData.mkdir()) {
			logger.info("Se creo el directorio para load data");
		} else {
			throw new LoadDataDirectoryException();
		}
	}
}

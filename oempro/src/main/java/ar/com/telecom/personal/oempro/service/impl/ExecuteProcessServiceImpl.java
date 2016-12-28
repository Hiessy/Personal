package ar.com.telecom.personal.oempro.service.impl;

import ar.com.telecom.personal.oempro.main.ImportMaillistMain;
import ar.com.telecom.personal.oempro.service.ExecuteProcessService;
import ar.com.telecom.personal.oempro.service.ImportMaillistService;
import ar.com.telecom.personal.oempro.service.util.PropertiesLoader;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExecuteProcessServiceImpl extends TimerTask implements ExecuteProcessService {
	private static Logger logger = LoggerFactory.getLogger(ExecuteProcessServiceImpl.class);
	public static final String EN_PROCESO_SUFFIX = "_en_proceso";
	private static Properties props;// = PropertiesLoader.getConfigProperties();
	private Timer timer = null;

	public ExecuteProcessServiceImpl(Timer timerExecute) throws IOException {
		this.timer = timerExecute;
		this.props = PropertiesLoader.getProperties();
	}

	public void executeProcess() {
		List<File> files = searchFilesToProcess();

		logger.debug("Inicio - proceso importar maillist");

		try {
			for (File file : files) {
				File archivoRenombrado = renombrar(file);

				ImportMaillistService importMaillistService = new ImportMaillistServiceImpl(archivoRenombrado);

				importMaillistService.importar();
			}

		} catch (Exception e) {
			logger.error("Error", e);
		}

		logger.debug("Fin - proceso importar maillist");

		try {
			evaluateEndingTime();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void evaluateEndingTime() throws IOException {
		if (new Date().after(ImportMaillistMain.endCalendar.getTime())) {
			this.timer.cancel();
			logger.info("Se termino el horario de corrida del proceso, se canceló el timer");
			int removedTasks = this.timer.purge();
			logger.debug("Se removieron: " + removedTasks + " tareas");

			relaunchApp();
		}
	}

	private void relaunchApp() throws IOException {
		ImportMaillistMain.main(new String[] { "" });
	}

	private List<File> searchFilesToProcess() {
		List<File> files = new ArrayList<File>();

		try {
			logger.debug("Inicio - checkeando nuevos archivos a procesar");

			String path = props.getProperty("dir.path");
			File dir = new File(path);

			if ((dir.exists()) && (dir.isDirectory())) {
				FilenameFilter filter = new FilenameFilter() {
					public boolean accept(File dir, String name) {
						return name.endsWith("." + ExecuteProcessServiceImpl.props.getProperty("file.extension"));
					}

				};
				File[] childrens = dir.listFiles(filter);

				if ((childrens != null) && (childrens.length > 0)) {
					for (File children : childrens) {
						logger.info("agregando archivo: " + children.getName() + " a la lista de archivos a procesar.");

						files.add(children);
					}

				} else {
					logger.debug("no se encontró  ningún archivo para procesar en: " + path);
				}
			} else {
				logger.error("el path configurado no corresponde a un directorio válido");
			}

			logger.debug("Fin - checkenado nuevos archivos a procesar");

		} catch (Exception e) {
			logger.error("error parseando archivo de configuración", e);
		}

		return files;
	}

	private File renombrar(File archivo) {
		String newName = archivo.getPath() + "_en_proceso";

		File archivoRenombrado = new File(newName);
		if (archivoRenombrado.exists()) {
			archivoRenombrado.delete();
		}
		boolean succeedRenamed = archivo.renameTo(archivoRenombrado);

		if (succeedRenamed) {
			logger.debug("El archivo fue renombrado a: " + newName + " correctamente.");
		} else {
			logger.error("No pudo renombrarse el archivo.");
		}

		return archivoRenombrado;
	}

	public void run() {
		executeProcess();
	}
}

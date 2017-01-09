package ar.com.personal.srvnews.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import ar.com.personal.srvnews.ExportResult;
import ar.com.personal.srvnews.dao.CampaignDAO;
import ar.com.personal.srvnews.dao.MailingDAO;
import ar.com.personal.srvnews.dao.MemberDAO;
import ar.com.personal.srvnews.pojo.Administrator;
import ar.com.personal.srvnews.pojo.Campaign;
import ar.com.personal.srvnews.pojo.Mailing;
import ar.com.personal.srvnews.pojo.Member;
import ar.com.personal.srvnews.pojo.MetaMember;

public class ExportServiceImpl implements ExportService {

	private CampaignDAO campaignDAO;
	private MailingDAO mailingDAO;
	private MemberDAO memberDAO;
	private Integer customID;
	private String startProcesingFromDate;
	private Administrator administrator;
	private String pathToExportFiles;
	private Integer daysToWaitBeforeExport;
	private static final Logger logger = Logger.getLogger(ExportServiceImpl.class);
	private Calendar today;

	public ExportServiceImpl(CampaignDAO campaignDAO, MailingDAO mailingDAO, MemberDAO memberDAO, Integer customID, String startProcesingFromDate, Integer daysToWaitBeforeExport, Administrator administrator) {
		this.campaignDAO = campaignDAO;
		this.mailingDAO = mailingDAO;
		this.customID = customID;
		this.startProcesingFromDate = startProcesingFromDate;
		this.administrator = administrator;
		this.memberDAO = memberDAO;
		this.daysToWaitBeforeExport = daysToWaitBeforeExport;
	}

	public Calendar getToday() {
		return today;
	}

	public void setToday(Calendar today) {
		this.today = today;
	}

	public void setPathToExportFiles(String path) {
		this.pathToExportFiles = path;
	}

	public String getPathToExportFiles() {
		return this.pathToExportFiles;
	}

	public ExportResult exportClickAndRead() {
		logger.info("==============================");
		logger.info("Exportando click & reads......");
		logger.info("==============================");

		ExportResult result = new ExportResult();

		// Search for the campaigns to be processes from the
		// "startProcesingFromDate" value set in the
		// applicationContext-beans.xml
		List<Campaign> campaigns = campaignDAO.findCampaingToProcess(administrator, startProcesingFromDate);

		int processed = 0;
		int exported = 0;
		String memberToExportReaded = "";
		int memberToExportReadCount = 0;
		String memberToExportClick = "";
		int memberToExportClickCount = 0;

		SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
		List<Campaign> campaignToMarkAsProcessed = new ArrayList<Campaign>();
		for (Campaign campaign : campaigns) {
			try {
				// This query has been changed because of the new database
				// schema
				List<Mailing> asociatedMailing = mailingDAO.findMailingsForCampaign(campaign, customID);

				if (asociatedMailing == null || asociatedMailing.size() == 0) {
					logger.info("--> Exportando campania: " + campaign.getCampaignID());
					logger.warn("--> La campania no tiene mailing list asociados o el" + " campo custom no fue relacionado. campaignID = " + campaign.getCampaignID());
					campaignToMarkAsProcessed.add(campaign);
				} else {
					logger.info("--> Exportando campania: " + campaign.getCampaignID());
					Date procesingDate = fmt.parse(startProcesingFromDate);

					Calendar campaignDatePlusDaysToWait = Calendar.getInstance();
					campaignDatePlusDaysToWait.setTime(campaign.getProcessedOn());
					campaignDatePlusDaysToWait.add(Calendar.DAY_OF_YEAR, daysToWaitBeforeExport);

					if (this.today == null) {
						today = Calendar.getInstance();
					}

					logger.info("--> Verificando si la fecha de la campania: " + campaign.getProcessedOn().toString() + " es mayor a la fecha de inicio de las exportaciones: " + procesingDate.toString());
					logger.info("--> y si la fecha de la misma mas " + daysToWaitBeforeExport + " dias: " + campaignDatePlusDaysToWait.getTime().toString());
					logger.info("--> es menor a la fecha de hoy " + today.getTime().toString());

					if (campaign.getProcessedOn().compareTo(procesingDate) >= 0 && campaignDatePlusDaysToWait.getTime().compareTo(today.getTime()) < 0) {

						Mailing mailing = asociatedMailing.get(0);
						logger.info("-> mailing ID: " + mailing.getMailListID());
						exported++;

						// TODO
						List<MetaMember> membersInteraction = memberDAO.getMemberInteraction("OpenDate", "oempro_stats_open", campaign.getCampaignID());
						List<Member> membersWhoRead = null;

						if (!membersInteraction.isEmpty()) {
							membersWhoRead = getMemberInfo(membersInteraction);
							logger.info("---> OK! Exportando members que abrieron mail:" + membersWhoRead.size());
							for (Member m : membersWhoRead) {
								if (m.getMailingCustomID().getId().startsWith("CAMP")) {
									memberToExportReaded += m.getMailingCustomID().getId() + "|" + formatDate(m.getMailingCustomID().getDate()) + System.getProperty("line.separator");
									memberToExportReadCount++;
								}
							}
						}

						membersInteraction = memberDAO.getMemberInteraction("ClickDate", "oempro_stats_link", campaign.getCampaignID());
						List<Member> membersWhoClick = null;

						if (!membersInteraction.isEmpty()) {
							membersWhoClick = getMemberInfo(membersInteraction);
							logger.info("---> OK! Exportando members que hicieron click en el mail:" + membersWhoClick.size());
							for (Member m : membersWhoClick) {
								if (m.getMailingCustomID().getId().startsWith("CAMP")) {
									memberToExportClick += m.getMailingCustomID().getId() + "|" + formatDate(m.getMailingCustomID().getDate()) + System.getProperty("line.separator");
									memberToExportClickCount++;
								}
							}
						}
						campaignToMarkAsProcessed.add(campaign);
					} else {
						logger.info("---> NO Exportar campania:" + campaign.getCampaignID());
					}
				}
			} catch (ParseException e) {
				logger.error("--> No se pudo procesar la campania ya que la fecha de inicio de las exportaciones no pude ser parseada:" + campaign.getCampaignID());
				e.printStackTrace();
			}
			processed++;
		}

		logger.debug("El total de campañas a procesar fueron: " + campaigns.size());
		logger.debug("Se exportaron un total de: " + exported + " campañas");
		logger.debug("Se procesaron un total de: " + processed + " campañas");

		try {
			logger.info("--");
			logger.info("--> Exportando total de members que leyeron el mail: " + memberToExportReadCount);
			Calendar cal = Calendar.getInstance();
			String fecha = fmt.format(cal.getTime());

			{
				String fullPath = pathToExportFiles + "/leidos-" + fecha + ".txt";
				result.setExportedOpenFile(fullPath);
				logger.info("--> Creando archivo de exportacion para leidos: " + fullPath);
				writeFile(memberToExportReaded, fullPath);
			}

			logger.info("--");
			logger.info("--> Exportando total de members que hicieron click: " + memberToExportClickCount);
			{
				String fullPathClick = pathToExportFiles + "/clicks-" + fecha + ".txt";
				result.setExportedClickFile(fullPathClick);
				logger.info("--> Creando archivo de exportacion para clicks: " + fullPathClick);
				writeFile(memberToExportClick, fullPathClick);
			}

			for (Campaign campaign : campaignToMarkAsProcessed) {
				logger.info("--> Marcando como procesada campania: " + campaign.getCampaignID());
				// campaignDAO.setCampaignAsProcessed(campaign);
			}
		} catch (IOException e) {
			logger.error("--> Error guardando en disco el arhivo de exportacion");
			e.printStackTrace();
		}

		result.setProcessed(processed);
		result.setExported(exported);
		return result;
	}

	public ExportResult exportUnsubscriptions() {
		logger.info("==============================");
		logger.info("-> Exportando usuarios que se desubscribieron de las campanias");
		logger.info("==============================");
		ExportResult result = new ExportResult();

		administrator.setAdministratorID(1); 
		List<Campaign> campaigns = campaignDAO.findAllCampaign(administrator, "20010101");
		logger.info("--> Recorriendo campanias:" + campaigns.size());

		int totalExported = 0;
		List<Member> memberListToExport = new ArrayList<Member>();
		for (Campaign c : campaigns) {
			logger.info("--> Campania: " + c.getCampaignID());

			List<MetaMember> membersInteraction = memberDAO.getMemberInteraction("UnsubscriptionDate", "oempro_stats_unsubscription", c.getCampaignID());
			List<Member> membersWhoUnsubscribed = null;

			if (!membersInteraction.isEmpty()) {
				membersWhoUnsubscribed = getMemberInfo(membersInteraction);
				logger.info("---> Usuarios encontrados: " + membersWhoUnsubscribed.size());
				memberListToExport.addAll(membersWhoUnsubscribed);
			}
		}

		String memberToExport = "";
		// exportar lista de desusbscripciones
		for (Member m : memberListToExport) {
			memberToExport += m.getEmail() + System.getProperty("line.separator");
			totalExported++;
		}

		logger.info("--> Usuarios a exportar: " + totalExported);
		// borrar los registro procesados de la base de datos
		if (memberListToExport.size() > 0) {
			try {
				Calendar cal = Calendar.getInstance();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
				String fecha = fmt.format(cal.getTime());
				String fullPath = pathToExportFiles + "/blist-" + fecha + ".txt";
				result.setExportedBListFile(fullPath);
				logger.info("--> Creando archivo de exportacion: " + fullPath);
				writeFile(memberToExport, fullPath);
				for (Campaign c : campaigns) {
					logger.info("--> Borrando usuarios de la base de datos campania: " + c.getCampaignID());
				}
				logger.info("--> Exportacion terminada");
			} catch (IOException e) {
				logger.error("Error intentando generar el archivo de salida para los usuarios " + "que se desusbscriben.");
				totalExported = 0;
				e.printStackTrace();
			}
		}

		result.setUnsubscribed(totalExported);

		return result;
	}

	void writeFile(String data, String fileName) throws IOException {
		File aFile = new File(fileName);
		Writer output = new BufferedWriter(new FileWriter(aFile, true));
		output.append(data);
		try {
			if (output != null) {
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	String formatDate(Date date) {
		try {
			SimpleDateFormat exportFormatter = new SimpleDateFormat("dd/MM/yyyy");
			return exportFormatter.format(date);
		} catch (Throwable e) {
			e.printStackTrace();
			return "";
		}
	}

	private List<Member> getMemberInfo(List<MetaMember> membersInteraction) {

		List<Member> members = new ArrayList<Member>();

		if (!membersInteraction.isEmpty()) {

			for (MetaMember metaMember : membersInteraction) {

				Member member = memberDAO.getMember("oempro_subscribers_" + metaMember.getListID(), metaMember.getSubscriberID());

				member.getMailingCustomID().setMailingID(metaMember.getSubscriberID());
				member.getMailingCustomID().setDate(metaMember.getDate());
				members.add(member);

			}
		}
		return members;
	}
}

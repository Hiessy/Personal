package com.test.database;

import java.util.Date;

import com.test.database.enums.CampaignStatus;
import com.test.database.enums.PublishOnRSS;
import com.test.database.enums.ScheduleType;

public class Procedure {

	private int campaignID;//	int(11)
	private int RelOwnerUserID;//	int(11)
	private CampaignStatus campaignStatus; //enum('Draft','Ready','Sending','Paused','Pending Approval','Sent','Failed')
	private String campaignName;//	varchar(250)
	private int relEmailID;//	int(11)
	private ScheduleType scheduleType;//	enum('Not Scheduled','Immediate','Future','Recursive')
	private Date sendDate;//	date
	private Date sendTime;//	time
	private String sendTimeZone;//	varchar(250)
	private String scheduleRecDaysOfWeek;//	varchar(250)
	private String scheduleRecDaysOfMonth;//	varchar(250)
	private String scheduleRecMonths;//	varchar(250)
	private String scheduleRecHours;//	varchar(250)
	private String scheduleRecMinutes;//	varchar(250)
	private int scheduleRecSendMaxInstance;//	int(11)
	private int scheduleRecSentInstances;//	int(11)
	private Date sendProcessStartedOn;//	datetime
	private Date sendProcessFinishedOn;//	datetime
	private int totalRecipients;//	int(11)
	private int totalSent;//	int(11)
	private int totalFailed;//	int(11)
	private int totalOpens;//	int(11)
	private int uniqueOpens;//	int(11)
	private int totalClicks;//	int(11)
	private int uniqueClicks;//	int(11)
	private int totalForwards;//	int(11)
	private int uniqueForwards;//	int(11)
	private int totalViewsOnBrowser;//	int(11)
	private int uniqueViewsOnBrowser;//	int(11)
	private int totalUnsubscriptions;//	int(11)
	private int totalHardBounces;//	int(11)
	private int totalSoftBounces;//	int(11)
	private double benchmarkEmailsPerSecond;//		double
	private String approvalUserExplanation;//		text
	private String googleAnalyticsDomains;//		text
	private PublishOnRSS publishOnRSS;//enum('Enabled','Disabled')
	private Date lastActivityDateTime;//		datetime
	private Date createDateTime;//		datetime	
	
	public Procedure() {
		super();
	}
	public Procedure(int campaignID, int relOwnerUserID, CampaignStatus campaignStatus, String campaignName, int relEmailID, ScheduleType scheduleType, Date sendDate, Date sendTime, String sendTimeZone, String scheduleRecDaysOfWeek, String scheduleRecDaysOfMonth,
			String scheduleRecMonths, String scheduleRecHours, String scheduleRecMinutes, int scheduleRecSendMaxInstance, int scheduleRecSentInstances, Date sendProcessStartedOn, Date sendProcessFinishedOn, int totalRecipients, int totalSent, int totalFailed, int totalOpens,
			int uniqueOpens, int totalClicks, int uniqueClicks, int totalForwards, int uniqueForwards, int totalViewsOnBrowser, int uniqueViewsOnBrowser, int totalUnsubscriptions, int totalHardBounces, int totalSoftBounces, double benchmarkEmailsPerSecond,
			String approvalUserExplanation, String googleAnalyticsDomains, PublishOnRSS publishOnRSS, Date lastActivityDateTime, Date createDateTime) {
		super();
		this.campaignID = campaignID;
		RelOwnerUserID = relOwnerUserID;
		this.campaignStatus = campaignStatus;
		this.campaignName = campaignName;
		this.relEmailID = relEmailID;
		this.scheduleType = scheduleType;
		this.sendDate = sendDate;
		this.sendTime = sendTime;
		this.sendTimeZone = sendTimeZone;
		this.scheduleRecDaysOfWeek = scheduleRecDaysOfWeek;
		this.scheduleRecDaysOfMonth = scheduleRecDaysOfMonth;
		this.scheduleRecMonths = scheduleRecMonths;
		this.scheduleRecHours = scheduleRecHours;
		this.scheduleRecMinutes = scheduleRecMinutes;
		this.scheduleRecSendMaxInstance = scheduleRecSendMaxInstance;
		this.scheduleRecSentInstances = scheduleRecSentInstances;
		this.sendProcessStartedOn = sendProcessStartedOn;
		this.sendProcessFinishedOn = sendProcessFinishedOn;
		this.totalRecipients = totalRecipients;
		this.totalSent = totalSent;
		this.totalFailed = totalFailed;
		this.totalOpens = totalOpens;
		this.uniqueOpens = uniqueOpens;
		this.totalClicks = totalClicks;
		this.uniqueClicks = uniqueClicks;
		this.totalForwards = totalForwards;
		this.uniqueForwards = uniqueForwards;
		this.totalViewsOnBrowser = totalViewsOnBrowser;
		this.uniqueViewsOnBrowser = uniqueViewsOnBrowser;
		this.totalUnsubscriptions = totalUnsubscriptions;
		this.totalHardBounces = totalHardBounces;
		this.totalSoftBounces = totalSoftBounces;
		this.benchmarkEmailsPerSecond = benchmarkEmailsPerSecond;
		this.approvalUserExplanation = approvalUserExplanation;
		this.googleAnalyticsDomains = googleAnalyticsDomains;
		this.publishOnRSS = publishOnRSS;
		this.lastActivityDateTime = lastActivityDateTime;
		this.createDateTime = createDateTime;
	}
	public int getCampaignID() {
		return campaignID;
	}
	public void setCampaignID(int campaignID) {
		this.campaignID = campaignID;
	}
	public int getRelOwnerUserID() {
		return RelOwnerUserID;
	}
	public void setRelOwnerUserID(int relOwnerUserID) {
		RelOwnerUserID = relOwnerUserID;
	}
	public CampaignStatus getCampaignStatus() {
		return campaignStatus;
	}
	public void setCampaignStatus(CampaignStatus campaignStatus) {
		this.campaignStatus = campaignStatus;
	}
	public String getCampaignName() {
		return campaignName;
	}
	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}
	public int getRelEmailID() {
		return relEmailID;
	}
	public void setRelEmailID(int relEmailID) {
		this.relEmailID = relEmailID;
	}
	public ScheduleType getScheduleType() {
		return scheduleType;
	}
	public void setScheduleType(ScheduleType scheduleType) {
		this.scheduleType = scheduleType;
	}
	public Date getSendDate() {
		return sendDate;
	}
	public void setSendDate(Date sendDate) {
		this.sendDate = sendDate;
	}
	public Date getSendTime() {
		return sendTime;
	}
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	public String getSendTimeZone() {
		return sendTimeZone;
	}
	public void setSendTimeZone(String sendTimeZone) {
		this.sendTimeZone = sendTimeZone;
	}
	public String getScheduleRecDaysOfWeek() {
		return scheduleRecDaysOfWeek;
	}
	public void setScheduleRecDaysOfWeek(String scheduleRecDaysOfWeek) {
		this.scheduleRecDaysOfWeek = scheduleRecDaysOfWeek;
	}
	public String getScheduleRecDaysOfMonth() {
		return scheduleRecDaysOfMonth;
	}
	public void setScheduleRecDaysOfMonth(String scheduleRecDaysOfMonth) {
		this.scheduleRecDaysOfMonth = scheduleRecDaysOfMonth;
	}
	public String getScheduleRecMonths() {
		return scheduleRecMonths;
	}
	public void setScheduleRecMonths(String scheduleRecMonths) {
		this.scheduleRecMonths = scheduleRecMonths;
	}
	public String getScheduleRecHours() {
		return scheduleRecHours;
	}
	public void setScheduleRecHours(String scheduleRecHours) {
		this.scheduleRecHours = scheduleRecHours;
	}
	public String getScheduleRecMinutes() {
		return scheduleRecMinutes;
	}
	public void setScheduleRecMinutes(String scheduleRecMinutes) {
		this.scheduleRecMinutes = scheduleRecMinutes;
	}
	public int getScheduleRecSendMaxInstance() {
		return scheduleRecSendMaxInstance;
	}
	public void setScheduleRecSendMaxInstance(int scheduleRecSendMaxInstance) {
		this.scheduleRecSendMaxInstance = scheduleRecSendMaxInstance;
	}
	public int getScheduleRecSentInstances() {
		return scheduleRecSentInstances;
	}
	public void setScheduleRecSentInstances(int scheduleRecSentInstances) {
		this.scheduleRecSentInstances = scheduleRecSentInstances;
	}
	public Date getSendProcessStartedOn() {
		return sendProcessStartedOn;
	}
	public void setSendProcessStartedOn(Date sendProcessStartedOn) {
		this.sendProcessStartedOn = sendProcessStartedOn;
	}
	public Date getSendProcessFinishedOn() {
		return sendProcessFinishedOn;
	}
	public void setSendProcessFinishedOn(Date sendProcessFinishedOn) {
		this.sendProcessFinishedOn = sendProcessFinishedOn;
	}
	public int getTotalRecipients() {
		return totalRecipients;
	}
	public void setTotalRecipients(int totalRecipients) {
		this.totalRecipients = totalRecipients;
	}
	public int getTotalSent() {
		return totalSent;
	}
	public void setTotalSent(int totalSent) {
		this.totalSent = totalSent;
	}
	public int getTotalFailed() {
		return totalFailed;
	}
	public void setTotalFailed(int totalFailed) {
		this.totalFailed = totalFailed;
	}
	public int getTotalOpens() {
		return totalOpens;
	}
	public void setTotalOpens(int totalOpens) {
		this.totalOpens = totalOpens;
	}
	public int getUniqueOpens() {
		return uniqueOpens;
	}
	public void setUniqueOpens(int uniqueOpens) {
		this.uniqueOpens = uniqueOpens;
	}
	public int getTotalClicks() {
		return totalClicks;
	}
	public void setTotalClicks(int totalClicks) {
		this.totalClicks = totalClicks;
	}
	public int getUniqueClicks() {
		return uniqueClicks;
	}
	public void setUniqueClicks(int uniqueClicks) {
		this.uniqueClicks = uniqueClicks;
	}
	public int getTotalForwards() {
		return totalForwards;
	}
	public void setTotalForwards(int totalForwards) {
		this.totalForwards = totalForwards;
	}
	public int getUniqueForwards() {
		return uniqueForwards;
	}
	public void setUniqueForwards(int uniqueForwards) {
		this.uniqueForwards = uniqueForwards;
	}
	public int getTotalViewsOnBrowser() {
		return totalViewsOnBrowser;
	}
	public void setTotalViewsOnBrowser(int totalViewsOnBrowser) {
		this.totalViewsOnBrowser = totalViewsOnBrowser;
	}
	public int getUniqueViewsOnBrowser() {
		return uniqueViewsOnBrowser;
	}
	public void setUniqueViewsOnBrowser(int uniqueViewsOnBrowser) {
		this.uniqueViewsOnBrowser = uniqueViewsOnBrowser;
	}
	public int getTotalUnsubscriptions() {
		return totalUnsubscriptions;
	}
	public void setTotalUnsubscriptions(int totalUnsubscriptions) {
		this.totalUnsubscriptions = totalUnsubscriptions;
	}
	public int getTotalHardBounces() {
		return totalHardBounces;
	}
	public void setTotalHardBounces(int totalHardBounces) {
		this.totalHardBounces = totalHardBounces;
	}
	public int getTotalSoftBounces() {
		return totalSoftBounces;
	}
	public void setTotalSoftBounces(int totalSoftBounces) {
		this.totalSoftBounces = totalSoftBounces;
	}
	public double getBenchmarkEmailsPerSecond() {
		return benchmarkEmailsPerSecond;
	}
	public void setBenchmarkEmailsPerSecond(double benchmarkEmailsPerSecond) {
		this.benchmarkEmailsPerSecond = benchmarkEmailsPerSecond;
	}
	public String getApprovalUserExplanation() {
		return approvalUserExplanation;
	}
	public void setApprovalUserExplanation(String approvalUserExplanation) {
		this.approvalUserExplanation = approvalUserExplanation;
	}
	public String getGoogleAnalyticsDomains() {
		return googleAnalyticsDomains;
	}
	public void setGoogleAnalyticsDomains(String googleAnalyticsDomains) {
		this.googleAnalyticsDomains = googleAnalyticsDomains;
	}
	public PublishOnRSS getPublishOnRSS() {
		return publishOnRSS;
	}
	public void setPublishOnRSS(PublishOnRSS publishOnRSS) {
		this.publishOnRSS = publishOnRSS;
	}
	public Date getLastActivityDateTime() {
		return lastActivityDateTime;
	}
	public void setLastActivityDateTime(Date lastActivityDateTime) {
		this.lastActivityDateTime = lastActivityDateTime;
	}
	public Date getCreateDateTime() {
		return createDateTime;
	}
	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}
	
	
}

package ar.com.telecom.personal.oempro.model;

public class Member {

	private String emailAddress;
	private String subscriptionDate;
	private String subscriptionStatus;
	private String subscriptionIP;
	private String unsubscriptionDate;
	private String unsubscriptionIP;
	private String optInDate;
	
	public String getEmailAddress() {
		return emailAddress;
	}
	
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	
	public String getSubscriptionDate() {
		return subscriptionDate;
	}
	
	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	
	public String getSubscriptionIP() {
		return subscriptionIP;
	}
	
	public void setSubscriptionIP(String subscriptionIP) {
		this.subscriptionIP = subscriptionIP;
	}
	
	public String getUnsubscriptionDate() {
		return unsubscriptionDate;
	}
	
	public void setUnsubscriptionDate(String unsubscriptionDate) {
		this.unsubscriptionDate = unsubscriptionDate;
	}
	
	public String getUnsubscriptionIP() {
		return unsubscriptionIP;
	}
	
	public void setUnsubscriptionIP(String unsubscriptionIP) {
		this.unsubscriptionIP = unsubscriptionIP;
	}
	
	public String getOptInDate() {
		return optInDate;
	}
	
	public void setOptInDate(String optInDate) {
		this.optInDate = optInDate;
	}
	
	@Override
	public String toString() {
		return "Member [emailAddress=" + emailAddress + ", subscriptionDate=" + subscriptionDate + ", subscriptionIP=" + subscriptionIP + ", unsubscriptionDate=" + unsubscriptionDate + ", unsubscriptionIP=" + unsubscriptionIP + ", optInDate=" + optInDate + "]";
	}

	public String getSubscriptionStatus() {
		return subscriptionStatus;
	}

	public void setSubscriptionStatus(String subscriptionStatus) {
		this.subscriptionStatus = subscriptionStatus;
	}

}

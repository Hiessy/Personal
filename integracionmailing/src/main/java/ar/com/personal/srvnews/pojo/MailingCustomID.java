package ar.com.personal.srvnews.pojo;

import java.sql.Date;

public class MailingCustomID {

	private String id;

	private int mailingID;

	private Date date;

	public MailingCustomID() {

	}

	public int getMailingID() {
		return mailingID;
	}

	public void setMailingID(int mailingID) {
		this.mailingID = mailingID;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}

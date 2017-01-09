package ar.com.personal.srvnews.pojo;

import java.sql.Date;

public class MetaMember {
	private int listID;
	private int subscriberID ;
	private int relEmailID;
	private Date date;
	
	public int getListID() {
		return listID;
	}
	public void setListID(int listID) {
		this.listID = listID;
	}
	public int getSubscriberID() {
		return subscriberID;
	}
	public void setSubscriberID(int subscriberID) {
		this.subscriberID = subscriberID;
	}
	public int getRelEmailID() {
		return relEmailID;
	}
	public void setRelEmailID(int relEmailID) {
		this.relEmailID = relEmailID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
}

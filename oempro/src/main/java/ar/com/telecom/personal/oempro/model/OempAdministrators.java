package ar.com.telecom.personal.oempro.model;

import java.io.Serializable;

public class OempAdministrators implements Serializable {

	private static final long serialVersionUID = 437052061746481192L;
	private Integer administratorID;

	public Integer getAdministratorID() {
		return this.administratorID;
	}

	public void setAdministratorID(Integer administratorID) {
		this.administratorID = administratorID;
	}
}

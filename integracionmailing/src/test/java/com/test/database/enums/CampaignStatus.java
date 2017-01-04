package com.test.database.enums;

public enum CampaignStatus {

	DRAFT("Draft"), READY("Ready"), SENDING("Sending"), PAUSED("Paused"), PENDING_APPROVAL("Pending Approval"), SENT("Sent"), FAILED("Failed");

	private String displayName;

	CampaignStatus(String displayName) {
		this.displayName = displayName;
	}

	public String displayName() {
		return displayName;
	}

	@Override
	public String toString() {
		return displayName;
	}

}

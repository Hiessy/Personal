package com.test.database.enums;

public enum PublishOnRSS {
	ENABLED("Enabled"),
	DISABLED("Disabled");
	
	private String displayName;

	PublishOnRSS(String displayName) {
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

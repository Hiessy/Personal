package com.test.database.enums;

public enum ScheduleType {
	NOT_SCHEDULED("Not Scheduled"), IMMEDIATE("Immediate"), FUTURE("Future"), RECURSIVE("Recursive"), ;

	private String displayName;

	ScheduleType(String displayName) {
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

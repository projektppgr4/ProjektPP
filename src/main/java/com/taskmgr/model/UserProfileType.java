package com.taskmgr.model;

/**
 * Created by Akai on 2017-04-03.
 */
public enum UserProfileType {
	USER("USER"),
	DBA("DBA"),
	ADMIN("ADMIN");

	String userProfileType;

	UserProfileType(String userProfileType) {
		this.userProfileType = userProfileType;
	}

	public String getUserProfileType() {
		return userProfileType;
	}

}
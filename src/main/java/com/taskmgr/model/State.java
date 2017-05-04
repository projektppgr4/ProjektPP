package com.taskmgr.model;

/**
 * Created by Akai on 2017-04-03.
 */
public enum State {
	ACTIVE("Active");

	private String state;

	State(final String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}
}

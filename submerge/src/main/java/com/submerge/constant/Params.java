package com.submerge.constant;

/**
 * Requests parameters
 */
public enum Params {

	REDIRECT("faces-redirect=true");

	private String param;

	Params(String param) {
		this.param = param;
	}

	public String getParam() {
		return this.param;
	}

	@Override
	public String toString() {
		return getParam();
	}

}

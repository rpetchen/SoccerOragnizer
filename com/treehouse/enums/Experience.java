package com.treehouse.enums;

public enum Experience {
	EXP("Experienced"), INEXP("Inexperienced");

	private final String value;
	

	Experience(String value) {
	this.value = value;

	}

	public String getValue() {
		return this.value;
	}

}

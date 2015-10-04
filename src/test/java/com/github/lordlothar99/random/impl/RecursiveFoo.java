package com.github.lordlothar99.random.impl;

public class RecursiveFoo {
	private RecursiveFoo2 parent;
	private String stringProperty;

	/**
	 * @return the parent
	 */
	public RecursiveFoo2 getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(RecursiveFoo2 parent) {
		this.parent = parent;
	}

	public String getStringProperty() {
		return stringProperty;
	}

	public void setStringProperty(String stringProperty) {
		this.stringProperty = stringProperty;
	}

}
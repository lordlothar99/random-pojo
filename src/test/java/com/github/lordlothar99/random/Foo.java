/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Foo {

	private long id;

	private String name;

	private String surname;

	private List<String> bars = new ArrayList<String>();

	private MyEnum myEnum;

	public Foo(long id, String name, String surname) {
		this.id = id;
		this.name = name;
		this.surname = surname;
	}

	public List<String> getBars() {
		return bars;
	}

	public void setBars(List<String> bars) {
		this.bars = bars;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public void setMyEnum(MyEnum myEnum) {
		this.myEnum = myEnum;
	}

	public MyEnum getMyEnum() {
		return myEnum;
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}
}

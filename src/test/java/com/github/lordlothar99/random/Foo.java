/**
 * https://github.com/lordlothar99/random-pojo
 */
package com.github.lordlothar99.random;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

public class Foo {

	private long id;

	private String name;

	private String surname;

	private List<Boolean> bars;
	private Set<String> qixs = new HashSet<String>();

	private MyEnum myEnum;

	private char aCharacter = 'a';

	public Foo() {
	}

	public List<Boolean> getBars() {
		return bars;
	}

	public void setBars(List<Boolean> bars) {
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

	public Set<String> getQixs() {
		return qixs;
	}

	public void setQixs(Set<String> qixs) {
		this.qixs = qixs;
	}

	public char getaCharacter() {
		return aCharacter;
	}

	public void setaCharacter(char aCharacter) {
		this.aCharacter = aCharacter;
	}

}

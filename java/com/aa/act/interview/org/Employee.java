package com.aa.act.interview.org;

public class Employee {

	private String identifier;
	private Name name;

	public Employee(String identifier, Name name) {
		if(name == null)
			throw new IllegalArgumentException("name cannot be null");
		this.identifier = identifier;
		this.name = name;
	}

	public String getIdentifier() {
		return identifier;
	}

	public Name getName() {
		return name;
	}

	@Override
	public String toString() {
		return name.toString() + ": " + identifier;
	}
}
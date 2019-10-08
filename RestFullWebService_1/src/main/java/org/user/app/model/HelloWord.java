package org.user.app.model;

public class HelloWord {
	private String name;

	public HelloWord(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "HelloWord [name=" + name + "]";
	}
	
}

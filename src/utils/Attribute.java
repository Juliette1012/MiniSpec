package utils;

import tools.Visitor;

public class Attribute extends MiniSpec {
	
	protected String name;
	protected String type;
	
	public Attribute() {}
	
	public Attribute(String n, String t) {
		this.name = n;
		this.type = t;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getType() {
		return this.type;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public void accept(Visitor v) {
		v.visitAttribute(this);
	}
}

package utils;

import java.util.ArrayList;

import tools.Visitor;

public class Entity extends MiniSpec {
	protected String name;
	private ArrayList<Attribute> attributes;
	
	public Entity() {}
	
	public Entity(String entityName) {
		this.name = entityName;
		this.attributes = new ArrayList<Attribute>();
	}
	
	public Entity(String entityName, ArrayList<Attribute> attributes) {
		this.name = entityName;
		this.attributes = attributes;
		
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public ArrayList<Attribute> getAttributes(){
		return this.attributes;
	}
	

	
	public void accept(Visitor v) {
		v.visitEntity(this);
	}
}

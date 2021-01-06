package utils;

import java.util.ArrayList;

import tools.Visitor;

public class Model extends MiniSpec {
	protected String name;
	protected ArrayList<Entity> entities; 
	
	public Model() {}
	
	public Model(String modelName) {
		this.name = modelName;
		this.entities = new ArrayList<Entity>();
	}
	
	public Model(String modelName, ArrayList<Entity> entities) {
		this.name = modelName;
		this.entities = entities;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String n) {
		this.name = n;
	}
	
	public ArrayList<Entity> getEntities(){
		return this.entities;
	}
	
	public void accept(Visitor v) {
		v.visitModel(this);
	}
}

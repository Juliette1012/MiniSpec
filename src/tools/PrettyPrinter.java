package tools;

import utils.Attribute;
import utils.Entity;
import utils.Model;

public class PrettyPrinter extends Visitor {
	
	String result;
	
	// constructor
	public PrettyPrinter() {
		result = "";
	}
	
	@Override
	public void visitModel(Model m) {
		result += "model " + m.getName() + ";\n";
		for (Entity e : m.getEntities()) {
			e.accept(this);
			result += "\n";
		}
		result += "end_model;";
	}
	
	@Override
	public void visitEntity(Entity e) {
		result += "\t entity " + e.getName() + " ;\n";
		for (Attribute a : e.getAttributes()) {
			a.accept(this);
		}
		result += "\t end_entity ;";
	}
	
	@Override
	public void visitAttribute(Attribute a) {
		result += "\t\t " + a.getName() +  " : " + a.getType() + " ;\n";
	}
	
	public String getResult() {
		return this.result;
	}

}

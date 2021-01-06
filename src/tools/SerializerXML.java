package tools;

import utils.Attribute;
import utils.Entity;
import utils.Model;

public class SerializerXML extends Visitor{
	
	String result;
	
	// constructor
	public SerializerXML() {
		result = "";
	}
	
	@Override
	public void visitModel(Model m) {
		result += "<model name=" + m.getName() + ">\n";
		for (Entity e : m.getEntities()) {
			e.accept(this);
			result += "\n";
		}
		result += "</model>";		
	}
	
	
	@Override
	public void visitEntity(Entity e) {
		result += "  <entity name=" + e.getName() + ">\n";
		for (Attribute a : e.getAttributes()) {
			a.accept(this);
		}
		result += "  </entity>";
	}
	
	
	@Override
	public void visitAttribute(Attribute a) {
		result += "    <attribute name=" + a.getName() +  " type=" + a.getType() + "/>\n";
	}

	public String getResult() {
		return this.result;
	}
}


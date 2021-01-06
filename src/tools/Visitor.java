package tools;

import utils.Attribute;
import utils.Entity;
import utils.Model;

public abstract class Visitor {
	
	public abstract void visitModel(Model m);
	
	public abstract void visitEntity(Entity e);

	public abstract void visitAttribute(Attribute a);

}

package tools;

import java.util.HashMap;import utils.Attribute;
import utils.Entity;
import utils.MiniSpec;
import utils.Model;

public class RenamingTool extends Visitor {

	protected HashMap<String, String> renameMap;
	
	// constructor
	public RenamingTool() {
		renameMap = new HashMap<String, String>();
	}
	
	
	@Override
	public void visitModel(Model m) {
		
        for (String key : renameMap.keySet()) {
        	String name = m.getName();
        	String newName = name.replaceAll(key, renameMap.get(key));
            m.setName(newName);
        }
        
        for (Entity e : m.getEntities()) {
            e.accept(this);
        }
	}

	
	@Override
	public void visitEntity(Entity e) {
		
        for (String key : renameMap.keySet()) {
        	String name = e.getName();
        	String newName = name.replaceAll(key, renameMap.get(key));
            e.setName(newName);
        }
        
        for (Attribute a : e.getAttributes()) {
            a.accept(this);
        }
	}

	
	@Override
	public void visitAttribute(Attribute a) {
        for (String key : renameMap.keySet()) {
        	String name = a.getName();
        	String newName = name.replaceAll(key, renameMap.get(key));
            a.setName(newName);
        }
	}
	
	
	public void putInMap(String before, String after) {
		renameMap.put(before, after);
	}
	
	public void rename(MiniSpec ms){
		ms.accept(this);
	}

}

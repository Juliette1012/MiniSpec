package tools;

import java.util.ArrayList;
import java.util.Stack;

import utils.Attribute;
import utils.Entity;
import utils.MiniSpec;
import utils.Model;
import utils.XMLBalise;

public class DeserializerXML{
	protected String result;
	
	public DeserializerXML() {}

	public static ArrayList<XMLBalise> parse(String text) {
		String[] l = text.split("\n");
		Stack<XMLBalise> stack = new Stack<>();
		ArrayList<XMLBalise> result = new ArrayList<XMLBalise>();
		
		for (String substring : l) {
			while (substring.startsWith(" ")) {
				substring = substring.substring(1);
			}
			while (substring.endsWith(" ")) {
				substring = substring.substring(0, substring.length() - 1);
			}
			
			if (!substring.startsWith("<") || !substring.endsWith(">")) {
				System.out.println("Error Line don't respect the syntax : begin with < and finish with >.");
			}
			
			substring = substring.substring(1, substring.length() - 1);

			if (substring.startsWith("/")) {
				if (!stack.isEmpty() && substring.substring(1).replaceAll(" ", "").equals(stack.peek().classMiniSpec)) {
					stack.pop();
					continue;
				} else {
					System.out.println("Error");
				}
			}

			XMLBalise balise = new XMLBalise();
			if (stack.isEmpty()) {
				result.add(balise);
			} else {
				stack.peek().listBalise.add(balise);
			}
			if (substring.endsWith("/")) {
				substring = substring.substring(0, substring.length() - 1);
			} else {
				stack.add(balise);
			}

			String[] attributes = substring.split(" ");
			if (attributes.length < 1) {
				System.out.println("Length attributes is inferior to 1 : impossible");
			}
			balise.classMiniSpec = attributes[0];

			for (int i = 1; i < attributes.length; i++) {
				String[] tuple = attributes[i].split("=");
				// %name=%value
				if (tuple.length != 2) {
					System.out.println("Error attributes must be respresented as %name=%value.");
				}
				balise.attributes.put(tuple[0], tuple[1]);
			}
		}
		if (!stack.isEmpty()) {
			System.out.println("Error stack is empty");
		}
		return result;
	}
	
	
	public static ArrayList<MiniSpec> deserializeInput(ArrayList<XMLBalise> balises) {
		
		ArrayList<MiniSpec> result = new ArrayList<>();
		
		for (XMLBalise b : balises) {
			
			MiniSpec m = null;
			
			switch(b.classMiniSpec) {
			
				case "model":
					if(!b.attributes.containsKey("name")) {
						System.out.println("The property \"name\" of the class model is missing");
					}
					ArrayList<MiniSpec> modelBalise = deserializeInput(b.listBalise);
					ArrayList<Entity> entities = new ArrayList<>();
					
					for(MiniSpec balise:modelBalise) {
						if(balise instanceof Entity) {
							entities.add((Entity) balise);
						}
					}
					
					m = new Model(b.attributes.get("name"), entities);
					break;
					
					
				case "entity":
					if(!b.attributes.containsKey("name")) {
						System.out.println("The property \"name\" of the class entity is missing");
					}
					
					ArrayList<MiniSpec> entityBalise = deserializeInput(b.listBalise);
					ArrayList<Attribute> attributes = new ArrayList<>();
					for(MiniSpec balise:entityBalise) {
						if(balise instanceof Attribute) {
							attributes.add((Attribute) balise);
						
						}
					}	
					m = new Entity(b.attributes.get("name"), attributes);
					break;
					
					
				case "attribute":
					if(!b.attributes.containsKey("name")) {
						System.out.println("The property \"name\" of the class entity is missing");
					}
					if(!b.attributes.containsKey("type")) {
						System.out.println("The property \"type\" of the class entity is missing");
					}
					
					m = new Attribute(b.attributes.get("name"), b.attributes.get("type"));
					
			}
			result.add(m);
		}
		return result;
	}
}

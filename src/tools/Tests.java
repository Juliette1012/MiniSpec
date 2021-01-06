package tools;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import utils.*;

public class Tests {
	
	@Test
	public void testPrettyPrinter() {
		System.out.println("\nTest of the tool : Pretty Printer");
		System.out.println("-----------------------------------\n");
		
		Model m = new Model("Flotte");
		Entity e = new Entity("Satellite");
		Attribute name = new Attribute("nom", "String");
		Attribute id = new Attribute("id", "Integer");
		
		
		e.getAttributes().add(name);
		e.getAttributes().add(id);
		m.getEntities().add(e);
		
		// Create an instance of the tool  : PrettyPrinter
		PrettyPrinter pp = new PrettyPrinter();
		
		m.accept(pp);
				
		String result = pp.getResult();
		System.out.println(result);
		
	
		assertTrue(result.equals("model Flotte;\n" + //
				"\t entity Satellite ;\n" + //
				"\t\t nom : String ;\n" + //
				"\t\t id : Integer ;\n" + //
				"\t end_entity ;\n" + //
				"end_model;"));
		
		System.out.println("\nTest of the tool PrettyPrinter réussi !\n\n");
	}

	
	@Test
	public void testSerializerXML() {
		System.out.println("\nTest of the tool : SerializerXML");
		System.out.println("-----------------------------------\n");
		
		Model m = new Model("Flotte");
		Entity e = new Entity("Satellite");
		Attribute name = new Attribute("nom", "String");
		Attribute id = new Attribute("id", "Integer");
		
		
		e.getAttributes().add(name);
		e.getAttributes().add(id);
		m.getEntities().add(e);
		
		// Create an instance of the tool  : SerializerXML
		SerializerXML s = new SerializerXML();
		
		m.accept(s);
				
		String result = s.getResult();
		System.out.println(result);
		
	
		assertTrue(result.equals("<model name=Flotte>\n" + //
				"  <entity name=Satellite>\n" + //
				"    <attribute name=nom type=String/>\n" + //
				"    <attribute name=id type=Integer/>\n" + //
				"  </entity>\n" + //
				"</model>"));
		
		System.out.println("\nTest of the tool SerializerXML réussi !\n\n");
	}
	
	
	@Test
	void DeserializerTool() {
		System.out.println("\nTest of the tool : DeserializerXML");
		System.out.println("-----------------------------------\n");
		
		String specXML =
				"<model name=Flotte>\n" + //
				"  <entity name=Satellite>\n" + //
				"    <attribute name=nom type=String/>\n" + //
				"    <attribute name=id type=Integer/>\n" + //
				"  </entity>\n" + //
				"</model>";
		
		ArrayList<XMLBalise> balises = DeserializerXML.parse(specXML);
		ArrayList<MiniSpec> ms = DeserializerXML.deserializeInput(balises);

		// Create an instance of the tool  : SerializerXML
		SerializerXML s = new SerializerXML();
		

		for(MiniSpec m : ms) {
			m.accept(s);
		}
		
		String result = s.getResult();
		System.out.println(result);
		
		assertTrue(result.equals(specXML));
		System.out.println("\nTest of the tool DeserializerXML réussi !\n\n");
		
	}
	
	@Test
	void testRenamingTool() {
		System.out.println("\nTest of the tool : Renaming Tool");
		System.out.println("-----------------------------------\n");
		
		Model m = new Model("Flotte");
		Entity e = new Entity("Satellite");
		Attribute name = new Attribute("nom", "String");
		Attribute type = new Attribute("id", "Integer");
		
		e.getAttributes().add(name);
		e.getAttributes().add(type);
		m.getEntities().add(e);
		
		
		// Create an instance of the tool : RenamingTool
		RenamingTool rtool = new RenamingTool();
		
		rtool.putInMap("Flotte", "Planètes");
		rtool.putInMap("Satellite", "Mars");
		rtool.putInMap("id", "identifiant");
		rtool.rename(m);
		
		// Create an instance of the tool : PrettyPrinter
		PrettyPrinter pp = new PrettyPrinter();
		// Print after use of renamingTool
		m.accept(pp);		
		System.out.println(pp.getResult());
		
		
		assertTrue(m.getName().equals("Planètes") &&
				e.getName().equals("Mars") &&
				type.getName().equals("identifiant"));
		System.out.println("\nTest of the tool RenamingTool réussi !\n\n");
		
	}
	
	@Test
	public void associationSimple() {
		System.out.println("\nTest Association simple :");
		System.out.println("-----------------------------------\n");
		
		Model m = new Model("Flotte");
		Entity e1 = new Entity("Satellite");
		Entity e2 = new Entity("Flotte");
		Attribute name = new Attribute("nom", "String");
		Attribute id = new Attribute("id", "Integer");
		Attribute parent = new Attribute("parent", "Flotte");
		
		
		e1.getAttributes().add(name);
		e1.getAttributes().add(id);
		e1.getAttributes().add(parent);
		
		m.getEntities().add(e1);
		m.getEntities().add(e2);
		
		// Create an instance of the tool  : PrettyPrinter
		PrettyPrinter pp = new PrettyPrinter();
		
		m.accept(pp);
				
		String result = pp.getResult();
		System.out.println(result);
		
	
		assertTrue(result.equals("model Flotte;\n" + //
				"\t entity Satellite ;\n" + //
				"\t\t nom : String ;\n" + //
				"\t\t id : Integer ;\n" + //
				"\t\t parent : Flotte ;\n" + //
				"\t end_entity ;\n" + //
				"\t entity Flotte ;\n" + //
				"\t end_entity ;\n" + //
				"end_model;"));
		
		System.out.println("\nTest Association Simple réussi !\n\n");
	}
}

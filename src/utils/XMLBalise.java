package utils;

import java.util.ArrayList;
import java.util.HashMap;

public class XMLBalise {
	public String classMiniSpec;
	public ArrayList<XMLBalise> listBalise;
	public HashMap<String, String> attributes;
	
	public XMLBalise() {
		this("None", "None");
	}
	
	public XMLBalise(String classMiniSpec, String name) {
		this.classMiniSpec = classMiniSpec;
		listBalise = new ArrayList<XMLBalise>();
		attributes = new HashMap<String, String>();
	}
}

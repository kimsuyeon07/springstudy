package ex03_xml;

import java.util.HashMap;
import java.util.Map;

public class MapBean {
	
	// field (property)
	private Map<String, String> map;
	
	// constructor
	
	// method
	public void info() {
		for (Map.Entry<String, String> entry : map.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		/*
		 * Set<String> keys = map.keySey();
		 * for (String key: keys) {
		 * 		System.out.println(key + " : " + map.get(key));
		 * }
		 * 
		 * */
		
		
	}

	// Getter ans Setter
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
	
	
	
	

}

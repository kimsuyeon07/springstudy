package ex03_xml;

import java.util.Iterator;
import java.util.Set;

public class SetBean {

	
	
	// field (property)
	private Set<String> set;

	public Set<String> getSet() {
		return set;
	}

	// constructor

	
	// method
	public void info() {
		/* (동일한 결과를 가지고 온다)
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		*/
		for (String s: set) {
			System.out.println(s);
		}
	}
	
	
	// Getter and Setter
	public void setSet(Set<String> set) {
		this.set = set;
	}
		
	
	
	
	
	
	
}

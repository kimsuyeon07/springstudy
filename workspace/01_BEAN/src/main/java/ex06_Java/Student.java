package ex06_Java;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Student {

	
	// field
	private String name;
	private List<Integer> scores;
	private Set<String> volunteers;
	private Map<String, String> home;
	
	
	// method
	public void info() {
		System.out.println("이름: " + name);
		System.out.println("점수: " + scores.toString());
		System.out.println("봉사활동: " + volunteers.toString());
		for(Map.Entry<String, String> entry : home.entrySet()) {
			System.out.println("home's " + entry.getKey() + " : " + entry.getValue());
		}
	}


	// Getter and Setter
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public List<Integer> getScores() {
		return scores;
	}


	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}


	public Set<String> getVolunteers() {
		return volunteers;
	}


	public void setVolunteers(Set<String> volunteers) {
		this.volunteers = volunteers;
	}


	public Map<String, String> getHome() {
		return home;
	}


	public void setHome(Map<String, String> home) {
		this.home = home;
	}
	
	
	
	
	
	
	
}

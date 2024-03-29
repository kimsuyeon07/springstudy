package ex04_xml;

import java.util.List;

import ex04_xml.BMICalculator;

public class Member {
	

	
	// field (property)
	private String name;
	private double height;
	private double weight;
	private BMICalculator bmiCalculator;
	private List<String> services;	// 헬스장에 신청한 서비스들을 모아두었다
	
	
	// constructor
	
	// method
	public void info() {
		System.out.println("이름: " + name);
		System.out.println("키: " + height + "cm, 몸무게: " + weight + "kg");
		bmiCalculator.info(height, weight);
		System.out.println("서비스: "+ services.toString());
	}


	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	public double getHeight() {
		return height;
	}
	public void setHeight(double height) {
		this.height = height;
	}


	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}


	public BMICalculator getBmiCalculator() {
		return bmiCalculator;
	}
	public void setBmiCalculator(BMICalculator bmiCalculator) {
		this.bmiCalculator = bmiCalculator;
	}


	public List<String> getServices() {
		return services;
	}
	public void setServices(List<String> services) {
		this.services = services;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

package quiz01;

public class Person {
	
	private String name;
	private Car car;
	
	// constructor
	public Person() {}
	public Person(String name, Car car) {
		super();
		this.name = name;
		this.car = car;
	}
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	
	
	// method
	public void info() {
		System.out.println("이름: " + name + ", 모델: " + car.getModel() + ", 가격: " + car.getPrice() + "원");
	}
	
	
	
	
	
}

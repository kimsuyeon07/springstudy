package ex09_scope;

public class Person {
	
	// field
	private String name;
	private int age;
	
	
	// constructor
	public Person() {}
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	
	// method
	public void info() {
		System.out.println("이름: " + name + ", 나이: " + age + "살");
	}
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	
	

}

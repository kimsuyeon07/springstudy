package quiz04;

public class Student {

	
	// field
	private String name;
	private Lecture lecture;
	
	// constructor
	public Student() {}
	public Student(String name, Lecture lecture) {
		super();
		this.name = name;
		this.lecture = lecture;
	}
	
	// method
	public void info() {
		System.out.println("학생 명:" + name);
		System.out.println("강의 명:" + lecture.getName());
		System.out.println("교수 명:" + lecture.getProfessor());
		System.out.println();
	}
	
	
	
	// Getter and Setter
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Lecture getLecture() {
		return lecture;
	}
	public void setLecture(Lecture lecture) {
		this.lecture = lecture;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
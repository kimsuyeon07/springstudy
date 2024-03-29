package quiz03;

import java.util.List;

public class Exam {

	// field(property)
	private List<Integer> scores; // 5개의 점수
	private double average;
	private char grade;
	
	
	
	// method
	public void info() {
		setAverage();
		setGrade();
		System.out.println("average: " + scores.toString());
		System.out.println("scores: " + average);
		System.out.println("grade: " + grade);
	}
	public char grade() {
		char g = 'F';
		if (average >= 90) {
			g = 'A';
		} else if (average >= 80) {
			g = 'B';
		} else if (average >= 70) {
			g = 'C';
		} else if (average >= 60) {
			g = 'D';
		}
		return g;
	}
	

	
	// Getter and Setter
	public List<Integer> getScores() {
		return scores;
	}
	public void setScores(List<Integer> scores) {
		this.scores = scores;
	}
	public double getAverage() {
		return average;
	}
	public void setAverage(double average) {
		this.average = average;
	}
	
	public void setAverage() {
		int total = 0;
		for (Integer score: scores) {
			total += score;
		}
		this.average =  total / (double)scores.size();
	}
	
	public char getGrade() {
		return grade;
	}
	public void setGrade(char grade) {
		this.grade = grade;
	}
	public void setGrade() {
		if (average >= 90) this.grade = 'A';
		else if (average >= 80) this.grade = 'B';
		else if (average >= 70) this.grade = 'C';
		else if (average >= 60) this.grade = 'D';
		else this.grade = 'F';
		
		
	}
	
	
	
	
}

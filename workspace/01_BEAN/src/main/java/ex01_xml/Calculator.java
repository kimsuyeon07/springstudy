package ex01_xml;

public class Calculator {

	/* 일반 계산기 */
	
	// field (property)
	
	// constructor
	// 디폴트 타입
	public Calculator() {}
	
	// method
	public void add(int a, int b) {
		System.out.println(a + " + " + b + " = " + (a+b));
	}
	public void substract(int a, int b) {
		System.out.println(a + " - " + b + " = " + (a-b));
	}
	public void multiply(int a, int b) {
		System.out.println(a + " * " + b + " = " + (a*b));
	}
	public void divide(int a, int b) {
		System.out.println(a + " / " + b + " = " + ((double)a/b));
	}
	
}

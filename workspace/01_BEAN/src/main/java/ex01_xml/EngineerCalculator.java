package ex01_xml;

public class EngineerCalculator {
	
	// 공학용 계산기
	
	// field (property)
	private int num1;
	private int num2;
	private Calculator calculator;
	
	// constructor
	// 디폴트 생성자
	public EngineerCalculator() {}
	// 필드를 이용한 생성자
	public EngineerCalculator(int num1, int num2, Calculator calculator) {
		super();
		this.num1 = num1;
		this.num2 = num2;
		this.calculator = calculator;
	}
	
	// method
	
	// calculate : 연산기능
	public void add() {
		calculator.add(num1, num2);
	}
	public void substract() {
		calculator.substract(num1, num2);
	}
	public void multiply() {
		calculator.multiply(num1, num2);
	}
	public void divide() {
		calculator.divide(num1, num2);
	}
	
	
	
	
	// Getter and Setter
	public int getNum1() {
		return num1;
	}
	public void setNum1(int num1) {
		this.num1 = num1;
	}
	public int getNum2() {
		return num2;
	}
	public void setNum2(int num2) {
		this.num2 = num2;
	}
	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
	
}

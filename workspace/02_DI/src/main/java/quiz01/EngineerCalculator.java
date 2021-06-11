package quiz01;


import org.springframework.beans.factory.annotation.Autowired;

public class EngineerCalculator {

	
	// Field
	private Calculator calculator;
	
	
	// Constructor
	public EngineerCalculator() {}
	public EngineerCalculator(Calculator calculator) {
		super();
		this.calculator = calculator;
	}



	// method
	public void add(int a, int b) {
		calculator.add(a, b);
	}
	public void subtract(int a, int b) {
		calculator.subtract(a, b);
	}
	public void multiply(int a, int b) {
		calculator.multiply(a, b);
	}
	public void divide(int a, int b) {
		calculator.divide(a, b);
	}
	


	// Getter and Setter
	public Calculator getCalculator() {
		return calculator;
	}
	
	/* 의존성 디펜던스 : 자동 주입 */
	@Autowired
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
	
	
	
	
	
	
}

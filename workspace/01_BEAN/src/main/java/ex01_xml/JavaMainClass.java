package ex01_xml;

public class JavaMainClass {

	public static void main(String[] args) {
		
		// 개발자가 직접 new를 이용해서 객체(Bean)를 생성한다.
		
		// Calculator는 field가 없어서 저장할 데이터가 없다.
		Calculator calculator1 = new Calculator();
		Calculator calculator2 = new Calculator();
		
		
		// EngineerCalculator는 field가 있어서 객체에 데이터를 저장해야 한다.
		
		
		// 방법 1. 디폴트 생성자 + setter 
		EngineerCalculator engineerCalculator1 = new EngineerCalculator();
		engineerCalculator1.setNum1(7);
		engineerCalculator1.setNum2(2);
		engineerCalculator1.setCalculator(calculator1);
		
		// 방법 2. 필드를 이용한 생성자
		EngineerCalculator engineerCalculator2 = new EngineerCalculator(5, 2, calculator2);
		
		
		/* 연산 실행 */  
		engineerCalculator1.add();
		engineerCalculator1.substract();
		engineerCalculator1.multiply();
		engineerCalculator1.divide();
		
		engineerCalculator2.add();
		engineerCalculator2.substract();
		engineerCalculator2.multiply();
		engineerCalculator2.divide();
		
		
	}
}

package ex04_xml;

public class BMICalculator {


	// field (property)
	// private double low;	// 저체중 (low < 20), normal 기준으로 처리하므로 불필요하다. (굳이 저장하지 않아도 됨) 
	private double normal;  // 정상(20 <= normal < 25) , 20 저장
	private double over; 	// 과체중 (25 <= over < 30), 25 저장
	private double obesity; // 비만 (30 <= obesity), 30 저장
	private Calculator calculator;
	
	
	// constructor
	public BMICalculator() {}

	public BMICalculator(double normal, double over, double obesity, Calculator calculator) {
		super();
		this.normal = normal;
		this.over = over;
		this.obesity = obesity;
		this.calculator = calculator;
	}

	
	// method
	public void info(double height, double weight) {
		// 체질량지수(bmi) = 몸무게(kg) / 키(m)제곱   
		height = calculator.divide(height, 100); // 키: 180cm -> 1.8m 로 바꿔주는 작업 진행
		double bmi = calculator.divide(weight, calculator.multiply(height, height));
		
		String health = null;
		if (bmi >= obesity) {
			health = "비만";
		} else if (bmi >= over) {
			health = "과체중";
		} else if (bmi >= normal) {
			health = "정상";
		} else {
			health = "저체중";
		}
		
		System.out.println("체질량지수: " + bmi + "( " + health + " )");
	}
	
	
	// Getter and Setter
	public double getNormal() {
		return normal;
	}
	public void setNormal(double normal) {
		this.normal = normal;
	}

	public double getOver() {
		return over;
	}
	public void setOver(double over) {
		this.over = over;
	}

	public double getObesity() {
		return obesity;
	}
	public void setObesity(double obesity) {
		this.obesity = obesity;
	}

	public Calculator getCalculator() {
		return calculator;
	}
	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

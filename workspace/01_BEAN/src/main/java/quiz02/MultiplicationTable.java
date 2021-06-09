package quiz02;

import java.util.ArrayList;
import java.util.List;

public class MultiplicationTable {

	/* 원하는 구구단을 보여준다. */
	
	// field (property)
	private int startDan;
	private int endDan;
	private int StartNum;
	private int endNum;
	private Calculator calculator;
	
	// method
	public void gugudan() {
		
		for (int dan = startDan; dan <= endDan; dan++) {
			for (int n = StartNum; n <=endNum; n++) {
				System.out.println(dan + "X" + n + "=" + calculator.multiply(dan, n));
			}
			System.out.println();
		}
	}
	
	
	// Getter and Setter

	public int getStartDan() {
		return startDan;
	}

	public void setStartDan(int startDan) {
		this.startDan = startDan;
	}

	public int getEndDan() {
		return endDan;
	}

	public void setEndDan(int endDan) {
		this.endDan = endDan;
	}

	public int getStartNum() {
		return StartNum;
	}

	public void setStartNum(int startNum) {
		StartNum = startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public void setEndNum(int endNum) {
		this.endNum = endNum;
	}

	public Calculator getCalculator() {
		return calculator;
	}

	public void setCalculator(Calculator calculator) {
		this.calculator = calculator;
	}
	
	
	
	
	
}

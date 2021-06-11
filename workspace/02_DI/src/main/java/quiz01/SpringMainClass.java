package quiz01;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		EngineerCalculator ec = ctx.getBean("engineerCalculator", EngineerCalculator.class);
		ec.add(7, 2);
		ec.subtract(5, 1);
		ec.multiply(10, 2);
		ec.divide(9, 2);
		
		ctx.close();
		

	}

}

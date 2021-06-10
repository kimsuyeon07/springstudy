package ex06_Java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
	
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		Student student = ctx.getBean("student", Student.class);
		student.info();
		
		ctx.close();
		
		
		
		
		
	}
}

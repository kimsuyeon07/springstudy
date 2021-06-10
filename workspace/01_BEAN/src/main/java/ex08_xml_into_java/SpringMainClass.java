package ex08_xml_into_java;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
	
		Soldier s1 = ctx.getBean("soldier1", Soldier.class);
		Soldier s2 = ctx.getBean("soldier2", Soldier.class);
		
		s1.info();
		System.out.println();
		s2.info();
		
		ctx.close();
		
		
		
		
	}
}

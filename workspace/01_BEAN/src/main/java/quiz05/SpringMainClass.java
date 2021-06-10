package quiz05;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {


		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(AppContext.class);
		
		HoneyMoon h1 = ctx.getBean("honeyMoon1", HoneyMoon.class);
		HoneyMoon h2 = ctx.getBean("honeyMoon2", HoneyMoon.class);
		
		h1.info();
		h2.info();
		
		ctx.close();
		

	}

}

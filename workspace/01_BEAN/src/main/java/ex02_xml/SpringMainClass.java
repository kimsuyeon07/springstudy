package ex02_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {


		String resourceLocations = "app-context2.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		Car car = ctx.getBean("car", Car.class);
		car.info();
		
		
		
		
		
		
		
		
		
		
	}
}

package quiz01;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class QuizMainClass {

	public static void main(String[] args) {
		
		String resourceLocations = "classpath:quiz01.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		Person p1 = ctx.getBean("person1", Person.class);
		p1.info();
		Person p2 = ctx.getBean("person2", Person.class);
		p2.info();
		

	}
}

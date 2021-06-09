package quiz02;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class QuizMainClass {

	public static void main(String[] args) {


		String resourceLocations = "quiz02.xml";
		AbstractApplicationContext ctx = new GenericXmlApplicationContext(resourceLocations);
		
		MultiplicationTable gugudan = ctx.getBean("multiplicationTable", MultiplicationTable.class);
		gugudan.gugudan();
		

	}

}

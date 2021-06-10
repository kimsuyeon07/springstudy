package ex10_component;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context10.xml");
		
		Person person = ctx.getBean("person", Person.class);
		person.info();
		
		Book book = ctx.getBean("bk", Book.class);
		book.info();
		
		ctx.close();
		
		
		/*----------------------------------------------------------*/
		
		
		AbstractApplicationContext ctx2 = new AnnotationConfigApplicationContext(AppContext.class);
		
		Person person2 = ctx.getBean("person", Person.class);
		Book book2 = ctx.getBean("bk", Book.class);
		
		person2.info();
		book2.info();
		
		ctx2.close();
		
		
		
		

	}

}


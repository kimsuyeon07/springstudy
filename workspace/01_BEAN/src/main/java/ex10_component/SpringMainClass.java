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
		
		Person person2 = ctx2.getBean("person", Person.class);
		Book book2 = ctx2.getBean("bk", Book.class);
		Computer com1 = ctx2.getBean("com", Computer.class);
		Computer com2 = ctx2.getBean("com", Computer.class);
		
		person2.info();
		book2.info();
		com1.info();
		
		System.out.println(com1 == com2);
		
		ctx2.close();
		
		
		
		

	}

}


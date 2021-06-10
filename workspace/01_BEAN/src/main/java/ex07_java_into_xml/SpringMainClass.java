package ex07_java_into_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context7.xml");
		
		Book book1 = ctx.getBean("book1", Book.class);
		Book book2 = ctx.getBean("book2", Book.class);
		
		book1.info();
		System.out.println();
		book2.info();
		
		ctx.close();
		
		
		
	}

}

package ex04_xml;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class SpringMainClass {

	public static void main(String[] args) {
		
		AbstractApplicationContext ctx = new GenericXmlApplicationContext("app-context4.xml");
		
		Member member = ctx.getBean("member", Member.class);
		member.info();
		
		ctx.close();

	}

}

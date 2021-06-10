package ex10_component;

import org.springframework.stereotype.Component;




/*
 *	 	@Component
 * 		1. Bean으로 만들 클래스를 직접 Bean으로 생성해준다.
 * 		2. XML아나 Java로 별도 생성하지 않아도 생성된다. 
 * 		3. ComponentScan을 통해서 Component를 찾는 방식으로 동작한다.ㄴ
*/




@Component  // <bean id="person" class="Person" />  	
			// 		 >>>> bean의 id는 자동으로 부여된다. (클래스명의 첫 글자를 소문자로 변경)
public class Person {

	// field


	public void info() {
		System.out.println("나는 사람이다.");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}

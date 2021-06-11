package ex10_component;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component ("com")  // <bean id="com" class="Computer" />
@Scope("prototype") // getBean()으로 요청할때마다 새로운 Bean이 생성된다.
public class Computer {

	public void info() {
		System.out.println("나는 컴퓨터다.");
	}
	
	
}

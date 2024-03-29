package quiz05;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


@ImportResource("quiz05.xml")
@Configuration
public class AppContext {

	@Bean(name="husband1")
	public Person husband() {
		return new Person("브레드", "남자");
	}
	@Bean(name="wife1")
	public Person wife() {
		return new Person("나타샤", "여자");
	}
	
	@Bean(name="honeyMoon1")
	public HoneyMoon honeyMoon() {
		return new HoneyMoon("몰디브", husband(), wife());
	}
	
	
	
	
	
	
	
	
	
	
}

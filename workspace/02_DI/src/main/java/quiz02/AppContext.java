package quiz02;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	
	@Bean
	public Speaker speaker() {
		return new Speaker();
	}
	
	@Bean
	public TV tv() {
		return new TV();
	}
	
	
}

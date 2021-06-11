package quiz01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	@Bean
	public Calculator calculator() {
		return new Calculator();
	}
	
	@Bean
	public EngineerCalculator engineerCalculator() {
		
		// @Autowried ,@Inject, @Resource 를 사용하면
		// return new EnginnerCalculator( calculator() ); 를 하지 않아도 된다.
		
		return new EngineerCalculator();
	}
	
	
}

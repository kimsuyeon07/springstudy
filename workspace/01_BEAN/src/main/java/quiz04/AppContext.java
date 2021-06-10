package quiz04;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class AppContext {

	@Bean(name="lecture2")
	public Lecture l2() {
		return new Lecture("Java", "교수님2");
	}
	
	@Bean(name="student2")
	public Student s2() {
		return new Student("스미스", l2());
	}
	
	
}

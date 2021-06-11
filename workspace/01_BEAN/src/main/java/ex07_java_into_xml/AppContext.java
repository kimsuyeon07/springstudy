package ex07_java_into_xml;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	
	@Bean(name="publisher2")
	public Publisher p2() {
		return new Publisher("스프링출판사", "서울시 마포구");
	}
	
	@Bean(name="book2")
	public Book b2() {
		Book b = new Book();
		b.setTitle("흑설공주이야기");
		b.setPrice(20000);
		b.setPublisher(p2());
		
		return b;
	}
	
}
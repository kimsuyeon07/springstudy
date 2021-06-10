package ex06_Java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppContext {

	
	
	// <bean id="student" class="Student">	
	@Bean
	public Student student() {
		List<Integer> scores = new ArrayList<Integer>();
		scores.add(90);
		scores.add(80);
		scores.add(77);
		scores.add(95);
		
		Set<String> volunteers = new HashSet<String>();
		volunteers.add("고아원");
		volunteers.add("양로원");
		volunteers.add("유치원");
		
		Map<String, String> home = new HashMap<String, String>();
		home.put("PHONE", "010-1234-5678");
		home.put("ADDRESS", "서울시 마포구 공덕동");
		
		
		Student student = new Student();
		student.setName("데이빗");
		student.setScores(scores);
		student.setVolunteers(volunteers);
		student.setHome(home);
		
		return student;
		
	}
	
	
}

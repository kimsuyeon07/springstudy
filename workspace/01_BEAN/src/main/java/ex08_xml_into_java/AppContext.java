package ex08_xml_into_java;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;


/*
 *	app-context8.xml을 가져오는 방법
 * 	@ImportResource 애너테이션을 추가한다.
 * 	[작성방법]
 * 	@ImportResource("app-context8.xml")
*/


@ImportResource("app-context8.xml")
@Configuration
public class AppContext {

	@Bean(name="gun1")
	public Gun g1() {
		return new Gun("K2", 15);
	}
	@Bean(name="soldier1")
	public Soldier s1() {
		Map<String, String> army = new HashMap<String, String>();
		army.put("부대명", "충성부대");
		army.put("사단장", "포스타");
		army.put("부대위치", "강원도 철원군");
		
		return new Soldier("김상사", g1(), army);
	}
	
	
}

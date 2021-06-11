package ex01_inject;

import javax.inject.Inject;
import javax.inject.Named;


/*
 * 		@Inject
 * 		
 * 		1. 객체의 타입(<bean class="">)이 일치하는 객체를 자동으로 주입한다.
 * 		2. 필드, 생성자, setter를 대상으로 한다. 
 * 
*/


public class SelectListCommand {
	
	// 1. 필드를 이용해서 주입하기
	//@Inject
	//private Dao dao;
	
	
	
	/*
		2. 생성자를 이용해서 주입하기 (필드에 직접 주입하지 않는다)
		private Dao dao;
	
		@Inject
		public SelectListCommand(Dao dao) {  // 매개변수(Dao dao)에 주입된다.
			this.dao = dao;
		}
		
		>>>>>>
		(Dao dao)를 보고
		<bean id="dao" class="Dao" />를 찾아간다.
		↓
		" 타입이 일치하면, 클래스를 기반으로 알아서 찾아간다.(id까지 일치할 필요는 없다) "
		
		>>>>>>
		<bean id="dao1" class="Dao" />
		<bean id="dao2" class="Dao" />  일 때,
		↓
		" 2개를 찾았기 때문에 오류! "
		
	*/
	
	
	
	// 3. setter를 이용해서 주입하기
	private Dao dao;
	
	// Setter
	@Inject
	@Named("dao1")  // 동일한 <bean>이 있을 경우, 해당 코드 작성 __ "id"를 가지고 구분해준다.
	public void setDao(Dao dao) { // 매개변수(Dao dao)에 주입된다.
		this.dao = dao;
	}
	
	// Getter
	public Dao getDao() {
		return dao;
	}
	
	
	/*  
	 * 		inject가 정상적으로 주입되지 않았다면, 매개변수 (dao = null)을 반환한다.
	 * 		오류가 나면 execaption : Null..이 나오게 된다!
	 */ 
	
	
	// method
	public void execute() {
		dao.selectList();
		// dao가 자동으로 주입되지 않았다면 NullPointerExcaption이 떨어진다.
	}

	
	
	
	
	
	
	
	
	
}

package ex03_autowired;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/*
		@Autowired
		
		1. 객체의 타입(<bean class="">)이 일치하는 객체를 자동으로 주입한다.
 		2. 필드, 생성자, setter를 대상으로 한다. 
 		3. 별도의 디펜던시는 필요하지 않다.
		
 */

public class SelectListCommand {

	
	// (1) field :로  주입하기
	// @Autowired
	private Dao dao;

	
	// (2) Constructor :로 주입하기
	// @Autowired
	public SelectListCommand(Dao dao) {
		super();
		this.dao = dao;
	}

	public SelectListCommand() {}
	
	
	// *선호*
	// (3) Setter :로 주입하기	 
	@Autowired
	@Qualifier("boardDao1")  // <qualifier value="boardDao1" />
	public void setDao(Dao dao) {
		this.dao = dao;
	}
	
	
	// Getter
	public Dao getDao() {
		return dao;
	}
	
	
	// method
	public void execute() {
		dao.selectList();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

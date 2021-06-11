package ex02_resource;

import javax.annotation.Resource;

/*
 * 		@Resource
 * 	
 * 		1. 객체의 이름(<bean id="">)이 일치하는 객체를 자동으로 주입한다.
 * 		2. 
 * 
 * 
 */


public class SelectListCommand {

	/*
	// 1. field를 이용한 주입
	@Resource
	private Dao boardDao;
	/* <bean id="boardDao" class="ex02_resource.Dao" /> */
	
	
	// 2. Setter를 이용한 주입
	private Dao boardDao;
	
	@Resource
	public void setBoardDao(Dao boardDao) {
		this.boardDao = boardDao;
	}
	
	
	// Getter
	public Dao getBoardDao() {
		return boardDao;
	}


	// method
	public void execute() {
		boardDao.selectList();
	}
	
	
}

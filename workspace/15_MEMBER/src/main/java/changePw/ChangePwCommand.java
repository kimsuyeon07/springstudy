package changePw;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.member.command.MemberCommand;
import com.koreait.member.dao.MemberDAO;
import com.koreait.member.dto.Member;
import com.koreait.member.utill.SecurityUtils;

public class ChangePwCommand implements MemberCommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {

		Map<String, Object> map = model.asMap();
		HttpServletRequest request= (HttpServletRequest)map.get("request");
		String email = request.getParameter("email");
		String pw = request.getParameter("pw");
		
		Member member = new Member();
		member.setPw(SecurityUtils.encodeBase64(pw));
		member.setEmail(email);
		
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		memberDAO.changePw(member);
		
		
		

	}

}

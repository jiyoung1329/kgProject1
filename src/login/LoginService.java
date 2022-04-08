package login;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import main.CommonService;

public class LoginService {
	
	public LoginDTO loginProc(String id, String pw) { // 로그인컨트롤러에서 id,pw값을 받아와서 데이터베이스 접속실행
		LoginDAO loginDao = new LoginDAO();
		LoginDTO loginDto = loginDao.selectId(id); //loginDAO의 selectID메소드 실행하여 LoginDTO자료형에 값을 저장
		if(loginDto != null && loginDto.getPw().equals(pw)) {  //값이 있고 비밀번호가 같으면 LoginDTO자료형 값 반환
			return loginDto;
		}else {										// 값이 없을 경우 메시지와 함께 null값 반환
			CommonService.msg("로그인 실패");
			
			return null;
		}
		
	}

}

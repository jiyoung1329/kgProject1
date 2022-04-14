package login;

import common.CommonService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import room.choice.Status;


public class LoginService {
	
	private Status status; 
	
	public LoginDTO loginProc(String id, String pw) { //id필드와 pw필드의 값을 받아와 사용
		LoginDAO loginDao = new LoginDAO();

		LoginDTO loginDto = loginDao.selectId(id); //loginDAO클래스의 selectID메소드를 사용하여 LoginDTO자료형의 값을 가져옴
		if(loginDto != null && loginDto.getPw().equals(pw)) {  //LoginDTO자로형의 값이 존재하고 입력한 pw와 가져온 값의 pw가 같다면 LoginDTO자료형의 값을 받환
		
		status.setLoginDTO(loginDto);
			
			return loginDto;
		}else {										// 처음 가져온 LoginDTO 자료형의 값이 존재하지 않을경우 메시지 출력
			CommonService.msg("로그인 실패");
			
			return null;
		}
		
	}

}

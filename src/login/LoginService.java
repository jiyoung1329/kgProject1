package login;

import common.CommonService;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;


public class LoginService {
	
	public LoginDTO loginProc(String id, String pw) { // �α�����Ʈ�ѷ����� id,pw���� �޾ƿͼ� �����ͺ��̽� ���ӽ���
		LoginDAO loginDao = new LoginDAO();
		LoginDTO loginDto = loginDao.selectId(id); //loginDAO�� selectID�޼ҵ� �����Ͽ� LoginDTO�ڷ����� ���� ����
		if(loginDto != null && loginDto.getPw().equals(pw)) {  //���� �ְ� ��й�ȣ�� ������ LoginDTO�ڷ��� �� ��ȯ
			return loginDto;
		}else {										// ���� ���� ��� �޽����� �Բ� null�� ��ȯ
			CommonService.msg("로그인 실패");
			
			return null;
		}
		
	}

}

package register;


import common.CommonService;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginDTO;


public class RegisterService {
	
	public void regProc(Parent regForm) {
		TextField idField = (TextField) regForm.lookup("#regId"); // regForm에서 regId필드 찾음.
		PasswordField pwField = (PasswordField)regForm.lookup("#regPw"); // regForm에서 regPw필드 찾음.
		PasswordField confirmField = (PasswordField)regForm.lookup("#regConfirmPw"); // regForm에서 regConfirmPw�필드 찾음.
		TextField mobileField = (TextField) regForm.lookup("#regMobile"); // regForm에서 regMobile필드 찾음.
		 
		String id = idField.getText(); // 각 필드에서 값을 받아오는 변수
		String pw = pwField.getText();
		String confirm = confirmField.getText();
		String mobile = mobileField.getText();
		
		if(pw.equals(confirm)) { //비밀번호와 비밀번화 확인 값이 값을 경우
			RegDAO regDao = new RegDAO();
			//�ߺ�Ȯ�� ��ư?
			LoginDTO login = regDao.selectId(id); //id필드의 값으로 데이터베이스 확인
			if(login == null) { // id(pk)값이 중복되지 않을경우 RegDTO자료형 데이터 반환
				RegDTO regDto = new RegDTO();
				regDto.setId(id);
				regDto.setPw(pw);
				regDto.setMobile(mobile);
				regDao.insert(regDto); //RegDAO에서 insert메소드 실행
				
				CommonService.msg(id + "회원가입이 완료되었습니다.");
				CommonService.windowClose(regForm);
			}else { // id가 중복될경우
				CommonService.msg(id +"는 중복된 아이디입니다.");
				return;
			}
		}else { // 입력한 패스워드 값과 패스워드 확인 값이 다를경우
			CommonService.msg("입력한 비밀번호와 비밀번호 확인 값이 다릅니다.");
			pwField.clear();confirmField.clear();
			pwField.requestFocus();
		}
	}




}

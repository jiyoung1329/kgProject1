package register;


import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginDTO;
import main.CommonService;

public class RegisterService {
	
	public void regProc(Parent regForm) {
		TextField idField = (TextField) regForm.lookup("#regId"); // regForm에서 regId이름을 찾음
		PasswordField pwField = (PasswordField)regForm.lookup("#regPw"); // regForm에서 regPw이름을 찾음
		PasswordField confirmField = (PasswordField)regForm.lookup("#regConfirmPw"); // regForm에서 regConfirmPw이름을 찾음
		TextField mobileField = (TextField) regForm.lookup("#regMobile"); // regForm에서 regMobile이름을 찾음
		 
		String id = idField.getText(); // 필드에서 값을 가져옴
		String pw = pwField.getText();
		String confirm = confirmField.getText();
		String mobile = mobileField.getText();
		
		if(pw.equals(confirm)) { // 비밀번호 값과 비밀번호 확인 값이 같을 경우
			RegDAO regDao = new RegDAO();
			//중복확인 버튼?
			LoginDTO login = regDao.selectId(id); //id값으로 해당 회원이 존재하는지 검색
			if(login == null) { // id(pk)값이 존재하지 않을경우 RegDTO 자료형에 값을 저장
				RegDTO regDto = new RegDTO();
				regDto.setId(id);
				regDto.setPw(pw);
				regDto.setMobile(mobile);
				regDao.insert(regDto); // 해당 값으로 RegDAO의 insert메소드 실행
				
				CommonService.msg(id + "회원이 가입되었습니다.");
				CommonService.windowClose(regForm);
			}else { // id값이 존재할 경우 실행
				CommonService.msg(id +"는 중복된 아이디입니다.");
				return;
			}
		}else { // 비밀번호값과 비밀번호 확인 값이 다를 경우 실행
			CommonService.msg("입력한 비밀번호와 확인할 비밀번호가 다릅니다.");
			pwField.clear();confirmField.clear();
			pwField.requestFocus();
		}
	}




}

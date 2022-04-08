package register;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginDTO;
import main.CommonService;
import main.MainController;

public class RegController implements Initializable {
	@FXML
	private TextField regId;
	@FXML
	private Button regSame;
	@FXML
	private PasswordField regPw;
	@FXML
	private PasswordField regConfirmPw;
	@FXML
	private TextField regMobile;
	@FXML
	private Button registerButton;

	private RegisterService regSvc;
	private Parent regForm;

	public void setRegForm(Parent regForm) {
		this.regForm = regForm;
	}

	public void regCancelProc() {
		CommonService.windowClose(regForm);
	}

	public void regProc() {
		regSvc.regProc(regForm);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		registerButton.setDisable(true); //회원가입 버튼 비활성화
		regPw.setDisable(true);			// 비밀번호 필드 비활성화
		regConfirmPw.setDisable(true);  // 비밀번호 확인 필드 비활성화
		regMobile.setDisable(true);		// 전화번호 필드 비활성화
		regSame.setDisable(true);		// 중복확인 버튼 비활성화
		regSvc = new RegisterService();
		regId.textProperty().addListener((attribute, before, after) -> {
			idLengthCheck();	// 회원가입 ID길이 확인 8자제한
			if(regId.getText().length()>0) { //ID필드에 값이 있을 경우에만 중복확인 버튼 활성화
				regSame.setDisable(false);
			}
			emptyCheck();
		});
		regPw.textProperty().addListener((attribute, before, after) -> {
			pwLengthCheck(); //비밀번호 길이 확인 10자제한
			emptyCheck();
		});
		
		regMobile.textProperty().addListener((attribute, before, after) -> {
			emptyCheck(); // 전화번호필드까지 값이 있어야만 회원가입 버튼 활성화
		});
	}
	public void regSameProc() { //중복버튼 클릭시 실행되는 메소드

		RegDAO regDao = new RegDAO();
		LoginDTO login = regDao.selectId(regId.getText());
			if (login != null) {
				CommonService.msg("중복된 아이디입니다.");
				return;
			} else { //중복이 아닐 경우에만 비활성화된 필드들이 황성화로 변경
				CommonService.msg("회원가입이 가능한 아이디입니다.");
				regPw.setDisable(false);
				regConfirmPw.setDisable(false);
				regMobile.setDisable(false);
		}

	}

	public void idLengthCheck() {
		if (regId.getLength() > 8) {
			String tmp = regId.getText();
			tmp = tmp.substring(0, 8);
			regId.setText(tmp);
		}
	}

	public void pwLengthCheck() {
		if (regPw.getLength() > 10) {
			String tmp = regPw.getText();
			tmp = tmp.substring(0, 10);
			regPw.setText(tmp);
		}
	}
	public void mobileLengthCheck(){
			if (regMobile.getLength() > 10) {
				String tmp = regMobile.getText();
				tmp = tmp.substring(0, 10);
				regMobile.setText(tmp);
			}
		}
	

	public void emptyCheck() {
		if (regId.getText().isEmpty() || regPw.getText().isEmpty() || regConfirmPw.getText().isEmpty()
				|| regMobile.getText().isEmpty()) {
			registerButton.setDisable(true);
		} else {
			registerButton.setDisable(false);
		}
	}

}

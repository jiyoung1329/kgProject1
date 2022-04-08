package register;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import login.LoginDTO;
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
		registerButton.setDisable(true); //ȸ������ ��ư ��Ȱ��ȭ
		regPw.setDisable(true);			// ��й�ȣ �ʵ� ��Ȱ��ȭ
		regConfirmPw.setDisable(true);  // ��й�ȣ Ȯ�� �ʵ� ��Ȱ��ȭ
		regMobile.setDisable(true);		// ��ȭ��ȣ �ʵ� ��Ȱ��ȭ
		regSame.setDisable(true);		// �ߺ�Ȯ�� ��ư ��Ȱ��ȭ
		regSvc = new RegisterService();
		regId.textProperty().addListener((attribute, before, after) -> {
			idLengthCheck();	// ȸ������ ID���� Ȯ�� 8������
			if(regId.getText().length()>0) { //ID�ʵ忡 ���� ���� ��쿡�� �ߺ�Ȯ�� ��ư Ȱ��ȭ
				regSame.setDisable(false);
			}
			emptyCheck();
		});
		regPw.textProperty().addListener((attribute, before, after) -> {
			pwLengthCheck(); //��й�ȣ ���� Ȯ�� 10������
			emptyCheck();
		});
		
		regMobile.textProperty().addListener((attribute, before, after) -> {
			emptyCheck(); // ��ȭ��ȣ�ʵ���� ���� �־�߸� ȸ������ ��ư Ȱ��ȭ
		});
	}
	public void regSameProc() { //�ߺ���ư Ŭ���� ����Ǵ� �޼ҵ�

		RegDAO regDao = new RegDAO();
		LoginDTO login = regDao.selectId(regId.getText());
			if (login != null) {
				CommonService.msg("�ߺ��� ���̵��Դϴ�.");
				return;
			} else { //�ߺ��� �ƴ� ��쿡�� ��Ȱ��ȭ�� �ʵ���� Ȳ��ȭ�� ����
				CommonService.msg("ȸ�������� ������ ���̵��Դϴ�.");
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

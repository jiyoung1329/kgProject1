package login;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import main.MainController;
import login.LoginDTO;

public class LoginController implements Initializable {
	@FXML private TextField id;
	@FXML private PasswordField pw;
	@FXML private Button loginButton;
	@FXML private Button regButton;
	@FXML private AnchorPane anchor;
	
	private Parent loginForm;
	private LoginService loginSvc;
	private MainController mainController;
	
	
	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	



	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSvc = new LoginService();
		loginButton.setDisable(true);	// �⺻������ ��Ȱ��ȭ�Ǿ�����.
		id.textProperty().addListener((attribute, before, after)->{
			idLengthCheck();		// ���̵� ����üũ
			emptyCheck();			// ���̵��ʵ尡 ����ִ��� üũ
		});
		pw.textProperty().addListener((attribute,before,after)->{
			pwLengthCheck();		// ��й�ȣ ����üũ
			emptyCheck();			// ��й�ȣ�ʵ� ����ִ��� üũ
		});
		anchor.setOnKeyPressed(event->{	//enter�Է½� �α��� ����
			if(event.getCode().equals(KeyCode.ENTER)) {
				loginProc();
			}
		});
//		loginButton.setOnMousePressed(event->{ ��ư �簨 �����õ�
//			
//			loginButton.setStyle("-fx-padding : 15, 10, 10, 10");
//		});
//		loginButton.setOnMouseReleased(event->{
//			loginButton.setStyle("-fx-background-color: #FFB84D");
//			loginButton.setStyle("-fx-padding : 10, 10, 10, 10");
//		});
//		regButton.setOnMousePressed(event->{
//			regButton.setStyle("-fx-padding : 15, 10, 10, 10");
//		});
//		regButton.setOnMouseReleased(event->{
//			regButton.setStyle("-fx-background-color: FFB84D");
//			regButton.setStyle("-fx-padding : 10, 10, 10, 10");
//		});
	
	}
	
	public void loginProc() {
		LoginDTO loginDto = loginSvc.loginProc(id.getText(), pw.getText());
		if(loginDto != null) {					// id,pw�� �α��μ��񽺿� �����Ͽ� �����ͺ��̽� ���� �� loginDTO �ڷ��� ������ ��ȯ�Ͽ� null���� �ƴҰ��
			if(loginDto.getIsAdmin() == 1){		// �޾ƿ� �ڷῡ Admin �ڷᰡ 1�� ��� Admin �� ����
				mainController.open("Admin");
			}else {								// �� �̿ܿ��� ��� RoomChoice�� ����
			CommonService.windowClose(loginForm); // �α��� ������ �α��� �� ����
			mainController.open("RoomChoice");
			}
		}else {
			id.clear();pw.clear(); // ���� ȸ���� ��� �ʵ� Ŭ����,���̵��ʵ� ��Ŀ��
			id.requestFocus();
		}
		
	}
	
	public void regProc(){

		mainController.open("Register"); // ȸ�����Թ�ư Ŭ���� Register�� ����
	}
	
	public void adminOpen() {
		mainController.open("Admin");
	}
	
	public void idLengthCheck() {	//id�ʵ� ����üũ 8�ڸ��� ��������
		if(id.getLength() > 8){
			String tmp = id.getText();
			tmp = tmp.substring(0,8);
			id.setText(tmp);
		}
	}
	
	public void pwLengthCheck() {	//�н������ʵ� ����üũ 10�ڸ��� ��������
		if(pw.getLength() > 10){
			String tmp = pw.getText();
			tmp = tmp.substring(0,10);
			pw.setText(tmp);
		}
	}
	public void emptyCheck() {		// ID/PW �ʵ尡 ������� ��� �α��ι�ư ��Ȱ��ȭ, �� �ʵ� ��� ���� �־�߸� �α��ι�ư�� Ȱ��ȭ
		if (id.getText().isEmpty() || pw.getText().isEmpty()) {
			loginButton.setDisable(true);
		} else {
			loginButton.setDisable(false);
		}
	}
	
}

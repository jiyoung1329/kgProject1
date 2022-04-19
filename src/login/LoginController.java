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
	

	public void setId(TextField id) {
		this.id = id;
	}

	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		loginSvc = new LoginService();
		loginButton.setDisable(true);	// 로그인 버튼 비활성화
		id.textProperty().addListener((attribute, before, after)->{
			idLengthCheck();		// 길이 제한 메소드
			emptyCheck();			// 아이디필드가 비었는지 확인
		});
		pw.textProperty().addListener((attribute,before,after)->{
			pwLengthCheck();		// 길이 제한 메소드
			emptyCheck();			// 비밀번호 필드가 비었는지 확인, id,pw 모두 통과했을시 로그인 버튼 활성화
		});
		anchor.setOnKeyPressed(event->{	//enter키로 로그인 버튼 클릭가능
			if(event.getCode().equals(KeyCode.ENTER)) {
				loginProc();
			}
		});
	
	}
	
	public void loginProc() {
		LoginDTO loginDto = loginSvc.loginProc(id.getText(), pw.getText());
		if(loginDto != null) {					// 메인서비스에서 LoginDTO자료형으로 받아옮. null값이 아닐경우
			if(loginDto.getIsAdmin() == 1){		// 받아온 LoginDTO의 isAdmin 값이 1일경우 String "Admin"반환
				mainController.open("Admin");
			}else {								// isAdmin값이 1이 아닐경우 String "RoomChoice"반환
			CommonService.windowClose(loginForm); // 로그인메뉴 종료
			mainController.open("RoomChoice");
			}
		}else {
			id.clear();pw.clear(); // 받아온 값이 null일 경우 id,pw필드를 지우고 id필드 포커스
			id.requestFocus();
		}
		
	}
	
	public void regProc(){
		mainController.open("Register"); // 회원가입 버튼 클릭시 String "Register" 받환
	}
	
	public void idLengthCheck() {	//id 길이제한 8자
		if(id.getLength() > 8){
			String tmp = id.getText();
			tmp = tmp.substring(0,8);
			id.setText(tmp);
		}
	}
	
	public void pwLengthCheck() {	//비밀번호 길이제한 8자
		if(pw.getLength() > 10){
			String tmp = pw.getText();
			tmp = tmp.substring(0,10);
			pw.setText(tmp);
		}
	}
	public void emptyCheck() {		// ID/PW 필드에 값이 없으면 로그인버튼 비활성화, 모두 값이 있어야 로그인버튼 활성화
		if (id.getText().isEmpty() || pw.getText().isEmpty()) {
			loginButton.setDisable(true);
		} else {
			loginButton.setDisable(false);
		}
	}
	
}

package login;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import main.MainController;
import main.CommonService;
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
		loginButton.setDisable(true);	// 기본적으로 비활성화되어있음.
		id.textProperty().addListener((attribute, before, after)->{
			idLengthCheck();		// 아이디 길이체크
			emptyCheck();			// 아이디필드가 비어있는지 체크
		});
		pw.textProperty().addListener((attribute,before,after)->{
			pwLengthCheck();		// 비밀번호 길이체크
			emptyCheck();			// 비밀번호필드 비어있는지 체크
		});
		anchor.setOnKeyPressed(event->{	//enter입력시 로그인 실행
			if(event.getCode().equals(KeyCode.ENTER)) {
				loginProc();
			}
		});
//		loginButton.setOnMousePressed(event->{ 버튼 양감 구현시도
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
		if(loginDto != null) {					// id,pw를 로그인서비스에 전달하여 데이터베이스 접속 후 loginDTO 자료형 파일을 반환하여 null값이 아닐경우
			if(loginDto.getIsAdmin() == 1){		// 받아온 자료에 Admin 자료가 1일 경우 Admin 폼 실행
				mainController.open("Admin");
			}else {								// 그 이외에는 모두 RoomChoice폼 실행
			CommonService.windowClose(loginForm); // 로그인 성공시 로그인 폼 종료
			mainController.open("RoomChoice");
			}
		}else {
			id.clear();pw.clear(); // 없는 회원일 경우 필드 클리어,아이디필드 포커스
			id.requestFocus();
		}
		
	}
	
	public void regProc(){

		mainController.open("Register"); // 회원가입버튼 클릭시 Register폼 실행
	}
	
	public void idLengthCheck() {	//id필드 길이체크 8자리로 길이제한
		if(id.getLength() > 8){
			String tmp = id.getText();
			tmp = tmp.substring(0,8);
			id.setText(tmp);
		}
	}
	
	public void pwLengthCheck() {	//패스워드필드 길이체크 10자리로 길이제한
		if(pw.getLength() > 10){
			String tmp = pw.getText();
			tmp = tmp.substring(0,10);
			pw.setText(tmp);
		}
	}
	public void emptyCheck() {		// ID/PW 필드가 비어있을 경우 로그인버튼 비활성화, 두 필드 모두 값이 있어야만 로그인버튼이 활성화
		if (id.getText().isEmpty() || pw.getText().isEmpty()) {
			loginButton.setDisable(true);
		} else {
			loginButton.setDisable(false);
		}
	}
	
}

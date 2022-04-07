package main;

import javafx.scene.Parent;
import login.LoginController;
import register.RegController;

public class MainController { //각종 데이터를 보관함
	private MainService mainSvc;
	private Parent loginForm;
	private LoginController loginController;
	private Parent roomChoiceForm;
	private Parent regForm;
	private RegController regController;
	
	public MainController() {
		mainSvc = new MainService();
		mainSvc.setMainController(this);
		
	}

	public void setLoginController(LoginController loginController) {
		this.loginController = loginController;
		
	}
	
	public void setLoginForm(Parent loginForm) {
		this.loginForm = loginForm;
	}
	
	public void setRoomChoiceForm(Parent roomChoiceForm) {
		this.roomChoiceForm = roomChoiceForm;
	}
	
	public void setRegForm(Parent regForm) {
		this.regForm = regForm;
	}
	
	public void setRegController(RegController regController) {
		this.regController = regController;
		regController.setRegForm(regForm);
	}
	public Parent getRegForm() {
		return regForm;
	}
	
	public void open(String division) { //로그인 컨트롤러에서 division값을 전송해주면 그 값에 따라 메인서비스의 폼을 오픈하는 메소드를 실행하는 메소드
		if("RoomChoice".equals(division)) {
			mainSvc.rcOpen();
		}else if("Register".equals(division)) {
			mainSvc.regOpen();
		}else if("Admin".equals(division)) {
			System.out.println("관리자");
		}
	}
	

	
	

}

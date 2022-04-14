package main;

import admin.AdminController;
import javafx.scene.Parent;
import login.LoginController;
import login.LoginDTO;
import register.RegController;
import room.choice.RoomChoiceController;
import room.menu.RoomMenuController;

public class MainController { //메인, 로그인, 레지스터 정보 저장소
	private MainService mainSvc;
	private Parent loginForm;
	private LoginController loginController;
	private Parent roomChoiceForm;
	private Parent regForm;
	private RegController regController;
	private Parent adminForm;
	private AdminController adminController;
	
	//-------------------------
	
	
	//------------------------------
	private RoomChoiceController roomChoiceController;
	private Parent rcForm;
	
	public void setRoomChoiceController(RoomChoiceController roomChoiceController) {
		this.roomChoiceController = roomChoiceController;
		roomChoiceController.setRcForm(roomChoiceForm);
	}
	public void setRcForm(Parent rcForm) {
		this.rcForm = rcForm;
	}
	//-------------------------------
	
	
	
	
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
	
	public void open(String division) { //메인서비스에서 String값을 받아 각 메뉴를 오픈하는 메소드
		if("RoomChoice".equals(division)) {
			mainSvc.rcOpen();
		}else if("Register".equals(division)) {
			mainSvc.regOpen();
		}else if("Admin".equals(division)) {
			mainSvc.adminOpen();
		}
	}

	public void setAdminForm(Parent adminForm) {
		 this.adminForm = adminForm;
		
	}

	public void setAdminController(AdminController Admincontroller) {
		this.adminController = Admincontroller;
		Admincontroller.setAdminForm(adminForm);
	}
	

	
	

}

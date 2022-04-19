package room.menu;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import login.LoginController;
import login.LoginDAO;
import login.LoginDTO;
import login.LoginService;
import main.MainController;
import room.charge.ChargeController;
import room.choice.Status;

public class RoomMenuController implements Initializable{	//메뉴선택 페이지 controller
	private RoomMenuService roomMenuService;
	private Parent roomMenuForm;
	private Parent chargeForm;
	private ChargeController chargeController;
	private RoomMenuController roomMenuController;
	private Status status;
	private LoginDTO loginDTO;
	@FXML Label customerId;
	@FXML Label remainSongLabel;
	
	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}
	
	public void setRoomMenuController(RoomMenuController roomMenuController) {
		this.roomMenuController = roomMenuController;
	}	
	
	public void setChargeForm(Parent chargeForm) {
		this.chargeForm = chargeForm;
	}
	
	public void setChargeController(ChargeController chargeController) {
		this.chargeController = chargeController;
		chargeController.setChargeForm(chargeForm);
	}
	
	public void setRoomMenuForm(Parent roomMenuForm) {
		this.roomMenuForm = roomMenuForm;
	}
		
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		roomMenuService = new RoomMenuService();
		
		loginDTO = status.getLoginDTO();
		
		// id 설정
		customerId.setText(loginDTO.getId());
		
		// songCount 설정
		int tmp = loginDTO.getSongConut();
		remainSongLabel.setText(Integer.toString(tmp));
	}
	
	public void chargeProc() throws Exception{
		roomMenuService.chargeProc();
		CommonService.windowClose(roomMenuForm);
	}

	public void selectProc() throws Exception{
		roomMenuService.selectProc();
		CommonService.windowClose(roomMenuForm);
	}
	
	public void backProc() throws Exception{
		roomMenuService.backProc();
		CommonService.windowClose(roomMenuForm);
	}
	
	
	
}

package room.charge;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import login.LoginDAO;
import login.LoginDTO;
import room.choice.Status;
import room.menu.RoomMenuController;

public class ChargeController implements Initializable{
	
	@FXML private RadioButton price1;
	@FXML private RadioButton price2;
	@FXML private RadioButton price3;
	@FXML private RadioButton price4;
	@FXML private Button chargePayButton;
	@FXML private Button chargeCancelButton;
	@FXML private ToggleGroup priceGroup;
	@FXML private TextField roomMenuId;
	@FXML private TextField remainSong;
	private ChargeService chargeSvc;
	private Parent chargeForm;
	private Status status;
	private LoginDTO loginDTO;
	private LoginDAO loginDAO = new LoginDAO();
	private RoomMenuController roomMenuController;
	
	public void setRoomMenuController(RoomMenuController roomMenuController) {
		this.roomMenuController = roomMenuController;
	}
	
	public void remainSongInfo() {	//선택한 가격에 따른 정보전달
		ChargeDAO chargeDAO = new ChargeDAO();
		
		loginDTO = status.getLoginDTO();
		Integer selectReaminTest;
		int remainSong = loginDTO.getSongConut();
		int sum = 0;		
		
		if(price1.isSelected()) {
			selectReaminTest = 4;
		}else if(price2.isSelected()) {
			selectReaminTest=22;
		}else if(price3.isSelected()) {
			selectReaminTest=45;
		}else {
			selectReaminTest=250;
		}
		sum = selectReaminTest + remainSong;
		chargeDAO.remainSongUpdate(sum, loginDTO.getId());
	
		status.setLoginDTO(loginDAO.selectId(loginDTO.getId()));
		
	}
	
	public void setChargeForm(Parent chargeForm) {
		this.chargeForm = chargeForm;
	}
	
	public void chargeCancelProc() throws Exception {
		chargeSvc.chargeCancelProc();
		CommonService.windowClose(chargeForm);
		
	}
	
	public void chargePayProc() throws Exception{
		remainSongInfo();
		chargeSvc.chargePayProc();
		CommonService.windowClose(chargeForm);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		chargeSvc = new ChargeService();
		int tmp = 0;
		
		chargePayButton.setDisable(true);	// 가격선택했을때 결제창 활성화 & 선택한 가격 정보 받아오기
		priceGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {
				if (priceGroup.getSelectedToggle() != null) {				
					chargePayButton.setDisable(false);
				}
			}
		});
	}
}
	


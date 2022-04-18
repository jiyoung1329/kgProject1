package room.menu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import room.charge.ChargeController;
import room.choice.RoomChoiceController;
import room.select.SelectController;

public class RoomMenuService {
	
	private RoomMenuController roomMenuController;
	private ChargeController chargeController;
	private SelectController selectController;
	private RoomChoiceController roomChoiceController;
	
	public void setSelectController(SelectController selectController) {
		this.selectController = selectController;
	}
	
	public void setRoomMenuController(RoomMenuController roomMenuController) {
		this.roomMenuController = roomMenuController;
	}
	
	public void setChargeController(ChargeController chargeController) {
		this.chargeController = chargeController;
	}
	
	public void chargeProc() throws Exception {		// 충전페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/charge/chargeForm.fxml"));
		
		Parent chargeForm = loader.load();
		chargeController = loader.getController();
		chargeController.setChargeForm(chargeForm);
		
		Scene scene = new Scene(chargeForm);
		Stage stage = new Stage();
		
		stage.setTitle("충전하기");
		stage.setScene(scene);
		stage.show();
	}
	
	
	public void selectProc() throws Exception {		// 곡 개수 선택 페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/select/selectForm.fxml"));
		
		Parent selectForm = loader.load();
		selectController = loader.getController();
		selectController.setSelectForm(selectForm);
		
		Scene scene = new Scene(selectForm);
		Stage stage = new Stage();
		
		stage.setTitle("곡 개수 선택");
		stage.setScene(scene);
		stage.show();
	}
	
	public void backProc() throws Exception {	// 방 선택 페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/choice/rcForm.fxml"));
		
		Parent rcForm = loader.load();
		roomChoiceController = loader.getController();
		roomChoiceController.setRcForm(rcForm);
		
		Scene scene = new Scene(rcForm);
		Stage stage = new Stage();
		
		stage.setTitle("방 선택");
		stage.setScene(scene);
		stage.show();
	}

}

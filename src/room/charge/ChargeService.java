package room.charge;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import login.LoginDTO;
import room.choice.Status;
import room.menu.RoomMenuController;

public class ChargeService {
	
	private LoginDTO loginDTO;
	private Status status;
	
	public void chargePayProc() throws Exception{
		Button okButton = CommonService.msg2("선택하신 요금으로 결제됩니다");
		okButton.setOnAction(event -> {
			openRoomMenu();
		});
		
	}
	
	public void openRoomMenu() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/menu/rmForm.fxml"));
		
		try {
			Parent rmForm = loader.load();
			
			RoomMenuController roomMenuController = loader.getController();
			roomMenuController.setRoomMenuForm(rmForm);
			
			loginDTO = status.getLoginDTO();
			Label remainSongLabel =  (Label)rmForm.lookup("#remainSongLabel");
			
			int tmp = loginDTO.getSongConut();
			remainSongLabel.setText(Integer.toString(tmp));
			
			Scene scene = new Scene(rmForm);
			Stage stage = new Stage();
			
			stage.setTitle("메뉴선택");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {}
	}
	

	public void chargeCancelProc() throws Exception{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/menu/rmForm.fxml"));
		
		Parent rmForm = loader.load();
		
		RoomMenuController roomMenuController = loader.getController();
		roomMenuController.setRoomMenuForm(rmForm);

		
		Scene scene = new Scene(rmForm);
		Stage stage = new Stage();
		
		stage.setTitle("메뉴선택");
		stage.setScene(scene);
		stage.show();
	}
	
}

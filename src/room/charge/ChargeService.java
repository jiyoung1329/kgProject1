package room.charge;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import login.LoginDTO;
import room.choice.Status;
import room.menu.RoomMenuController;

public class ChargeService {
	
	private LoginDTO loginDTO;
	private Status status;
	
	public void chargePayProc() throws Exception{
		CommonService.msg("선택하신 요금으로 결제됩니다");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/menu/rmForm.fxml"));
		
		Parent rmForm = loader.load();
		
		RoomMenuController roomMenuController = loader.getController();
		roomMenuController.setRoomMenuForm(rmForm);
		
		loginDTO = status.getLoginDTO();
		Label remainSongLabel =  (Label)rmForm.lookup("#remainSongLabel");
		int tmp = loginDTO.getSongConut();
		String tmp1 = Integer.toString(tmp);
		remainSongLabel.setText(tmp1);
		
		Scene scene = new Scene(rmForm);
		Stage stage = new Stage();
		
		stage.setTitle("메뉴선택");
		stage.setScene(scene);
		stage.show();
	}
	
}

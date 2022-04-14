package room.choice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import login.LoginController;
import login.LoginDAO;
import login.LoginDTO;
import room.menu.RoomMenuController;


public class RoomChoiceService {	
	
	public void rmProc() throws Exception {		// 메뉴선택페이지로 이동
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

package roomChoice;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Controller;

public class RoomChoiceService {

	public void rmProc() throws Exception {		// 메뉴선택페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomMenu/rmForm.fxml"));
		
		Parent rmForm = loader.load();
		
		Scene scene = new Scene(rmForm);
		Stage stage = new Stage();
		
		stage.setTitle("메뉴선택");
		stage.setScene(scene);
		stage.show();

	}

	public void setController(Controller controller) {
		// 오류떠서 일단 만듬!ㄴ
		
	}
	
}

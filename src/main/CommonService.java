package main;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class CommonService {
	
	public static void msg(String content) {  // alert메소드를 실행 String값을 받아 String값을 alert창의 내용으로서 실행
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
	}
	
	public static void windowClose(Parent form) { //form값을 받아와서 form을 종료하는 메소드
		Stage stage = (Stage) form.getScene().getWindow();
		stage.close();
	}
	
}

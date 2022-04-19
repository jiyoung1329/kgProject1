package common;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class CommonService {
	
	public static void msg(String content) {  
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
		
		Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		okButton.setOnAction(event -> {
			System.out.println("ok");
		});
		
		
	}
	public static Button msg2(String content) {  
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
		
		Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		return okButton;
//		okButton.setOnAction(event -> {
//			System.out.println("ok");
//		});
		
		
	}
	
	public static void windowClose(Parent form) { 
		Stage stage = (Stage) form.getScene().getWindow();
		stage.close();
	}
	
}

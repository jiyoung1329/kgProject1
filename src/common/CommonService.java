package common;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class CommonService {
	
	public static void windowClose(Parent form) {
		Stage stage = (Stage)form.getScene().getWindow();
		stage.close();
	}

}

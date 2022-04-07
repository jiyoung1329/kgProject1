package charge;

import javafx.scene.Parent;
import javafx.stage.Stage;

public class ChargeCommonService {

	public void windowClose(Parent form) {
		Stage stage = (Stage) form.getScene().getWindow();
		stage.close();
	}
	
}

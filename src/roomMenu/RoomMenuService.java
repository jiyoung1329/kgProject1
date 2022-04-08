package roomMenu;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoomMenuService {
	
	public void chargeProc() throws Exception {		// 충전페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/charge/chargeForm.fxml"));
		
		Parent chargeForm = loader.load();
		
		Scene scene = new Scene(chargeForm);
		Stage stage = new Stage();
		
		stage.setTitle("가격선택");
		stage.setScene(scene);
		stage.show();
	}
	
//	public void chargeCancelProc(/*뭘 넣어야할지 thinking */) {
//		ChargeCommonService ccs = new ChargeCommonService();
//		ccs.windowClose(null/*뭘 넣어야할지 thinking */);
//	}
	
	public void selectProc() throws Exception {		// 곡 개수 선택 페이지로 이동
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/select/selectForm.fxml"));
		
		Parent selectForm = loader.load();
		
		Scene scene = new Scene(selectForm);
		Stage stage = new Stage();
		
		stage.setTitle("곡갯수선택??### 임시제목");
		stage.setScene(scene);
		stage.show();
	}
	
	public void backProc() throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomChoice/rcForm.fxml"));
		
		Parent rcForm = loader.load();
		
		Scene scene = new Scene(rcForm);
		Stage stage = new Stage();
		
		stage.setTitle("방 선택");
		stage.setScene(scene);
		stage.show();
	}

}

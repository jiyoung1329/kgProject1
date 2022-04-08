package room.choice;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RoomChoiceController implements Initializable {
	private RoomChoiceService rcs;
	private Parent imsi2;	// 임시 테스트

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rcs = new RoomChoiceService();
	}
	
	public void rmProc() throws Exception{
		rcs.rmProc();
//		CommonService.windowClose(imsi2);	//임시 테스트
	}


}

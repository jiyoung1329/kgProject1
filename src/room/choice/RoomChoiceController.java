package room.choice;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RoomChoiceController implements Initializable {
	private RoomChoiceService rcs;
	private Parent rcForm;
	
	public void setRcForm(Parent rcForm) {
		this.rcForm = rcForm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rcs = new RoomChoiceService();
	}
	
	public void rmProc() throws Exception{
		rcs.rmProc();
//		rcs.rmProc(rcForm);
		CommonService.windowClose(rcForm);
	}


}

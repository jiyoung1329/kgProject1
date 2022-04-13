package room.select;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class SelectController implements Initializable{
	
	private SelectService selectService; // 나중에 구현할때 필요
	private Parent selectForm;	// selectForm띄우는 거는 RoomMenuService에서
	
	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}
	
	public void setSelectForm(Parent selectForm) {
		this.selectForm = selectForm;
	}
	
	public void selectCancelProc() throws Exception {
		CommonService.windowClose(selectForm);
	}
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}

package roomMenu;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class RoomMenuController implements Initializable{		//메뉴선택 페이지 controller
	private RoomMenuService rs;
	private Parent imsi; //꺼지긴 하는데 NullPoint에러

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rs = new RoomMenuService();		
	}
	
	public void chargeProc() throws Exception{
		rs.chargeProc();
	}

	public void selectProc() throws Exception{
		rs.selectProc();
	}
	
	public void backProc() throws Exception{
		rs.backProc();
		CommonService.windowClose(imsi); //꺼지긴 하는데 NullPoint에러
	}
	
}

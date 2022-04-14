package room.select;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import song.SongController;

public class SelectController implements Initializable{
	
	@FXML private Label roomNumber;
	@FXML private Label remainCount;
	@FXML private Label songCount;
	
	@FXML private Button selectButton;
	@FXML private Button cancelButton;
	@FXML private Button plusButton;
	@FXML private Button minusButton;
	
	private SelectService selectService; // 나중에 구현할때 필요
	private Parent selectForm;	// selectForm띄우는 거는 RoomMenuService에서
	
	// 노래방화면 controller
	private SongController songController;
	
	
	public SongController getSongController() {
		return songController;
	}

	public void setSongController(SongController songController) {
		this.songController = songController;
	}

	public void setSelectForm(Parent selectForm) {
		this.selectForm = selectForm;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectService = new SelectService();
		selectService.setSelectController(this);
		
	}
	

	public void countMinusProc() {
		int count = Integer.parseInt(songCount.getText());
		if (count == 0) CommonService.msg("0개 이하의 곡은 선택할 수 없습니다.");
		else songCount.setText(Integer.toString(--count));
	}
	public void countPlusProc() {
		int count = Integer.parseInt(songCount.getText());
		int remain = Integer.parseInt(remainCount.getText());
		if (count == remain) CommonService.msg(remain + "개 이상의 곡은 선택할 수 없습니다.");
		else songCount.setText(Integer.toString(++count));
	}
	
	public void selectProc() {
		int room = Integer.parseInt(roomNumber.getText());
		int count = Integer.parseInt(songCount.getText());
		selectService.saveSongCount(selectForm);
		
	}
	
	
	public void selectCancelProc() throws Exception {
		CommonService.windowClose(selectForm);
	}
	


}

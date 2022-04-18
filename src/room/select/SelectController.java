package room.select;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import login.LoginDAO;
import login.LoginDTO;
import room.choice.Status;
import song.SongController;

public class SelectController implements Initializable{
	
	@FXML private Label roomNumber;
	@FXML private Label remainCount;
	@FXML private Label songCount;
	
	@FXML private Button selectButton;
	@FXML private Button cancelButton;
	@FXML private Button plusButton;
	@FXML private Button minusButton;
	
	//###220414
	private SelectDTO selectDTO;
	private SelectDAO selectDAO;
	
	//###220414
	
	
	//DB에 있는 남은곡수 받아오기 위해 호출
	private Status status;
	private LoginDTO loginDTO;
	
	
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
	
	public void setLoginDTO(LoginDTO loginDTO) {
		this.loginDTO = loginDTO;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectService = new SelectService();
		selectService.setSelectController(this);
		
		//DB에 있는 남은곡수 전달
		loginDTO = status.getLoginDTO();
		
		LoginDAO loginDao = new LoginDAO();
		LoginDTO login= loginDao.selectId(loginDTO.getId());
		
		int tmp = login.getSongConut();
		String tmp1 = Integer.toString(tmp);	//문자열로 변환
		remainCount.setText(tmp1);
		
		//DB에 있는 방번호 전달
		selectDTO = status.getSelectDTO();
		
		SelectDAO selectDAO = new SelectDAO();
		SelectDTO selectRoomNumber = selectDAO.selectNum(selectDTO.getNum());
		SelectDTO test = selectDAO.selectNum(selectDTO.getNum());
		
		int room = test.getNum();
		String room1 = Integer.toString(room);
		roomNumber.setText(room1);
		
	}
	

	public void countMinusProc() {
		int count = Integer.parseInt(songCount.getText());
		if (count == 1) CommonService.msg("1곡 이상 불러주세요");
		else songCount.setText(Integer.toString(--count));
	}
	public void countPlusProc() {
		int count = Integer.parseInt(songCount.getText());
		int remain = Integer.parseInt(remainCount.getText());
		if (count == remain) CommonService.msg(remain + "개 이상의 곡은 선택할 수 없습니다.");
		else songCount.setText(Integer.toString(++count));
	}
	
	public void selectProc() {
		String room = roomNumber.getText();
		if (selectService.checkRoomReserve(room)) {
			selectService.saveSongCount(selectForm);
			SelectDAO imsi = new SelectDAO();
			imsi.update(status.getSelectDTO());//@@
			status.setSelectDTO(imsi.selectNum(status.getSelectDTO().getNum()));
			//업데이트된 reservation
			//System.out.println(status.getSelectDTO());
		} else {
			CommonService.msg("이미 예약된 방입니다.");
		}
		
	}
	
	public void testResv() {
		String room = roomNumber.getText();
		if (selectService.checkRoomReserve(room)) {
			selectService.saveSongCount(selectForm);
			selectDAO.update(selectDTO);
		}
	}
	
	
	public void selectCancelProc() throws Exception {
		CommonService.windowClose(selectForm);
	}
	


}

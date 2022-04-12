package song;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import song.search.SongSearchController;

public class SongController implements Initializable{
	private Parent songForm;
	private Parent searchForm;
	private SongService songSvc;
	private SongDTO songDto;
	private SongSearchController songSearchController;
	
	@FXML private Label num1;
	@FXML private Label num2;
	@FXML private Label num3;
	@FXML private Label num4;
	@FXML private Label num5;
	@FXML private Label num6;
	
	public SongSearchController getSongSearchController() {
		return songSearchController;
	}

	public void setSongSearchController(SongSearchController songSearchController) {
		this.songSearchController = songSearchController;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songSvc = new SongService();
		songSvc.setSongController(this);
		
	}
	
	public void setSong(Parent songForm) {
		this.songForm = songForm;
	}
	
	public void setSongSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
	}
	
	//SongDB 내 Count int +1 등록, 예약곡 리스트 첫번째 곡 삭제, 남은 곡 수 감소, SongDB 내 동영상 MediaView로 재생
	public void songStartProc() {
//		songSvc.reservSongReg();
	}
	
	//노래 검색창 오픈
	public void songSearch() {
		songSvc.songSearchOpen();
		
	}
	
	//예약된 노래번호 띄우기
	public void reserveSong(SongDTO songDTO) {
		
	}
	


}

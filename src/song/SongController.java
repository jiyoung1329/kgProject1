package song;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class SongController implements Initializable{
	private SongService songSvc;
	private Parent songForm;
	private Parent songSearchForm;
	private Parent select;
	private SongDTO songDto;
	
	
	public SongController() {
		songSvc = new SongService();
		songSvc.setSongController(this);
	}
	
	public void setSongForm(Parent songForm) {
		this.songForm = songForm;
	}
	
	public void setSongSearchForm(Parent songSearchForm) {
		this.songSearchForm = songSearchForm;
	}
	
	//재생되고 있는 MediaView 비활성화 
	public void songCacelProc() {
		
	} 
	
	//SongDB 내 Count int +1 등록, 예약곡 리스트 첫번째 곡 삭제, 남은 곡 수 감소, SongDB 내 동영상 MediaView로 재생
	public void songStartProc() {
		songSvc.reservSongReg();
	}
	
	//노래 검색창 오픈
	public void songSearch() {
		songSvc.songSearchOpen();
	}
	
	


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songSvc = new SongService();
		
	}


}

package song;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.Parent;
import song.search.SongSearchController;

public class SongController implements Initializable{
	private Parent songForm;
	private Parent searchForm;
	private SongService songSvc;
	private SongDTO songDto;
	private SongSearchController songSearchController;
	
	public SongSearchController getSongSearchController() {
		return songSearchController;
	}

	public void setSongSearchController(SongSearchController songSearchController) {
		this.songSearchController = songSearchController;
	}

	public SongController() {
		songSvc = new SongService();
		songSvc.setSongController(this);
	}
	
	public void setSong(Parent songForm) {
		this.songForm = songForm;
	}
	
	public void setSongSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
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

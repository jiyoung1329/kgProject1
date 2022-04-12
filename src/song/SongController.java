package song;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CommonService;
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
	private ArrayList<SongDTO> songNumber = new ArrayList<SongDTO>();
	
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
		//songSvc.reservSongReg();
	}
	
	//노래 검색창 오픈
	public void songSearch() {
		songSvc.songSearchOpen();
		
	}
	
	//예약된 노래번호 띄우기
	public void reserveSong(SongDTO songDTO) {
		
		if(songNumber.size() >= 6) {
			CommonService.msg("곡을 더 이상 추가할 수 없습니다.");
		}else {
			songNumber.add(songDTO);
			num1.setText(songNumber.get(0).getSongNum());
			
			try {
				num1.setText(songNumber.get(0).getSongNum());
				num2.setText(songNumber.get(1).getSongNum());
				num3.setText(songNumber.get(2).getSongNum());
				num4.setText(songNumber.get(3).getSongNum());
				num5.setText(songNumber.get(4).getSongNum());
				num6.setText(songNumber.get(5).getSongNum());
			} catch(Exception e) {}
		}
		
	}
	
	// 남은 곡 디카운팅
	


}

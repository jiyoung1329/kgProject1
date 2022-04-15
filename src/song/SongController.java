package song;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import song.search.SongSearchController;

public class SongController implements Initializable{
	private Parent songForm;
	private Parent searchForm;
	private SongService songSvc;
	private SongDTO songDto;
	private SongSearchController songSearchController;
	private ArrayList<SongDTO> songNumber = new ArrayList<SongDTO>();
	private int count;
	private String room;
	private MediaPlayer mediaPlayer;
	private boolean endOfMedia;
	
	@FXML private Label num1;
	@FXML private Label num2;
	@FXML private Label num3;
	@FXML private Label num4;
	@FXML private Label num5;
	@FXML private Label num6;
	@FXML private Label remainSong;
	@FXML private MediaView songMedia;
	@FXML private ImageView songDefault;
	
	
	public SongSearchController getSongSearchController() {
		return songSearchController;
	}

	public void setSongSearchController(SongSearchController songSearchController) {
		this.songSearchController = songSearchController;
	}
	
	public void setSong(Parent songForm) {
		this.songForm = songForm;
	}
	
	
	public Parent getSongForm() {
		return songForm;
	}

	public void setSongSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
	}
	
	public void setCount(String count) {
		this.count = Integer.parseInt(count);
		remainSong.setText(count);
	}

	public void setRoomNumber(String room) {
		this.room = room;
		songSvc.roomReserve(room);
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songSvc = new SongService();
		songSvc.setSongController(this);
		songSvc.roomReserve(room);
		
		mediaPlayer = null;
		
		if (mediaPlayer != null) {
			mediaPlayer.setOnEndOfMedia(()-> {
				endOfMedia = true;
				if(endOfMedia) {
					songDefault.setOpacity(100);
					if(count == 0) {
						songOutProc();
					}
				}
			});
		}
		
		
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
	
	// 시작 버튼 누를 때
	public void songStartProc() {
		
		// 미디어재생의 끝(endOfMedia = ture)일 때만 시작버튼 구
		if(endOfMedia) {			
			if(songNumber.size() == 0) {
				// 예약된 곡이 0일 때
				CommonService.msg("먼저 곡을 예약해 주세요");
			}else {
				// SongDB 내 곡의 재생횟수 +1 시키기
				songDto = songSvc.songPlay(songNumber.get(0).getSongNum());
				
				//MediaView재생
				String url = getClass().getResource(songDto.getSongLink()).toString();
				if(url != null) {
					songDefault.setOpacity(0);
					Media media = new Media(url);
					mediaPlayer = new MediaPlayer(media);
					songMedia.setMediaPlayer(mediaPlayer);
					songMedia.setPreserveRatio(false);
					mediaPlayer.play();
				}
				
				// 남은 곡 수 숫자 업데이트
				remainSong.setText(Integer.toString(--count));
				
				// 첫 번째 예약곡 지우기
				songNumber.remove(0);
				
				// 예약곡 리스트 업데이트(기존 데이터 지우고 새로운 데이터 입력)
				num1.setText("");num2.setText("");num3.setText("");
				num4.setText("");num5.setText("");num6.setText("");
				
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
		
	}
	
	// 취소 버튼 누를 때 
	public void songCancelProc() {
		// 미디어 재생 멈추기
		mediaPlayer.pause();
		
		// 대기화면 불러오기
		songDefault.setOpacity(100);
		
		//시작버튼 활성화 조건 세팅하기
		endOfMedia = true;
		
		// 남은 곡 수가 0일 때 방 사용여부 가능으로 바꾸고 창 모두 닫기
		if(count == 0) {
			songSvc.roomAvailable(room);
			CommonService.msg("노래방이 종료됩니다.");
			
			try {
			Thread.sleep(1000);
			}catch(Exception e) {}
			
			// 창 모두 닫기
			if(songForm != null) CommonService.windowClose(songForm);
			if(searchForm != null) CommonService.windowClose(searchForm);
		}		
	}
	
	// 나가기 버튼 누를 때
	public void songOutProc() {
		// 방 사용여부 가능으로 바꾸기
		songSvc.roomAvailable(room);
		
		// 창 모두 닫기
		if(songForm != null) CommonService.windowClose(songForm);
		if(searchForm != null) CommonService.windowClose(searchForm);
	}



}

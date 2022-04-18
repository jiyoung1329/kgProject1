package song;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.media.Media;
import javafx.scene.media.MediaMarkerEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;
import song.remotecontrol.RemoteControlController;
import song.search.SongSearchController;

public class SongController implements Initializable{
	@FXML private Label num1;
	@FXML private Label num2;
	@FXML private Label num3;
	@FXML private Label num4;
	@FXML private Label num5;
	@FXML private Label num6;
	@FXML private Label remainSong;
	@FXML private Label resNumTitle;
	@FXML private Label resNum;
	@FXML private MediaView songMedia;
	@FXML private ImageView songDefault;

	private Parent songForm;
	private Parent searchForm;
	private Parent remoteForm;
	private Parent chartForm;
	
	private SongService songSvc;
	private SongDTO songDto;
	private SongSearchController songSearchController;
	private RemoteControlController remoteController;
	
	private ArrayList<SongDTO> songNumber = new ArrayList<SongDTO>();
	private int count = 1;
	private String room;
	private MediaPlayer mediaPlayer;
	private boolean endOfMedia;
	

	public SongService getSongSvc() {
		return songSvc;
	}

	public void setSongSvc(SongService songSvc) {
		this.songSvc = songSvc;
	}

	public SongSearchController getSongSearchController() {
		return songSearchController;
	}

	public void setSongSearchController(SongSearchController songSearchController) {
		this.songSearchController = songSearchController;
	}
	
	
	public RemoteControlController getRemoteController() {
		return remoteController;
	}

	public void setRemoteController(RemoteControlController remoteController) {
		this.remoteController = remoteController;
	}

	public Parent getChartForm() {
		return chartForm;
	}

	public void setChartForm(Parent chartForm) {
		this.chartForm = chartForm;
	}

	public void setSong(Parent songForm) {
		this.songForm = songForm;
	}
	
	public Parent getSongForm() {
		return songForm;
	}

	public Parent getRemoteForm() {
		return remoteForm;
	}

	public void setRemoteForm(Parent remoteForm) {
		this.remoteForm = remoteForm;
	}

	public void setSongSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
	}
	
	
	public Label getResNum() {
		return resNum;
	}

	public void setResNum(Label resNum) {
		this.resNum = resNum;
	}

	public void setCount(String count) {
		this.count = Integer.parseInt(count);
		remainSong.setText(count);
	}

	public void setRoomNumber(String room) {
		this.room = room;
		songSvc.roomReserve(room);
	}
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	// 취소 버튼 눌렀을떄
	public void setEndOfMedia(boolean endOfMedia) {
		this.endOfMedia = endOfMedia;
	}
	
	// 미디어가 끝났을때
	public void setEndOfMedia2(boolean endOfMedia) {
		this.endOfMedia = endOfMedia;
		if(endOfMedia) {
			// 대기화면 띄우기
			songDefault.setOpacity(100);
			// 남은곡이 0일때 노래방 나가기
			if (count <= 0) {
				exit();
				return;
			}
			// 자동재생
			if(songNumber.size() != 0) {
				sleep(1000);
				songMediaView();
			}
		}
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songSvc = new SongService();
		songSvc.setSongController(this);
		songSvc.roomReserve(room);
		endOfMedia = true;
		
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
			
			insertReserveSong();
		}
		
	}
	
	// 우선 예약 기능
	public void primaryReserveSong(SongDTO songDTO) {
		if(songNumber.size() >= 6) {
			CommonService.msg("곡을 더 이상 추가할 수 없습니다.");
		}else {
			songNumber.add(0, songDTO);
			
			insertReserveSong();
		}
	}
	
	// 시작 버튼 누를 때
	public void songStartProc() {
		
		if(endOfMedia) {
			
			if(songNumber.size() == 0) {
				// 예약된 곡이 0일 때
				CommonService.msg("먼저 곡을 예약해 주세요");
			}else {
				//MediaView재생
				songMediaView();
				
				// 남은 곡 수 숫자 업데이트
				remainSong.setText(Integer.toString(--count));
				
				// 첫 번째 예약곡 지우기
				songNumber.remove(0);
				
				// 예약곡 리스트 업데이트(기존 데이터 지우고 새로운 데이터 입력)
				num1.setText("");num2.setText("");num3.setText("");
				num4.setText("");num5.setText("");num6.setText("");
				
				insertReserveSong();
				
			}
			
		} 
		
	}
	
	// 취소 버튼 누를 때 
	public void songCancelProc() {
		if (mediaPlayer != null) {
			// 미디어 재생 멈추기
			mediaPlayer.pause();
			
			// 대기화면 불러오기
			songDefault.setOpacity(100);
			
			//시작버튼 활성화 조건 세팅하기
			setEndOfMedia(true);
			
			// 남은 곡 수가 0일 때 방 사용여부 가능으로 바꾸고 창 모두 닫기
			if(count <= 0) {
				exit();
			}		
			
		}
	}
	
	// 나가기 버튼 누를 때
	public void songOutProc() {
		exit();
	}
	
	
	// 노래 일시정지
	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}
	
	// 노래 일시정지 해제
	public void pauseCancel() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}
	
	// 노래 마디점프
	public void madiJump() {
		if (mediaPlayer != null) {
			System.out.println(mediaPlayer.getStatus().toString());
			if (mediaPlayer.getStatus().toString().equals("PLAYING")) {
				Duration now = mediaPlayer.getCurrentTime();
				Duration later = new Duration(3000);
				mediaPlayer.seek(now.add(later));
				
			}
		}
		
	}
	
	// 노래 예약 취소
	public void cancelReserve() {
		
		if (songNumber.size() > 0) {
			// 첫 번째 예약곡 지우기
			songNumber.remove(0);
			
			// 예약곡 리스트 업데이트(기존 데이터 지우고 새로운 데이터 입력)
			num1.setText("");num2.setText("");num3.setText("");
			num4.setText("");num5.setText("");num6.setText("");
			
			insertReserveSong();
			
		} else {
			CommonService.msg("예약된 곡이 없습니다.");
		}
		
	}
	
	public void exit() {
		songSvc.roomAvailable(room);
		CommonService.msg("노래방이 종료됩니다.");
		
		sleep(1000);

		closeForm();
	}
	
	
	public void closeForm() {
		// 창 모두 닫기
		if(songForm != null) CommonService.windowClose(songForm);
		if(searchForm != null) CommonService.windowClose(searchForm);
		if(remoteForm != null) CommonService.windowClose(remoteForm);
		if(chartForm != null) CommonService.windowClose(chartForm);

	}
	
	public void sleep(int time) {
		try {
			Thread.sleep(time);
		} catch(Exception e) {}
	}
	
	public void insertReserveSong() {
		
		try {
			num1.setText(songNumber.get(0).getSongNum());
			num2.setText(songNumber.get(1).getSongNum());
			num3.setText(songNumber.get(2).getSongNum());
			num4.setText(songNumber.get(3).getSongNum());
			num5.setText(songNumber.get(4).getSongNum());
			num6.setText(songNumber.get(5).getSongNum());
		} catch(Exception e) {}
	}
	
	public void songMediaView() {
		// songDB count +1
		songDto = songSvc.songPlay(songNumber.get(0).getSongNum());
		// MediaView 재생
		String url = getClass().getResource(songDto.getSongLink()).toString();
		if(url != null) {
			songDefault.setOpacity(0);
			Media media = new Media(url);
			mediaPlayer = new MediaPlayer(media);
			songMedia.setMediaPlayer(mediaPlayer);
			songMedia.setPreserveRatio(false);
			mediaPlayer.play();
			endOfMedia = false;
			
			mediaPlayer.currentTimeProperty().addListener((obs,oldV,newV) -> {
				Double now = mediaPlayer.getCurrentTime().toSeconds();
				Double end = mediaPlayer.getTotalDuration().toSeconds();

				if((now/end) >= 0.99) {
					setEndOfMedia2(true);
				}
				
			});
		
		}
	}




}

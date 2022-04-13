package song;

import java.net.URL;
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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songSvc = new SongService();
		songSvc.setSongController(this);
		count = 5;
		remainSong.setText(Integer.toString(count));
		
		mediaPlayer = null;
		if (mediaPlayer != null) {
			mediaPlayer.setOnEndOfMedia(()-> {
				endOfMedia = true;
				if(endOfMedia) {
					songDefault.setOpacity(100);
					if(count == 0) {
						// 방DB 방 사용여부 변경 메서드
					}
				}
			});
		}
		
		
	}
	
	public void setSong(Parent songForm) {
		this.songForm = songForm;
	}
	
	public void setSongSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
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
	
	//SongDB 내 Count int +1 등록, 예약곡 리스트 첫번째 곡 삭제, 남은 곡 수 감소, SongDB 내 동영상 MediaView로 재생
	public void songStartProc() {
		
		// 남은 곡 수 감소 및 예약곡 리스트 업데이트
		if(songNumber.size() == 0) {
			CommonService.msg("먼저 곡을 예약해 주세요");
		}else {
			remainSong.setText(Integer.toString(--count));
			//System.out.println(count);
			//System.out.println(songNumber.get(0).getSongNum());
			songNumber.remove(0);
			num1.setText("");
			num2.setText("");
			num3.setText("");
			num4.setText("");
			num5.setText("");
			num6.setText("");
			try {
				num1.setText(songNumber.get(0).getSongNum());
				num2.setText(songNumber.get(1).getSongNum());
				num3.setText(songNumber.get(2).getSongNum());
				num4.setText(songNumber.get(3).getSongNum());
				num5.setText(songNumber.get(4).getSongNum());
				num6.setText(songNumber.get(5).getSongNum());
			} catch(Exception e) {}
			
			// DB카운트
			SongDAO songDao = new SongDAO();
			songDto = new SongDTO();
			songDto = songDao.selectNum(songNumber.get(0).getSongNum());
			songSvc.songPlay(songDto);
			
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
		}
		
	}
	
	// 취소버튼을 눌렀을 때 남은 곡 수가 0이면 방의 사용여부를 0으로 바꾸기 & 취소버튼 누르면 대기화면 띄우기
	public void songCancelProc() {
		mediaPlayer.pause();
		songDefault.setOpacity(100);
		if(count == 0) {
			// 방 번호를 받아서 방DB에 접근하는 DAO 필
		}
	}


}

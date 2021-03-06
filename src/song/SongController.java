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
import javafx.scene.control.Button;
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
	private int count;
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
	
	public ArrayList<SongDTO> getSongNumber() {
		return songNumber;
	}

	public void setSongNumber(ArrayList<SongDTO> songNumber) {
		this.songNumber = songNumber;
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

	// ?????? ?????? ????????????
	public void setEndOfMedia(boolean endOfMedia) {
		this.endOfMedia = endOfMedia;
	}
	
	// ???????????? ????????????
	public void setEndOfMedia2(boolean endOfMedia) {
		this.endOfMedia = endOfMedia;
		mediaPlayer.stop();
		if(endOfMedia) {
			// ???????????? ?????????
			songDefault.setOpacity(100);
			// ???????????? 0?????? ????????? ?????????
			if (count <= 0) {
				exit();
				return;
			}
			// ????????? ????????????
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
	
	//?????? ????????? ??????
	public void songSearch() {
		songSvc.songSearchOpen();
	}
	
	//????????? ???????????? ?????????
	public void reserveSong(SongDTO songDTO) {
		// ???????????? 6?????? ????????? ??? ?????? 
		if(songNumber.size() >= 6) {
			CommonService.msg("?????? ??? ?????? ????????? ??? ????????????.");
		}else {
			songNumber.add(songDTO); // songDTO??? ?????????
			num1.setText(songNumber.get(0).getSongNum()); // DTO ?????? ??? ????????? ???????????? ????????? ???????????? ??????
			insertReserveSong(); // ????????? ????????? ?????? ??? ?????? ??????
		}
		
	}
	
	// ?????? ?????? ??????
	public void primaryReserveSong(SongDTO songDTO) {
		if(songNumber.size() >= 6) {
			CommonService.msg("?????? ??? ?????? ????????? ??? ????????????.");
		}else {
			songNumber.add(0, songDTO); // ????????? 0?????? ?????? ???????????? 1????????? ???????????? ????????? 
			
			insertReserveSong(); // ????????? ????????? ?????????
		}
	}
	
	// ?????? ?????? ?????? ???
	public void songStartProc() {
		
		if(endOfMedia) {
			
			if(songNumber.size() == 0) {
				// ????????? ?????? 0??? ??? ?????????
				CommonService.msg("?????? ?????? ????????? ?????????");
			}else {
				//MediaView??????
				songMediaView();
			}
			
		} 
		
	}
	
	// ?????? ?????? ?????? ??? 
	public void songCancelProc() {
		if (mediaPlayer != null) {
			// ????????? ?????? ?????????
			mediaPlayer.stop();
			
			// ???????????? ????????????
			songDefault.setOpacity(100);
			
			//???????????? ????????? ?????? ????????????
			setEndOfMedia(true);
			
			// ?????? ??? ?????? 0??? ??? ??? ???????????? ???????????? ????????? ??? ?????? ??????
			if(count <= 0) {
				exit();
			}		
			
		}
	}
	
	// ????????? ?????? ?????? ???
	public void songOutProc() {
		exit();
	}
	
	
	// ?????? ????????????
	public void pause() {
		if (mediaPlayer != null) {
			mediaPlayer.pause();
		}
	}
	
	// ?????? ???????????? ??????
	public void pauseCancel() {
		if (mediaPlayer != null) {
			mediaPlayer.play();
		}
	}
	
	// ?????? ????????????
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
	
	// ?????? ?????? ??????
	public void cancelReserve() {
		
		if (songNumber.size() > 0) {
			// ??? ?????? ????????? ?????????
			songNumber.remove(0);
			
			// ????????? ????????? ????????????(?????? ????????? ????????? ????????? ????????? ??????)
			num1.setText("");num2.setText("");num3.setText("");
			num4.setText("");num5.setText("");num6.setText("");
			
			insertReserveSong();
			
		} else {
			CommonService.msg("????????? ?????? ????????????.");
		}
		
	}
	
	public void exit() {
		songSvc.roomAvailable(room);
		Button okButton = CommonService.msg2("???????????? ???????????????.");
		okButton.setOnAction(event -> {
			closeForm();
			
		});
		
	}
	
	
	public void closeForm() {
		// ??? ?????? ??????
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
	
	// ????????? ?????? ????????? ?????? ??? ?????????
	public void songMediaView() {
		// songDB ??? ?????? count +1
		songDto = songSvc.songPlay(songNumber.get(0).getSongNum());
		// MediaView ??????
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
		
		// ?????? ??? ??? ?????? ????????????
		remainSong.setText(Integer.toString(--count));
		
		// ??? ?????? ????????? ?????????
		songNumber.remove(0);
		
		// ????????? ????????? ????????????(?????? ????????? ????????? ????????? ????????? ??????)
		num1.setText("");num2.setText("");num3.setText("");
		num4.setText("");num5.setText("");num6.setText("");
		
		insertReserveSong();
	}
	
	public void sound1() {
		// ????????? ?????????
		if (mediaPlayer == null || mediaPlayer.getStatus().toString().equals("STOPPED"))
			CommonService.sound1();
	}



}

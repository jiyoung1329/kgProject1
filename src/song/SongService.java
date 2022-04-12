package song;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import song.search.SongSearchController;

public class SongService {
	private SongController songController;
	private MediaPlayer mediaPlayer;
	
	@FXML private MediaView songMedia;
	@FXML private Label reservSong1;
	@FXML private Label reservSong2;
	@FXML private Label reservSong3;
	@FXML private Label reservSong4;
	@FXML private Label reservSong5;
	@FXML private Label reservSong6;
	
	
	
	public void setSongController(SongController songController) {
		this.songController = songController;
		
	}
	
	// 노래 리스트 창 오픈 메소드
	public void songSearchOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/search/songSearch.fxml"));
		
		Parent songSearchForm;
		try {
			songSearchForm = loader.load();
			
			System.out.println(songSearchForm);
	
			songController.setSongSearchController(loader.getController());
			songController.getSongSearchController().setSearchForm(songSearchForm);
			
			Scene scene = new Scene(songSearchForm);
		
			Stage stage = new Stage();
			stage.setTitle("노래 검색");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 남은 곡 수 카운트 - deCounting만
	public void remainNumCount() {
		
	}
	
	// 예약 곡 바에 띄울 노래 리스트 메소드
	public void reservSongReg() {
	
	}
	
	// 곡 재생 
	public void songPlay(String url) {
		if(url != null) {
			Media media = new Media(url);
			MediaPlayer mediaPlayer = new MediaPlayer(media);
			songMedia.setMediaPlayer(mediaPlayer);
			
			mediaPlayer.play();
		}
		
	}
	
	// 재생정지 
	public void songCancel() {
		mediaPlayer.pause();
	}

	
	
}

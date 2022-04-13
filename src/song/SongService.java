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
	
	
	
	public SongController getSongController() {
		return songController;
	}
	
	public void setSongController(SongController songController) {
		this.songController = songController;
	}
	
	// 노래 리스트 창 오픈 메소드
	public void songSearchOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/search/songSearch.fxml"));
		Parent songSearchForm;
		
		try {
			songSearchForm = loader.load();
			
			songController.setSongSearchController(loader.getController());
			songController.getSongSearchController().setSearchForm(songSearchForm);
			
			songController.getSongSearchController().setSongController(songController);
			
			Scene scene = new Scene(songSearchForm);
		
			Stage stage = new Stage();
			stage.setTitle("노래 검색");
			stage.setScene(scene);
			stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// DB내 카운트 추가
	public void songPlay(SongDTO songDto) {
		// DB내 카운트 추가
		SongDAO songDao = new SongDAO();
		songDao.addCount(songDto);
		System.out.println(songDto);
	}
	
	
	
}

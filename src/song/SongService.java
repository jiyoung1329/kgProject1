package song;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SongService {
	private SongController songController;
	
	
	public void setSongController(SongController songController) {
		this.songController = songController;
		
	}
	
	// 노래 리스트 창 오픈 메소드
	public void songSearchOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/songSearch.fxml"));
		
		Parent songSearchForm;
		try {
			songSearchForm = loader.load();
			
			songController.setSongSearchForm(songSearchForm);
			
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
	
	// 시작버튼을 누르면 곡 재생 메소드
	public void songPlay() {
		
		
	}
	
	// 취소버튼 누를 시 재생정지 + 대기화면 띄우기 
	public void songCancel() {
		
	}

	
	
}

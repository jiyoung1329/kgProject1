package song.remotecontrol;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import song.SongController;
import song.search.PopularChartController;
import song.search.SongSearchController;

public class RemoteControlController implements Initializable {
	@FXML private Button one;
	@FXML private Button two;
	@FXML private Button three;
	@FXML private Button four;
	@FXML private Button five;
	@FXML private Button six;
	@FXML private Button seven;
	@FXML private Button eight;
	@FXML private Button nine;
	@FXML private Button zero;

	@FXML private Button reserve;
	@FXML private Button primaryReserve;
	@FXML private Button start;

	@FXML private Button popularChart;
	@FXML private Button titleSearch;
	@FXML private Button singerSearch;
	
	@FXML private Button pause;
	@FXML private Button maidJump;
	@FXML private Button clap;
	
	@FXML private Label testLabel;
	
	private SongController songController;
	private RemoteControlController remoteController; 
	private PopularChartController chartController;
	private SongSearchController searchController;
	
	private RemoteControlService remoteService;
	private Parent remoteForm;
	private Parent songForm;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		remoteService = new RemoteControlService();
		remoteService.setRemoteController(this);
		
	}

	
	public RemoteControlService getRemoteService() {
		return remoteService;
	}


	public void setRemoteService(RemoteControlService remoteService) {
		this.remoteService = remoteService;
	}


	public RemoteControlController getRemoteController() {
		return remoteController;
	}


	public void setRemoteController(RemoteControlController remoteController) {
		this.remoteController = remoteController;
	}


	public PopularChartController getChartController() {
		return chartController;
	}


	public void setChartController(PopularChartController chartController) {
		this.chartController = chartController;
	}


	public SongSearchController getSearchController() {
		return searchController;
	}


	public void setSearchController(SongSearchController searchController) {
		this.searchController = searchController;
	}


	public Parent getSongForm() {
		return songForm;
	}


	public void setSongForm(Parent songForm) {
		this.songForm = songForm;
	}


	public Parent getRemoteForm() {
		return remoteForm;
	}


	public void setRemoteForm(Parent remoteForm) {
		this.remoteForm = remoteForm;
	}



	public SongController getSongController() {
		return songController;
	}


	public void setSongController(SongController songController) {
		this.songController = songController;
	}



	public void one() {}
	public void two() {}
	public void three() {}
	public void four() {}
	public void five() {}
	public void six() {}
	public void seven() {}
	public void eight() {}
	public void nine() {}
	public void zero() {}
	
	public void cancel() {
		songController.songCancelProc();
	}
	public void start() {
		songController.songStartProc();
	}
	
	public void reserve() {
		
	}
	public void primaryReserve() {}
	public void cancelReserve() {
		songController.cancelReserve();
	}
	
	public void popularChart() {
		remoteService.popularChart();
	}
	public void titleSearch() {
		remoteService.titleSearch();
	}
	public void singerSearch() {
		remoteService.singerSearch();
	}
	
	public void pause() {
		// 미디어 플레이어가 재생중이 아니면 일시정지 못하게
		if (songController.getMediaPlayer() == null) return;
		
		if (pause.getText().equals("일시정지")) {
			songController.pause();
			pause.setText("일시정지해제");
			Font font = Font.font("Hancom Gothic", FontWeight.BOLD, 16);
			pause.setFont(font);
			
		} else if (pause.getText().equals("일시정지해제")) {
			songController.pauseCancel();
			pause.setText("일시정지");
			Font font = Font.font("Hancom Gothic", FontWeight.BOLD, 18);
			pause.setFont(font);
		}
		
	}
	public void madijump() {
		songController.madiJump();
		
	}
	public void clap() {
		try {
			File clapEffect = new File("src/song/remotecontrol/clap.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(clapEffect));
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
;
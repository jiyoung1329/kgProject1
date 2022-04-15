package song.remotecontrol;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		remoteService = new RemoteControlService();
		remoteService.setRemoteController(this);
		
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
	
	public void cancel() {}
	public void start() {}
	
	public void reserve() {}
	public void primaryReserve() {}
	public void cancelReserve() {}
	
	public void popularChart() {
		remoteService.popularChart();
	}
	public void titleSearch() {
		remoteService.titleSearch();
	}
	public void singerSearch() {
		remoteService.singerSearch();
	}
	
	public void pause() {}
	public void madijump() {}
	public void clap() {
		
	}
}

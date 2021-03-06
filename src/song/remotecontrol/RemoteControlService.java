package song.remotecontrol;

import java.io.IOException;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import song.SongController;
import song.search.PopularChartController;
import song.search.SongSearchController;
import song.search.songSearchDAO;

public class RemoteControlService {
	
	private SongController songController;
	private RemoteControlController remoteController; 
	
	private Parent searchForm;
	private Parent chartForm;
	
	
	public RemoteControlService() {
		songSearchDAO searchDAO = new songSearchDAO();
	}
	
	
	public RemoteControlController getRemoteController() {
		return remoteController;
	}


	public void setRemoteController(RemoteControlController remoteController) {
		this.remoteController = remoteController;
	}


	public SongController getSongController() {
		return songController;
	}

	public void setSongController(SongController songController) {
		this.songController = songController;
	}

	public void popularChart() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/search/popularChart.fxml"));
		Parent chartForm;
		
		try {
			chartForm = loader.load();
			
			// 기존에 인기차트나 검색창 있으면 닫기
			closeForm();
			
			PopularChartController popularChartController = loader.getController();
			remoteController.setChartController(popularChartController);
			popularChartController.setChartForm(chartForm);
			songController.setChartForm(chartForm);
			popularChartController.setSongController(songController);
			
			
			Scene scene = new Scene(chartForm);
			
			// 화면 위치 조정
			Stage nowStage = (Stage) songController.getSongForm().getScene().getWindow();
			Double SongX = nowStage.getX();
			Double SongY = nowStage.getY();
			
			Stage stage = new Stage();
			stage.setTitle("인기 차트");
			stage.setX(SongX + 1005);
			stage.setY(SongY);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void titleSearch() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/search/titleSongSearch.fxml"));
		Parent titleSearchForm;
		
		try {
			titleSearchForm = loader.load();
			
			closeForm();
			
			SongSearchController searchController = loader.getController();
			searchController.setSearchForm(titleSearchForm);
			searchController.setSongController(songController);
			remoteController.setSearchController(searchController);
			songController.setSongSearchForm(titleSearchForm);
			
			
			Scene scene = new Scene(titleSearchForm);
			
			// 화면 위치 조정
			Stage nowStage = (Stage) songController.getSongForm().getScene().getWindow();
			Double SongX = nowStage.getX();
			Double SongY = nowStage.getY();
			
			Stage stage = new Stage();
			stage.setTitle("제목 검색");
			stage.setX(SongX + 1005);
			stage.setY(SongY);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void singerSearch() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/search/singerSongSearch.fxml"));
		Parent singerSearchForm;
		
		try {
			singerSearchForm = loader.load();
			
			closeForm();
			
			SongSearchController searchController = loader.getController();
			searchController.setSearchForm(singerSearchForm);
			searchController.setSongController(songController);
			remoteController.setSearchController(searchController);
			songController.setSongSearchForm(singerSearchForm);
			
			Scene scene = new Scene(singerSearchForm);

			// 화면 위치 조정
			Stage nowStage = (Stage) songController.getSongForm().getScene().getWindow();
			Double SongX = nowStage.getX();
			Double SongY = nowStage.getY();
			
			Stage stage = new Stage();
			stage.setTitle("가수 검색");
			stage.setX(SongX + 1005);
			stage.setY(SongY);
			stage.setScene(scene);
			stage.show();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 기존에 인기차트나 검색창이 있으면 닫기
	public void closeForm() {
		try {
			CommonService.windowClose(remoteController.getChartController().getChartForm());
			CommonService.windowClose(remoteController.getSearchController().getSearchForm());
		} catch (Exception e) {}
	}

}

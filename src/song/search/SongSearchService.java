package song.search;

import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SongSearchService {
	private songSearchDAO searchDAO;
	
	
	public SongSearchService() {
		searchDAO = new songSearchDAO();
	}

	public void searchAll(TableView<SongSearchDTO> songTable, TableColumn<SongSearchDTO, String> songNumber, 
						  TableColumn<SongSearchDTO, String> songTitle, TableColumn<SongSearchDTO, String> songSinger) {
		ArrayList<SongSearchDTO> songs = searchDAO.searchAll();
//		
		// 데이터 출력 테스트
//		int i=0;
//		for (SongSearchDTO song : songs) {
//			System.out.println(i++ + ". " + song.getSongNum() + ",  " + song.getSongTitle() + ", " + song.getSongSinger() + ", " + song.getSongLink());
//		}
		
		// tableView에 노래 데이터 삽입
		ObservableList<SongSearchDTO> songDatas = FXCollections.observableArrayList();
		for(SongSearchDTO song : songs) {
			songDatas.add(new SongSearchDTO(song.getSongNum(), song.getSongTitle(), song.getSongSinger(), song.getSongLink()));
		}
		songNumber.setCellValueFactory(new PropertyValueFactory<SongSearchDTO, String>("songNum"));
		songTitle.setCellValueFactory(new PropertyValueFactory<SongSearchDTO, String>("songTitle"));
		songSinger.setCellValueFactory(new PropertyValueFactory<SongSearchDTO, String>("songSinger"));
		songTable.setItems(songDatas);
		
		
		
	}
	

	public void searchResult(Parent searchForm) {
		TextField searchField = (TextField) searchForm.lookup("#searchContent");
		
		String searchContent = searchField.getText();
		ObservableList<SongSearchDTO> songDatas = searchDAO.searchSong(searchContent); 
		// 데이터 출력 테스트
//		int i=0;
//		for (SongSearchDTO song : songDatas) {
//			System.out.println(i++ + ". " + song.getSongNum() + ",  " + song.getSongTitle() + ", " + song.getSongSinger() + ", " + song.getSongLink());
//		}
		
		// 검색 결과 테이블에 추가
		TableView<SongSearchDTO> songTable = (TableView<SongSearchDTO>) searchForm.lookup("#songTable");
//		TableColumn<SongSearchDTO, String> songNumber = (TableColumn) songTable.getColumns().get(0);
//		songNumber.setCellValueFactory(new PropertyValueFactory<SongSearchDTO, String>("songNum"));
		songTable.setItems(songDatas);
		
	}
	
	public void windowClose(Parent searchForm) {
		Stage stage = (Stage)searchForm.getScene().getWindow();
		stage.close();
		
	}
	
}

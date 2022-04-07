package song.search;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class SongSearchController implements Initializable{
	@FXML 
	private TableView<SongSearchDTO> songTable;
	@FXML
	private TableColumn<SongSearchDTO, String> songNumber;
	@FXML
	private TableColumn<SongSearchDTO, String> songTitle;
	@FXML
	private TableColumn<SongSearchDTO, String> songSinger;
	
	private Parent searchForm; 
	private SongSearchService searchService;
	
	public Parent getSearchForm() {
		return searchForm;
	}

	public void setSearchForm(Parent searchForm) {
		this.searchForm = searchForm;
	}
	
	// 화면창 생성과 동시에 노래 목록 띄우기
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchService = new SongSearchService();
		searchService.searchAll(songTable, songNumber, songTitle, songSinger);
	}
	
	// 검색 버튼 클릭했을 때 결과 출력
	public void searchProc() {
		searchService.searchResult(searchForm);
		
	}
	
	// 엔터 쳤을때에도 검색하는 기능 추가
	public void EnterSearchProc() {
		
	}
	
	// 노래 선택
	
	// 예약 버튼 클릭 - searchDTO를 노래방 페이지로 전달
	
	// 취소버튼
	
	
	
}

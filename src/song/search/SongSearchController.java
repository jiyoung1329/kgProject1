package song.search;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import song.SongController;
import song.SongDTO;

public class SongSearchController implements Initializable{
	@FXML 
	private TableView<SongSearchDTO> songTable;
	@FXML
	private TableColumn<SongSearchDTO, String> songNumber;
	@FXML
	private TableColumn<SongSearchDTO, String> songTitle;
	@FXML
	private TableColumn<SongSearchDTO, String> songSinger;
	@FXML
	private TextField searchContent; 
	
	private Parent searchForm; 
	private SongSearchService searchService;
	private SongSearchDTO searchDTO;
	private SongController songController;
	
	public void setSongController(SongController songController) {
		this.songController = songController;
	}

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
		
		// 화면창 띄울때 전체 목록 불러오기
		searchService.searchAll(songTable, songNumber, songTitle, songSinger);
		
		// 테이블 내용 선택했을때 해당 데이터 뽑아오기
		songTable.getFocusModel().focusedCellProperty().addListener(
				(ObservableValue<? extends TablePosition> observable, TablePosition oldPos, TablePosition pos) -> {
			int row = pos.getRow();
			int column = pos.getColumn();
			if ((pos.getRow() != -1) && (pos.getColumn() != -1)) {
				searchDTO = songTable.getItems().get(row);
				
			}
		}
		);
		
	}
	
	// 검색 버튼 클릭했을 때 결과 출력
	public void searchProc() {
		searchService.searchResult(searchForm);
		
	}
	
	// 예약 버튼 클릭 - searchDTO를 노래방 페이지로 전달
	public void songReserveProc() {
		if (searchDTO != null) {
			SongDTO songdto = new SongDTO();
			songdto.setSongNum(searchDTO.getSongNum());
			songdto.setSongCount(searchDTO.getSongCount());
			songdto.setSongLink(searchDTO.getSongLink());
			songController.reserveSong(songdto);
			//System.out.println(searchDTO.getSongNum() + ", " + searchDTO.getSongTitle() + ", " + searchDTO.getSongSinger() + ", "  + searchDTO.getSongLink());
			
		} 
		
		
	}
	
	// 취소버튼
	public void pageCancelProc() {
		searchService.windowClose(searchForm);
		
	}
	
	
	
}

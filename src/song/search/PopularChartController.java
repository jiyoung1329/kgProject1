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
import song.remotecontrol.RemoteControlController;
import song.remotecontrol.RemoteControlService;

public class PopularChartController implements Initializable {
	@FXML 
	private TableView<SongSearchDTO> songTable;
	@FXML
	private TableColumn<SongSearchDTO, String> songNumber;
	@FXML
	private TableColumn<SongSearchDTO, String> songTitle;
	@FXML
	private TableColumn<SongSearchDTO, String> songSinger;
	
	private Parent chartForm; 
	private SongSearchService searchService;
	private SongSearchDTO searchDTO;
	private SongController songController;
	private RemoteControlController remoteController;
	
	
	public void setSongController(SongController songController) {
		this.songController = songController;
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


	// 화면창 생성과 동시에 노래 목록 띄우기
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		searchService = new SongSearchService();
		
		// 화면창 띄울때 전체 목록 불러오기
		searchService.popluarSong(songTable, songNumber, songTitle, songSinger);
		
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
		searchService.windowClose(chartForm);
		
	}

}

package room.select;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginDTO;

public class SelectService {
	
	private SelectDAO selectDAO;
	private SelectController selectController;
	
	public SelectController getSelectController() {
		return selectController;
	}

	public void setSelectController(SelectController selectController) {
		this.selectController = selectController;
	}

	public SelectService() {
		selectDAO = new SelectDAO();
	}
	
	// 곡선택하면 DB에 남은 곡수 minus한 상태로 저장
	public void saveSongCount(int count, Parent selectForm) {
		LoginDTO dto = selectDAO.callMember();
		// 남은 곡수 minus 후 저장
		selectDAO.saveSongCount(count, dto);
		// 현재 화면 끄기
		CommonService.windowClose(selectForm);
		// 노래방 화면으로 이동
		moveSongPage();
		
	}
	
	// 노래방 페이지로 화면 넘기기
	public void moveSongPage() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/Song.fxml"));
		try {
			Parent songForm = loader.load();
			
			selectController.setSongController(loader.getController());
			selectController.getSongController().setSong(songForm);
			
			Scene scene = new Scene(songForm);
		
			Stage stage = new Stage();
			stage.setTitle("노래방");
			stage.setScene(scene);
			stage.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

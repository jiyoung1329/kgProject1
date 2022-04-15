package room.select;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
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
	public void saveSongCount(Parent selectForm) {
		String count = ((Label) selectForm.lookup("#songCount")).getText();
		String room = ((Label) selectForm.lookup("#roomNumber")).getText();
		
		LoginDTO dto = selectDAO.callMember();

		// 남은 곡수 minus 후 저장
		selectDAO.saveSongCount(Integer.parseInt(count), dto);
		// 현재 화면 끄기
		CommonService.windowClose(selectForm);

		// 노래방 화면으로 이동
		// 노래방 화면으로 이동할 떄 넘겨야하는 인자 : 곡 수, 방 번호
		moveSongPage(count, room);
		
	}
	
	public boolean checkRoomReserve(String room) {
		int isReservation = selectDAO.checkRoomReserve(room);
		if (isReservation == 0) return true;
		else return false;
	}
	
	// 노래방 페이지로 화면 넘기기
	public void moveSongPage(String count, String room) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/Song.fxml"));
		try {
			Parent songForm = loader.load();
			
			selectController.setSongController(loader.getController());
			selectController.getSongController().setSong(songForm);
//			System.out.println(count + " " + room);
			selectController.getSongController().setCount(count);
			selectController.getSongController().setRoomNumber(room);
			
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

package room.select;

import common.CommonService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import login.LoginDTO;
import room.choice.Status;
import song.SongController;
import song.remotecontrol.RemoteControlController;

public class SelectService {
	
	private SelectDAO selectDAO;
	private SelectController selectController;
	
	private Status status;
	
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
		
		LoginDTO dto = status.getLoginDTO();

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
		FXMLLoader loader2 = new FXMLLoader(getClass().getResource("/song/remotecontrol/remotecontrol.fxml"));
		
		try {
			Parent songForm = loader.load();
			Parent remoteForm = loader2.load();
			
			// songForm 열기
			SongController songController = loader.getController();
			songController.setSong(songForm);
			songController.setCount(count);
			songController.setRoomNumber(room);

			// remoteForm 열기
			RemoteControlController remoteController = loader2.getController();
			remoteController.setSongController(songController);
			remoteController.setRemoteForm(remoteForm);
			// 노래방 종료시 동시에 꺼지게 form 저장
			songController.setRemoteForm(remoteForm);
			// remoteService의 songController 설정 -> 페이지 위치조정에 필요
			remoteController.getRemoteService().setSongController(songController);
			
			Scene scene1 = new Scene(songForm);
			Scene scene2 = new Scene(remoteForm);
		
			Stage stage1 = new Stage();
			stage1.initStyle(StageStyle.UNDECORATED);
			stage1.setTitle("노래방");
			stage1.setScene(scene1);
			stage1.show();
			Double X1 = stage1.getX();
			Double Y1 = stage1.getY();

			Stage stage2 = new Stage();
			stage2.setX(X1-395);
			stage2.setY(Y1);
			stage2.initStyle(StageStyle.UNDECORATED);
			stage2.setTitle("리모컨");
			stage2.setScene(scene2);
			stage2.show();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	

}

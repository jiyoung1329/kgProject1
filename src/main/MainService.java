package main;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainService {
	private MainController mainController;

	public void setMainController(MainController mainController) {
		this.mainController = mainController;
	}

	public void rcOpen() {	// 회원 로그인 성공시 RoomChoice 폼을 실행하는 메소드
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/roomChoice/rcForm.fxml"));

		Parent roomChoiceForm;

		try {

			roomChoiceForm = loader.load();
			mainController.setRoomChoiceForm(roomChoiceForm);
			Scene scene = new Scene(roomChoiceForm);

			Stage stage = new Stage();
			stage.setTitle("roomChoiceForm");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void regOpen() {  //회원가입 버튼 클릭시 Register폼을 실행하는 메소드
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/register/regForm.fxml"));

		Parent regForm;

		try {
			regForm = loader.load();
			
			mainController.setRegForm(regForm);
			mainController.setRegController(loader.getController());
			
			Scene scene = new Scene(regForm);

			Stage stage = new Stage();
			stage.setTitle("RegisterForm");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	// 관리자 로그인 성공시 관리자 폼을 실행하는 메소드를 작성해야함
}

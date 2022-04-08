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

	public void rcOpen() {	// ȸ�� �α��� ������ RoomChoice ���� �����ϴ� �޼ҵ�
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/room/choice/rcForm.fxml"));

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

	public void regOpen() {  //ȸ������ ��ư Ŭ���� Register���� �����ϴ� �޼ҵ�
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
	// ������ �α��� ������ ������ ���� �����ϴ� �޼ҵ带 �ۼ��ؾ���

	public void adminOpen() {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Admin/AdminForm.fxml"));
		
		Parent adminForm;
		try {
		adminForm = loader.load();
		
		mainController.setAdminForm(adminForm);
		mainController.setAdminController(loader.getController());
		
		Scene scene = new Scene(adminForm);

		Stage stage = new Stage();
		stage.setTitle("adminForm");
		stage.setScene(scene);
		stage.show();

	} catch (IOException e) {
		e.printStackTrace();
	}

		

		
	}
}

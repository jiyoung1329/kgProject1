package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;
import register.RegController;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/loginForm.fxml"));
		Parent loginForm = loader.load();
		
		LoginController loginController = loader.getController(); //�α���(����)���� ��Ʈ�ѷ����� �����ͼ� ����
		loginController.setLoginForm(loginForm);
		
		MainController mainController = new MainController(); // ������Ʈ�ѷ��� ������ ���� ����
		mainController.setLoginController(loginController);
		loginController.setMainController(mainController);
				
		Scene scene = new Scene(loginForm);
		
		primaryStage.setTitle("Karaoke");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

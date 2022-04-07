package roomChoice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RoomChoiceMain extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("rcForm.fxml"));
		
		Parent root = loader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setTitle("rcForm");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}

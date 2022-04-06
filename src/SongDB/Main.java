package SongDB;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("songSearch.fxml"));
		Parent searchForm = loader.load();
		
		Scene scene = new Scene(searchForm);
		primaryStage.setTitle("search");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}

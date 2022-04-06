package song;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class SongMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/song/Song.fxml"));
		Parent songForm = loader.load();
		Scene scene = new Scene(songForm);
		
		primaryStage.setTitle("노래방 화면");
		primaryStage.setScene(scene);
		primaryStage.show();
				
	}



}

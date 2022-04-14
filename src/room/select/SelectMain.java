package room.select;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import song.search.SongSearchController;

public class SelectMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("selectForm.fxml"));
		Parent roomSelectForm = loader.load();
		
		SelectController selectController = loader.getController();
		selectController.setSelectForm(roomSelectForm);
		
		primaryStage.setScene(new Scene(roomSelectForm));
		primaryStage.setTitle("곡선택");
		primaryStage.show();
	}
	

}

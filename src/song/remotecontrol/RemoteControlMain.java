package song.remotecontrol;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RemoteControlMain extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("remotecontrol.fxml"));
		Parent remoteControlForm = loader.load();
		
		RemoteControlController remoteController = loader.getController();
		remoteController.setRemoteForm(remoteControlForm);
		
		primaryStage.setTitle("KG노래방 리모컨");
		primaryStage.setScene(new Scene(remoteControlForm));
		primaryStage.show();
	}

}

package admin.song;


import java.net.URL;
import java.util.ResourceBundle;

import common.CommonService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AdminSongController implements Initializable {
	@FXML private TextField songNumber;
	@FXML private TextField songName;
	@FXML private TextField songSinger;
	@FXML private Button reg_button;
	@FXML private Button check;
	@FXML private TextField fileroot;
	private Parent songform;
	private AdminSongService ssvc;
	private AdminSongController scon;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		songform=getSongform();
		ssvc = new AdminSongService();
		reg_button.setDisable(true);
		check.setDisable(true);
		songNumber.textProperty().addListener( (attribute, before, after)->{
			emptyCheck();
		
		});
		
	}
	
	public void emptyCheck() {
		if(songNumber.getText().isEmpty()) {
			check.setDisable(true);
		}else{
			check.setDisable(false);
		}
	}
	
	public Parent getSongform() {
		return songform;
	}

	public void setSongform(Parent songform) {
		this.songform = songform;
	}

	public void close() {
		Stage stage = (Stage) songform.getScene().getWindow();
		stage.close();
	}
	
	public void checkProc(){
		String num = songNumber.getText();
		try {
			int test = Integer.parseInt(num);
			ssvc.check(songNumber,reg_button,num);
		}catch(Exception e) {
			CommonService.msg("공백없이 숫자만 입력하시오");
		}
	}
	
	public void regProc() {
		ssvc.regProc(songNumber, songName,songSinger,fileroot);
	}
	
	public void resetProc() {
		songNumber.clear();
		songName.clear();
		songNumber.setDisable(false);
		songSinger.clear();
		reg_button.setDisable(true);
	}
	public void fileProc() {
		ssvc.filereg(fileroot);

	}

}

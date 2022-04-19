package common;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.stage.Stage;

public class CommonService {
	
	public static void msg(String content) {  
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
	}
	
	public static Button msg2(String content) {  
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setContentText(content);
		alert.show();
		
		Button okButton = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
		return okButton;
		
	}
	
	public static void windowClose(Parent form) { 
		Stage stage = (Stage) form.getScene().getWindow();
		stage.close();
	}
	
	public static void sound1() {
		try {
			File effect = new File("src/common/sound1.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect));
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void sound2() {
		try {
			File effect = new File("src/common/sound2.wav");
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(effect));
			clip.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}

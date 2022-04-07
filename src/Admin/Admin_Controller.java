package Admin;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import Member.Member_Controler;
import Song.Song_Controller;

public class Admin_Controller {
	Parent memberform;
	Parent songform;
	
	public Parent getMemberform() {
		return memberform;
	}

	public void setMemberform(Parent memberform) {
		this.memberform = memberform;
	}

	public void reg(){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/Member/MemberForm.fxml"));
			try {
				memberform = loader.load();
				Member_Controler member = loader.getController();
				member.setMemberform(memberform);
				Scene scene = new Scene(memberform);
				Stage stage = new Stage();
				stage.setTitle("회원관리");
				stage.setScene(scene);
				stage.show();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}			
	public void song(){
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Song/SongForm.fxml"));

		try {
			songform = loader.load();
			Song_Controller song = loader.getController();
			song.setSongform(songform);
			Scene scene = new Scene(songform);
			Stage stage = new Stage();
			stage.setTitle("노래등록");
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}


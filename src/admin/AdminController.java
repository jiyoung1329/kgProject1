package admin;

import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import login.LoginController;
import main.Main;

import java.io.IOException;

import admin.member.MemberControler;
import admin.song.AdminSongController;



public class AdminController {
	Parent memberform;
	Parent songform;
	Parent adminForm;
	
	public Parent getMemberform() {
		return memberform;
	}

	public void setMemberform(Parent memberform) {
		this.memberform = memberform;
	}

	public void reg(){
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/member/MemberForm.fxml"));
			try {
				memberform = loader.load();
				MemberControler member = loader.getController();
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
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin/song/AdminSong.fxml"));

		try {
			songform = loader.load();
			AdminSongController song = loader.getController();
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

	public void setAdminForm(Parent adminForm) {
		this.adminForm = adminForm;
		
	}
	public void cancel_proc() throws Exception {
		Stage stage = (Stage) adminForm.getScene().getWindow();
		stage.close();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/login/loginForm.fxml"));

		Parent adminForm;
		Main main = new Main();
		try {
			adminForm = loader.load();


			Scene scene = new Scene(adminForm);

			Stage stage2 = new Stage();
			stage2.setTitle("로그인");
			stage2.setScene(scene);
			stage2.show();
			main.start(stage2);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}


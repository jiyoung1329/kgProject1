package Member;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Admin.Admin_Controller;
import Admin.Admin_Main;

public class Member_Controler implements Initializable{
	@FXML private TextField Search_Bar;
	@FXML private ListView list_one;
	@FXML private ListView list_two;
	private Member_Service msv;
	private Parent memberform;
	
	public Parent getMemberform() {
		return memberform;
	}
	public void setMemberform(Parent memberform) {
		this.memberform = memberform;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		msv=new Member_Service();
		msv.list_all(list_one);
	}
	public void search() {
		String text = Search_Bar.getText();
		list_two.getItems().clear();
		msv.list_search(list_one,text);
	}
	public void delete() {
		msv.delete(list_two);
	}
	public void right_move() {
		msv.right_move(list_one,list_two);
	}
	public void left_move() {
		msv.left_move(list_one,list_two);
	}
	public void reset_all() {
		list_two.getItems().clear();
		msv.list_all(list_one);
	}
	public void close() {
		memberform=getMemberform();
		Stage stage = (Stage)memberform.getScene().getWindow();
		stage.close();
		}
}

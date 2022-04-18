package admin.member;

import java.net.URL;

import java.util.ResourceBundle;

import admin.AdminController;
import admin.AdminMain;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MemberControler implements Initializable{
	@FXML private TextField Search_Bar;
	@FXML private TableView table_one;
	@FXML private TableView table_two;
	@FXML private TableColumn t1id;
	@FXML private TableColumn t1phone;
	@FXML private TableColumn t1song;
	@FXML private TableColumn t2id;
	@FXML private TableColumn t2phone;
	@FXML private TableColumn t2song;
	
	private MemberService msv;
	private Parent memberform;
	
	public Parent getMemberform() {
		return memberform;
	}
	public void setMemberform(Parent memberform) {
		this.memberform = memberform;
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//멤버서비스 호출
		msv=new MemberService();
		
		// 테이블뷰 밸류값 세팅
		t1id.setCellValueFactory(new PropertyValueFactory<>("t1id"));
		t1phone.setCellValueFactory(new PropertyValueFactory<>("t1phone"));
		t1song.setCellValueFactory(new PropertyValueFactory<>("t1song"));
		t2id.setCellValueFactory(new PropertyValueFactory<>("t2id"));
		t2phone.setCellValueFactory(new PropertyValueFactory<>("t2phone"));
		t2song.setCellValueFactory(new PropertyValueFactory<>("t2song"));
		
		//기본적으로 시작시 list_all 실행되도록 설정
		msv.list_all(table_one);
	}
	public void search() {
		
		//Serarch_Bar에있는 텍스트를 받아와 멤버서비스에 전달
		String text = Search_Bar.getText();
		table_two.getItems().clear();
		msv.list_search(table_one,text);
	}
	public void delete() {
		msv.delete(table_two,t2id);
	}
	public void right_move() {
		msv.right_move(table_one,table_two);
	}
	public void left_move() {
		msv.left_move(table_one,table_two);
	}
	public void reset_all() {
		table_two.getItems().clear();
		Search_Bar.clear();
		msv.list_all(table_one);
	}
	public void close() {
		memberform=getMemberform();
		Stage stage = (Stage)memberform.getScene().getWindow();
		stage.close();
		}
}

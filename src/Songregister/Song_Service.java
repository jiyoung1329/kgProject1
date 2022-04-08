package Songregister;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class Song_Service extends Song_Controller {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	Alert alert;

	public Song_Service() {
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String user = "oracle";
		String pw = "oracle";
//		String url ="jdbc:oracle:thin:@kgproject_high?TNS_ADMIN=C:\\Wallet_kgProject";
//		String user = "admin";
//		String pw = "KGproject1234!";
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			con = DriverManager.getConnection(url, user, pw);
		} catch (Exception e) {
			System.out.println("연결실패");
			e.printStackTrace();
		}
	}
	public void check(TextField songNumber, Button reg_button, int Number) {
		String sql = "Select * from song where num=?";
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, Number);
			rs=ps.executeQuery();
			if(rs.next()) {
				alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("등록 불가");
				alert.setContentText("노래번호가 중복됩니다");
				alert.show();
			}else {
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("등록 가능");
				alert.setContentText("등록 가능한 번호입니다.");
				alert.show();
				songNumber.setDisable(true);
				reg_button.setDisable(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void urlProc(TextField url) {
		String str_url = url.getText();
		try {
			Desktop.getDesktop().browse(new URI(str_url));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void regProc(TextField number,TextField name,TextField singer,TextField url) {
		String sql = "insert into song values (?,?,?,?)";
		String reg_str = number.getText();
		int reg_num;
		reg_num = Integer.parseInt(reg_str);
		try {
			ps= con.prepareStatement(sql);
			ps.setInt(1, reg_num);
			ps.setString(2, name.getText());
			ps.setString(3, singer.getText());
			ps.setString(4, url.getText());
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

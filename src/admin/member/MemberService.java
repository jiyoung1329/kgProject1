package admin.member;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MemberService {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	
	public MemberService() {
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

	public void list_all(ListView list) {
		list.getItems().clear();
		String sql = "SELECT * FROM member";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.getItems().add(rs.getString("ID"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void list_search(ListView list, String text) {
		String id = text;
		String sql = "select * from member where id like '%" + id + "%' ";
		try {

			list.getItems().clear();
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				list.getItems().add(rs.getString("id"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void right_move(ListView list1, ListView list2) {
		Object names = list1.getSelectionModel().getSelectedItem();
		if (names != null) {
			list1.getItems().remove(names);
			list2.getItems().add(names);
		}
	}

	public void left_move(ListView list1, ListView list2) {
		Object names = list2.getSelectionModel().getSelectedItem();
		if (names != null) {
			list1.getItems().add(names);
			list2.getItems().remove(names);
		}
	}

	public void delete(ListView list2) {
		ArrayList<String> array = new ArrayList(list2.getItems());
		for (String i : array) {
			String sql = "delete FROM member where id ='"+i+"'";
			try {
				ps = con.prepareStatement(sql);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null)
						rs.close();
					if (ps != null)
						ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		list2.getItems().clear();
	}
	public void close(Parent memberform) {
		Stage stage = (Stage)memberform.getScene().getWindow();
		stage.close();
	}
}

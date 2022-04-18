package admin.member;

import java.sql.Connection;


import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import common.CommonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Parent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MemberService {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public MemberService() {
//		String url = "jdbc:oracle:thin:@localhost:1521:xe";
//		String user = "oracle";
//		String pw = "oracle";
		
		//데이터베이스 호출
		CommonDAO common=new CommonDAO();


		con=common.makeConnection();
	}

	public void list_all(TableView list) {

		list.getItems().clear();

		ObservableList<MemberDTO> datas = FXCollections.observableArrayList();
		String sql = "SELECT * FROM member";
		try {
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO mdto = new MemberDTO();
				if (rs.getInt("ISadmin")==1)
					continue;
				mdto.setT1id(rs.getString("id"));
				mdto.setT1phone(rs.getString("mobile"));
				mdto.setT1song(rs.getInt("Songcount"));
				datas.add(mdto);

			}
			list.getItems().addAll(datas);

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

	public void list_search(TableView list, String text) {
		String id = text;
		String sql = "select * from member where id like '%" + id + "%' ";
		list.getItems().clear();
		ObservableList<MemberDTO> datas = FXCollections.observableArrayList();
		try {

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				MemberDTO mdto = new MemberDTO();
				mdto.setT1id(rs.getString("id"));
				mdto.setT1phone(rs.getString("mobile"));
				mdto.setT1song(rs.getInt("Songcount"));
				datas.add(mdto);
				
			}
			list.getItems().addAll(datas);
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

	public void right_move(TableView list1, TableView list2) {
		ObservableList<MemberDTO> datas = FXCollections.observableArrayList();
		MemberDTO mdto = (MemberDTO) list1.getSelectionModel().getSelectedItem();
		
		if (mdto != null) {
			datas.add(mdto);
			list1.getItems().remove(mdto);
			mdto.setT2id(mdto.getT1id());
			mdto.setT2phone(mdto.getT1phone());
			mdto.setT2song(mdto.getT1song());
			list2.getItems().add(mdto);
		}
	}

	public void left_move(TableView list1, TableView list2) {
		Object names = list2.getSelectionModel().getSelectedItem();
		if (names != null) {
			list1.getItems().add(names);
			list2.getItems().remove(names);
		}
	}

	public void delete(TableView list2, TableColumn t2id) {
		ArrayList<String> array = new ArrayList(list2.getItems());
			for (int i =0; i <  array.size() ;i++) {
			MemberDTO mdto = new MemberDTO();
			mdto = (MemberDTO) list2.getItems().get(0);
			String sql = "delete FROM member where id ='" + mdto.getT2id() + "'";
			list2.getItems().remove(0);
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
		Stage stage = (Stage) memberform.getScene().getWindow();
		stage.close();
	}
}

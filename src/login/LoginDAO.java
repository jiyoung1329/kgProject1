package login;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import login.LoginDTO;

public class LoginDAO {
	private Connection con;

	public Connection getCon() {
		return con;
	}

	public LoginDAO() { //LoginDAO 인스턴스 생성시 memberDB접속
		String url = "jdbc:oracle:thin:@kgproject_high?TNS_ADMIN=C:/Wallet_kgProject";
		String user = "admin";
		String pwd = "KGproject1234!";

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url, user, pwd);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public LoginDTO selectId(String id) { //id로 DB에 있는 값을 가져옮
		PreparedStatement ps = null;
		ResultSet rs = null;
		LoginDTO login = null;
		String sql = "SELECT * FROM member WHERE id = ?"; // memberDB에서 id가 가진 정보를 모두 가져옴
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				login = new LoginDTO();
				login.setIsAdmin(rs.getInt("isAdmin"));
				login.setId(rs.getString("id"));
				login.setPw(rs.getString("pw"));
				login.setSongConut(rs.getInt("songCount"));
				login.setMobile(rs.getString("mobile"));
			}
		} catch (SQLException e) {
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
		return login;
	}
}

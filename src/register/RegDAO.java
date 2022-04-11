package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import login.LoginDAO;

public class RegDAO extends LoginDAO {
	
	public void insert(RegDTO reg) { // RegDT값을 받아와서 실행
		String sql = "INSERT INTO member VALUES(?,?,?,?,?)";
		Connection con = getCon();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reg.getId());
			ps.setString(2, reg.getPw());
			ps.setString(3, reg.getMobile());
			ps.setInt(4, 0); // songCount의 기본값은 0
			ps.setInt(5, 0); // Admin은 관리자만 1, 외 모든 회원은 0의 값 고정
			ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (ps != null)
					ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

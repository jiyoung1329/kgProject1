package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import login.LoginDAO;

public class RegDAO extends LoginDAO {
	
	public void insert(RegDTO reg) { // RegDTO값을 받아와서 데이터베이스에 값을 추가하는 메소드
		String sql = "INSERT INTO member VALUES(?,?,?,?,?)";
		Connection con = getCon();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reg.getId());
			ps.setString(2, reg.getPw());
			ps.setString(3, reg.getMobile());
			ps.setInt(4, 0); // songCount 컬럼 기본값 0
			ps.setInt(5, 0); // Admin확인 컬럼 기본값은 0, 관리자만 1
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

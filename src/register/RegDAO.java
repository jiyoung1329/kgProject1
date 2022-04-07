package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import login.LoginDAO;

public class RegDAO extends LoginDAO {
	
	public void insert(RegDTO reg) { // RegDTO���� �޾ƿͼ� �����ͺ��̽��� ���� �߰��ϴ� �޼ҵ�
		String sql = "INSERT INTO member VALUES(?,?,?,?,?)";
		Connection con = getCon();
		PreparedStatement ps = null;

		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, reg.getId());
			ps.setString(2, reg.getPw());
			ps.setString(3, reg.getMobile());
			ps.setInt(4, 0); // songCount �÷� �⺻�� 0
			ps.setInt(5, 0); // AdminȮ�� �÷� �⺻���� 0, �����ڸ� 1
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

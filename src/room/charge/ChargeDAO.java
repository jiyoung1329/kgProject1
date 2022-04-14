package room.charge;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.CommonDAO;
import login.LoginDTO;

public class ChargeDAO {
	private CommonDAO commonDAO;
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public ChargeDAO() {
		commonDAO = new CommonDAO();
		conn = commonDAO.makeConnection();
	}
	
	public void remainSongUpdate(int a, String b) {
		String query = "UPDATE member SET songCount = ? WHERE id =?";
		try {
//			System.out.println("ChargeDAO : " + conn);
			ps = conn.prepareStatement(query);
			ps.setInt(1, a);
			ps.setString(2, b);
			ps.executeUpdate();
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	

}

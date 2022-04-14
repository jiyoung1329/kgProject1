package song;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.CommonDAO;

public class SongDAO {
	private CommonDAO commonDAO;
		
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs= null;
		
	public SongDAO() {	
		commonDAO = new CommonDAO();
		conn = commonDAO.makeConnection();
	}
	
	public SongDTO selectNum(String num) {
		SongDTO song = null;
		String sql = "SELECT * FROM song WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, num);
			rs = ps.executeQuery();
			if(rs.next()) {
				song = new SongDTO();
				song.setSongCount(rs.getInt("count"));
				song.setSongLink(rs.getString("link"));
				song.setSongNum(num);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return song;
	}
	
	public void addCount(SongDTO song) {
		String sql = "UPDATE song SET count = count+1 WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, song.getSongNum());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 

	
	}
	
	public void roomAvailable(String room) {
		String sql = "UPDATE room SET isreservation = 0 WHERE num = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, room);
			ps.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				if(ps != null) ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} 
	}
	
	
	public void roomReserve(String room) {
		String sql = "update room set isreservation=1 where num=?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, room);
			ps.executeUpdate();
			
		} catch (SQLException e) {
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

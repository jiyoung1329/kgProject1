package song.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import common.CommonDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class songSearchDAO {
	private CommonDAO commonDAO;
	
	private Connection conn = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	
	public songSearchDAO() {
		commonDAO = new CommonDAO();
		conn = commonDAO.makeConnection();
	}
	
	public ArrayList<SongSearchDTO> searchAll() {
		String num, title, singer, link;
		String query = "select * from song";
		ArrayList<SongSearchDTO> songs = new ArrayList<SongSearchDTO>();
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SongSearchDTO searchDTO = new SongSearchDTO();
				searchDTO.setSongNum(rs.getString("num"));
				searchDTO.setSongTitle(rs.getString("title"));
				searchDTO.setSongSinger(rs.getString("singer"));
				searchDTO.setSongLink(rs.getString("link"));
				searchDTO.setSongCount(rs.getInt("count"));
				songs.add(searchDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return songs;
		
	}
	
	public ObservableList<SongSearchDTO> titleSearchSong(String searchContent){
		String query = "select * from song where title like '%" + searchContent + "%'";
		ObservableList<SongSearchDTO> songDatas = FXCollections.observableArrayList();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
//				System.out.println(rs.getString("num") + rs.getString("title"));
				SongSearchDTO searchDTO = new SongSearchDTO();
				searchDTO.setSongNum(rs.getString("num"));
				searchDTO.setSongTitle(rs.getString("title"));
				searchDTO.setSongSinger(rs.getString("singer"));
				searchDTO.setSongCount(rs.getInt("count"));
				searchDTO.setSongLink(rs.getString("link"));
				songDatas.add(searchDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return songDatas;
	}
	
	public ArrayList<SongSearchDTO> popularSong() {
		String num, title, singer, link;
		String query = "select * from song where rownum <= 100 order by count desc ";
		ArrayList<SongSearchDTO> songs = new ArrayList<SongSearchDTO>();
		
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while (rs.next()) {
				SongSearchDTO searchDTO = new SongSearchDTO();
				searchDTO.setSongNum(rs.getString("num"));
				searchDTO.setSongTitle(rs.getString("title"));
				searchDTO.setSongSinger(rs.getString("singer"));
				searchDTO.setSongLink(rs.getString("link"));
				searchDTO.setSongCount(rs.getInt("count"));
				songs.add(searchDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return songs;
		
	}
	
	public ObservableList<SongSearchDTO> singerSearchSong(String searchContent){
		String query = "select * from song where singer like '%" + searchContent + "%'";
		ObservableList<SongSearchDTO> songDatas = FXCollections.observableArrayList();
		try {
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();
			while(rs.next()) {
//				System.out.println(rs.getString("num") + rs.getString("title"));
				SongSearchDTO searchDTO = new SongSearchDTO();
				searchDTO.setSongNum(rs.getString("num"));
				searchDTO.setSongTitle(rs.getString("title"));
				searchDTO.setSongSinger(rs.getString("singer"));
				searchDTO.setSongCount(rs.getInt("count"));
				searchDTO.setSongLink(rs.getString("link"));
				songDatas.add(searchDTO);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return songDatas;
	}
	
	
	public void exit() {
		try {
			if (rs != null) rs.close();
			if (ps != null) ps.close();
		} catch(Exception e) {
			e.printStackTrace();
		}
			
	}

	
	

}

package admin.song;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import common.CommonDAO;
import common.CommonService;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class AdminSongService extends AdminSongController {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	Alert alert;

	public AdminSongService() {

		CommonDAO common=new CommonDAO();
		con=common.makeConnection();
	}

	public void check(TextField songNumber, Button reg_button, String number) {
		String sql = "Select * from song where num=?";
		try {
			ps = con.prepareStatement(sql);
			ps.setString(1, number);
			rs = ps.executeQuery();
			if (rs.next()) {
				CommonService.msg("노래번호가 중복됩니다");
			} else {
				CommonService.msg("등록 가능한 번호입니다.");
				songNumber.setDisable(true);
				reg_button.setDisable(false);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void regProc(TextField number, TextField name, TextField singer, TextField fileroot) {
		String sql = "insert into song(num,title,singer) values (?,?,?)";
		try {
			
			
			//파일복사파트
			String origin = fileroot.getText();
			String copydir = "./src/media";
			String copyfile = copydir + "/" + number.getText() + ".mp4";

			File originfile = new File(origin);
			File copyfiles = new File(copyfile);
			File dirCopyFile = new File(copydir);

			//디렉터리가 없으면 만들어주는 파트
			if (!dirCopyFile.exists()) {
				dirCopyFile.mkdirs();
			}

			try {
				FileInputStream fis = new FileInputStream(originfile);
				FileOutputStream fos = new FileOutputStream(copyfiles);
				int filebyte = 0;
				try {
					while ((filebyte = fis.read()) != -1) {
						fos.write(filebyte);
					}
					fis.close();
					fos.close();
					//파일복사 성공시 노래등록
					ps = con.prepareStatement(sql);
					ps.setString(1, number.getText());
					ps.setString(2, name.getText());
					ps.setString(3, singer.getText());
					rs = ps.executeQuery();
					CommonService.msg("노래가 등록되었습니다.");
					
				} catch (IOException e) {
					CommonService.msg("노래등록이 실패했습니다");
					
				}
			} catch (FileNotFoundException e) {
				CommonService.msg("노래등록이 실패했습니다");
				
			}

			//등록완료 파트
			number.clear();
			name.clear();
			singer.clear();
			fileroot.clear();


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void filereg(TextField fileroot) {
		FileChooser filechoo = new FileChooser();
		filechoo.getExtensionFilters().addAll(new ExtensionFilter("동영상 : Video Files", "*.mp4", "*.webp", "*.mkv"));
		File file = filechoo.showOpenDialog(null);
		String files = file.toString();
		fileroot.setText(files);
	}

}

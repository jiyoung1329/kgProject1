package login;
/*
 * SQL> CREATE TABLE membertmp(
  2  isAdmin number(4),
  3  id varchar2(15),
  4  pw varchar2(15),
  5  songCount number(4),
  6  mobile varchar2(15)
  7  );

 */
public class LoginDTO {
	private Integer isAdmin;
	private String id;
	private String pw;
	private Integer songConut;
	private String mobile;

	public Integer getIsAdmin() {
		return isAdmin;
	}
	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public Integer getSongConut() {
		return songConut;
	}
	public void setSongConut(Integer songConut) {
		this.songConut = songConut;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
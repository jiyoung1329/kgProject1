package song.search;

public class SongSearchDTO {
	private String songNum;
	private String songTitle;
	private String songSinger;
	private int songCount; 

	private String songLink;
	
	public SongSearchDTO() {}

	public SongSearchDTO(String songNum, String songTitle, String songSinger, String songLink) {
		super();
		this.songNum = songNum;
		this.songTitle = songTitle;
		this.songSinger = songSinger;
		this.songLink = songLink;
	}

	public String getSongNum() {
		return songNum;
	}

	public void setSongNum(String songNum) {
		this.songNum = songNum;
	}

	public String getSongTitle() {
		return songTitle;
	}

	public void setSongTitle(String songTitle) {
		this.songTitle = songTitle;
	}

	public String getSongSinger() {
		return songSinger;
	}

	public void setSongSinger(String songSinger) {
		this.songSinger = songSinger;
	}
	
	public int getSongCount() {
		return songCount;
	}
	
	public void setSongCount(int songCount) {
		this.songCount = songCount;
	}

	public String getSongLink() {
		return songLink;
	}

	public void setSongLink(String songLink) {
		this.songLink = songLink;
	}
	

}


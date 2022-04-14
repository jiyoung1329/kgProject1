package song.songDB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Test {
	public static void main(String[] args) throws IOException {
        String APIURL = "https://www.googleapis.com/youtube/v3/playlists";
        APIURL += "&part=snippet";
        String AUTHURL = "https://accounts.google.com/o/oauth2/auth";
        String client_id = "645820059221-sk4njjngp68kvp37vft6g0ttdov7d9p6.apps.googleusercontent.com";
        String redirect_uri = "http://localhost:5000/youtubeAPI";
        String scope = "https://www.googleapis.com/auth/youtube";
        String response_type= "token";
        
        AUTHURL += ("?client_id=" + client_id);
        AUTHURL += ("&redirect_uri=" + redirect_uri);
        AUTHURL += ("&scope=" + scope);
        AUTHURL += ("&response_type=" + response_type);
        
        
        URL url = new URL(AUTHURL);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        System.out.println(con);
        con.setRequestMethod("POST");
        System.out.println(con.getInputStream());

	}
}

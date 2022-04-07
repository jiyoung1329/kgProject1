package SongDB;
import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;

public class Youtube {
    public String search(String search) throws IOException {
//        String API_KEY = "AIzaSyAvK5rxkonXkOiX7S-KuBcvk_zZBB_mV90";
        String API_KEY = "AIzaSyDBRoxiEmgmfDWxPfN5WAAVkDkh_LFc2pc";

        String APIURL = "https://www.googleapis.com/youtube/v3/search?";
        APIURL += "key=AIzaSyAvK5rxkonXkOiX7S-KuBcvk_zZBB_mV90";
        APIURL += "&part=id";
        APIURL += ("&q=" + search);
        
//        System.out.println(APIURL);
        
        URL url = new URL(APIURL);
//        System.out.println(url);

        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//        System.out.println(con);
        con.setRequestMethod("GET");
//        System.out.println(con.getInputStream());

        BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

        String inputLine;
        StringBuffer response = new StringBuffer();
        while ((inputLine = br.readLine()) != null) {
            response.append(inputLine);
        }
        br.close();
//        System.out.println(response);
//        System.out.println(response.getClass().getSimpleName());
        
        JSONParser jParser = new JSONParser();
        JSONObject res = (JSONObject) JSONValue.parse(response.toString());
        JSONArray videos = (JSONArray) res.get("items");
        JSONObject video = (JSONObject) videos.get(0);
        String videoId = (String)((JSONObject) video.get("id")).get("videoId");
        
        System.out.println("www.youtube.com/watch?v=" + videoId);
        return videoId;
    }

}
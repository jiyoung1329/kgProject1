package song.songDB;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class WebCroller {
    public static void main(String[] args) throws IOException {
        StringBuilder query = new StringBuilder();
        Youtube youtube = new Youtube();
        
        String url = "https://www.youtube.com/watch?v=-oSdbZ2OFF4&list=PL5rkMpxC5Ex81afWiXpTnBNyGAfk-L06u&index=1";
        Document document = Jsoup.connect(url).get();
        
        Elements media = document.select("#movie_player > div.ytp-cued-thumbnail-overlay > div");
        System.out.println(media);
        Elements tjSongBox = document.select("#items");
        Elements tjsong = document.select("#wc-endpoint");
        Elements tjSongs = document.select("#playlist-items");
        System.out.println(tjSongs);
        for (int i = 1; i < tjSongs.size(); i++) {
        	System.out.println(tjSongs.get(i));
        }
//        MakeSQLFile(query.toString());
    }

    public static void MakeSQLFile(String sb) throws IOException {
        String path = "./songInsert.sql";
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(path), "UTF-8"));

        System.out.println(sb);
        bw.write(sb);
        bw.flush();
        bw.close();

    }

}

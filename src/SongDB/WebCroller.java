package SongDB;

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
        
        String url = "https://www.tjmedia.com/tjsong/song_monthPopular.asp?strType=1&SYY=2022&SMM=01&SDD=01&EYY=2022&EMM=04&EDD=01";
        Document document = Jsoup.connect(url).get();

        Elements tjSongs = document.select("#BoardType1 > table > tbody > tr");
        for (int i = 1; i < tjSongs.size(); i++) {
            Element song = tjSongs.get(i);
            String number = song.select("td:nth-child(2)").text();
            String title = song.select("td:nth-child(3)").text();
            String singer = song.select("td:nth-child(4)").text();
            String link = "www.youtube.com/watch?v=" + youtube.search("tj+"+number);
            query.append("insert into song values(")
                    .append("'").append(number).append("', ")
                    .append("'").append(title).append("', ")
                    .append("'").append(singer).append("', ")
                    .append("0, ")
                    .append("'").append(link).append("'")
                    .append("); \n");

        }
        MakeSQLFile(query.toString());
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

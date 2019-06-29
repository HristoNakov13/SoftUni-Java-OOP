package onlineRadioDatabase;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bfr = new BufferedReader(new InputStreamReader(System.in));
        int inputCount = Integer.parseInt(bfr.readLine());

        SongDataBase dataBase = new SongDataBase();
        int addedSongs = 0;

        for (int i = 0; i < inputCount; i++) {
            String[] songData = bfr.readLine().split(";");
            String artistName = songData[0];
            String songName = songData[1];
            String length = songData[2];
            try {
                Song song = new Song(artistName, songName, length);
                System.out.println("Song added.");
                dataBase.addSong(song);
                addedSongs++;
            } catch (Exception e) {
                System.out.println(e.getCause().getMessage());
            }
        }
        System.out.println(String.format("Songs added: %d%n%s", addedSongs, dataBase.getTotalLengthOfSongs()));

    }
}

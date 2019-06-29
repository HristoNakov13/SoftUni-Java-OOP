package onlineRadioDatabase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SongDataBase {
    private List<Song> songs;

    public SongDataBase() {
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        this.songs.add(song);
    }

    public String getTotalLengthOfSongs() {
        int hours = 0;
        int minutes = 0;
        int seconds = 0;

        for(Song song : this.songs) {
            int[] playTime = Arrays.stream(song.getLength().split(":")).mapToInt(Integer::parseInt).toArray();
            int songMins = playTime[0];
            int songSecs = playTime[1];
            minutes += songMins;
            seconds += songSecs;

            if (seconds > 59) {
                minutes++;
                seconds %= 60;
            }

            if(minutes > 59) {
                hours++;
                minutes %= 60;
            }
        }
        return String.format("Playlist length: %dh %dm %ds", hours, minutes, seconds);
    }
}

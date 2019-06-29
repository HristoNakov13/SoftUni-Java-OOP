package onlineRadioDatabase;

import java.util.Arrays;

public class Song {
    private String artistName;
    private String songName;
    private String length;

    public Song(String artistName, String songName, String length) throws Exception {
        this.setArtistName(artistName);
        this.setSongName(songName);
        this.setLength(length);
    }

    private void setArtistName(String artistName) throws Exception {


        if (artistName.length() < 3 || artistName.length() > 20) {
            throw new InvalidArtistNameException("Artist name should be between 3 and 20 symbols.");
        }
        this.artistName = artistName;
    }

    private void setSongName(String songName) throws InvalidSongNameException {
        if (songName.length() < 3 || songName.length() > 20) {
            throw new InvalidSongNameException("Song name should be between 3 and 30 symbols.");
        }
        this.songName = songName;
    }

    private void setLength(String length) throws InvalidSongLengthException {
        int[] playTime = Arrays.stream(length.split(":")).mapToInt(Integer::parseInt).toArray();
        int minutes = playTime[0];
        int seconds = playTime[1];

        InvalidSongLengthException exception = new InvalidSongLengthException("Invalid song length.");
        if (minutes < 0 || minutes > 14) {
            exception.initCause(new InvalidSongMinutesException("Song minutes should be between 0 and 14."));
            throw exception;
        } else if (seconds < 0 || seconds > 59) {
            exception.initCause(new InvalidSongSecondsException("Song seconds should be between 0 and 59."));
            throw exception;
        }

        this.length = length;
    }

    public String getLength() {
        return this.length;
    }
}

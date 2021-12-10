package com.example.musicproject;

public class Music {

    private int musicID;
    private String SongName;
    private String YearRealsed;
    private String ArtistName;

    public Music() {
        musicID = -1;
    }

    public int getMusicID() { return musicID; }

    public void setMusicID(int musicID) { this.musicID = musicID; }

    public String getSongName() {return SongName; }

    public void setSongName(String Songname) { this.SongName = Songname; }

    public String getYearRealsed() { return YearRealsed; }

    public void setYearRealsed(String yearRealsed ) { this.YearRealsed = yearRealsed; }

    public String getArtistName() { return ArtistName; }

    public void setArtistName(String artistName ) { this.ArtistName = artistName; }
}

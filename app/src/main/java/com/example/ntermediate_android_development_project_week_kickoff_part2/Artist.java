package com.example.ntermediate_android_development_project_week_kickoff_part2;

public class Artist {
    private String artistId;
    private String artistName;
    private String artistGenre;

    public Artist(String id){
        //this constructor is required
    }

    public Artist(String artistId, String artistName, String artistGenre) {
        this.artistId = artistId;
        this.artistName = artistName;
        this.artistGenre = artistGenre;
    }

    public String getArtistId() {
        return artistId;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getArtistGenre() {
        return artistGenre;
    }

}

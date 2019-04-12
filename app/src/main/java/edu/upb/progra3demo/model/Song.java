package edu.upb.progra3demo.model;

import com.google.gson.annotations.Expose;

public class Song {

    @Expose
    private int id;

    @Expose
    private String playlistTitle;

    @Expose
    private int coverImage;

    @Expose
    private String songTitle;

    @Expose
    private String songArtist;

    @Expose
    private int audioFile;

    public Song(int id, int coverImage, String songTitle, String songArtist) {
        this.id = id;
        this.coverImage = coverImage;
        this.songTitle = songTitle;
        this.songArtist = songArtist;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public int getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(int coverImage) {
        this.coverImage = coverImage;
    }

    public String getSongTitle() {
        return songTitle;
    }

    public void setSongTitle(String songTitle) {
        this.songTitle = songTitle;
    }

    public String getSongArtist() {
        return songArtist;
    }

    public void setSongArtist(String songArtist) {
        this.songArtist = songArtist;
    }

    public int getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(int audioFile) {
        this.audioFile = audioFile;
    }
}

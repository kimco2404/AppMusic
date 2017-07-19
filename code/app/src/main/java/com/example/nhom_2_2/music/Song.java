package com.example.nhom_2_2.music;

/**
 * Created by Calm on 7/16/2017.
 */

public class Song {

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public int getFile() {
        return File;
    }

    public void setFile(int file) {
        File = file;
    }

    private String Title;
    private int File;

    public Song(String title, int file) {
        Title = title;
        File = file;
    }
}

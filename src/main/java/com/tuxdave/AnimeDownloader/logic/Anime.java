package com.tuxdave.AnimeDownloader.logic;

import java.net.MalformedURLException;
import java.net.URL;

public class Anime {
    private String name;
    private int episodeNumber;
    private URL imagePath;

    public Anime(String _name, int _en, String _imagePath) throws MalformedURLException {
        name = _name;
        episodeNumber = _en;
        imagePath = new URL(_imagePath);
    }
    public Anime() throws MalformedURLException {
        this("",0,"");
    }

    public String getName() {
        return name;
    }

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public URL getImagePath() {
        return imagePath;
    }

    //"dunder" methods
    @Override
    public String toString() {
        return name + " - " + episodeNumber + " - " + imagePath;
    }
}

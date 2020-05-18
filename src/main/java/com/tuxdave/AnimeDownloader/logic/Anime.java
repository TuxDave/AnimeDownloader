package com.tuxdave.AnimeDownloader.logic;

import java.net.MalformedURLException;
import java.net.URL;

public class Anime {
    private String name;
    private int episodeNumber;
    private String episodeLength;
    private URL imagePath;
    private URL link;

    public Anime(String _name, int _en, String _el, String _imagePath, String _link) throws MalformedURLException {
        name = _name;
        episodeNumber = _en;
        episodeLength = _el;
        imagePath = new URL(_imagePath);
        link = new URL(_link);
    }
    public Anime() {
        name = "";
        episodeNumber = 0;
        episodeLength = "";
        imagePath = getClass().getClassLoader().getResource("icons/segnapostoImage.png");
        link = null;
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

    public URL getLink() {
        return link;
    }

    public String getEpisodeLength() {
        return episodeLength;
    }

    //"dunder" methods
    @Override
    public String toString() {
        return name + " - " + episodeNumber;
    }
}

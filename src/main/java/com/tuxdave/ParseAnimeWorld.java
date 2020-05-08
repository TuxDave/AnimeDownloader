package com.tuxdave;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ParseAnimeWorld{
    private String url;
    private String[] urlsEpisodes;
    private String[] downloadLinks = null;
    private int nEpisodes;
    private int current;
    private Document page;
    private boolean scraping = false;

    static private boolean downloading = false;
    static private int currentDownloading = 0;

    public ParseAnimeWorld(String _url)throws IOException {
        url = _url;
        page = Jsoup.connect(url).userAgent("Mozilla").get();
        nEpisodes = getEpisodesNumber();
        current = 0;
        urlsEpisodes = getOtherEpisodesLink();
    }

    private int getEpisodesNumber(){
        int ep = Integer.parseInt(page.body().getElementsByTag("font").html());
        return ep;
    }

    public int getEpisodes() {
        return nEpisodes;
    }

    public String[] getOtherEpisodesLink(){
        String[] s = new String[nEpisodes];
        Elements els = page.getElementsByAttributeValue("data-title","0");
        for(int i = 0; i < nEpisodes+1; i++){
            if(i > 1){
                s[i-1] = "https://www.animeworld.tv" + els.get(i).attr("href");
            }else{
                s[0] = "https://www.animeworld.tv" + els.get(i).attr("href");
            }
        }
        return s;
    }

    public boolean isScraping(){
        return scraping;
    }

    public String getCurrentDownloadLink(){
        Elements es = page.body().getElementsByAttributeValue("id","downloadLink");
        for(Element e : es){
            if(e.html().contains("Alternativo") || e.html().contains("Alternative")){
                return e.attr("href");
            }
        }
        return "";
    }

    public int getCurrent(){
        return current;
    }

    public void scrapeAllEpisodeDownloadLink()throws IOException{
        downloadLinks = new String[nEpisodes];
        scraping = true;
        Thread t1 = new Work();
        t1.start();
    }

    public String[] getAllEpisodeDownloadLink(){
        return downloadLinks;
    }

    public static boolean isDownloading(){
        return downloading;
    }
    public static int getCurrentDownloading(){
        return currentDownloading + 1;
    }

    private class Work extends Thread{
        @Override
        public void run() {
            super.run();
            downloadLinks = new String[nEpisodes];
            scraping = true;
            for(; current < nEpisodes; current++){
                try {
                    ParseAnimeWorld parser = new ParseAnimeWorld(urlsEpisodes[current]);
                    downloadLinks[current] = parser.getCurrentDownloadLink();
                    System.out.println("[INFO] - Episodio " + Integer.toString(current + 1) + " trovato!");
                } catch (IOException e) {
                }
            }
            scraping = false;
        }
    }

    public static class EpisodesDownloader extends Thread{//suportati solo file MP4,

        //variabili in aggiunta a quelle di thread
        String[] episodelinks = null;
        String downloadFolderPath = null;
        String animeName = null;
        int nEpisodes = 0;
        int start = 0;
        int stop = 0;

        public EpisodesDownloader(String[] _episodeLinks, String _downloadFolderPath, String _animeName, int _start, int _stop){
            super();
            currentDownloading = 0;//azzera il counter dei downloads
            episodelinks = _episodeLinks;
            downloadFolderPath = _downloadFolderPath;
            if(downloadFolderPath.toCharArray()[downloadFolderPath.length() - 1] != '/'){//controllo sulla path
                downloadFolderPath += '/';
            }
            animeName = _animeName;
            nEpisodes = episodelinks.length;
            if(_start <= nEpisodes && _start > 0){
                start = _start;
            }else{
                start = 1;
            }
            start--;
            if(_stop > start && _stop <= nEpisodes){
                stop = _stop;
            }else{
                stop = nEpisodes;
            }
        }
        public EpisodesDownloader(String[] _episodeLinks, String _downloadFolderPath, String _animeName){
            this(_episodeLinks, _downloadFolderPath, _animeName, 0, _episodeLinks.length);
        }

        @Override
        public void run() {
            super.run();
            downloading = true;
            for(; start < stop; start++){
                currentDownloading = start;
                try {
                    FileUtils.copyURLToFile(new URL(episodelinks[start]), new File(downloadFolderPath + animeName + "_ep" + (start+1) + ".mp4"), 20000,20000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            downloading = false;
            currentDownloading = 0;
        }
    }

}

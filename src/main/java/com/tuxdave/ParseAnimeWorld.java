package com.tuxdave;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseAnimeWorld{
    private String url;
    private String[] urlsEpisodes;
    private String[] downloadLinks = null;
    private int nEpisodes;
    private int current;
    private Document page;
    private boolean scraping = false;

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

}

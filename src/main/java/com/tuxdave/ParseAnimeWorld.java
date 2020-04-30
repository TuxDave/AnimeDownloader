package com.tuxdave;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ParseAnimeWorld{
    private String url;
    private String[] urlsEpisodes;
    private int nEpisodes;
    private int current;
    private Document page;

    public ParseAnimeWorld(String _url)throws IOException {
        url = _url;
        page = Jsoup.connect(url).userAgent("Mozzilla").get();
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

    public String getCurrentDownloadLink(){
        Elements es = page.body().getElementsByAttributeValue("id","downloadLink");
        for(Element e : es){
            if(e.html().contains("Alternativo") || e.html().contains("Alternative")){
                return e.attr("href");
            }
        }
        return "";
    }

    public String[] getAllEpisodeDownloadLink()throws IOException{
        String[] downloadLinks = new String[nEpisodes];
        for(; current < nEpisodes; current++){
            ParseAnimeWorld parser = new ParseAnimeWorld(urlsEpisodes[current]);
            downloadLinks[current] = parser.getCurrentDownloadLink();
            System.out.println("[INFO] - Episodio " + Integer.toString(current + 1) + " trovato!");
        }
        return downloadLinks;
    }
    /*
    Elements es = page.body().getElementsByAttributeValue("id","downloadLink");
        for(Element e : es){
            if(e.html().contains("Alternativo") || e.html().contains("Alternative")){
                String a = e.attr("href");
                System.out.println(a);
            }
        }
    */
}

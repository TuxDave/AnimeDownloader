package com.tuxdave.AnimeDownloader.logic;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class ParseAnimeWorld {
    private String url;
    private String[] urlsEpisodes;
    private String[] downloadLinks = null;
    private String animeName;
    private int nEpisodes;
    private int current;
    private Document page;
    private boolean scraping = false;

    static private boolean downloading = false;
    static private int currentDownloading = 0;

    public ParseAnimeWorld(String _url) throws IOException, IllegalArgumentException {
        url = _url;
        page = Jsoup.connect(url.contains("https://www.animeworld.tv") ? url : "https://www.animeworld.tv" + url).userAgent("Mozilla").get();
        nEpisodes = getEpisodesNumber();
        current = 0;
        urlsEpisodes = getOtherEpisodesLink();
        //parse anime name
        Elements es = page.getElementsByTag("h2");
        for (Element e : es) {
            animeName = e.getElementsByAttribute("data-jtitle").html();
        }
    }

    public ParseAnimeWorld() {
        url = "";
        page = new Document("");
        nEpisodes = 0;
        current = 0;
        urlsEpisodes = new String[0];
        animeName = "";
    }

    public String getAnimeName() {
        return animeName;
    }

    private int getEpisodesNumber() {
        String[] temp2 = page.body().getElementsByTag("dd").html().split("\n");
        String temp = temp2[temp2.length - 3];
        temp = temp.replace(" ", "");
        if (temp.contains("+")) {
            String temp1 = "";
            for (char c : temp.toCharArray()) {
                if (c != '+') {
                    temp1 += c;
                } else {
                    break;
                }
            }
            temp = temp1;
        }
        temp = temp.replaceAll("[^0-9]", "");
        int ep = Integer.parseInt(temp);
        return ep;
    }

    public int getEpisodes() {
        return nEpisodes;
    }

    public String[] getOtherEpisodesLink() {
        String[] s = new String[nEpisodes];

        Elements els = page.getElementsByClass("episodes range hidden");
        for (Element el : els) {
            el.attr("style", "display: block;");
        }

        els = page.getElementsByClass("episode");
        for (int i = 0; i < nEpisodes + 1; i++) {
            try {
                if (i >= 1) {
                    s[i - 1] = "https://www.animeworld.tv" + els.get(i).getElementsByTag("a").attr("href");
                    ;
                } else {
                    s[0] = "https://www.animeworld.tv" + els.get(i).getElementsByTag("a").attr("href");
                }
            } catch (IndexOutOfBoundsException e) {
                s[i - 1] = null;
            }
        }
        return s;
    }

    public boolean isScraping() {
        return scraping;
    }

    public String getCurrentDownloadLink() {//TODO: aggiustare questo scraper perchè probabilmente hanno cambiato leggermente per ottenere il link download
        Elements es = page.body().getElementsByAttributeValue("id", "alternativeDownloadLink");
        String r = "";
        for (Element e : es) {
            if (e.html().contains("Alternativo") || e.html().contains("Alternative")) {
                r = e.attr("href");
            } else {
                r = "";
            }
        }
        return r;
    }

    public String getEpisodedLength() {
        Element e = page.body().getElementsByAttributeValue("class", "meta col-sm-12").get(1);
        Elements es = e.getElementsByTag("dd");
        String r = "";
        for (Element e1 : es) {
            if (e1.html().contains("min")) {
                System.out.println();
                return e1.html();
            }
        }
        if (r.equals("")) {
            r = "24min";
        }
        return r;
    }

    public int getCurrent() {
        return current;
    }

    public void scrapeAllEpisodeDownloadLink() throws IOException {
        downloadLinks = new String[nEpisodes];
        scraping = true;
        Thread t1 = new Work();
        t1.start();
    }

    public String[] getAllEpisodeDownloadLink() {
        return downloadLinks;
    }

    public static boolean isDownloading() {
        return downloading;
    }

    public static int getCurrentDownloading() {
        return currentDownloading + 1;
    }

    public static class AnimeSearcher {
        private String keyWord;
        private Anime[] found; //willl be returned at final
        private Document animeFoundPage;

        //for external ask
        private static boolean searching = false;
        private static int animeFoundNumber = 0;
        private static int currentFound = 0;
        private static boolean allreadyFound = false;

        public AnimeSearcher(String _keyWord) throws IOException {
            keyWord = _keyWord;
            if (keyWord.equals("")) {
                throw new IOException("Inserire una parola chiave da cercare (come il nome di una serie Anime)");
            }
            animeFoundPage = Jsoup.connect("https://www.animeworld.tv/search?keyword=" + keyWord.replace(" ", "+")).userAgent("Mozilla").get();
        }

        public void search() {
            found = null;
            allreadyFound = false;

            final Elements[] titles = new Elements[1];
            titles[0] = animeFoundPage.getElementsByAttributeValue("class", "film-list");
            titles[0] = titles[0].get(0).getElementsByAttributeValue("class", "item");
            animeFoundNumber = titles[0].size();
            found = new Anime[animeFoundNumber];

            //start searching
            searching = true;
            currentFound = 0;

            new Thread() {
                @Override
                public void run() {
                    super.run();
                    for (int i = 0; i < animeFoundNumber; i++) {
                        currentFound = i;
                        ParseAnimeWorld p = new ParseAnimeWorld(); //si collega alla pagina dell'anime trovato
                        try {
                            p = new ParseAnimeWorld(titles[0].get(i).getElementsByAttribute("href").attr("href"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        try {
                            found[i] = new Anime(
                                    titles[0].get(i).getElementsByAttributeValue("class", "name").html(),//nome anime
                                    p.getEpisodesNumber(),//numero episodi
                                    p.getEpisodedLength(),//Lunghezza episodi
                                    titles[0].get(i).getElementsByTag("img").attr("src"), //percorso immagine
                                    titles[0].get(i).getElementsByTag("a").get(0).attr("href")//link alla serie anime
                            );
                        } catch (MalformedURLException e) {
                            found[i] = null;
                        }
                    }
                    allreadyFound = true;
                    searching = false;
                }
            }.start();
        }

        public static boolean isSearching() {
            return searching;
        }

        public static int getAnimeFoundNumber() {
            return animeFoundNumber;
        }

        public static int getCurrentFound() {
            return currentFound;
        }

        public Anime[] getFound() {
            if (!searching && allreadyFound) {//si deve aspettare la fine della ricerca o almeno di averne effettuata almeno una
                return found;
            } else {
                return null;
            }
        }
    }

    private class Work extends Thread {
        @Override
        public void run() {
            super.run();
            downloadLinks = new String[nEpisodes];
            scraping = true;
            for (; current < nEpisodes; current++) {
                try {
                    ParseAnimeWorld parser = new ParseAnimeWorld(urlsEpisodes[current]);
                    downloadLinks[current] = parser.getCurrentDownloadLink();
                    System.out.println("[INFO] - Episodio " + Integer.toString(current + 1) + " trovato!");
                } catch (Exception ignored) {
                }
            }
            scraping = false;
        }
    }

    public static class EpisodesDownloader extends Thread {//suportati solo file MP4,

        //variabili in aggiunta a quelle di thread
        String[] episodelinks = null;
        String downloadFolderPath = null;
        String animeName = null;
        int nEpisodes = 0;
        int start = 0;
        int stop = 0;

        /*
         * questa deve ricevere un array con tutti i link degli episodi da scaricare,
         *                       una path per il download,
         *                       un nome per salvare gli episodi
         *                       **Opzionalmente un punto dacui iniziare ed uno al quale finire
         * */
        public EpisodesDownloader(String[] _episodeLinks, String _downloadFolderPath, String _animeName) {
            this(_episodeLinks, _downloadFolderPath, _animeName, 0, _episodeLinks.length);
        }

        public EpisodesDownloader(String[] _episodeLinks, String _downloadFolderPath, String _animeName, int _start, int _stop) {
            super();
            currentDownloading = 0;//azzera il counter dei downloads
            episodelinks = _episodeLinks;
            downloadFolderPath = _downloadFolderPath;
            if (downloadFolderPath.toCharArray()[downloadFolderPath.length() - 1] != '/') {//controllo sulla path
                downloadFolderPath += '/';
            }
            animeName = _animeName;
            nEpisodes = episodelinks.length;
            if (_start <= nEpisodes && _start > 0) {
                start = _start;
            } else {
                start = 1;
            }
            start--;
            if (_stop > start && _stop <= nEpisodes) {
                stop = _stop;
            } else {
                stop = nEpisodes;
            }
        }

        @Override
        public void run() {
            super.run();
            downloading = true;
            for (; start < stop; start++) {
                currentDownloading = start;
                try {
                    FileUtils.copyURLToFile(new URL(episodelinks[start]), new File(downloadFolderPath + animeName + "_ep" + (start + 1) + ".mp4"), 20000, 20000);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            downloading = false;
            currentDownloading = 0;
        }
    }
}

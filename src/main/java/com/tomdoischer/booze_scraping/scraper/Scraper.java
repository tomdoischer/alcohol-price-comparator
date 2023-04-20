package com.tomdoischer.booze_scraping.scraper;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.service.WhiskyBottleUpdateService;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class Scraper extends AbstractScraper {

    public static final String baseUrl = "https://www.panalfred.cz/whisky/";

    public Scraper(WhiskyBottleUpdateService whiskyBottleUpdateService) {
        super(whiskyBottleUpdateService);
    }

    public void scrape() throws IOException {
        List<String> urlsToParse = getUrlsToParse();
        List<WhiskyBottleUpdate> whiskyBottleUpdates = new ArrayList<>();
        for (String url : urlsToParse) {
            parseOnePage(whiskyBottleUpdates, url);
        }

        System.out.println("");
    }

    private void parseOnePage(List<WhiskyBottleUpdate> updates, String url) throws IOException {
        Document doc = Jsoup
                .connect(url)
                .userAgent("?page=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Accept-Language", "*")
                .get();

        Elements products = doc.select("div.p-in");
        for (Element product : products) {
            String price = getPrice(product);
            String availability = getAvailability(product);
            String name = getName(product);
            String link = getLink(product);

            WhiskyBottleUpdate whiskyBottleUpdate = createWhiskyBottleUpdate(parsePrice(price),
                    parseInStock(availability), name, baseUrl + link);

            updates.add(whiskyBottleUpdate);
        }
    }

    private static String getLink(Element product) {
        return product.selectFirst("a").attr("href");
    }

    private static String getName(Element product) {
        return product.selectFirst("a").text();
    }

    private static String getAvailability(Element product) {
        return product.getElementsByClass("availability").text();
    }

    private static String getPrice(Element product) {
        return product.getElementsByClass("price price-final").text();
    }

    private static double parsePrice(String price) {
        int i = price.lastIndexOf(" ");
        String priceParts =  price.substring(0, i);
        String priceWithoutSpaces = priceParts.replaceAll(" ", "");
        return Double.parseDouble(priceWithoutSpaces);
    }

    private List<String> getUrlsToParse() throws IOException {
        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Accept-Language", "*")
                .get();

        Elements paginationElements = doc.getElementsByClass("pagination");
        String maximumPage = paginationElements.get(0).select("a").last().text();
        int maximumPageInt = Integer.parseInt(maximumPage);
        List<String> urls = new ArrayList<>();
        urls.add(baseUrl);
        for (int i = 2; i <= maximumPageInt; i++) {
            urls.add(baseUrl + "strana-" + i + "/");
        }

        return urls;
    }

    private boolean parseInStock(String availability) {
        return availability != null && !availability.isBlank() && availability.startsWith("Skladem");
    }

}

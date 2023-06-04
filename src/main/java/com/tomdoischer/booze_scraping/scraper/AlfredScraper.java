package com.tomdoischer.booze_scraping.scraper;

import com.tomdoischer.booze_scraping.entity.WhiskyBottleUpdate;
import com.tomdoischer.booze_scraping.service.WhiskyBottleService;
import com.tomdoischer.booze_scraping.service.WhiskyBottleUpdateService;

import com.tomdoischer.booze_scraping.stores.Store;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlfredScraper extends AbstractScraper {

    public static final String baseUrl = "https://www.panalfred.cz/whisky/";

    @Autowired
    public AlfredScraper(WhiskyBottleUpdateService whiskyBottleUpdateService, WhiskyBottleService whiskyBottleService) {
        super(whiskyBottleUpdateService, whiskyBottleService);
    }

    protected void processOnePage(List<WhiskyBottleUpdate> updates, String url) throws IOException {
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
                    parseInStock(availability), name, createLink(link), Store.ALFRED);

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

    protected List<String> getUrlsToParse() throws IOException {
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

    private String createLink(String endLink) {
        return "https://www.panalfred.cz" + endLink;
    }
}

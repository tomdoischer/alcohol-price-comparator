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
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class GWScraper extends AbstractScraper {

    public static final String baseUrl = "https://www.global-wines.cz/whisky";

    @Autowired
    public GWScraper(WhiskyBottleUpdateService whiskyBottleUpdateService, WhiskyBottleService whiskyBottleService) {
        super(whiskyBottleUpdateService, whiskyBottleService);
    }
    @Override
    protected List<String> getUrlsToParse() throws IOException {
        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Accept-Language", "*")
                .get();

        Element mainPaginationElement = doc.getElementsByClass("pager").first();
        assert mainPaginationElement != null;
        Elements paginationElements = mainPaginationElement.select("a");
        String maximumPage = Objects.requireNonNull(paginationElements.last()).text();
        int maximumPageInt = Integer.parseInt(maximumPage);
        List<String> urls = new ArrayList<>();
        urls.add(baseUrl);
        for (int i = 2; i <= maximumPageInt; i++) {
            urls.add(baseUrl + "?page=-" + i);
        }

        return urls;
    }

    @Override
    protected void processOnePage(List<WhiskyBottleUpdate> updates, String url) throws IOException {
        Document doc = Jsoup
                .connect(url)
                .userAgent("?page=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Accept-Language", "*")
                .get();
        Elements products = doc.getElementsByClass("product-box product-list__item");
        for (Element product : products) {
            String name = product.getElementsByClass("product-box__title").first().text();
            String availability = product.getElementsByClass("product-box__availability").first().text();
            String price = product.getElementsByClass("product-box__price").first().text();
            String link = getLink(product);

            WhiskyBottleUpdate whiskyBottleUpdate = createWhiskyBottleUpdate(parsePrice(price),
                    parseInStock(availability), name, createLink(link), Store.GLOBALWINES);

            updates.add(whiskyBottleUpdate);
        }
    }

    private static String getLink(Element product) {
        return product.selectFirst("a").attr("href");
    }

    private static double parsePrice(String price) {
        int i = price.lastIndexOf(" ");
        String priceParts =  price.substring(0, i);
        String priceWithoutSpaces = priceParts.replaceAll(" ", "");
        return Double.parseDouble(priceWithoutSpaces);
    }

    private boolean parseInStock(String availability) {
        return availability != null && !availability.isBlank() && availability.startsWith("Skladem");
    }

    private String createLink(String endLink) {
        return "https://www.global-wines.cz" + endLink;
    }
}

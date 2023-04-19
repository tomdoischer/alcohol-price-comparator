package com.tomdoischer.booze_scraping.scraper;

import com.tomdoischer.booze_scraping.entity.Bottle;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {

    public void scrape() throws IOException {
        String baseUrl = "https://www.panalfred.cz/whisky/";

        Document doc = Jsoup
                .connect(baseUrl)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                .header("Accept-Language", "*")
                .get();

        Elements products = doc.select("div.p-in");
        List<Bottle> bottles = new ArrayList<>();
        for (Element product : products) {

            String price = product.getElementsByClass("price price-final").text();
            String name = product.selectFirst("a").text();
            String link = product.selectFirst("a").attr("href");

            Bottle bottle = new Bottle();
            bottle.setName(name);
            bottle.setPrice(Double.parseDouble(price.split(" ")[0]));
            bottle.setLink(baseUrl + link);

            bottles.add(bottle);
        }

        // TODO handle pagination

        System.out.println("");
    }

}

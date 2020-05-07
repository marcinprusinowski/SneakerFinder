package pl.marcin.thymleafshoefinder.Interfacess;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import pl.marcin.thymleafshoefinder.Links.LinkBuilder;
import pl.marcin.thymleafshoefinder.Links.SneakerShop;
import pl.marcin.thymleafshoefinder.Model.Preferences;

import java.io.IOException;

public interface Parsable {

    void parseForShoes();

    static  Document assignDocument(SneakerShop sneakerShop, Preferences preferences) {
        Document document = new Document("");
        try {
            document = Jsoup.connect(Parsable.getUrl(sneakerShop,preferences)).timeout(10000).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return document;
    }

    static String getUrl(SneakerShop sneakerShop ,Preferences preferences) {
        LinkBuilder linkBuilder = new LinkBuilder();
        return linkBuilder.buildLink(sneakerShop, preferences);
    }
}


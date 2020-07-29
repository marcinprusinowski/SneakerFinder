package pl.marcin.thymleafshoefinder.ShoesSuppliers;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import pl.marcin.thymleafshoefinder.Interfacess.Parsable;
import pl.marcin.thymleafshoefinder.Links.SneakerShop;
import pl.marcin.thymleafshoefinder.Model.Preferences;
import pl.marcin.thymleafshoefinder.Model.Shoe;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

public class StreetSupply implements Parsable {
    private List<Shoe> shoeList;
    private Document document;
    private Preferences preferences;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;

    public StreetSupply() {
    }

    public StreetSupply(List<Shoe> shoeList, Preferences preferences, ExecutorService executorService, ReentrantLock reentrantLock) {
        this.shoeList = shoeList;
        this.preferences = preferences;
        this.executorService = executorService;
        this.reentrantLock = reentrantLock;
    }

    public List<Element> parseForShoes(){
        ShoeDataProvider shoeDataProvider = new ShoeDataProvider();
        this.document = Parsable.assignDocument(SneakerShop.STREETSUPLY , preferences);
        List<Element> elements = document.select("div.col-sm-6");

        for (Element element : elements){
            executorService.submit(()->{
                reentrantLock.lock();
                addShoeIfExists(shoeDataProvider, element);
                reentrantLock.unlock();
            });
        }
        return elements;
    }

    private void addShoeIfExists(ShoeDataProvider shoeDataProvider, Element element) {
        String name = shoeDataProvider.getName(element , ".product-name");
        if (shoeDataProvider.isNameCorrect(name,preferences)){
            String link = getLink(element);
            String linkImage = getImageLink(element);
            String price = getPrice(element);

            Shoe shoe = new Shoe()
                    .withName(name)
                    .withLink(link)
                    .withImageLink(linkImage)
                    .withPrice(price)
                    .build();

            shoeList.add(shoe);
        }
    }

    private String getImageLink(Element element ) {
        return "https://image.flaticon.com/icons/png/512/95/95019.png";
    }


    public String getPrice(Element element) {
        String price ="";
        price  = element.select(".product-price span").first().text();
        return price.replace("zł" ," PLN");
    }

    public String getLink(Element element) {
        String link = "https://streetsupply.pl/";
        link += element.select("a").attr("href");
        return link;
    }
}

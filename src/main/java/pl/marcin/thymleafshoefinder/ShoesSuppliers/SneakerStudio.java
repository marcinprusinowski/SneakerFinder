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

public class SneakerStudio implements Parsable {
    private List<Shoe> shoeList;
    private Document document;
    private Preferences preferences;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;

    public SneakerStudio(List<Shoe> shoeList, Preferences preferences, ExecutorService executorService, ReentrantLock reentrantLock) {
        this.shoeList = shoeList;
        this.preferences = preferences;
        this.executorService = executorService;
        this.reentrantLock = reentrantLock;
    }

    public void parseForShoes(){
        ShoeDataProvider shoeDataProvider = new ShoeDataProvider();
        this.document = Parsable.assignDocument(SneakerShop.SNEAKERSTUDIO , preferences);
        List<Element> elements = this.document.select("div.product_wrapper");

        for (Element element : elements){
            executorService.submit(()->{
                reentrantLock.lock();
                addShoeIfExists(shoeDataProvider, element);
                reentrantLock.unlock();
            });
        }

    }

    private void addShoeIfExists(ShoeDataProvider shoeDataProvider, Element element) {
        String name = shoeDataProvider.getName(element  , "a.product-name");
        if (shoeDataProvider.isNameCorrect(name,preferences)){
            String link = getLink(element);
            String linkImage = getImageLink(element);
            String price = shoeDataProvider.getPrice(element ,".price" );

            Shoe shoe = new Shoe()
                    .withName(name)
                    .withLink(link)
                    .withImageLink(linkImage)
                    .withPrice(price)
                    .build();

            shoeList.add(shoe);

        }
    }


    private String getImageLink(Element element) {
        String imageLink ="https://sneakerstudio.pl/";
        imageLink += element.select(".product_icon_item img").attr("data-lazy");
        return imageLink;
    }

    private String getLink(Element element) {
        String link = "https://sneakerstudio.pl/";
        link += element.select("a.product_icon_item").attr("href");
        return link;
    }
}

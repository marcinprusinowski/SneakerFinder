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

public class WorldBox implements Parsable {
    private List<Shoe> shoeList;
    private Document document;
    private Preferences preferences;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;

    public WorldBox() {
    }

    public WorldBox(List<Shoe> shoeList, Preferences preferences, ExecutorService executorService, ReentrantLock reentrantLock) {
        this.shoeList = shoeList;
        this.preferences = preferences;
        this.executorService = executorService;
        this.reentrantLock = reentrantLock;
    }

    public List<Element> parseForShoes(){
        ShoeDataProvider shoeDataProvider = new ShoeDataProvider();
        this.document = Parsable.assignDocument(SneakerShop.WORLDBOX , preferences);
        List<Element> elements = document.select("div.product");

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
        String name = shoeDataProvider.getName(element , ".product-details p");
        if (shoeDataProvider.isNameCorrect(name , preferences)){
            String link = shoeDataProvider.getLink(element , "a" , "href");
            String linkImage = shoeDataProvider.getImageLink(element , "a img" , "data-echo");
            String price = getPrice(element);
            List<String> sizes = shoeDataProvider.getSizes(element, ".sizes ul li span");
            Shoe shoe = new Shoe()
                    .withName(name)
                    .withLink(link)
                    .withImageLink(linkImage)
                    .withPrice(price)
                    .withSizes(sizes)
                    .build();

            shoeList.add(shoe);
        }
    }


    public String getPrice(Element element) {
        String pprice ="";
        String dprice ="";
        dprice  = element.select(".single-price-tag").text();
        pprice  = element.select(".dp").text();

        if (pprice.isEmpty()){
            return dprice;
        }

        return pprice ;
    }
}

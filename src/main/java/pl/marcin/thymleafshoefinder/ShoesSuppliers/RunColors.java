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

public class RunColors implements Parsable {
    private List<Shoe> shoeList;
    private Document document;
    private Preferences preferences;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;

    public RunColors() {
    }

    public RunColors(List<Shoe> shoeList, Preferences preferences, ExecutorService executorService, ReentrantLock reentrantLock) {
        this.shoeList = shoeList;
        this.preferences = preferences;
        this.executorService = executorService;
        this.reentrantLock = reentrantLock;
    }

    public List<Element> parseForShoes(){
        ShoeDataProvider shoeDataProvider = new ShoeDataProvider();
        this.document = Parsable.assignDocument( SneakerShop.RUNCOLORS,preferences);
        List<Element> elements =document.select(".pList__item");

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
        String name = shoeDataProvider.getName(element,".pList__title");

        if (shoeDataProvider.isNameCorrect(name,preferences)){
            String link = getLink(element);
            String linkImage = shoeDataProvider.getImageLink(element, ".pList__picture img" , "src");
            String price = getPrice(element);
            List<String> sizes = shoeDataProvider.getSizes(element , ".pList__item_big__variants--item");
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
        String normalPrice ="";
        String newPrice ="";
        normalPrice  = element.select(".pList__price").text();
        newPrice  = element.select(".pList__price em").text();
        if (newPrice.isEmpty()){
            return normalPrice.replace("zł" ,"PLN");
        }
        return newPrice.replace("zł" , "PLN");
    }
    public String getLink(Element element){
        String link = "https://runcolors.pl";
        link += element.select(".pList__item_hover a").attr("href");
        return link;
    }
}

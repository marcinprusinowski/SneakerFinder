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

public class Chmielna implements Parsable {
    private List<Shoe> shoeList;
    private Document document;
    private Preferences preferences;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;

    public Chmielna(List<Shoe> shoeList, Preferences preferences, ExecutorService executorService, ReentrantLock reentrantLock) {
        this.shoeList = shoeList;
        this.preferences = preferences;
        this.executorService = executorService;
        this.reentrantLock = reentrantLock;
    }

    public void parseForShoes(){
        ShoeDataProvider shoeDataProvider = new ShoeDataProvider();
        this.document = Parsable.assignDocument( SneakerShop.CHMIELNA,preferences);
        List<Element> elements = document.select(".products__item");
        for (Element element : elements){
            executorService.submit(()->{
                reentrantLock.lock();
                addShoeIfExists(shoeDataProvider, element);
                reentrantLock.unlock();
            });
        }

    }

    private void addShoeIfExists(ShoeDataProvider shoeDataProvider, Element element) {
        String name = shoeDataProvider.getName(element , ".products__item-name a");
        if (shoeDataProvider.isNameCorrect(name,preferences)){
            String link = shoeDataProvider.getLink(element,"a" ,"href");
            String linkImage = shoeDataProvider.getImageLink(element,"a img" , "data-echo");
            String price = shoeDataProvider.getPrice(element,".products__item-price .price__tag");
            List<String> sizes = shoeDataProvider.getSizes(element,".sizes ul li");

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




}

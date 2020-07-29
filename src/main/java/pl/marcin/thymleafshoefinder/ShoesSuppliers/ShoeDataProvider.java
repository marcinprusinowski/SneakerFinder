package pl.marcin.thymleafshoefinder.ShoesSuppliers;

import org.jsoup.nodes.Element;
import pl.marcin.thymleafshoefinder.Model.Preferences;

import java.util.ArrayList;
import java.util.List;

public class ShoeDataProvider {

    public boolean isNameCorrect(String nameToCheck , Preferences preferences){
        nameToCheck = nameToCheck.toLowerCase();
        String modelExpected = preferences.getModel().toLowerCase();
        String brandExpected = preferences.getBrand().toLowerCase();
        boolean result = nameToCheck.contains(modelExpected) && nameToCheck.contains(brandExpected);
        return result;
    }

    public String getPrice(Element element , String cssQuery){
        String price ="";
        price = element.select(cssQuery).text();
        String[] prices = price.split(" ");
        if (prices.length > 1){
            price = prices[0];
        }
        price += " PLN";
        return price;
    }

    public String getImageLink(Element element , String cssQuery , String attribute){
        return element.select(cssQuery).attr(attribute);
    }

    public String getLink(Element element , String cssQuery , String attribute){
        return element.select(cssQuery).attr(attribute);
    }

    public String getName(Element element, String cssQuery){
        return element.select(cssQuery).text();
    }

    public List<String> getSizes(Element element, String cssQuery){

        List<String> sizes = new ArrayList<>();
        List<Element> elements = element.select(cssQuery);
        elements.forEach( span ->{
            sizes.add(span.text());
        });
        return sizes ;
    }
}

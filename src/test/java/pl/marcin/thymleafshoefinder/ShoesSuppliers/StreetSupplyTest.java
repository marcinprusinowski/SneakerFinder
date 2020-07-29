package pl.marcin.thymleafshoefinder.ShoesSuppliers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.marcin.thymleafshoefinder.Interfacess.Parsable;
import pl.marcin.thymleafshoefinder.Links.SneakerShop;
import pl.marcin.thymleafshoefinder.Model.Preferences;
import java.util.List;


class StreetSupplyTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<div class=\"col-sm-6 flex col-xs-6 mb20 col-md-3\">\n" +
                " <div id=\"product-tile-box\" class=\"product relative align-center w-100\" data-v-a3b14f5c>\n" +
                "  <div class=\"block no-underline product-link relative\" data-v-a3b14f5c>\n" +
                "   <span class=\"badge-sale\" data-v-a3b14f5c>Wyprzedaż</span> \n" +
                "   <a href=\"/nike-wmns-air-force-1-07-low-black-315115-038?childSku=315115-038-c\" data-testid=\"productLink\" data-v-a3b14f5c>\n" +
                "    <div class=\"product-image relative pt20\" data-v-a3b14f5c>\n" +
                "     <div data-testid=\"productImage\" class=\"product-image product-cover__thumb product-image--height\" data-v-295f98c8 data-v-a3b14f5c>\n" +
                "      <img src=\"/assets/placeholderGray.png\" alt=\"Nike WMNS Air Force 1 &quot;07 Low Black (315115-038)\" class=\"product-image__placeholder\" style=\"\" data-v-295f98c8> \n" +
                "      <img alt=\"Nike WMNS Air Force 1 &quot;07 Low Black (315115-038)\" class=\"product-image__thumb\" style=\"display:none\" data-v-295f98c8> \n" +
                "      <img alt=\"Nike WMNS Air Force 1 &quot;07 Low Black (315115-038)\" class=\"product-image__thumb\" style=\"display:none\" data-v-295f98c8>\n" +
                "     </div>\n" +
                "    </div> \n" +
                "    <div class=\"product-attr px10 my20 hidden-xs\" data-v-a3b14f5c>\n" +
                "     <!---->\n" +
                "    </div> <p class=\"mb0 cl-black align-left mt20 product-name px10 uppercase\" data-v-a3b14f5c> Nike WMNS Air Force 1 \"07 Low Black (315115-038) </p> \n" +
                "    <div class=\"product-price relative px10 pb10 mt5 sale\" data-v-a3b14f5c>\n" +
                "     <span class=\"price-special cl-black\" data-v-a3b14f5c>289.00zł</span> \n" +
                "     <span class=\"price-original ml5 cl-black\" data-v-a3b14f5c>419.99zł</span> \n" +
                "     <!---->\n" +
                "    </div></a>\n" +
                "  </div>\n" +
                " </div>\n" +
                "</div>\n";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.STREETSUPLY ,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.STREETSUPLY , preferences);
        Assertions.assertEquals("https://streetsupply.pl/sklep?query=Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.STREETSUPLY , preferences);
        List<Element> elements = document.select("div.col-sm-6");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss()   {
        Assertions.assertEquals("Nike WMNS Air Force 1 \"07 Low Black (315115-038)" ,shoeDataProvider.getName(tag , ".product-name"));
    }
    @Test
    public void checkShoeLinkCss()   {
        StreetSupply streetSupply = new StreetSupply();
        Assertions.assertEquals("https://streetsupply.pl//nike-wmns-air-force-1-07-low-black-315115-038?childSku=315115-038-c" ,streetSupply.getLink(tag));
    }

    @Test
    public void checkShoePriceCss()   {
        StreetSupply streetSupply = new StreetSupply();
        Assertions.assertEquals("289.00 PLN" ,streetSupply.getPrice(tag));
    }


}
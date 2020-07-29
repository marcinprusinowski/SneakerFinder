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


class SneakerStudioTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<div class=\"product_wrapper col-md-4 col-xs-6\" data-id=\"5235\">\n" +
                " <strong class=\"icons\"></strong>\n" +
                " <div class=\"product_icon_wrapper\">\n" +
                "  <a class=\"product_icon_item\" href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"><img data-lazy=\"/pol_il_Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111-5235.jpg\" data-href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"></a>\n" +
                "  <a class=\"product_icon_item icon_slide _beforeslick img_hidden\" href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"><img data-lazy=\"/pol_pm_Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111-5235_2.jpg\" data-href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"></a>\n" +
                "  <a class=\"product_icon_item icon_slide _beforeslick img_hidden\" href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"><img data-lazy=\"/pol_pm_Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111-5235_3.jpg\" data-href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"></a>\n" +
                "  <a class=\"product_icon_item icon_slide _beforeslick img_hidden\" href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"><img data-lazy=\"/pol_pm_Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111-5235_4.jpg\" data-href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\"></a>\n" +
                " </div>\n" +
                " <h3><a class=\"product-name\" href=\"/product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html\" title=\"Buty męskie sneakersy Nike Air Force 1 Mid 315123 111\">Buty męskie sneakersy Nike Air Force 1 Mid 315123 111</a></h3>\n" +
                " <div class=\"product_prices\">\n" +
                "  <a class=\"label_special\" href=\"/search.php?special=y\" title=\"Wysyłka gratis!\"></a>\n" +
                "  <span class=\"max-price srp_formatted\">469,00 zł</span> &nbsp; \n" +
                "  <span class=\"price\">399,00 zł<span class=\"promo_percent\"> -15% </span></span>\n" +
                "  <span class=\"point-price\"> &nbsp; 399<span class=\"currency\"> PKT</span></span>\n" +
                "  <div class=\"price_gross_info\">\n" +
                "    brutto + \n" +
                "   <a class=\"shipping_info\" target=\"blank\" title=\"Sprawdź czasy i koszty wysyłki\" href=\"/Koszty-wysylki-cterms-pol-14.html\" data-product-id=\"5235\" data-product-size=\"\"> koszty wysyłki </a>\n" +
                "  </div>\n" +
                " </div>\n" +
                " <form class=\"add_to_basket_form\" action=\"basketchange.php\" method=\"post\">\n" +
                "  <input type=\"hidden\" name=\"product\" value=\"5235\">\n" +
                "  <input class=\"n60137_amount\" type=\"text\" name=\"number\" value=\"1\">\n" +
                "  <input type=\"hidden\" name=\"size\" value=\"uniw\">\n" +
                "  <a class=\"btn-small add_to_basket__button\"> Dodaj do koszyka </a>\n" +
                " </form>\n" +
                "</div>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.SNEAKERSTUDIO,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.SNEAKERSTUDIO , preferences);
        Assertions.assertEquals("https://sneakerstudio.pl/search.php?text=Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.SNEAKERSTUDIO,preferences);
        List<Element> elements = document.select("div.product_wrapper");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss()   {
        Assertions.assertEquals("Buty męskie sneakersy Nike Air Force 1 Mid 315123 111" ,shoeDataProvider.getName(tag , "a.product-name"));
    }
    @Test
    public void checkShoeLinkCss()   {
        SneakerStudio sneakerStudio = new SneakerStudio();
        Assertions.assertEquals("https://sneakerstudio.pl//product-pol-5235-Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111.html" ,sneakerStudio.getLink(tag));
    }

    @Test
    public void checkShoeImageLinkCss()   {
        SneakerStudio sneakerStudio = new SneakerStudio();
        Assertions.assertEquals("https://sneakerstudio.pl//pol_il_Buty-meskie-sneakersy-Nike-Air-Force-1-Mid-315123-111-5235.jpg" ,sneakerStudio.getImageLink(tag) );
    }


    @Test
    public void checkShoePriceCss()   {
        Assertions.assertEquals("399,00 PLN" ,shoeDataProvider.getPrice(tag,".price"));
    }
}
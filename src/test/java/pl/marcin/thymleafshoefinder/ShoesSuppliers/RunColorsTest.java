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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class RunColorsTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<li class=\"pList__item js--productListElement\" data-default-name=\"WMNS AIR FORCE 1 SHADOW CJ1641-100\" data-id=\"20216\" data-default-price=\"469.99\" data-brand=\"Nike\" data-default-category=\"SNEAKERS\" data-default-variant=\"niebieski\" data-position=\"1\" data-full-nice_url=\"https://runcolors.pl/sneakers/wmns_air_force_1_shadow_cj1641-100.html\"> \n" +
                " <div class=\"pList__item_hover\"> \n" +
                "  <div class=\"pList__item_big\"> \n" +
                "   <div class=\"pList__big__similar\"> \n" +
                "    <div class=\"pList__big__similar__item\"> \n" +
                "     <a href=\"/sneakers/wmns_air_force_1_sage_low_ar5339-201.html\"> <img alt=\"WMNS AIR FORCE 1 SAGE LOW AR5339-201\" src=\"https://runcolors.pl/product_picture/fit_in_75x75/wmns_air_force_1_sage_low_ar5339-201_nike_ar5339-201_8.jpg\"> </a> \n" +
                "    </div> \n" +
                "    <div class=\"pList__big__similar__item\"> \n" +
                "     <a href=\"/sneakers/wmns_air_force_1_sage_low_ar5339-002.html\"> <img alt=\"WMNS AIR FORCE 1 SAGE LOW AR5339-002\" src=\"https://runcolors.pl/product_picture/fit_in_75x75/wmns_air_force_1_sage_low_ar5339-002_nike_ar5339-002_6.jpg\"> </a> \n" +
                "    </div> \n" +
                "   </div> \n" +
                "  </div> \n" +
                "  <a href=\"/sneakers/wmns_air_force_1_shadow_cj1641-100.html\" class=\"pList__item__link\"> \n" +
                "   <div class=\"pList__picture\"> \n" +
                "    <img alt=\"WMNS AIR FORCE 1 SHADOW CJ1641-100\" src=\"https://runcolors.pl/product_picture/fit_in_424x424/wmns_air_force_1_shadow_cj1641-100_nike_cj1641-100_1.jpg\" class=\"pList__picture__img\" name=\"wmns_air_force_1_shadow_cj1641\" productid=\"20216\"> \n" +
                "   </div> \n" +
                "   <div class=\"pList__item_big__variants\"> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468105\">\n" +
                "     36\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468106\">\n" +
                "     36.5\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468107\">\n" +
                "     37.5\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468108\">\n" +
                "     38\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468109\">\n" +
                "     38.5\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468110\">\n" +
                "     39\n" +
                "    </div> \n" +
                "    <div class=\"pList__item_big__variants--item js--variant--item\" data-option=\"468111\">\n" +
                "     40\n" +
                "    </div> \n" +
                "   </div> <p class=\"pList__price\"> 469,99 zł </p> <span class=\"pList__title\"> Nike WMNS AIR FORCE 1 SHADOW CJ1641-100</span> </a> \n" +
                " </div> </li>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.RUNCOLORS,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.RUNCOLORS , preferences);
        Assertions.assertEquals("https://runcolors.pl/product/search_products.html?query=Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.RUNCOLORS,preferences);
        List<Element> elements = document.select(".pList__item");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss()  {
        Assertions.assertEquals("Nike WMNS AIR FORCE 1 SHADOW CJ1641-100" ,shoeDataProvider.getName(tag , ".pList__title"));
    }
    @Test
    public void checkShoeLinkCss()   {
        RunColors runColors = new RunColors();
        Assertions.assertEquals("https://runcolors.pl/sneakers/wmns_air_force_1_sage_low_ar5339-201.html" ,runColors.getLink(tag));
    }

    @Test
    public void checkShoeImageLinkCss()   {
        Assertions.assertEquals("https://runcolors.pl/product_picture/fit_in_424x424/wmns_air_force_1_shadow_cj1641-100_nike_cj1641-100_1.jpg" ,shoeDataProvider.getImageLink(tag,".pList__picture img" , "src"));
    }
    @Test
    public void checkShoePriceCss()   {
        RunColors runColors = new RunColors();
        Assertions.assertEquals("469,99 PLN" ,runColors.getPrice(tag));
    }

    @Test
    public void checkSizes(){
        List<String> sizes = new ArrayList<>(Arrays.asList("36", "36.5", "37.5", "38", "38.5", "39", "40"));
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".pList__item_big__variants--item") );
    }

}
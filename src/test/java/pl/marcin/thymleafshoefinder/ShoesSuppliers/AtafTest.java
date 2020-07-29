package pl.marcin.thymleafshoefinder.ShoesSuppliers;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.junit.jupiter.api.*;
import pl.marcin.thymleafshoefinder.Interfacess.Parsable;
import pl.marcin.thymleafshoefinder.Links.SneakerShop;
import pl.marcin.thymleafshoefinder.Model.Preferences;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class AtafTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 44);
        String html = "<div class=\"col-md-6 col-lg-4 no-pad\">\n" +
                " <div class=\"prod-wrapper\"> \n" +
                "  <a href=\"https://www.ataf.pl/782-nike-air-force-1-high-07-suede-ocean-bliss.html#\" class=\"item-hover\"> <img src=\"https://www.ataf.pl/31986-home_default/nike-air-force-1-high-07-suede-ocean-bliss.jpg\" alt=\"\" class=\"img-fluid mx-auto img-block\">\n" +
                "   <div class=\"prod-info-wrapper\">\n" +
                "    <h5 class=\"slider-product\">Nike Air Force 1 High 07 Suede Ocean Bliss</h5>\n" +
                "    <p class=\"slider-price\">419.00 PLN</p>\n" +
                "    <p class=\"slider-price old-price\">489.00 PLN</p>\n" +
                "   </div>\n" +
                "   <div class=\"size-info-wrapper d-flex justify-content-center align-content-center flex-wrap align-items-center\">\n" +
                "    <span href-size=\"1\" data-size=\"https://www.ataf.pl/782-nike-air-force-1-high-07-suede-ocean-bliss.html#/55885-rozmiar_nike_rozmiar_obuwie_męskie-eu_45\">Eu 45</span>\n" +
                "    <span href-size=\"1\" data-size=\"https://www.ataf.pl/782-nike-air-force-1-high-07-suede-ocean-bliss.html#/55887-rozmiar_nike_rozmiar_obuwie_męskie-eu_46\">Eu 46</span>\n" +
                "   </div> </a>\n" +
                " </div>\n" +
                "</div>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.ATAF,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.ATAF , preferences);
        Assertions.assertEquals("https://chmielna20.pl/products/Nike-Air-Force-1/keyword,Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.ATAF,preferences);
        List<Element> elements = document.select("div.no-pad");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss() throws IOException {
        Assertions.assertEquals("Nike Air Force 1 High 07 Suede Ocean Bliss" ,shoeDataProvider.getName(tag , "h5.slider-product"));
    }
    @Test
    public void checkShoeLinkCss() throws IOException {
        Assertions.assertEquals("https://www.ataf.pl/782-nike-air-force-1-high-07-suede-ocean-bliss.html#" ,shoeDataProvider.getLink(tag,"div.prod-wrapper a" , "href"));
    }
    @Test
    public void checkShoeImageLinkCss() throws IOException {
        Assertions.assertEquals("https://www.ataf.pl/31986-home_default/nike-air-force-1-high-07-suede-ocean-bliss.jpg" ,shoeDataProvider.getImageLink(tag,".img-fluid" ,"src"));
    }
    @Test
    public void checkShoePriceCss() throws IOException {
        Assertions.assertEquals("419.00 PLN" ,shoeDataProvider.getPrice(tag,".slider-price"));
    }
    @Test
    public void checkPrices(){
        List<String> sizes = new ArrayList<>(Arrays.asList("Eu 45", "Eu 46"));
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".size-info-wrapper span") );
    }


}
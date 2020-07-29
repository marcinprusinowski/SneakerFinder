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


class SklepKoszykarzaTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<div class=\"col-sm-6 col-md-4 col-xs-12 product\" data-product=\"170813\"> \n" +
                " <a href=\"https://sklepkoszykarza.pl/buty-wmns-nike-air-force-1-07-cv3030-100.html\" class=\"text-center\" title=\"women's nike air force 1 '07  (cv3030‑100)\"> <img src=\"https://sklepkoszykarza.pl/img/worldbox/lazyloader.jpg\" data-echo=\"https://blob.sxv.pl/shops/media/wbsquare2/2020/nike/170813/buty-wmns-nike-air-force-1-07-cv3030-100-5f0e25e681b64.jpg\" alt=\"women's nike air force 1 '07  (cv3030‑100)\"> <p class=\"n\">Women's Nike Air Force 1 '07 (CV3030‑100) </p><p class=\"s\">Kod produktu: <span>CV3030-100</span></p> <p class=\"p\"> <span class=\"ps pricebox\">419.99 PLN</span> </p> </a> \n" +
                " <div class=\"more\"> \n" +
                "  <div class=\"sizes\"> \n" +
                "   <p><span class=\"title\">Dostepne rozmiary:</span></p> \n" +
                "   <ul> \n" +
                "    <li data-size=\"10.5\"><span>42.5</span></li> \n" +
                "    <li data-size=\"11.0\"><span>43.0</span></li> \n" +
                "    <li data-size=\"11.5\"><span>44.0</span></li> \n" +
                "    <li data-size=\"12.0\"><span>44.5</span></li> \n" +
                "   </ul> \n" +
                "   <a href=\"#\" class=\"btn btn-primary btn-small addToBasket\">Do koszyka</a> \n" +
                "   <a href=\"https://sklepkoszykarza.pl/buty-wmns-nike-air-force-1-07-cv3030-100.html\" class=\"btn btn-default btn-small\">Szczegóły</a> \n" +
                "   <div class=\"product__favorite\"> \n" +
                "    <a href=\"#\"> <i class=\"icon icon-star\"><span>Zapamiętaj</span></i> </a> \n" +
                "   </div> \n" +
                "  </div> \n" +
                " </div> \n" +
                "</div>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.SKLEPKOSZYKARZA,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.SKLEPKOSZYKARZA , preferences);
        Assertions.assertEquals("https://sklepkoszykarza.pl/products/Nike-Air-Force-1-obuwie/keyword,Nike%20Air%20Force%201/category,2/item,72" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.SKLEPKOSZYKARZA,preferences);
        List<Element> elements = document.select("div.product");
        System.out.println(elements.get(0));
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss()   {
        Assertions.assertEquals("Women's Nike Air Force 1 '07 (CV3030‑100)" ,shoeDataProvider.getName(tag , "p.n"));
    }
    @Test
    public void checkShoeLinkCss()   {
        Assertions.assertEquals("https://sklepkoszykarza.pl/buty-wmns-nike-air-force-1-07-cv3030-100.html" ,shoeDataProvider.getLink(tag,"div.product a" ,"href"));
    }
    @Test
    public void checkShoeImageLinkCss()   {
        Assertions.assertEquals("https://blob.sxv.pl/shops/media/wbsquare2/2020/nike/170813/buty-wmns-nike-air-force-1-07-cv3030-100-5f0e25e681b64.jpg" ,shoeDataProvider.getImageLink(tag,"div.product a img" , "data-echo"));
    }
    @Test
    public void checkShoePriceCss()   {
        SklepKoszykarza sklepKoszykarza = new SklepKoszykarza();
        Assertions.assertEquals("419.99 PLN" ,sklepKoszykarza.getPrice(tag));
    }

    @Test
    public void checkSizes(){
        List<String> sizes = new ArrayList<>(Arrays.asList("42.5", "43.0", "44.0", "44.5"));
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".sizes ul li span") );
    }
}
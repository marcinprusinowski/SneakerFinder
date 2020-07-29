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


class WorldBoxTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<div class=\"block__flags product col-sm-6 col-md-4 col-xs-6\" data-product=\"171592\"> \n" +
                " <a href=\"https://worldbox.pl/buty-air-force-1-07-lv8-3-cj1379-101.html\" onclick=\"if (!window.__cfRLUnblockHandlers) return false; productClick(171592, 'https://worldbox.pl/buty-air-force-1-07-lv8-3-cj1379-101.html', 1);\" title=\"nike air force 1 '07 lv8 3 męskie białe (cj1379‑101)\" data-cf-modified-7661a3d9fa3882e5d482f96a-=\"\"> <img src=\"https://worldbox.pl/img/worldbox/lazyloader.jpg\" data-echo=\"https://blob.sxv.pl/shops/media/wbsquare2/2020/nike/171592/buty-air-force-1-07-lv8-3-cj1379-101-5f0d4f1488410.jpeg\" alt=\"nike air force 1 '07 lv8 3 męskie białe (cj1379‑101)\"> \n" +
                "  <div class=\"product-details\"> \n" +
                "   <p>Nike Air Force 1 '07 LV8 3 Męskie Białe (CJ1379‑101)</p> \n" +
                "   <span class=\"price-tag\"> <span class=\"single-price-tag\">469.99 PLN<br></span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"PLN\">(≈ 470 PLN)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"USD\">(≈ 120 USD)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"EUR\">(≈ 105 EUR)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"GBP\">(≈ 95 GBP)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"RUB\">(≈ 8499 RUB)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"CZK\">(≈ 2798 CZK)</span> <span class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"UAH\">(≈ 3259 UAH)</span> </span> \n" +
                "  </div> </a> \n" +
                " <div class=\"more\"> \n" +
                "  <div class=\"sizes\"> \n" +
                "   <p><span class=\"title\">Dostępne rozmiary:</span> <span class=\"sizeType\" data-sizetype=\"sizeus\">US</span> <span class=\"sizeType\" data-sizetype=\"sizeuk\">UK</span> <span class=\"sizeType active\" data-sizetype=\"sizeeu\">EU</span> <span class=\"sizeType\" data-sizetype=\"sizecm\">CM</span> </p> \n" +
                "   <ul> \n" +
                "    <li data-size=\"8.0\" data-sizeus=\"8.0\" data-sizeuk=\"7.0\" data-sizeeu=\"41.0\" data-sizecm=\"26.0\"> <span>41.0</span> </li> \n" +
                "    <li data-size=\"8.5\" data-sizeus=\"8.5\" data-sizeuk=\"7.5\" data-sizeeu=\"42.0\" data-sizecm=\"26.5\"> <span>42.0</span> </li> \n" +
                "    <li data-size=\"9.0\" data-sizeus=\"9.0\" data-sizeuk=\"8.0\" data-sizeeu=\"42.5\" data-sizecm=\"27.0\"> <span>42.5</span> </li> \n" +
                "    <li data-size=\"9.5\" data-sizeus=\"9.5\" data-sizeuk=\"8.5\" data-sizeeu=\"43.0\" data-sizecm=\"27.5\"> <span>43.0</span> </li> \n" +
                "    <li data-size=\"10.0\" data-sizeus=\"10.0\" data-sizeuk=\"9.0\" data-sizeeu=\"44.0\" data-sizecm=\"28.0\"> <span>44.0</span> </li> \n" +
                "    <li data-size=\"10.5\" data-sizeus=\"10.5\" data-sizeuk=\"9.5\" data-sizeeu=\"44.5\" data-sizecm=\"28.5\"> <span>44.5</span> </li> \n" +
                "    <li data-size=\"11.0\" data-sizeus=\"11.0\" data-sizeuk=\"10.0\" data-sizeeu=\"45.0\" data-sizecm=\"29.0\"> <span>45.0</span> </li> \n" +
                "    <li data-size=\"11.5\" data-sizeus=\"11.5\" data-sizeuk=\"10.5\" data-sizeeu=\"45.5\" data-sizecm=\"29.5\"> <span>45.5</span> </li> \n" +
                "    <li data-size=\"12.0\" data-sizeus=\"12.0\" data-sizeuk=\"11.0\" data-sizeeu=\"46.0\" data-sizecm=\"30.0\"> <span>46.0</span> </li> \n" +
                "   </ul> \n" +
                "   <a href=\"#\" class=\"btn btn-primary btn-small addToBasket\">Do koszyka</a> \n" +
                "   <a href=\"https://worldbox.pl/buty-air-force-1-07-lv8-3-cj1379-101.html\" onclick=\"if (!window.__cfRLUnblockHandlers) return false; home_prod_click('Nike Air Force 1 '07 LV8 3 Męskie Białe (CJ1379‑101)')\" class=\"btn btn-default btn-small\" data-cf-modified-7661a3d9fa3882e5d482f96a-=\"\">Szczegóły</a> \n" +
                "  </div> \n" +
                " </div> \n" +
                "</div>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.WORLDBOX,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.WORLDBOX , preferences);
        Assertions.assertEquals("https://worldbox.pl/products/Nike-Air-Force-1-obuwie/keyword,Nike%20Air%20Force%201/category,2/item,72" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.WORLDBOX,preferences);
        List<Element> elements = document.select("div.product");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss()   {
        Assertions.assertEquals("Nike Air Force 1 '07 LV8 3 Męskie Białe (CJ1379‑101)" ,shoeDataProvider.getName(tag , ".product-details p"));
    }
    @Test
    public void checkShoeLinkCss()   {
        Assertions.assertEquals("https://worldbox.pl/buty-air-force-1-07-lv8-3-cj1379-101.html" ,shoeDataProvider.getLink(tag,"a" , "href"));
    }
    @Test
    public void checkShoeImageLinkCss()   {
        Assertions.assertEquals("https://blob.sxv.pl/shops/media/wbsquare2/2020/nike/171592/buty-air-force-1-07-lv8-3-cj1379-101-5f0d4f1488410.jpeg" ,shoeDataProvider.getImageLink(tag,"a img" , "data-echo"));
    }
    @Test
    public void checkShoePriceCss()   {
        WorldBox wb = new WorldBox();
        Assertions.assertEquals("469.99 PLN" ,wb.getPrice(tag));
    }
    @Test
    public void checkPrices(){
        List<String> sizes = new ArrayList<>(Arrays.asList("41.0", "42.0", "42.5", "43.0", "44.0", "44.5", "45.0", "45.5", "46.0"));
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".sizes ul li span") );
    }
}
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
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class ChmielnaTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 44);
        String html = "<div class=\"col-sm-4 col-md-3 col-xs-6 products__item\"> \n" +
                " <a href=\"https://chmielna20.pl/w-air-force-1-07-nxn-ck3314-400.html\" title=\"nike by olivia kim <br/><b>wmns air force 1 '07</b> <br/>(ck3314‑400)\"> <img src=\"https://chmielna20.pl/img/worldbox/lazyloader.jpg\" data-echo=\"https://blob.sxv.pl/shops/media/wss-list/2019/nike/161905/nike-x-olivia-kim-wmns-air-force-1-07-ck3314-400-5ee9ea1274b84.jpg\" alt=\"nike by olivia kim <br/><b>wmns air force 1 '07</b> <br/>(ck3314‑400)\"> </a> \n" +
                " <div class=\"flag_container flag_container--top\"> \n" +
                " </div> \n" +
                " <div class=\"flag_container\"> \n" +
                "  <span class=\"flag flag-new\">Nowość</span> \n" +
                " </div> \n" +
                " <p class=\"products__item-name\"> <a href=\"https://chmielna20.pl/w-air-force-1-07-nxn-ck3314-400.html\">Nike by Olivia Kim <br><b>Wmns Air Force 1 '07</b> <br>(CK3314‑400)</a> </p> \n" +
                " <p class=\"products__item-price\"> <span> 499.99 PLN </span> <span>399.99 PLN</span> </p>\n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"PLN\">(≈ 400 PLN)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"USD\">(≈ 101 USD)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"EUR\">(≈ 89 EUR)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"GBP\">(≈ 81 GBP)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"RUB\">(≈ 7194 RUB)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"CZK\">(≈ 2377 CZK)</p> \n" +
                " <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"UAH\">(≈ 2743 UAH)</p> \n" +
                " <p></p> \n" +
                " <div class=\"box-flag-item-down\"> \n" +
                "  <div class=\"sizes \"> \n" +
                "   <p><span class=\"title\">Dostępne rozmiary:</span> <span class=\"sizeType\" data-sizetype=\"sizeus\">US</span> <span class=\"sizeType\" data-sizetype=\"sizeuk\">UK</span> <span class=\"sizeType active\" data-sizetype=\"sizeeu\">EU</span> <span class=\"sizeType\" data-sizetype=\"sizecm\">CM</span> </p> \n" +
                "   <ul> \n" +
                "    <li data-size=\"7.5\" data-sizeus=\"7.5\" data-sizeuk=\"5.0\" data-sizeeu=\"38.5\" data-sizecm=\"24.5\"> <span>38.5</span> </li> \n" +
                "    <li data-size=\"8.0\" data-sizeus=\"8.0\" data-sizeuk=\"5.5\" data-sizeeu=\"39.0\" data-sizecm=\"25.0\"> <span>39.0</span> </li> \n" +
                "    <li data-size=\"8.5\" data-sizeus=\"8.5\" data-sizeuk=\"6.5\" data-sizeeu=\"40.0\" data-sizecm=\"25.5\"> <span>40.0</span> </li> \n" +
                "    <li data-size=\"9.0\" data-sizeus=\"9.0\" data-sizeuk=\"6.5\" data-sizeeu=\"40.5\" data-sizecm=\"26.0\"> <span>40.5</span> </li> \n" +
                "    <li data-size=\"10.0\" data-sizeus=\"10.0\" data-sizeuk=\"7.5\" data-sizeeu=\"42.0\" data-sizecm=\"27.0\"> <span>42.0</span> </li> \n" +
                "    <li data-size=\"11.5\" data-sizeus=\"11.5\" data-sizeuk=\"9.0\" data-sizeeu=\"44.0\" data-sizecm=\"28.5\"> <span>44.0</span> </li> \n" +
                "    <li data-size=\"13.0\" data-sizeus=\"13.0\" data-sizeuk=\"10.5\" data-sizeeu=\"45.5\" data-sizecm=\"30.0\"> <span>45.5</span> </li> \n" +
                "   </ul> \n" +
                "   <div class=\"size__box--name\"> \n" +
                "    <p class=\"products__item-name\"> Nike by Olivia Kim <br><b>Wmns Air Force 1 '07</b> <br>(CK3314‑400) </p> \n" +
                "    <p class=\"products__item-price\"> <span class=\"price__tag price__tag--special\">499.99 PLN</span> <span class=\"price__tag\"> 399.99 PLN </span> </p>\n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"PLN\">(≈ 400 PLN)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"USD\">(≈ 101 USD)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"EUR\">(≈ 89 EUR)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"GBP\">(≈ 81 GBP)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"RUB\">(≈ 7194 RUB)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"CZK\">(≈ 2377 CZK)</p> \n" +
                "    <p class=\"select_currency currency hide currency-price-tag\" data-currency_key=\"UAH\">(≈ 2743 UAH)</p> \n" +
                "    <p></p> \n" +
                "   </div> \n" +
                "   <a class=\"size__box_link\" href=\"https://chmielna20.pl/w-air-force-1-07-nxn-ck3314-400.html\" title=\"nike by olivia kim <br/><b>wmns air force 1 '07</b> <br/>(ck3314‑400)\"></a> \n" +
                "  </div> \n" +
                " </div> \n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "Process finished with exit code 0\n";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.CHMIELNA,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.CHMIELNA , preferences);
        Assertions.assertEquals("https://chmielna20.pl/products/Nike-Air-Force-1/keyword,Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.CHMIELNA,preferences);
        List<Element> elements = document.select(".products__item");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss() throws IOException {
        Assertions.assertEquals("Nike by Olivia Kim Wmns Air Force 1 '07 (CK3314‑400)" ,shoeDataProvider.getName(tag , ".products__item-name a"));
    }
    @Test
    public void checkShoeLinkCss() throws IOException {
        Assertions.assertEquals("https://chmielna20.pl/w-air-force-1-07-nxn-ck3314-400.html" ,shoeDataProvider.getLink(tag,"a" ,"href"));
    }
    @Test
    public void checkShoeImageLinkCss() throws IOException {
        Assertions.assertEquals("https://blob.sxv.pl/shops/media/wss-list/2019/nike/161905/nike-x-olivia-kim-wmns-air-force-1-07-ck3314-400-5ee9ea1274b84.jpg" ,shoeDataProvider.getImageLink(tag,"a img" , "data-echo"));
    }
    @Test
    public void checkShoePriceCss() throws IOException {
        Assertions.assertEquals("499.99 PLN" ,shoeDataProvider.getPrice(tag,".products__item-price .price__tag"));
    }
    @Test
    public void checkPrices(){
        List<String> sizes = new ArrayList<>(Arrays.asList("38.5", "39.0","40.0", "40.5", "42.0", "44.0", "45.5"));
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".sizes ul li span") );
    }




}
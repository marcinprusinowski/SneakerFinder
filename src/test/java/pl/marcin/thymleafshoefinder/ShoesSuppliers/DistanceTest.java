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


class DistanceTest {
    private Preferences preferences;
    private Element tag;
    private ShoeDataProvider shoeDataProvider;
    @BeforeEach
    public void init(){
        this.shoeDataProvider= new ShoeDataProvider();
        this.preferences = new Preferences("Nike" , "Air Force 1" , 0);
        String html = "<li class=\"item product product-item\"> \n" +
                " <div class=\"product-item-info\" data-container=\"product-grid\" itemscope itemtype=\"//schema.org/product\"> \n" +
                "  <div class=\"related-products arp-container-list\" data-product-id=\"252539\">\n" +
                "   <div class=\"thumbs-up\">\n" +
                "    <span class=\"fa fa-caret-up\"></span>\n" +
                "   </div>\n" +
                "   <div class=\"arp-container-item\"></div>\n" +
                "   <div class=\"thumbs-down\">\n" +
                "    <span class=\"fa fa-caret-down\"></span>\n" +
                "   </div>\n" +
                "  </div>\n" +
                "  <div class=\"product-item-labels\">\n" +
                "   <div class=\"labels-label\"></div> \n" +
                "   <span class=\"labels-discount\" id=\"label_discount_5f0eceb54ba43\"></span> \n" +
                "   <script>\n" +
                "                            require(['Unity_DisplayDiscount/js/product/percentAmount', 'domReady!'], function (percentAmount) {\n" +
                "                                var element = document.getElementById('label_discount_5f0eceb54ba43');\n" +
                "                                var dsc = percentAmount(\n" +
                "                                    {\n" +
                "                                        priceContainer: \".price-box[data-price-box=\\\"product-id-252539\\\"]\"\n" +
                "                                    },\n" +
                "                                    element\n" +
                "                                );\n" +
                "\n" +
                "                                if (dsc.canShowDiscount()) {\n" +
                "                                    element.innerText = dsc.getSavedPercent()\n" +
                "                                }\n" +
                "                            });</script>\n" +
                "  </div> \n" +
                "  <a href=\"https://distance.pl/buty air force 1 lv8 3 (gs)-n-cj4092100\" class=\"product photo product-item-photo\" tabindex=\"-1\"> <span class=\"product-image-container\" style=\"width:600px;\"><span class=\"product-image-wrapper\" style=\"padding-bottom: 100%;\"><img class=\"product-image-photo lazy\" src=\"https://cdn-distance.pl/static/version1594701710/frontend/TemplateMonster/distance/pl_PL/Unity_LazyLoading/images/Loader.gif\" data-original=\"https://cdn-distance.pl/media/catalog/product/cache/08cfa2b3e5a792d1a4ca8f69ad61be8c/p/r/projekt-16.12_156_9.jpg\" width=\"\" height=\"\" alt=\"Nike Air Force 1 LV8 3 GS CJ4092-100\"></span></span></a> \n" +
                "  <div class=\"product details product-item-details\"> \n" +
                "   <div class=\"price-box price-final_price\" data-role=\"priceBox\" data-product-id=\"252539\" data-price-box=\"product-id-252539\">\n" +
                "    <span class=\"normal-price\"><span class=\"price-container price-final_price tax weee\"><span class=\"price-label\">Zaczynając od</span> <span id=\"product-price-252539\" data-price-amount=\"369.99\" data-price-type=\"finalPrice\" class=\"price-wrapper \"><span class=\"price\">369,99&nbsp;zł</span></span></span></span> \n" +
                "   </div> \n" +
                "   <strong class=\"product name product-item-name\" itemprop=\"name\"><a class=\"product-item-link\" href=\"https://distance.pl/buty air force 1 lv8 3 (gs)-n-cj4092100\">Nike Air Force 1 LV8 3 GS CJ4092-100</a></strong> \n" +
                "   <div class=\"unity-swatches\" id=\"unitySwatch252539\" data-bind=\"scope: 'unitySwatch252539'\">\n" +
                "    <!-- ko if: canShowSwatches() -->\n" +
                "    <!-- ko template: getTemplate() -->\n" +
                "    <!-- /ko -->\n" +
                "    <!-- /ko -->\n" +
                "   </div>\n" +
                "   <script>\n" +
                "    require([\n" +
                "        'Magento_Ui/js/core/app',\n" +
                "        'Unity_Swatches/js/product/lightSwatches',\n" +
                "        'domReady!'\n" +
                "    ], function (componentContainer) {\n" +
                "        componentContainer(\n" +
                "            {\n" +
                "                components: {\n" +
                "                    \"unitySwatch252539\": {\n" +
                "                        component: \"Unity_Swatches/js/product/lightSwatches\",\n" +
                "                        sku: \"N-CJ4092100\",\n" +
                "                        elementId: \"unitySwatch252539\",\n" +
                "                        swatchConfig: {\"configurable_options\":{\"size\":{\"attribute_code\":\"size\",\"values\":[{\"label\":\"36\",\"value_index\":\"1780\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"},{\"label\":\"36 1\\/2\",\"value_index\":\"1781\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"},{\"label\":\"37 1\\/2\",\"value_index\":\"1784\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"},{\"label\":\"38\",\"value_index\":\"1786\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"},{\"label\":\"38 1\\/2\",\"value_index\":\"1787\",\"attribute_id\":\"167\",\"is_available\":\"0\",\"parent_id\":\"252539\"},{\"label\":\"39\",\"value_index\":\"1790\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"},{\"label\":\"40\",\"value_index\":\"1794\",\"attribute_id\":\"167\",\"is_available\":\"1\",\"parent_id\":\"252539\"}]}}}                    }\n" +
                "                },\n" +
                "                types: []\n" +
                "            },\n" +
                "            false\n" +
                "        );\n" +
                "    });</script> \n" +
                "  </div>\n" +
                " </div> </li>";
        this.tag = Jsoup.parse(html, "", Parser.xmlParser());
    }
    @Test
    public void assignDocument(){
        Assertions.assertDoesNotThrow(() -> Parsable.assignDocument(SneakerShop.DISTANCE,  preferences));
    }

    @Test
    public void getUrl(){
        String url  = Parsable.getUrl(SneakerShop.DISTANCE , preferences);
        Assertions.assertEquals("https://distance.pl/catalogsearch/result/index/?q=Nike%20Air%20Force%201" ,url);
    }

    @Test
    public void checkMainCss(){
        Document document = Parsable.assignDocument( SneakerShop.DISTANCE,preferences);
        List<Element> elements = document.select(".product-item");
        Assertions.assertFalse(elements.isEmpty());
    }

    @Test
    public void checkShoeNameCss() throws IOException {
        Assertions.assertEquals("Nike Air Force 1 LV8 3 GS CJ4092-100" ,shoeDataProvider.getName(tag , ".product-item-link"));
    }
    @Test
    public void checkShoeLinkCss() throws IOException {
        Assertions.assertEquals("https://distance.pl/buty air force 1 lv8 3 (gs)-n-cj4092100" ,shoeDataProvider.getLink(tag,"a.product-item-link","href"));
    }
    @Test
    public void checkShoeImageLinkCss() throws IOException {
        Assertions.assertEquals("https://cdn-distance.pl/media/catalog/product/cache/08cfa2b3e5a792d1a4ca8f69ad61be8c/p/r/projekt-16.12_156_9.jpg" ,shoeDataProvider.getImageLink(tag,"img.product-image-photo","data-original"));
    }
    @Test
    public void checkShoePriceCss() throws IOException {
        Assertions.assertEquals("369,99 PLN" ,shoeDataProvider.getPrice(tag,"span.price"));
    }
    @Test
    public void checkPrices(){
        List<String> sizes = new ArrayList<>(Arrays.asList());
        Assertions.assertEquals( sizes, shoeDataProvider.getSizes(tag,".size div a div") );
    }
}
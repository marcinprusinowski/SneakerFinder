package pl.marcin.thymleafshoefinder.Links;

public enum SneakerShop  {
    ATAF("https://ataf.pl/szukaj?s="),
    CHMIELNA("https://chmielna20.pl/products/"),
    DISTANCE("https://distance.pl/catalogsearch/result/index/?q="),
    RUNCOLORS("https://runcolors.pl/product/search_products.html?query="),
    SKLEPKOSZYKARZA("https://sklepkoszykarza.pl/products/"),
    SNEAKERSTUDIO("https://sneakerstudio.pl/search.php?text="),
    STREETSUPLY("https://streetsupply.pl/sklep?query="),
    WORLDBOX("https://worldbox.pl/products/"),
    TESTCASE("WRONGLINK");

    private String linkPrefix;

    SneakerShop(String linkPrefix) {
        this.linkPrefix = linkPrefix;
    }

    public String getLinkPrefix() {
        return linkPrefix;
    }
}


package pl.marcin.thymleafshoefinder.Links;


import pl.marcin.thymleafshoefinder.Model.Preferences;

public class LinkBuilder {

    public String buildLink(SneakerShop sneakerShop , Preferences preferences){
        LinkBuilderStrategy linkCreateStrategy;
        switch (sneakerShop){
            case DISTANCE:
            case RUNCOLORS:
            case SNEAKERSTUDIO:
            case ATAF:
            case STREETSUPLY:
                linkCreateStrategy = new BasicSufixLinkCreator();
                break;

            case CHMIELNA:
                linkCreateStrategy = new KeywordSufixLinkBuilder();
                break;

            case WORLDBOX:

            case SKLEPKOSZYKARZA:
                linkCreateStrategy = new KeywordCategorySufixLinkBuilder();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + sneakerShop.name());
        }
        return linkCreateStrategy.createLink(sneakerShop.getLinkPrefix(),preferences );
    }



}

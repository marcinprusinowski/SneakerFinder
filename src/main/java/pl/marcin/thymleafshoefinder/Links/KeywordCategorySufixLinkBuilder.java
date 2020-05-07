package pl.marcin.thymleafshoefinder.Links;

import pl.marcin.thymleafshoefinder.Links.Name.FullNameConnector;
import pl.marcin.thymleafshoefinder.Links.Name.NameConnector;
import pl.marcin.thymleafshoefinder.Model.Preferences;

public class KeywordCategorySufixLinkBuilder implements LinkBuilderStrategy {
    @Override
    public String createLink(String linkPrefix, Preferences preferences) {
        NameConnector nameConnector = new FullNameConnector();
        String url  ="";
        StringBuilder link = new StringBuilder(url);
        link.append(linkPrefix)
                .append(nameConnector.connect(preferences,"-"))
                .append("-obuwie")
                .append("/keyword,")
                .append(nameConnector.connect(preferences,"%20"))
                .append("/category,2")
                .append("/item,72");

        return link.toString();
    }
}

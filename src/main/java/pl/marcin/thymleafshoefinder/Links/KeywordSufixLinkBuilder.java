package pl.marcin.thymleafshoefinder.Links;

import pl.marcin.thymleafshoefinder.Links.Name.FullNameConnector;
import pl.marcin.thymleafshoefinder.Links.Name.NameConnector;
import pl.marcin.thymleafshoefinder.Model.Preferences;

public class KeywordSufixLinkBuilder implements LinkBuilderStrategy {
    @Override
    public String createLink(String linkPrefix, Preferences preferences) {

        NameConnector nameConnector = new FullNameConnector();
        String url  ="";
        StringBuilder link = new StringBuilder(url);
        link.append(linkPrefix)
                .append(nameConnector.connect(preferences , "-"))
                .append("/keyword,")
                .append(nameConnector.connect(preferences,"%20"));

        return link.toString();
    }
}

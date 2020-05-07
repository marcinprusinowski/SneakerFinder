package pl.marcin.thymleafshoefinder.Links;

import pl.marcin.thymleafshoefinder.Model.Preferences;

public interface LinkBuilderStrategy {
    String createLink(String linkPrefix , Preferences preferences);
}

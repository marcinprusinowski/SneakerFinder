package pl.marcin.thymleafshoefinder.Links.Name;

import pl.marcin.thymleafshoefinder.Model.Preferences;

import java.util.Arrays;
import java.util.List;

public class FullNameConnector implements NameConnector {
    @Override
    public String connect(Preferences preferences , String connector){
        String result ="";
        StringBuilder link = new StringBuilder(result);
        link.append(preferences.getBrand());
        List<String> modelArgs = Arrays.asList(preferences.getModel().split(" "));
        modelArgs.stream().filter(x->!x.equals("")).forEach(s->{
            link.append(connector)
                    .append(s);
        });

        return link.toString();
    }
}

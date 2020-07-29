package pl.marcin.thymleafshoefinder.Controllers;

import pl.marcin.thymleafshoefinder.Model.Preferences;
import pl.marcin.thymleafshoefinder.Model.Shoe;

import java.util.List;
import java.util.stream.Collectors;

public class ResultHandler {
    private  List<Shoe> shoeList;
    private Preferences preferences;

    public ResultHandler(List<Shoe> shoeList, Preferences preferences) {
        this.shoeList = shoeList;
        this.preferences = preferences;
    }
    public List<Shoe> handle(){
        int size = preferences.getSize();
        if (size!= 0){
            this.shoeList = filterBySize(size);
        }
        return shoeList;
    }

    private List<Shoe> filterBySize(int size){
        return shoeList.stream()
                .filter(shoe -> shoe.getSizes() != null && shoe.getSizes().contains(String.valueOf(size)))
                .collect(Collectors.toList());
    }
}

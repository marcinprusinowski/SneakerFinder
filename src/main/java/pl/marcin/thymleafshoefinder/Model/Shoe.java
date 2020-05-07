package pl.marcin.thymleafshoefinder.Model;

import java.util.List;

public class Shoe {
        private  String name;
        private  String link;
        private  String imageLink;
        private  String price;
        private List<String> sizes;


    public Shoe(String name, String link, String imageLink, String price , List<String> sizes) {
        this.name = name;
        this.link = link;
        this.imageLink = imageLink;
        this.price = price;
        this.sizes = sizes;
    }

    public Shoe() {
    }
    public Shoe withName(String name){
        this.name = name;
        return this;
    }
    public Shoe withLink(String link){
        this.link  = link;
        return this;
    }
    public Shoe withImageLink(String imageLink){
        this.imageLink = imageLink;
        return this;
    }
    public Shoe withPrice(String price){
        this.price = price;
        return this;
    }
    public Shoe build() {
        return this;
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getPrice() {
        return price;
    }

    public List<String> getSizes() {
        return sizes;
    }

    public Shoe withSizes(List<String> sizes) {
        this.sizes = sizes;
        return this;
    }

    @Override
    public String toString() {
        return "Shoe{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                ", imageLink='" + imageLink + '\'' +
                ", price='" + price + '\'' +
                '}';
    }


}

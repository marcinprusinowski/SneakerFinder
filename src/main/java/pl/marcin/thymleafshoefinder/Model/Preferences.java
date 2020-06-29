package pl.marcin.thymleafshoefinder.Model;


import javax.validation.constraints.NotNull;

public class Preferences {
    @NotNull
    private String brand;
    @NotNull
    private String model;
    private int size ;

    public Preferences(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public Preferences() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void clean(){
        this.brand = "";
        this.model="";
    }


    @Override
    public String toString() {
        return "Preferences{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}

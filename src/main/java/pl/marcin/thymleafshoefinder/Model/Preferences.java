package pl.marcin.thymleafshoefinder.Model;


import javax.validation.constraints.NotNull;

public class Preferences {
    @NotNull
    private String brand;
    @NotNull
    private String model;

    public Preferences(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
    }

    public Preferences() {
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

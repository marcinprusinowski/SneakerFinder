package pl.marcin.thymleafshoefinder.Controllers;

import pl.marcin.thymleafshoefinder.Interfacess.Parsable;
import pl.marcin.thymleafshoefinder.Model.Preferences;
import pl.marcin.thymleafshoefinder.Model.Shoe;
import pl.marcin.thymleafshoefinder.ShoesSuppliers.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

public class ResultController {
    private List<Parsable> classList;
    private List<Shoe> shoeList ;
    private ExecutorService executorService ;
    private ReentrantLock reentrantLock;
    private Preferences preferences;


    public ResultController(Preferences preferences) {
        this.classList = new ArrayList<>();
        this.preferences = preferences;
        this.shoeList = new ArrayList<>(Collections.emptyList());
        this.executorService = Executors.newFixedThreadPool(30);
        this.reentrantLock = new ReentrantLock();
    }

    private void providersInitialization(){
         classList.add(new Distance(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new Chmielna(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new Ataf(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new RunColors(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new SklepKoszykarza(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new SneakerStudio(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new StreetSupply(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
         classList.add(new WorldBox(this.shoeList, this.preferences ,this.executorService ,this.reentrantLock));
    }


    public List<Shoe> getShoeList(){
        executeParsing();
        return shoeList;
    }


    private void executeParsing() {
        providersInitialization();
        classList.forEach(shop -> {
            long time = System.currentTimeMillis();
            shop.parseForShoes();
            System.out.println(System.currentTimeMillis() - time +" " +shop.getClass().getSimpleName());
        });
        executorService.shutdown();
    }
    public void removeAll(){
        shoeList.removeAll(shoeList);
    }
}

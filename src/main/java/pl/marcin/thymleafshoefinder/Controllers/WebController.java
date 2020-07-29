package pl.marcin.thymleafshoefinder.Controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.marcin.thymleafshoefinder.Model.Preferences;
import pl.marcin.thymleafshoefinder.Model.Shoe;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class WebController {

    @PostMapping("/send")
    public ModelAndView getPreferences( @ModelAttribute Preferences preferences){
        ResultController rc = new ResultController(preferences);
        List<Shoe> shoeList = List.copyOf(rc.getShoeList());
        ResultHandler rh = new ResultHandler(shoeList ,preferences);
        shoeList = rh.handle();
        rc.removeAll();
        ModelAndView modelAndView = new ModelAndView("result");
        if (shoeList == null) { shoeList = new ArrayList<>(); }
        modelAndView.addObject("shoeList" ,  shoeList);
        return modelAndView;
    }

    @GetMapping("/shoeForm")
    public String home(Model model){
        model.addAttribute("preferences" , new Preferences());
        return "shoeform";
    }

    @GetMapping("/")
    public String welcome(){
        return "welcome";
    }

}

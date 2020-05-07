package pl.marcin.thymleafshoefinder.Controllers;

import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import pl.marcin.thymleafshoefinder.Model.Preferences;
import pl.marcin.thymleafshoefinder.Model.Shoe;

import java.util.List;
import java.util.Map;

@Controller
public class WebController {

    @PostMapping("/send")
    public ModelAndView getPreferences(Model model , @ModelAttribute Preferences preferences){
        ResultController rc = new ResultController(preferences);
        List<Shoe> shoeList = rc.getShoeList();
        ModelAndView modelAndView = new ModelAndView("result");
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

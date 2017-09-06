package ru.atc.bclient.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import ru.atc.bclient.CurrentInformation;
import ru.atc.bclient.model.PaymentOrderHandler;
import ru.atc.bclient.model.entities.dim.User;
import ru.atc.bclient.model.service.UserService;

@Controller
public class MainController {
    @Autowired
    private UserService userService;

    @Autowired
    private CurrentInformation currentInformation;

    @Autowired
    PaymentOrderHandler paymentOrderHandler;

    @RequestMapping(value={"/", "/login"}, method = RequestMethod.GET)
    public ModelAndView login(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @GetMapping(value = "/end_op_day")
    public String endOpDate(){

        paymentOrderHandler.handle();
        return "redirect:/manage/home";
    }

    @RequestMapping(value="/registration", method = RequestMethod.GET)
    public ModelAndView registration(){
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

//    @RequestMapping(value = "/registration", method = RequestMethod.POST)
//    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
//        ModelAndView modelAndView = new ModelAndView();
//        User userExists = userService.findUserByEmail(user.getEmail());
//        if (userExists != null) {
//            bindingResult
//                    .rejectValue("email", "error.user",
//                            "There is already a user registered with the email provided");
//        }
//        if (bindingResult.hasErrors()) {
//            modelAndView.setViewName("registration");
//        } else {
//            userService.saveUser(user);
//            modelAndView.addObject("successMessage", "User has been registered successfully");
//            modelAndView.addObject("user", new User());
//            modelAndView.setViewName("registration");
//
//        }
//        return modelAndView;
//    }
//
    @RequestMapping(value="/manage/home", method = RequestMethod.GET)
    public ModelAndView home(){

        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.getByLogin(auth.getName());
        System.out.println(user);
        currentInformation.setUser(user);
        modelAndView.addObject("userName", "Welcome " + user.getFullName() + " " + " (" + user.getLogin() + ")");

        modelAndView.addObject("legalEntities", user.getLegalEntities());
        modelAndView.setViewName("manage/home");
        return modelAndView;
    }
}

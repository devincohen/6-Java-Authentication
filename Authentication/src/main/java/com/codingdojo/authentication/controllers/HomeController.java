package com.codingdojo.authentication.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.codingdojo.authentication.models.LoginUser;
import com.codingdojo.authentication.models.User;
import com.codingdojo.authentication.repositories.UserRepository;
import com.codingdojo.authentication.services.UserService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class HomeController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/login")
	public String loginRegister(Model model) {
		 // Bind empty User and LoginUser objects to the JSP
        // to capture the form input
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
		return "index.jsp";
	}
	
	@PostMapping("/newUser")
	public String register(@Valid @ModelAttribute("newUser") User newUser, BindingResult result, Model model, HttpSession session){
		//clear session of errors
		session.removeAttribute("registerErrors");
		session.removeAttribute("loginErrors");
		User user = userService.register(newUser, result);
		if (result.hasErrors()) {
			session.setAttribute("registerErrors", result.getAllErrors());
			model.addAttribute("errors", session.getAttribute("errors"));
			return "redirect:/login";
		}
;		model.addAttribute("newUser", user);
		session.setAttribute("id", user.getId());
		return "redirect:/home";
	}
	
    @PostMapping("/newLogin")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin, BindingResult result, Model model, HttpSession session) {
    		//clear session of errors
		session.removeAttribute("registerErrors");
		session.removeAttribute("loginErrors");    	
        // Add once service is implemented:
        User user = userService.login(newLogin, result);
    
        if(result.hasErrors()) {
        	session.setAttribute("loginErrors", result.getAllErrors());
            model.addAttribute("newUser", new User());
            return "index.jsp";
        }
        model.addAttribute("user", user);
		session.setAttribute("id", user.getId());

        // No errors! 
        // TO-DO Later: Store their ID from the DB in session, 
        // in other words, log them in.
    
        return "redirect:/home";
    }
    
    //after successful login, show "home page"
    @GetMapping("/home")
    public String homePage(Model model, HttpSession session) {
    		if(session.getAttribute("id") != null) {
    			model.addAttribute("user", userService.findById((Long)session.getAttribute("id")));
    			return "home.jsp";    			
    		}
        model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
    		return "redirect:/login";
    }
    
    @GetMapping("/logout")
    public String logout(Model model, HttpSession session) {
    		session.invalidate();
    		model.addAttribute("newUser", new User());
        model.addAttribute("newLogin", new LoginUser());
    		return "redirect:/login";
    }
    
	
}

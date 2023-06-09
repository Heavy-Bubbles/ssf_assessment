package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import vttp2023.batch3.ssf.frontcontroller.models.Captcha;
import vttp2023.batch3.ssf.frontcontroller.models.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
@RequestMapping (path = "/")
public class FrontController {

	@Autowired
	AuthenticationService authService;

	@GetMapping
	public String getLoginPage(Model model, HttpSession session){

		model.addAttribute("user", new User());
		return "view0";
	}

	// TODO: Task 2, Task 3, Task 4, Task 6
	@PostMapping (path = "/login", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String postLogin(@Valid User user, BindingResult bindingResult, Model model, HttpSession session) throws Exception{
		if (bindingResult.hasErrors()){
			model.addAttribute("user", user);
			return "view0";
		}
		
		
		if (authService.isLocked(user.getUsername())){
			model.addAttribute("disabledUser", user);
			return "view2";
		}

		boolean isAuthenticated = authService.authenticate(user.getUsername(), user.getPassword());

		if (isAuthenticated){
			user.setAuthenticated(isAuthenticated);
			model.addAttribute("user", user);
			return "view1";
		}

			user.setErrorCount(user.getErrorCount() + 1);
			session.setAttribute("user", user);
			System.out.println(user.getErrorCount());
			if (user.getErrorCount() >= 3){
				authService.disableUser(user.getUsername());
			}
			ObjectError err = new ObjectError("globalError", "Invalid Username/Password!");
			bindingResult.addError(err);
			model.addAttribute("captcha", new Captcha());
			model.addAttribute("user", user);
			return "view0";

	}

	@GetMapping(path = "/logout")
	public String logout(User user, Model model, HttpSession session){
		session.invalidate();
		model.addAttribute("user", user);
		return "view0";
	}
	
}

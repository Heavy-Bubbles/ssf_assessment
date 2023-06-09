package vttp2023.batch3.ssf.frontcontroller.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import jakarta.servlet.http.HttpSession;
import vttp2023.batch3.ssf.frontcontroller.models.User;
import vttp2023.batch3.ssf.frontcontroller.services.AuthenticationService;

@Controller
public class ProtectedController {

	@Autowired
	AuthenticationService authSvc;

	// TODO Task 5
	// Write a controller to protect resources rooted under /protected
	@GetMapping(path="/protected/{pageName}")
	public String getProtectedPage(HttpSession session, @PathVariable String pageName) throws Exception{
		User user = (User) session.getAttribute("user");

		if (user == null){
			return "view0";
		}

		boolean isAuth = authSvc.authenticate(user.getUsername(), user.getPassword());
		if (isAuth){
			return pageName;
		}
		return "view0";
	}
}

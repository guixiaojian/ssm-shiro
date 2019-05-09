package com.tx.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tx.pojo.User;
import com.tx.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("user")
	public ModelAndView queryUser(int id) {
		ModelAndView modelAndView  = new ModelAndView();
		User user = new User();
		try {
			user = userService.selectUserById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		modelAndView.addObject("user", user);
		modelAndView.setViewName("user");
		return modelAndView;
	}
}

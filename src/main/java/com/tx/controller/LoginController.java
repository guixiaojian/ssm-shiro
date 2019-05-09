package com.tx.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.tx.pojo.User;
import com.tx.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tx.utils.TxException;

@Controller
@RequestMapping("sys")
public class LoginController {

	private static Logger log = LoggerFactory.getLogger(LoginController.class);

	@Autowired
	private UserService userService;
	
	@RequestMapping("login")
	public String toLogin(HttpServletRequest request) {
		return "login";
	}
	
	@RequestMapping("doLogin")
	public String doLogin(String account,String passWord,String isRememberMe,HttpSession session) {
		if(StringUtils.isEmpty(account) || StringUtils.isEmpty(passWord)) {
			throw new TxException("用户名或密码不能为空");
		}

		session.setAttribute("account",account);


		//获取subject实例
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token = new UsernamePasswordToken(account, passWord);

		// 设置是否'记住我'
		boolean rememberme = (isRememberMe == null) ? false : true;
		token.setRememberMe(rememberme);

		try {
			User user = userService.selectUserById(12);
			System.out.println(user.getUsername());
		} catch (Exception e) {
			e.printStackTrace();
		}
		subject.login(token);
		if(!subject.isAuthenticated()) {
			return "404";
		}
		return "sucess";
	}
	
	@RequestMapping("index")
	public String tiaozhuan() {
		return "index";
	}

	@RequestMapping("doLogout")
	public String doLogout(){

		return "login";
	}
	
}

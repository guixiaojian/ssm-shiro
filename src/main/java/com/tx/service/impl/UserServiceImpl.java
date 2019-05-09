package com.tx.service.impl;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tx.dao.UserMapper;
import com.tx.pojo.User;
import com.tx.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
	public User selectUserById(int id) throws Exception {
		
		/*User user = new User();
		user.setAge(1);
		user.setPassword("123");
		user.setPhone("15111509330");
		user.setSex(true);
		user.setUsername("Tom");*/
		Session session = SecurityUtils.getSubject().getSession();
		Object accout = session.getAttribute("account");
		System.out.println(accout);
		return userMapper.selectByPrimaryKey(2);


	}

}

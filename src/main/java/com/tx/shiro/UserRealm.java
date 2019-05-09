package com.tx.shiro;

import java.util.List;

import javax.security.auth.login.CredentialExpiredException;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Autowired;

import com.tx.dao.PermissionMapper;
import com.tx.dao.RoleMapper;
import com.tx.dao.UserMapper;
import com.tx.pojo.User;
import com.tx.pojo.UserExample;
import com.tx.pojo.UserExample.Criteria;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.DependsOn;


public class UserRealm  extends AuthorizingRealm{

	public static Logger logger = LoggerFactory.getLogger(UserRealm.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Autowired 
	private PermissionMapper permissionMapper;
	
	/**
	 * 	授权
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		
		//获取到当前用户
		User user = (User) principals.getPrimaryPrincipal();
		
		//获取用户的权限
		
		//获取用户的角色
		
		//交给shiro管理
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//可以添加单个权限和权限，亦可直接添加权限和角色集合
		info.addStringPermission("select");
		info.addRole("admin");
		System.out.println("授权+______________________________________");
		return info;
	}

	/**
	 * 	认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
		//获取当前用户名和密码
		String username = (String) token.getPrincipal();
		String password = new String((char[]) token.getCredentials());
		
		//查询用户信息
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<User> userList = userMapper.selectByExample(example);
		
		//判断用户是否存在
		if(CollectionUtils.isEmpty(userList)) {
			throw new UnknownAccountException("账号不存在!");
		}
		
		//对密码加密
		//password = new Md5Hash(password).toString();
		
		//判断密码是否正确
		/*if(!password.equals(userList.get(0).getPassword())) {
			throw new IncorrectCredentialsException("账号或密码不正确!");
		}*/
		
		//将username作为盐值
		String salt = username;
		
		// 密码加盐  认证
		return new SimpleAuthenticationInfo(userList.get(0),  userList.get(0).getPassword(), ByteSource.Util.bytes(salt), getName());
		
		//不加盐认证
		//return new SimpleAuthenticationInfo(userList.get(0), userList.get(0).getPassword(), getName());
		
	}

	// 配置加密
	public void setCredentialMatcher(){
		HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
		credentialsMatcher.setHashAlgorithmName("MD5");//MD5算法加密
		credentialsMatcher.setHashIterations(2);//1024次循环加密
		setCredentialsMatcher(credentialsMatcher);
	}

	/*
	 * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
	 * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
	 * @return
	 */
	/*@Bean
	@DependsOn({"lifecycleBeanPostProcessor"})
	public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator(){
		DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		advisorAutoProxyCreator.setProxyTargetClass(true);
		return advisorAutoProxyCreator;
	}
	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(){
		AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
		return authorizationAttributeSourceAdvisor;
	}*/
}

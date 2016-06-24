package com.mycompany.security.bean;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.service.sys.SysUserManager;
import com.mycompany.service.impl.GenericManagerImpl;

	/*
	*
	*
	*
	*
	* **/
@Service("UserDetailsService")
public class CustomUserDetailsServiceImpl extends GenericManagerImpl<SysUser, Long> implements
			UserDetailsService {
	Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysUserManager sysUserService;


	@Override
	public UserDetails loadUserByUsername(String loginUserName)
			throws UsernameNotFoundException {
		SysUser sysUser = new SysUser();
		sysUser.setUsername(loginUserName);
		//当前用户
		SysUser result = sysUserService.loadUserByUsername(loginUserName);
//		ShaPasswordEncoder sha = new ShaPasswordEncoder();
//		String encode = sha.encodePassword(result.getPassword(),null);
//		System.out.println(encode);
//		DaoAuthenticationProvider
//		//获得当前用户的角色列表在本系统中，只有一个角色)
		Collection<GrantedAuthority> grantedAuths = obtionGrantedAuthorities(result);
		//TODO 加入权限
//		result.setAuthorities(grantedAuths);
//		logger.info("加入权限"+grantedAuths.toString());
		return result;
	}

	// 取得用户的权限
	@SuppressWarnings("deprecation")
	private Set<GrantedAuthority> obtionGrantedAuthorities(SysUser user) {
		Set<GrantedAuthority> authSet = new HashSet<GrantedAuthority>();
		Set<SysRole> roles = user.getSysRoles();
		//存在我们的角色列表
		for(SysRole role : roles) {
			authSet.add(new GrantedAuthorityImpl(role.getRoleSecurity()));
		}
		return authSet;
	}

//	public void setSysUserService(SysUserManager sysUserService) {
//		this.sysUserService = sysUserService;
//	}

}

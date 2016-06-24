package com.mycompany.security.bean;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import com.mycompany.model.sys.SysRight;
import com.mycompany.model.sys.SysRole;
import com.mycompany.model.sys.SysUser;
import com.mycompany.util.StringUtil;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

@Service("AccessDecisionManager")
public class CustomAccessDecisionManagerImpl implements
		AccessDecisionManager {

	/**
	 * 思路:如果该页面不需要权限访问,则直接结束
	 * authentication:用户的权限
	 * configAttributes:访问该资源所需要的权限
	 */
	@Override
	public void decide(Authentication authentication, Object object,
			Collection<ConfigAttribute> configAttributes)
			throws AccessDeniedException, InsufficientAuthenticationException {

		if (null == configAttributes) {
			return;
		}
		String url = ((FilterInvocation)object).getRequestUrl();
		if(url.startsWith("/app")) {
			url = url.substring(4);
		}
		url = url + " ";
//		System.out.println(url + " url ..................");
		//从数据库or缓存中取出进行比较。
		if(authentication.getPrincipal() instanceof SysUser) {
			SysUser user = (SysUser) authentication.getPrincipal();
			Set<SysRole> roleSet = user.getSysRoles();
			for (SysRole role : roleSet) {
				for (SysRight right : role.getSysRights()) {
//					System.out.println("url is user*=" + url + "  " + right.getRightUrl());
					String patten = right.getRightUrl() + "*";
					if (StringUtil.match(patten, url)) {
						//获得该uri所需要的角色列表
//						System.out.println("pass " + url);
						return;
					}
				}
			}
		}
		throw new AccessDeniedException("Access Denied");
		//访问该uri所需要的角色列表
		/*Iterator<ConfigAttribute> cons = configAttributes.iterator();

		while (cons.hasNext()) {
			ConfigAttribute ca = cons.next();
			String needRole = ((SecurityConfig) ca).getAttribute();//访问该资源所需要的权限
			for (GrantedAuthority gra : authentication.getAuthorities()) {//gra:该用户拥有的权限
				if (needRole.trim().equals(gra.getAuthority().trim())) {
					//放行
					return;
				}
			}
		}
		//该用户没有权限访问该资源
		throw new AccessDeniedException("Access Denied");*/
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}

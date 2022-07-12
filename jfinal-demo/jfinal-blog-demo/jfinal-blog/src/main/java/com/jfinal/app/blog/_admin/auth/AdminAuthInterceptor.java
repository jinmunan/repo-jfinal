/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.auth;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.app.blog._admin.login.LoginService;
import com.jfinal.app.blog.common.model.Account;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;

/**
 * 后台授权拦截器，已登录则放行，否则重定向到登录页面
 * 
 * 需要 LoginSessionInterceptor 拦截器先通过 setAttr(LoginService.LOGIN_ACCOUNT, account)
 * 传递 loginAccount 对象
 */
public class AdminAuthInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		
		// 获取 LoginSessionInterceptor 中传递的 loginAccount 对象
		Account loginAccount = c.getAttr(LoginService.LOGIN_ACCOUNT);
		if (loginAccount != null) {
			inv.invoke();
			return ;	
		}
		
		// ajax 请求不能使用 forwardAction(...)、redirect(...)，否则登录界面将被插入到页面右侧
		if (isAjaxRequest(c)) {
			c.renderJson(Ret.fail("当前账户已退出登录，请刷新页面并重新登录"));
		} else {
			c.forwardAction("/admin/login");
		}
	}
	
	/**
	 * 判断是否为 ajax 请求
	 */
	boolean isAjaxRequest(Controller c) {
		return "XMLHttpRequest".equalsIgnoreCase(c.getRequest().getHeader("X-Requested-With"));
	}
}







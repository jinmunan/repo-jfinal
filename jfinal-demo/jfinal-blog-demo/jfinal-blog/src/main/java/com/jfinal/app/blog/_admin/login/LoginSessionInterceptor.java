/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.login;

import static com.jfinal.app.blog._admin.login.LoginService.LOGIN_ACCOUNT;
import static com.jfinal.app.blog._admin.login.LoginService.SESSION_ID;
import com.jfinal.aop.Inject;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.app.blog.common.model.Account;
import com.jfinal.core.Controller;

/**
 * 从 cookie 中获取 sessionId，通过 sessionId 得到当前登录账户对象 loginAccount，
 * loginAccount 供后续流程使用
 * 
 * 注意：LoginSessionInterceptor 需要配置为全局拦截器并且配置次序排在第一位
 */
public class LoginSessionInterceptor implements Interceptor {
	
	@Inject
	LoginService loginAdminService;
	
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		String sessionId = c.getCookie(SESSION_ID);
		if (sessionId != null) {
			Account loginAccount = loginAdminService.getAccountBySessionId(sessionId);
			if (loginAccount != null) {
				// 传递给 Controller、enjoy 模板、Interceptor 等等地方使用
				c.setAttr(LOGIN_ACCOUNT, loginAccount);
			} else {
				// cookie 未获取到 loginAccount ，证明该 cookie 已经过期，删之
				c.removeCookie(SESSION_ID);
			}
		}
		
		inv.invoke();
	}
}




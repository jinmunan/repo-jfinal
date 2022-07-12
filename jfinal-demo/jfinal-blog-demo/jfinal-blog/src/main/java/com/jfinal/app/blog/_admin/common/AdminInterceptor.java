/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.common;

import javax.servlet.http.HttpServletRequest;
import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;
import com.jfinal.core.Controller;

/**
 * AdminInterceptor
 * 
 * 主要功能是识别是否为 ajax 请求，是则去调用 action，否则直接调用
 * render(_admin_layout.html);
 * 
 * 后续可能会添加一些后台通用拦截功能，处理一些后台全局都要依赖的事情
 * 
 * 
 * 后台管理 UI 采用 enjoy + ajax 方案实现前后分离，无需使用 vue
 * react angular 技术栈，降低成本、提升效率：
 *  1：非 ajax 请求 enjoy 渲染布局模板 _admin_layout.html
 *  2：ajax 请求 enjoy 渲染 html 片段
 *  3：enjoy 渲染的 html 片段通过 js 插入相应的位置
 *  4：一个页面可以使用多个 ajax 请求插入不同的 html 片段来组合整个页面内容 
 * 
 */
public class AdminInterceptor implements Interceptor {
	
	public void intercept(Invocation inv) {
		Controller c = inv.getController();
		if (isAjaxRequest(c.getRequest())) {
			inv.invoke();
		} else {
			// 设置 url 变量供 layout 中的 kit.fill(...) 使用
			// setUrl(c);
			
			// 直接渲染 layout 模板，在 layout 模板中通过 ajax 加载 html 内容片段
			c.render("/_view/_admin/common/_admin_layout.html");
		}
	}
	
	/**
	 * 判断是否为 ajax 请求
	 */
	boolean isAjaxRequest(HttpServletRequest req) {
		return "XMLHttpRequest".equalsIgnoreCase(req.getHeader("X-Requested-With"));
	}
	
	/**
	 * 页面使用 kit.fill('#(url)', data, '#content-box') 可以获取 url 变量值来加载数据
	 * 
	 * 页面中使用 location.pathname + location.search 可获取 url 值，
	 * 所以目前不使用此方法，如果出现个别浏览器无法通过 js 获取该值时再使用本方法
	 */
	void setUrl(Controller c) {
		HttpServletRequest req = c.getRequest();
		String url = req.getRequestURI();
		if (req.getQueryString() != null) {
			url = url + '?' + req.getQueryString();
		}
		c.set("url", url);
	}
}



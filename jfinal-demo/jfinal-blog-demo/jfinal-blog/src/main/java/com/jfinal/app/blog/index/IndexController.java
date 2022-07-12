/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog.index;

import com.jfinal.aop.Inject;
import com.jfinal.app.blog.blog.BlogService;
import com.jfinal.app.blog.common.BaseController;
import com.jfinal.core.Path;

/**
 * 首页
 */
@Path(value = "/", viewPath = "/index")
public class IndexController extends BaseController {
	
	@Inject
	BlogService blogService;
	
	public void index() {
		System.out.println(111111);
		set("allBlogs", blogService.getAllBlogs());
		render("index.html");
	}
	
	public void contact() {
		render("contact.html");
	}
}

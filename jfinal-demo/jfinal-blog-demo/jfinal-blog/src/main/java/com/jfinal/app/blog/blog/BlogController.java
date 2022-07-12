/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog.blog;

import com.jfinal.aop.Inject;
import com.jfinal.app.blog.common.BaseController;
import com.jfinal.app.blog.common.model.Blog;
import com.jfinal.core.Path;

/**
 * BlogController
 */
@Path("/blog")
public class BlogController extends BaseController {
	
	@Inject
	BlogService srv;
	
	public void index() {
		Blog blog = srv.getById(getInt());
		if (blog != null) {
			set("blog", blog);
			render("index.html");
		} else {
			renderError(404);
		}
	}
}




/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.index;

import com.jfinal.aop.Inject;
import com.jfinal.app.blog._admin.account.AccountAdminService;
import com.jfinal.app.blog.common.BaseController;
import com.jfinal.core.Path;

/**
 * 后台首页控制层
 */
@Path(value = "/admin", viewPath = "/index")
public class IndexAdminController extends BaseController {
	
	@Inject
	IndexAdminService srv;
	
	@Inject
	AccountAdminService accountAdminSrv;
	
	public void index() {
		set("isDefaultPassword", accountAdminSrv.isDefaultPassword(getLoginAccount()));
		render("index.html");
	}
	
	/**
	 * 概览
	 */
	public void overview() {
		set("totalBlog", srv.getTotalBlog());
		set("totalImage", srv.getTotalImage());
		set("totalAccount", srv.getTotalAccount());
		render("_overview.html");
	}
	
	/**
	 * 最新图片
	 */
	public void latestImage() {
		set("latestImage", srv.getLatestImage());
		render("_latest_image.html");
	}
}



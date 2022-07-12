/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.blog;

import com.jfinal.aop.Inject;
import com.jfinal.app.blog.common.BaseController;
import com.jfinal.app.blog.common.model.Blog;
import com.jfinal.core.Path;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Page;

/**
 * 博客管理控制层
 */
@Path(value = "/admin/blog", viewPath = "/blog")
public class BlogAdminController extends BaseController {
	
	@Inject
	BlogAdminService srv;
	
	/**
	 * 列表与搜索
	 */
	public void index() {
		// pn 为分页号 pageNumber
		int pn = getInt("pn", 1);
		String searchKey = get("searchKey");
		
		Page<Blog> page = StrKit.isBlank(searchKey)
								? srv.paginate(pn)
								: srv.search(searchKey, pn);
		
		// 保持住 searchKey 变量，便于输出到搜索框的 value 中
		keepPara("searchKey");
		set("page", page);
		render("index.html");
	}
	
	/**
	 * 支持 switch 开关的发布功能
	 */
	public void publish() {
		Ret ret = srv.publish(getInt("id"), getBoolean("checked"));
		renderJson(ret);
	}
	
	/**
	 * 进入创建页面
	 */
	public void add() {
		render("add_edit.html");
	}
	
	/**
	 * 保存
	 */
	public void save() {
		Ret ret = srv.save(getLoginAccountId(), getBean(Blog.class));
		renderJson(ret);
	}
	
	/**
	 * 进入修改页面
	 */
	public void edit() {
		set("blog", srv.getById(getInt("id")));
		render("add_edit.html");
	}
	
	/**
	 * 更新
	 */
	public void update() {
		Ret ret = srv.update(getBean(Blog.class));
		renderJson(ret);
	}
	
	/**
	 * 删除
	 */
	public void delete() {
		Ret ret = srv.deleteById(getInt("id"));
		renderJson(ret);
	}
}





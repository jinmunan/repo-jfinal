/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.blog;

import java.util.Date;
import com.jfinal.app.blog.common.model.Blog;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * 博客管理业务层
 */
public class BlogAdminService {
	
	private static int pageSize = 25;
	private Blog dao = new Blog().dao();
	
	/**
	 * 分页
	 */
	public Page<Blog> paginate(int pageNumber) {
		return dao.paginate(pageNumber, pageSize, "select *", "from blog order by updated desc");
	}
	
	/**
	 * 搜索
	 */
	public Page<Blog> search(String key, int pageNumber) {
		String sql = "select * from blog where title like concat('%', #para(0), '%') order by updated desc";
		return dao.templateByString(sql, key).paginate(pageNumber, pageSize);
	}
	
	/**
	 * 创建
	 */
	public Ret save(int accountId, Blog blog) {
		if (StrKit.isBlank(blog.getTitle())) {
			return Ret.fail("title 不能为空");
		}
		if (StrKit.isBlank(blog.getContent())) {
			return Ret.fail("content 不能为空");
		}
		
		blog.setAccountId(accountId);
		blog.setState(Blog.STATE_UNPUBLISHED);	// 默认未发布
		blog.setCreated(new Date());
		blog.setUpdated(blog.getCreated());
		blog.setViewCount(0);
		blog.save();
		return Ret.ok("msg", "创建成功");
	}
	
	/**
	 * 更新
	 */
	public Ret update(Blog blog) {
		blog.setUpdated(new Date());
		blog.update();
		return Ret.ok("msg", "更新成功"); 
	}
	
	/**
	 * 发布
	 */
	public Ret publish(int id, boolean checked) {
		int state = checked ? Blog.STATE_PUBLISHED : Blog.STATE_UNPUBLISHED;
		String sql = "update blog set state = ? where id = ?";
		Db.update(sql, state, id);
		return Ret.ok();
	}
	
	/**
	 * 获取
	 */
	public Blog getById(int id) {
		return dao.findById(id);
	}
	
	/**
	 * 删除
	 */
	public Ret deleteById(int id) {
		dao.deleteById(id);
		return Ret.ok("msg", "删除成功");
	}
	
}





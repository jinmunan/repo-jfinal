/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog.blog;

import java.util.List;
import com.jfinal.app.blog.common.model.Blog;

/**
 * BlogService
 */
public class BlogService {
	
	private Blog dao = new Blog().dao();
	
	public List<Blog> getAllBlogs() {
		String sql = "select id, accountId, title, left(content, 200) as content, created, updated from blog where state = ? order by created desc";
		return dao.find(sql, Blog.STATE_PUBLISHED);
	}
	
	public Blog getById(int id) {
		String sql = "select * from blog where id = ? and state = ? limit 1";
		return dao.findFirst(sql, id, Blog.STATE_PUBLISHED);
	}
}





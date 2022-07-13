package com.cj.jfinal.service;

import com.cj.jfinal.model.Blog;

/**
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-13 11:58
 */
public class BlogService {
    public static final Blog dao = new Blog().dao();

    // 查找一个blog
    public Blog findById(int id) {
        return dao.findById(id);
    }

    // 删除
    public Boolean deleteById(int id) {
        return dao.deleteById(id);
    }

    // 保存
    public Boolean save(Blog blog) {
        return blog.save();
    }

}

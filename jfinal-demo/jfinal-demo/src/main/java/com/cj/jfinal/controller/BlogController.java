package com.cj.jfinal.controller;

import com.cj.jfinal.model.Blog;
import com.cj.jfinal.service.BlogService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;


/**
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-13 10:52
 */
@Path("/blog")
public class BlogController extends Controller {
    //http://127.0.0.1:18080/blog
    public void index() {
        //返回text文本
        render("blog.html");
    }

    public void save() {
        //返回text文本
        //页面的modelName正好是Blog类名的首字母小写
        Blog blog = getModel(Blog.class);
        System.out.println(blog.toString());
        // 如果表单域的名称为 "otherName.title"可加上一个参数来获取
        blog = getModel(Blog.class, "otherName");
        render("index.html");
    }

    public void save2() {
        // 如果表单域的名称为 "otherName.title"可加上一个参数来获取
        Blog blog = getModel(Blog.class, "");
        System.out.println(blog.toString());
        render("index.html");
    }

    //-----------------------------------------------
    @Inject
    BlogService blogService;

    public void find() {
        Blog blog = blogService.findById(1);
        renderJson(blog);
    }

    // http://127.0.0.1:18080/blog/find?id=1
    public void findByParam() {
        Blog blog = blogService.findById(getInt("id"));
        renderJson(blog);
    }

    // http://127.0.0.1:18080/blog/find/1
    public void findByParam2() {
        Blog blog = blogService.findById(getInt());
        renderJson(blog);
    }

    public void deleteById() {
        Boolean aBoolean = blogService.deleteById(getInt());
        renderJson(aBoolean);
    }

    //http://127.0.0.1:18080/blog/save3?title=12312313&content=123123123123
    public void save3() {
        Blog blog = getBean(Blog.class);
        System.out.println(blog);
        Boolean save = blogService.save(blog);
        renderJson(save);
    }


}

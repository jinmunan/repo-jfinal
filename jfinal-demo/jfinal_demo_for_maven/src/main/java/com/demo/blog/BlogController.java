package com.demo.blog;

import com.jfinal.aop.Before;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.core.Path;
import com.demo.common.model.Blog;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: https://jfinal.com/club
 * <p>
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
@Path("/blog")
@Before(BlogInterceptor.class)
public class BlogController extends Controller {

    @Inject
    BlogService blogService;

    /**
     * 首页展示
     */
    public void index() {
        setAttr("blogPage", blogService.paginate(getParaToInt(0, 1), 10));
        render("blog.html");
    }

    /**
     * 跳转到add页面?
     */
    public void add() {

    }

    /**
     * 跳转到-form页面?
     */
    public void _form() {

    }

    /**
     * 增加
     */
    @Before(BlogValidator.class)
    public void save() {
        Blog blog = getBean(Blog.class);
        blogService.save(blog);
        redirect("/blog");
    }

    /**
     * 回显数据
     */
    public void edit() {
        /*request域*/
        setAttr("blog", blogService.findById(getParaToInt()));
    }

    /**
     * 在执行方法前验证
     */
    @Before(BlogValidator.class)
    public void update() {
        Blog blog = getBean(Blog.class);
        blogService.update(blog);
        redirect("/blog");
    }

    /**
     * 删除
     */
    public void delete() {
        blogService.deleteById(getParaToInt());
        redirect("/blog");
    }
}



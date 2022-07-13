package com.demo.blog;

import com.demo.common.model.Blog;
import com.jfinal.plugin.activerecord.Page;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: https://jfinal.com/club
 * <p>
 * BlogService
 * 所有 sql 与业务逻辑写在 Service 中，不要放在 Model 中，更不
 * 要放在 Controller 中，养成好习惯，有利于大型项目的开发与维护
 */
public class BlogService {

    /**
     * 所有的 dao 对象也放在 Service 中，并且声明为 private，避免 sql 满天飞
     * sql 只放在业务层，或者放在外部 sql 模板，用模板引擎管理：
     * https://jfinal.com/doc/5-13
     */
    private Blog dao = new Blog().dao();

    /**
     * 分页显示
     *
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public Page<Blog> paginate(int pageNumber, int pageSize) {
        return dao.paginate(pageNumber, pageSize, "select *", "from blog order by id asc");
    }

    /**
     * 回显
     *
     * @param id
     * @return
     */
    public Blog findById(int id) {
        return dao.findById(id);
    }

    /**
     * 删除
     *
     * @param id
     */
    public void deleteById(int id) {
        dao.deleteById(id);
    }

    /**
     * 保存
     *
     * @param blog
     */
    public void save(Blog blog) {
        blog.save();
    }

    /**
     * 更新
     *
     * @param blog
     */
    public void update(Blog blog) {
        blog.update();
    }
}

package com.cj.jfinal.model;

import com.jfinal.plugin.activerecord.Model;

/**
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-13 10:50
 */
// 定义Model，在此为Blog
public class Blog extends Model<Blog> {
    // 获得操作数据库的能力
    public static final Blog dao = new Blog().dao();

}

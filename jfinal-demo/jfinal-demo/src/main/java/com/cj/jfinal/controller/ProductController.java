package com.cj.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-13 09:31
 */
@Path(value = "/product", viewPath = "123")
public class ProductController extends Controller {
    //控制器的默认访问方法，相当于Struts的execute
    public void index() {
        //返回text文本
        renderText("Hello JFinal 123456");
    }

    // http://localhost:18080/product/hello
    // E:\repo-jfinal\jfinal-demo\jfinal-demo\src\main\webapp\view\product
    // E:/repo-jfinal/jfinal-demo/jfinal-demo/src/main/webapp/view/product/hello.html
    // config中的baseView是不用加的  直接 控制器路由加方法名字 但是页面路径是 baseView+viewpath+view
    //控制器的普通访问方法
    public void hello() {
        //返回页面
        render("hello.html");
    }

    /**
     * post请求
     * 这里不能填写参数
     */
    public void para() {
        Integer password = getParaToInt("password");
        System.out.println("username:" + getPara("username"));
        System.out.println("password:" + getPara("password"));
        System.out.println("password:" + password);
        // base + vp + v
        render("index.html");

    }

    /**
     * get 请求
     * http://127.0.0.1:18080/product/get/123-123
     * http://127.0.0.1:18080/product/get/123-123-123-123
     */
    public void get() {
        System.out.println(getPara());
        System.out.println(getParaToInt(1));
        render("index.html");
    }

    /**
     * String json = getRawData();
     * User user = FastJson.getJson().parse(json, User.class);
     * 以上代码通过 getRawData() 获取到了客户端传过来的 String 型的 json 数据库。 getRawData() 方法可以在一次请求交互中多次反复调用，不会抛出异常。
     */

}


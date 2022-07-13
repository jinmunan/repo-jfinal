package com.cj.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.NotAction;
import com.jfinal.core.Path;


/**
 * 测试接口
 *
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-11 21:33
 */
@Path("/test")
public class TestController extends Controller {
    public void index() {
        renderText("此方法是一个action");
    }

    // 不希望成为 action，仅供子类调用，或拦截器中调用
    @NotAction
    public void getLoginUser() {

    }
}

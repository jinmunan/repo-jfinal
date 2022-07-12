package com.cj.jfinal.controller;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;


/**
 * 测试接口
 *
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-11 21:33
 */
@Path("/hello")
public class HelloController extends Controller {
	public void index() {
		renderText("Hello JFinal World.");
	}
}

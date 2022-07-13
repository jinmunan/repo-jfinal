package com.demo.index;

import com.jfinal.core.Controller;
import com.jfinal.core.Path;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: https://jfinal.com/club
 * 
 * IndexController
 */
@Path(value = "/", viewPath = "/index")
public class IndexController extends Controller {
	public void index() {
		render("index.html");
	}
}




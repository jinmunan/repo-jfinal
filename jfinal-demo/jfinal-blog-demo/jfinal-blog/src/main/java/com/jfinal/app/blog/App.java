/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog;

import com.jfinal.app.blog.common.AppConfig;
import com.jfinal.server.undertow.UndertowServer;

/**
 * 启动入口
 */
public class App {
	public static void main(String[] args) {
		UndertowServer.start(AppConfig.class);
	}
}






package com.cj.jfinal;

import com.cj.jfinal.config.JfinalDemoConfig;
import com.jfinal.server.undertow.UndertowServer;

/**
 * 服务器启动类
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-11 21:28
 */
public class JfinalAppStart {
	public static void main(String[] args) {
		// 服务器启动
		UndertowServer.start(JfinalDemoConfig.class, 80, true);
	}
}

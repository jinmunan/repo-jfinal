package com.cj.jfinal;

import com.jfinal.core.JFinal;

/**
 * 服务器启动类
 *
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-11 21:28
 */
public class JfinalAppStart {
	public static void main(String[] args) {
		// 服务器启动
		// 没有启动jfinal 需要指定模块名
		JFinal.start("jfinal-demo/jfinal-jetty/src/main/webapp", 28088, "/", 5);
	}
}

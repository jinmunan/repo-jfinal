package com.cj.jfinal.config;

import com.cj.jfinal.controller.HelloController;
import com.jfinal.config.*;
import com.jfinal.template.Engine;

/**
 * jfinal 配置文件
 *
 * @version 1.0
 * @author： jinmunan
 * @date： 2022-07-11 21:27
 */
public class JfinalDemoConfig extends JFinalConfig {

	/**
	 * 配置常量
	 *
	 * @param constants
	 */
	@Override
	public void configConstant(Constants constants) {
		// 设置开发模式
		constants.setDevMode(true);
	}

	/**
	 * 配置路由
	 *
	 * @param routes
	 */
	@Override
	public void configRoute(Routes routes) {
		// 路由扫描
		//routes.scan("com.cj.jfinal.");

		// 原本是注册路由
		routes.add("/hello", HelloController.class);
	}

	/**
	 * 配置引擎
	 *
	 * @param engine
	 */
	@Override
	public void configEngine(Engine engine) {

	}

	/**
	 * 配置插件
	 *
	 * @param plugins
	 */
	@Override
	public void configPlugin(Plugins plugins) {
	}

	/**
	 * 配置拦截器
	 *
	 * @param interceptors
	 */
	@Override
	public void configInterceptor(Interceptors interceptors) {

	}

	/**
	 * 配置处理器
	 *
	 * @param handlers
	 */
	@Override
	public void configHandler(Handlers handlers) {

	}
}

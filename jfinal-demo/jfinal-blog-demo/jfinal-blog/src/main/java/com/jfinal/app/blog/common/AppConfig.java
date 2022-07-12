/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog.common;

import com.jfinal.app.blog._admin.auth.AdminAuthInterceptor;
import com.jfinal.app.blog._admin.common.AdminInterceptor;
import com.jfinal.app.blog._admin.common.RunningTime;
import com.jfinal.app.blog._admin.login.LoginSessionInterceptor;
import com.jfinal.app.blog.common.kit.SharedMethodLib;
import com.jfinal.app.blog.common.model._MappingKit;
import com.jfinal.config.*;
import com.jfinal.kit.Prop;
import com.jfinal.kit.PropKit;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
import com.jfinal.template.Engine;

/**
 * 配置中心
 */
public class AppConfig extends JFinalConfig {
	
	static Prop p;
	
	static void loadConfig() {
		if (p == null) {
			// 加载从左到右第一个被找到的配置文件
			p = PropKit.useFirstFound("app-config-pro.txt", "app-config-dev.txt");
		}
	}
	
	public void configConstant(Constants me) {
		loadConfig();
		me.setDevMode(p.getBoolean("devMode", false));
		
		// 支持依赖注入
		me.setInjectDependency(true);
		// 不对父类进行注入，提升注入性能
		me.setInjectSuperClass(false);
	}
	
	public void configRoute(Routes me) {
		// 添加后台路由
		me.add(new Routes() {
			public void config() {
				// 添加路由级别的拦截器，拦截所有在此方法中添加的 Controller 中的所有 action
				this.addInterceptor(new AdminAuthInterceptor());
				this.addInterceptor(new AdminInterceptor());
				
				// 配置视图的基础路径，避免 render(...) 参数输入前缀 "/_view/_admin"
				this.setBaseViewPath("/_view/_admin");
				
				// 扫描后台路由
				this.scan("com.jfinal.app.blog._admin.");
			}
		});
		
		// 添加前台路由
		me.add(new Routes() {
			public void config() {
				// 配置视图的基础路径
				this.setBaseViewPath("/_view");
				
				// 扫描前台路由，过滤掉后台路由的扫描
				this.scan("com.jfinal.app.blog.", className -> {
					// className 为当前正扫描的类名，返回 true 时表示过滤掉当前类不扫描
					return className.startsWith("com.jfinal.app.blog._admin.");
				});				
			}
		});
	}
	
	public void configEngine(Engine me) {
		// devMode 为 true 时支持模板文件热加载
		me.setDevMode(p.getBoolean("engineDevMode", false));
		
		// 添加共享方法库
		me.addSharedMethod(new SharedMethodLib());
		
		// 添加共享对象
		me.addSharedObject("StrKit", new StrKit());
		me.addSharedObject("RunningTime", new RunningTime());
		
		// 添加后台分页模板函数
		me.addSharedFunction("/_view/_admin/common/_paginate.html");
	}
	
	/**
	 * 抽取成独立的方法，便于 _Generator 中重用该方法，减少代码冗余
	 */
	public static DruidPlugin getDruidPlugin() {
		loadConfig();
		return new DruidPlugin(p.get("jdbcUrl"), p.get("user"), p.get("password").trim());
	}
	
	public void configPlugin(Plugins me) {
		// 配置 JDBC 连接池插件
		DruidPlugin druidPlugin = getDruidPlugin();
		me.add(druidPlugin);
		
		// 配置 ActiveRecordPlugin
		ActiveRecordPlugin arp = new ActiveRecordPlugin(druidPlugin);
		arp.setShowSql(p.getBoolean("devMode", false));	// 是否输出 sql 到控制台
		_MappingKit.mapping(arp);	// 自动添加 model 到 table 的映射
		me.add(arp);
	}
	
	public void configInterceptor(Interceptors me) {
		// 登录会话拦截器
		me.add(new LoginSessionInterceptor());
	}
	
	public void configHandler(Handlers me) {}
	
	// 服务启动时回调 onStart()
	public void onStart() {}
	// 服务关闭时回调 onStop()
	public void onStop() {}
}






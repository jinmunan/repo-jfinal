package com.demo.blog;

import com.jfinal.aop.Interceptor;
import com.jfinal.aop.Invocation;

/**
 * 本 demo 仅表达最为粗浅的 jfinal 用法，更为有价值的实用的企业级用法
 * 详见 JFinal 俱乐部: https://jfinal.com/club
 * <p>
 * BlogInterceptor
 * 此拦截器仅做为示例展示，在本 demo 中并不需要
 */
public class BlogInterceptor implements Interceptor {

    @Override
    public void intercept(Invocation invocation) {
        System.out.println("方法执行前 控制器方法名 Before invoking " + invocation.getActionKey());
        invocation.invoke();
        System.out.println("方法执行前 控制器方法名 After invoking " + invocation.getActionKey());
    }
}

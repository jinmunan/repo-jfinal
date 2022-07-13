package com.demo;

import com.demo.common.DemoConfig;
import com.jfinal.server.undertow.UndertowServer;

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
        UndertowServer.start(DemoConfig.class, 80, true);
    }
}

package com.cj.jfinal.config;


import com.cj.jfinal.model.Blog;
import com.jfinal.config.*;
import com.jfinal.plugin.activerecord.ActiveRecordPlugin;
import com.jfinal.plugin.druid.DruidPlugin;
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

        /**
         * 支持 Controller、Interceptor、Validator 之中使用 @Inject 注入业务层，并且自动实现 AOP
         * 注入动作支持任意深度并自动处理循环注入
         */
        constants.setInjectDependency(true);
        // 配置对超类中的属性进行注入
        constants.setInjectSuperClass(true);

        /**
         * 		   // 配置 aop 代理使用 cglib，否则将使用 jfinal 默认的动态编译代理方案
         *         constants.setToCglibProxyFactory();
         *
         *         // 配置依赖注入
         *         constants.setInjectDependency(true);
         *
         *         // 配置依赖注入时，是否对被注入类的超类进行注入
         *         constants.setInjectSuperClass(false);
         *
         *         // 配置为 slf4j 日志系统，否则默认将使用 log4j
         *         // 还可以通过 constants.setLogFactory(...) 配置为自行扩展的日志系统实现类
         *         constants.setToSlf4jLogFactory();
         *
         *         // 设置 Json 转换工厂实现类，更多说明见第 12 章
         *         constants.setJsonFactory(new MixedJsonFactory());
         *
         *         // 配置视图类型，默认使用 jfinal enjoy 模板引擎
         *         constants.setViewType(ViewType.JFINAL_TEMPLATE);
         *
         *         // 配置基础下载路径，默认为 webapp 下的 download
         *         constants.setBaseDownloadPath(...);
         *
         *         // 配置基础上传路径，默认为 webapp 下的 upload
         *         constants.setBaseUploadPath(...);
         *
         *         // 配置 404、500 页面
         *         constants.setError404View("/common/404.html");
         *         constants.setError500View("/common/500.html");
         *
         *         // 开启解析 json 请求，5.0.0 版本新增功能
         *         constants.setResolveJsonRequest(true);
         *
         *          // 配置 encoding，默认为 UTF8
         *         constants.setEncoding("UTF8");
         *
         *         // 配置 json 转换 Date 类型时使用的 data parttern
         *         constants.setJsonDatePattern("yyyy-MM-dd HH:mm");
         *
         *         // 配置是否拒绝访问 JSP，是指直接访问 .jsp 文件，与 renderJsp(xxx.jsp) 无关
         *         constants.setDenyAccessJsp(true);
         *
         *         // 配置上传文件最大数据量，默认 10M
         *         constants.setMaxPostSize(10 * 1024 * 1024);
         *
         *         // 配置验证码缓存 cache，配置成集中共享缓存可以支持分布式与集群
         *         constants.setCaptchaCache(...);
         *
         *         // 配置 urlPara 参数分隔字符，默认为 "-"
         *         constants.setUrlParaSeparator("-");
         */

    }

    /**
     * 配置路由
     *
     * @param routes
     */
    @Override
    public void configRoute(Routes routes) {
        // 基本路由
        routes.setBaseViewPath("/view");
        // 路由扫描
        routes.scan("com.cj.jfinal.controller");

        // 原本是注册路由
        //routes.add("/hello", HelloController.class);
        //routes.add("/test", TestController.class);

        /**
         *         // 如果要将控制器超类中的 public 方法映射为 action 配置成 true，一般不用配置
         *         routes.setMappingSuperClass(false);
         *
         *         // 配置 baseViewPath，可以让 render(...) 参数省去 baseViewPath 这部分前缀
         *         routes.setBaseViewPath("/view");
         *
         *         // 配置作用于该 Routes 对象内配置的所有 Controller 的拦截器
         *         routes.addInterceptor(new FrontInterceptor());
         *
         *         // 路由扫描，jfinal 4.9.03 新增功能。参数 "com.xxx." 表示扫描被限定的包名，
         *         // 扫描仅会在该包以及该包的子包下进行
         *         routes.scan("com.xxx.");
         *
         *         // 手工添加路由。注意：使用了路由扫描就不要再使用手工添加路由，两者选其一
         *         routes.add("/hello", HelloController.class);
         */
    }

    /**
     * 配置引擎
     *
     * @param engine
     */
    @Override
    public void configEngine(Engine engine) {
        /**
         *         engine.addSharedFunction("/view/common/layout.html");
         *         engine.addSharedFunction("/view/common/paginate.html");
         *         engine.addSharedFunction("/view/admin/common/layout.html");
         */
    }

    /**
     * 配置插件
     *
     * @param plugins
     */
    @Override
    public void configPlugin(Plugins plugins) {
        /**
         *        DruidPlugin dp = new DruidPlugin(jdbcUrl, userName, password);
         *         plugins.add(dp);
         *
         *         ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
         *         arp.addMapping("user", User.class);
         *         plugins.add(arp);
         */

        DruidPlugin dp = new DruidPlugin("jdbc:mysql://localhost/jfinal", "root", "123456");
        plugins.add(dp);
        ActiveRecordPlugin arp = new ActiveRecordPlugin(dp);
        plugins.add(arp);
        // ActiveRecordPlugin
        arp.addMapping("blog", "id", Blog.class);
    }

    public static DruidPlugin createDruidPlugin() {

        return new DruidPlugin("jdbc:mysql://localhost/jfinal", "root", "123456");
    }

    /**
     * 配置拦截器
     *
     * @param interceptors
     */
    @Override
    public void configInterceptor(Interceptors interceptors) {
        /**
         *         // 以下两行代码配置作用于控制层的全局拦截器
         *         interceptors.add(new AuthInterceptor());
         *         interceptors.addGlobalActionInterceptor(new AaaInterceptor());
         *
         *         // 以下一行代码配置业务层全局拦截器
         *         interceptors.addGlobalServiceInterceptor(new BbbInterceptor());
         */
    }

    /**
     * 配置处理器
     *
     * @param handlers
     */
    @Override
    public void configHandler(Handlers handlers) {
        /**
         *     handlers.add(new ResourceHandler());
         */
    }

    // 系统启动完成后回调
    @Override
    public void onStart() {
    }

    // 系统关闭之前回调
    @Override
    public void onStop() {
    }
}

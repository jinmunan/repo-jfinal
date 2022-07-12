# ************************************************************
# Sequel Pro SQL dump
# Version 4541
#
# http://www.sequelpro.com/
# https://github.com/sequelpro/sequelpro
#
# Host: 127.0.0.1 (MySQL 5.7.20)
# Database: jfinal_blog
# Generation Time: 2020-10-08 14:32:24 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# Dump of table account
# ------------------------------------------------------------

DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickName` varchar(50) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `salt` varchar(150) NOT NULL,
  `state` int(11) NOT NULL,
  `avatar` varchar(128) NOT NULL DEFAULT '' COMMENT '头像',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;

INSERT INTO `account` (`id`, `nickName`, `userName`, `password`, `salt`, `state`, `avatar`, `created`, `updated`)
VALUES
	(1,'James','jfinal','40ad6941dcc31b4dc6271bad843811cdaec733d24edbfc2fe16f6b27119f3b32','xsRCygUWPPx32S-6m4gLqJ0_aLYwMHZE',1,'','2020-10-01 10:25:49','2020-10-01 10:25:49');

/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table blog
# ------------------------------------------------------------

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL,
  `title` varchar(150) NOT NULL,
  `content` text NOT NULL,
  `pic` varchar(128) NOT NULL DEFAULT '' COMMENT '文章配图',
  `state` int(11) NOT NULL COMMENT '0为草稿，1为发布',
  `seoKeywords` int(11) DEFAULT NULL,
  `seoDescription` int(11) DEFAULT NULL,
  `viewCount` int(11) NOT NULL DEFAULT '0' COMMENT '浏览量',
  `created` datetime NOT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '最后更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

LOCK TABLES `blog` WRITE;
/*!40000 ALTER TABLE `blog` DISABLE KEYS */;

INSERT INTO `blog` (`id`, `accountId`, `title`, `content`, `pic`, `state`, `seoKeywords`, `seoDescription`, `viewCount`, `created`, `updated`)
VALUES
	(1,1,'jfinal blog','<h2>一、概述</h2><p>&nbsp; &nbsp;再过三个月 jfinal 将走过 10 年的迭代时期，经过如此长时间的打磨 jfinal 现今已十分完善、稳定。</p><p>&nbsp; &nbsp;jfinal 起步时就定下的开发效率高、学习成本低的核心目标，一直不忘初心，坚持至今。</p><p>&nbsp; &nbsp;jfinal 在开发效率方向越来越逼近极限，如果还要进一步提升开发效率，唯一的道路就是入场前端。</p><p>&nbsp; &nbsp;jfinal 社区经常有同学反馈，用上 jfinal 以后，90% 以上的时间都在折腾前端，强烈希望 jfinal 官方能出手前端，推出一个 jfinal 风格的前端框架。</p><p>&nbsp; &nbsp;虽然我个人对前端没有兴趣，但为了进一步提升广大 jfinal 用户的开发效率，决定这次在前端先小试牛刀。</p><p>&nbsp; &nbsp;本次为大家带来是&nbsp;jfinal 极简风格的前端交互工具箱：jfinal-kit.js。jfinal-kit.js 主要特色如下：</p><ol class=\" list-paddingleft-2\"><li><p>学习成本：不引入&nbsp;vue react angular 这类前端技术栈，核心用法 10 分钟掌握，极大降低学习成本</p></li><li><p>开发效率：尽可能避免编写 js 代码就能实现前端功能，极大提升开发效率</p></li><li><p>用户体验：交互全程 ajax，交互过程 UI 尽可能及时反馈</p></li><li><p>前后分离：只在必要之处使用前后分离，其它地方使用模板引擎，结合前后分离与模板引擎优势<br></p></li></ol><p>&nbsp; &nbsp; 仍是熟悉的味道：学习成本低、开发效率高</p><h2>二、五种交互抽象</h2><p>&nbsp;&nbsp; jfinal-kit.js 将前端交互由轻到重抽象为：msg、switch、confirm、open、fill 五种模式(未来还将增加tab抽象)，以下分别介绍。</p><h3>1、msg 交互</h3><p>&nbsp; &nbsp; msg 用于最轻量级的交互，当用户点击页面中某个组件（如按钮）时立即向后端发起 ajax 请求，然后将后端响应输出到页面。要用该功能，第一步通过 jfinal-kit.js 中的 kit.bindMsg(...) 绑定需要 msg 交互的页面元素：</p><pre>kit.bindMsg(\'#content-box\',&nbsp;\'button[msg],a[msg]\',&nbsp;\'正在处理,&nbsp;请稍候&nbsp;.....\');</pre><p>&nbsp; &nbsp;以上一行代码就可以为带有 msg 属性的标签&nbsp;button 与标签 a 添加 msg 交互功能。</p><p>&nbsp; &nbsp; 注意，bindMsg 方法中的前两个参数在底层实际上就是用的 jquery 的事件绑定方法 on，尽可能用上开发者已有的技术只累，降低学习成本。第三个参数是在交互过程中的提示信息，用于提升用户体验，该参数可以省略。</p><p>&nbsp; &nbsp;第二步在 html 中使用第一步中绑定所带来的功能：</p><pre>&lt;button&nbsp;msg&nbsp;url=\"/func/clearCache\"&gt;\n&nbsp;&nbsp;&nbsp;清除缓存\n&lt;/button&gt;</pre><p>&nbsp; &nbsp;上面的 button 标签中的 url 指向了后端的 action。由于第一步中第二个参数的选择器同时也绑定了 a 标签，所以 button 改为 a 也可以。</p><p>&nbsp; &nbsp; 第三步，在后端添加第二步 url 指向的 action 即可：</p><pre>public&nbsp;void&nbsp;clearCache()&nbsp;{\n&nbsp;&nbsp;&nbsp;cacheService.clearCache();\n&nbsp;&nbsp;&nbsp;renderJson(Ret.ok(\"msg\",&nbsp;\"缓存清除完成\"));\n}</pre><p>&nbsp; &nbsp; 整个过程的代码量极少，前端交互功能的实现也像后端一样快了，开发效率得到极大提升。</p><h3>2、switch 交互</h3><p>&nbsp; &nbsp;switch 交互是指类似于手机设置中心开关控件功能，点击 switch 可在两种状态间来回切换，使用方法：</p><pre>kit.bindSwitch(\'#content-box\',&nbsp;\'div.custom-switch&nbsp;input[url]\');</pre><p>&nbsp; &nbsp;与 msg 交互类似，同样也是一行代码。参数用法也一样：将 switch 交互功能绑定到带有 url 的 input 控件上（div.custom-switch是jquery选择器的一部分）。功能绑定后，就可以在 html 中使用了：</p><pre>&lt;div&nbsp;class=\"custom-control&nbsp;custom-switch\"&gt;\n&nbsp;&nbsp;&nbsp;&lt;input&nbsp;#(x.state&nbsp;==&nbsp;1&nbsp;?&nbsp;\'checked\':\'\')&nbsp;url=\'/blog/publish?id=#(x.id)\'&nbsp;type=\"checkbox\"&gt;\n&nbsp;&nbsp;&nbsp;&lt;label&nbsp;class=\"custom-control-label\"&nbsp;for=\"id-#(x.id)\"&gt;&lt;/label&gt;\n&lt;/div&gt;</pre><p>&nbsp; &nbsp;上面代码中的 div、lable 仅仅为 bootstrap 4 的 switch 组件所要求的内容，不必关注，重点关注 input 标签，其 url 指向了后端 action，在后端添加即可：</p><pre>public&nbsp;void&nbsp;publish()&nbsp;{\n&nbsp;&nbsp;&nbsp;Ret&nbsp;ret&nbsp;=&nbsp;srv.publish(getInt(\"id\"),&nbsp;getBoolean(\"checked\"));\n&nbsp;&nbsp;&nbsp;renderJson(ret);\n}</pre><p>&nbsp; &nbsp;switch 交互与 msg 在本质上是完全相同的。</p><h3>3、confirm 交互</h3><p>&nbsp; &nbsp; confirm 交互与 msg 交互基本一样，只不过在与后端交互之前会弹出对话框进行确认，使用方法：</p><pre>kit.bindConfirm(\'#content-box\',&nbsp;\'a[confirm],button[confirm]\');</pre><p>&nbsp; &nbsp;与 msg、switch 本质上一样，将 confirm 交互绑定到具有 confirm 属性的 a 标签与 button 标签上。在 html 中使用：</p><pre>&lt;button&nbsp;confirm=\"确定重启项目&nbsp;？\"&nbsp;url=\"/admin/func/restart\"&gt;\n&nbsp;&nbsp;&nbsp;重启项目\n&lt;/button&gt;</pre><p>&nbsp; &nbsp;最后是添加后端 action：</p><pre class=\"brush:java;toolbar:false\">public&nbsp;void&nbsp;restart()&nbsp;{\n&nbsp;&nbsp;&nbsp;renderJson(srv.restart());\n}</pre><p>&nbsp; &nbsp;以上 msg、switch、confirm 三种交互方式，使用模式完全一样：<span style=\"font-family: 微软雅黑; font-size: 18px;\">绑定、添加 html（url指向后端action）、添加 action。</span></p><h3><span style=\"font-family: 微软雅黑; font-size: 18px;\">4、open 交互<span style=\"font-size: 18px; font-family: arial, helvetica, sans-serif;\"></span></span></h3><p>&nbsp; &nbsp;&nbsp;<span style=\"font-family: 微软雅黑; font-size: 18px;\">open 交互方式与前面三种交互方式基本相同，不同之处在于前三种交互方式参与的元素就在当前页面，而 open 交互方式的参与元素是一个独立的 html 文件，第一步仍然是绑定：</span></p><pre class=\"brush:java;toolbar:false\">kit.bindOpen(\'#content-box\',&nbsp;\'a[open],button[open]\',&nbsp;\'正在加载,&nbsp;请稍候&nbsp;.....\');</pre><p><span style=\"font-family: 微软雅黑; font-size: 18px;\"></span>&nbsp; &nbsp; 以上代码的含义与 msg 类似，将 open 交互功能绑定到带有 open 属性的 a 标签与 button 标签之上。</p><p>&nbsp; &nbsp; 第二步仍然是在 html 中使用：</p><pre class=\"brush:html;toolbar:false;\">&lt;button&nbsp;open&nbsp;url=\"/account/add\"&gt;\n&nbsp;&nbsp;&nbsp;创建\n&lt;/button&gt;</pre><p>&nbsp; &nbsp; 第三步仍然是创建 url 指向的 action：</p><pre class=\"brush:java;toolbar:false\">public&nbsp;void&nbsp;add()&nbsp;{\n&nbsp;&nbsp;&nbsp;render(\"add.html\");\n}</pre><p>&nbsp; &nbsp; 第三步与 msg、switch、confirm 交互不同之处在于，这里是返回一个独立的页面，而非返回 json 数据。注意，如果页面并没有动态内容，无需模板引擎渲染的话，无需创建该 action，而是让 url 直接指向它就可以了：</p><pre class=\"brush:html;toolbar:false\">&lt;button&nbsp;open&nbsp;url=\"/这里是一个静态页面文件.html\"&gt;\n&nbsp;&nbsp;&nbsp;创建\n&lt;/button&gt;</pre><p>&nbsp; &nbsp; 第四步是创建页面 \"add.html\" 单独用于交互，页面的主要内容如下：</p><pre class=\"brush:html;toolbar:false\">&lt;!--&nbsp;弹出层主体&nbsp;--&gt;\n&lt;div&nbsp;class=\"open-box\"&gt;\n&nbsp;&nbsp;&lt;form&nbsp;id=\"open-form\"&nbsp;action=\"/account/save\"&gt;	\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;div&nbsp;class=\"row\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;label&nbsp;class=\"col-2&nbsp;col-form-label\"&gt;昵称&lt;/label&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;input&nbsp;name=\"nickName\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;div&nbsp;class=\"row\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;label&nbsp;class=\"col-2&nbsp;col-form-label\"&gt;账号&lt;/label&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;input&nbsp;name=\"userName\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;div&nbsp;class=\"row\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;label&nbsp;class=\"col-2&nbsp;col-form-label\"&gt;密码&lt;/label&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;input&nbsp;name=\"password\"&nbsp;type=\"password\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;div&nbsp;class=\"row\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;button&nbsp;onclick=\"submitAccount();\"&gt;提交&lt;/button&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;&nbsp;&lt;/form&gt;\n&lt;/div&gt;\n\n&lt;!--&nbsp;弹出层样式&nbsp;--&gt;\n&lt;style&gt;\n&nbsp;&nbsp;.open-box&nbsp;{padding:&nbsp;20px&nbsp;30px&nbsp;0&nbsp;35px;}\n&lt;/style&gt;\n\n&lt;!--&nbsp;弹出层&nbsp;js&nbsp;脚本&nbsp;--&gt;\n&lt;script&gt;\n&nbsp;&nbsp;function&nbsp;submitAccount()&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;$form&nbsp;=&nbsp;$(\'#open-form\');\n&nbsp;&nbsp;&nbsp;&nbsp;kit.post($form.attr(\'action\'),&nbsp;$form.serialize(),&nbsp;function(ret)&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;kit.msg(ret);\n&nbsp;&nbsp;&nbsp;&nbsp;});\n&nbsp;&nbsp;}\n&lt;/script&gt;</pre><p>&nbsp; &nbsp; 以上 add.html 页面是用于交互的 html 内容，该内容将会显示在一个弹出的对话框之中。该文件的内容分为 html、css、js 三个部分，从而可以实现功能的模块化。</p><p>&nbsp; &nbsp; 第五步，针对 add.html 中 form 表单的 action=\"/account/save\" 创建相应的 action：</p><pre class=\"brush:java;toolbar:false\">public&nbsp;void&nbsp;save()&nbsp;{\n&nbsp;&nbsp;Ret&nbsp;ret&nbsp;=&nbsp;srv.save(getBean(Account.class));\n&nbsp;&nbsp;renderJson(ret);\n}</pre><p>&nbsp; &nbsp;action 代码十分简单，与 msg 交互模式代码风格一样。</p><p>&nbsp; &nbsp; open 交互需要一个独立的页面作为载体，而 msg、switch、confirm 没有这个载体。</p><h3>5、fill 交互</h3><p>&nbsp; &nbsp;fill 交互与前面四种交互很不一样，它是向当前页面的指定容器填充 html 内容，从而在当前页面中进行交互。</p><p>&nbsp; &nbsp;第一步仍然是绑定：</p><pre class=\"brush:java;toolbar:false\">kit.bindFill(\'#content-box\',&nbsp;\'a[fill],button[fill],ul.pagination&nbsp;a[href]\',&nbsp;\'#content-box\');</pre><p>&nbsp; &nbsp;前两个参数与前面四种交互模式完全一样，最后一个参数 \'#content-box\' 表示从后端被加载的 html 内容 fill 到的容器。<br></p><p>&nbsp; &nbsp;第二步与前面四种交互模式的用法完全一样，不再详述。</p><p>&nbsp; &nbsp;第三步与 open 模式的<span style=\"font-family: 微软雅黑; font-size: 18px;\">第四步创建页面 \"add.html\" 单独用于交互完全一样，也不再详述。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;fill 与 open 在本质上是一样的，只不过前者是将交互用的 html 文件内容直接 fill 到当前页面，后者是用弹出层来承载 html 文件内容，仅此而已。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;所以，学会了 open，相当于就学会了 fill。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;最后，fill 交互是实现前后分离模式的基础，后续章节将深入介绍。</span></p><h2><span style=\"font-family: 微软雅黑; font-size: 18px;\"></span></h2><h2 style=\"white-space: normal;\"><span style=\"font-family: 微软雅黑; font-size: 18px;\"><span style=\"font-size: 24px;\">三、前后端半分离方案</span></span></h2><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;最近几年前后分离技术很热，前后分离有很多优点，但对于全栈开发者和中小企业也有一定的缺点。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;首先，前后分离不利于 SEO，不利于搜索引擎收录。搜索引擎仍是巨大的流量入口，如果辛辛苦苦创建的内容没有被搜索引擎收录，将是巨大的损失。</span></p><p>&nbsp; &nbsp;其次，前后分离通常要引入其整个技术栈，会带来一定的学习成本。jfinal 社区多数开发者主要面向后端开发，如果再引入前后分离技术栈，很多同学并没有多少时间与动力。有兴趣原因也有专注度原因，前端也是一片汪洋大海。</p><p>&nbsp; &nbsp;再次，前后分离通常要设置前端与后端两种工作岗位，对于小企业有成本压力。维护前后分离项目的成本也有所增加。</p><p>&nbsp; &nbsp;最后，前后分离减轻了后端工作负担，加重了前端工作负担，但对于 jfinal 社区的全栈开发者来说，相当程度上是工作负担的转移，总体上并没有消除多少工作量。对于后端包打天下，未设置前端职位的中小企业带来的是成本提升与效率降低。</p><p>&nbsp; &nbsp;jfinal-kit.js 希望能得到前后分离的优点，并同时能消除它的缺点。<br></p><p>&nbsp; &nbsp;jfinal-kit.js 的采用前后端 \"半分离\" 方案：只在必要的地方前后分离拿走前后分离的好处，其它地方使用模板引擎扔掉前后分离的坏处。并且不必引入&nbsp;<span style=\"font-family: 微软雅黑; font-size: 18px;\">vue、react 等前端技术栈，消除学习成本。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;jfinal-kit.js 的前后半分离具体是下面这样的，需要前后分离的 \"xxx.html\" 页面内容如下：</span></p><pre class=\"brush:java;toolbar:false\">&lt;!DOCTYPE&nbsp;html&gt;\n&lt;html&nbsp;lang=\"zh-CN\"&gt;\n&nbsp;&nbsp;&lt;head&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容省略...\n&nbsp;&nbsp;&lt;/head&gt;\n\n&nbsp;&nbsp;&lt;body&nbsp;class=\"home-template\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;内容省略...\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;!--&nbsp;下面&nbsp;div&nbsp;内的内容通过&nbsp;ajax&nbsp;获取，实现前后分离&nbsp;--&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;div&nbsp;id=\'article\'&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;&nbsp;\n&nbsp;&nbsp;&lt;/body&gt;\n&nbsp;&nbsp;\n&nbsp;&nbsp;&lt;script&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;$(function()&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;kit.fill(\'/article/123\',&nbsp;null,&nbsp;\'#article\');\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;});&nbsp;&nbsp;&nbsp;\n&nbsp;&nbsp;&lt;/script&gt;\n&lt;/html&gt;</pre><p><span style=\"font-family: 微软雅黑; font-size: 18px;\"></span>&nbsp; &nbsp;以上 \"xxx.html\" 文件只有静态内容，动态内容通过 kit.fill(...) 异步加载，其第一个参数指向的 action 后端代码如下：</p><pre class=\"brush:java;toolbar:false\">public&nbsp;void&nbsp;article()&nbsp;{\n&nbsp;&nbsp;&nbsp;set(\"article\",&nbsp;srv.getById(getPara()));\n&nbsp;&nbsp;&nbsp;render(\"_article.html\");\n}</pre><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;以上代码中的 \"_article.html\" 是与传统前的分离方案不同的地方，传统前后分离返回的是 json 数据，而这里返回的是 html 片段，其代码结构如下：</span></p><pre class=\"brush:html;toolbar:false\">&lt;div&nbsp;class=\"article-box\"&gt;\n&nbsp;&nbsp;&lt;div&nbsp;class=\"title\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt;#(article.title)&lt;/span&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;span&gt;#date(article.createTime)&lt;/span&gt;\n&nbsp;&nbsp;&lt;/div&gt;\n&nbsp;&nbsp;\n&nbsp;&nbsp;&lt;div&nbsp;class=\"content\"&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;#(article.content)\n&nbsp;&nbsp;&lt;/div&gt;\n&lt;/div&gt;</pre><p>&nbsp; &nbsp; 上面的 html 片段内容以模板方式展现，可读性、可维护性比传统前后分离要好。并且 html 片将由模板引擎渲染，客户端没有计算压力。</p><p>&nbsp; &nbsp; 以上仅仅示范了静态页面的一处动态加载方式，也可以使用任意多处动态加载，并且动态部分的粒度可以极细。例如假定静态部分如下：</p><pre class=\"brush:html;toolbar:false\">其它地方与前面的&nbsp;xxx.html&nbsp;一样，省去....\n\n&lt;table&nbsp;class=\"table&nbsp;table-hover\"&gt;			\n&nbsp;&nbsp;&lt;thead&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;tr&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;ID&lt;/th&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;昵称&lt;/th&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;登录名&lt;/th&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&lt;th&gt;创建&lt;/th&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;/tr&gt;\n&nbsp;&nbsp;&lt;/thead&gt;			\n&nbsp;&nbsp;&lt;tbody&nbsp;id=\'account-table\'&gt;\n&nbsp;&nbsp;&lt;/tbody&gt;\n&lt;/table&gt;\n\n&lt;script&gt;\n&nbsp;&nbsp;$(function()&nbsp;{\n&nbsp;&nbsp;&nbsp;&nbsp;kit.fill(\'/account/list\',&nbsp;null,&nbsp;\'#account-table\');\n&nbsp;&nbsp;});&nbsp;&nbsp;&nbsp;\n&lt;/script&gt;\n\n其它地方与前面的&nbsp;xxx.html&nbsp;一样，省去....</pre><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp;然后创建一个 action 响应上面代码中的 \"/account/list\"：</span></p><pre class=\"brush:java;toolbar:false\">public&nbsp;void&nbsp;list()&nbsp;{\n&nbsp;&nbsp;set(\"accountList\",&nbsp;srv.getAccountList());\n&nbsp;&nbsp;render(\"_account_table.html\");\n}</pre><p>&nbsp; &nbsp;以上代码中的 _account_table.html 如下：<br></p><pre class=\"brush:html;toolbar:false;\">#for&nbsp;(x&nbsp;:&nbsp;accountList)\n&nbsp;&nbsp;&lt;tr&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;#(x.id)&lt;/td&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;#(x.nickName)&lt;/td&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;#(x.userName)&lt;/td&gt;\n&nbsp;&nbsp;&nbsp;&nbsp;&lt;td&gt;#date(x.created)&lt;/td&gt;\n&nbsp;&nbsp;&lt;/tr&gt;\n#end</pre><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp; 以上 _account.html 以模板形式展示，用 enjoy 进行渲染，使用简单，可读性高。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp; 从本质上来说传统前后分离与 jfinal-kit.js 前后分离几乎一样，都是先向客户端响应静态 html + css + js，然后通过 ajax 向后端获取数据并渲染出动态内容，区别就在于前者是获取 json 并在客户端进行渲染，而后者是直接获取后端渲染好的 html 片进行简单的填充，仅此而已。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp; 即便在底层技术实现上是如此的相似，但 jfinal-kit.js 无需引入复杂的技术栈，极大降低了学习成本。</span></p><h2><span style=\"font-family: 微软雅黑; font-size: 18px;\"><span style=\"font-family: 微软雅黑; font-size: 24px;\">四、jfinal blog 实践</span></span></h2><p>&nbsp; &nbsp;&nbsp;<span style=\"font-family: 微软雅黑; font-size: 18px;\">jfinal-kit.js 以 jfinal blog 为载体，演示了 jfinal-kit.js 中的功能用法，实现了一些常见功能：账户管理、文章管理、图片管理、功能管理、登录等功能。</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp; jfinal blog 后台管理 UI 面向实际项目精心设计，可以作为项目起步的蓝本。以下是部分界面截图：</span></p><p><span style=\"font-family: 微软雅黑; font-size: 18px;\">&nbsp; &nbsp; 补充截图</span></p><p>&nbsp; &nbsp; 实践证明，开发效率极大提升，学习成本极低，几乎不用写 js 代码就轻松实现前后交互。<span style=\"font-family: 微软雅黑; font-size: 18px;\">学习成本、开发效率两个方向符合预期目标，符合 jfinal 极简设计思想。</span></p><h2>五、咖啡授权</h2><p>&nbsp; &nbsp; app &amp; coffee 频道所有 application 采用咖啡授权模式，意在请作者喝一杯咖啡即可获得授权。</p>','',1,NULL,NULL,0,'2020-10-01 10:25:49','2020-09-26 22:02:02'),
	(2,1,'app & coffee 是什么？','<p>&nbsp; app &amp; coffee 是 jfinal 社区推出的新频道，目的是建立 jfinal 生态，以及进一步提升开发效率。</p>\r\n\r\n<p>&nbsp; 先说建立生态。jfinal 开源这 9 年，过于专注于技术，生态建设一直被忽视，这是 jfinal 最大的战略失误。jfinal 已经错过了建立生态的黄金时期，常规方式会很难也很慢，所以只能在创新方面想办法。</p>\r\n\r\n<p>&nbsp; app &amp; coffee 的核心在于建立一个正向激励的循环系统，让大家有动力参与生态的建设。简单来说就是俱乐部会员可以在 app &amp; coffee 频道发布符合规范的 application，通过咖啡授权协议获取一定的回报。被授权方可以用一杯咖啡的代价获得需要的 application。</p>\r\n\r\n<p>&nbsp; 再说提升开发效率。app &amp; coffee 频道发布的 application 是要通过严格规范才能上架的，例如对代码量、项目结构、项目类型都是有要求的。简单来说就是要细致入微地针对需求方来打造 application，需求方拿到授权以后，能大大提升开发效率。</p>\r\n\r\n<p>&nbsp; 最后，由于去年小孩出生，app &amp; coffee 的实施已被严重耽误，对于生态建设的时机造成了重大损失，app &amp; coffee 这个模式是否能成，还需要时间的检验，希望得到你的支持</p>\r\n\r\n<p>&nbsp;</p>\r\n\r\n<p>&nbsp;</p>\r\n','',1,NULL,NULL,0,'2020-10-01 10:25:49','2020-10-08 00:25:31');

/*!40000 ALTER TABLE `blog` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table image
# ------------------------------------------------------------

DROP TABLE IF EXISTS `image`;

CREATE TABLE `image` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `accountId` int(11) NOT NULL COMMENT '上传者',
  `path` varchar(200) NOT NULL DEFAULT '' COMMENT '相对于jfinal baseUploadPath 的相对路径',
  `fileName` varchar(200) NOT NULL DEFAULT '' COMMENT '文件名',
  `showName` varchar(200) NOT NULL DEFAULT '' COMMENT '显示名',
  `length` int(11) NOT NULL COMMENT '文件长度',
  `created` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

LOCK TABLES `image` WRITE;
/*!40000 ALTER TABLE `image` DISABLE KEYS */;

INSERT INTO `image` (`id`, `accountId`, `path`, `fileName`, `showName`, `length`, `created`)
VALUES
	(1,1,'/image/','1_20200912005046.png','1_20200912005046.png',319578,'2020-10-01 10:25:49'),
	(2,1,'/image/','1_20200926171145.jpg','1_20200926171145.jpg',69746,'2020-10-02 10:25:49'),
	(3,1,'/image/','1_20200926171135.jpg','1_20200926171135.jpg',73739,'2020-10-03 10:25:49'),
	(4,1,'/image/','1_20200926171305.jpeg','1_20200926171305.jpeg',134981,'2020-10-04 10:25:49'),
	(5,1,'/image/','1_20200926171251.jpg','1_20200926171251.jpg',52549,'2020-10-05 10:25:49'),
	(6,1,'/image/','1_20200926171153.jpg','1_20200926171153.jpg',98774,'2020-10-06 10:25:49'),
	(7,1,'/image/','1_20200912004925.jpg','1_20200912004925.jpg',96153,'2020-10-07 10:25:49'),
	(8,1,'/image/','1_20200911222106.png','1_20200911222106.png',200785,'2020-10-08 10:25:49'),
	(9,1,'/image/','1_20200926171222.jpg','1_20200926171222.jpg',32091,'2020-10-09 10:25:49'),
	(10,1,'/image/','1_20200926171205.jpg','1_20200926171205.jpg',178034,'2020-10-10 10:25:49');

/*!40000 ALTER TABLE `image` ENABLE KEYS */;
UNLOCK TABLES;


# Dump of table login_log
# ------------------------------------------------------------

DROP TABLE IF EXISTS `login_log`;

CREATE TABLE `login_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userName` varchar(50) NOT NULL DEFAULT '' COMMENT '登录使用的账户名',
  `accountId` int(11) DEFAULT NULL COMMENT '登录成功的accountId',
  `state` int(11) NOT NULL COMMENT '登录成功为1,否则为0',
  `ip` varchar(100) DEFAULT NULL,
  `port` int(11) NOT NULL,
  `created` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



# Dump of table session
# ------------------------------------------------------------

DROP TABLE IF EXISTS `session`;

CREATE TABLE `session` (
  `id` varchar(33) NOT NULL DEFAULT '' COMMENT 'session id',
  `accountId` int(11) NOT NULL COMMENT '账户 id',
  `created` datetime NOT NULL COMMENT '创建时间',
  `expires` datetime NOT NULL COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

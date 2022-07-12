package com.jfinal.app.blog.common.model;

import com.jfinal.app.blog.common.AppConfig;
import com.jfinal.plugin.activerecord.dialect.MysqlDialect;
import com.jfinal.plugin.activerecord.generator.Generator;
import com.jfinal.plugin.activerecord.generator.TypeMapping;
import com.jfinal.plugin.druid.DruidPlugin;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import javax.sql.DataSource;

/**
 * Model、BaseModel、_MappingKit 生成器
 * 
 * 添加新表，或者修改表结构时重新运行该生成器即可
 */
public class _Generator {
	
	/**
	 * 在此统一添加不参与生成的 table。不参与生成的 table 主要有：
	 * 1：用于建立表之间关系的关联表，如 account_blog
	 * 2：部分功能使用 Db + Record 模式实现，所涉及到的 table 也不参与生成
	 */
	private static final String[] blacklist = {
		"login_log",
		"account_blog"
	};
	
	/**
	 * 重用 JFinalClubConfig 中的数据源配置，避免冗余配置
	 */
	public static DataSource getDataSource() {
		DruidPlugin druidPlugin = AppConfig.getDruidPlugin();
		druidPlugin.start();
		return druidPlugin.getDataSource();
	}
	
	public static void main(String[] args) {
		// model 所使用的包名 (MappingKit 默认使用的包名)
		String modelPackageName = "com.jfinal.app.blog.common.model";
		
		// base model 所使用的包名
		String baseModelPackageName = modelPackageName + ".base";
		
		// base model 文件保存路径
		String baseModelOutputDir = System.getProperty("user.dir")
				+ "/src/main/java/" + baseModelPackageName.replace('.', '/');
		
		System.out.println("输出路径："+ baseModelOutputDir);
		
		// model 文件保存路径 (MappingKit 与 DataDictionary 文件默认保存路径)
		String modelOutputDir = baseModelOutputDir + "/..";
		
		// 创建生成器
		Generator gen = new Generator(getDataSource(), baseModelPackageName, baseModelOutputDir, modelPackageName, modelOutputDir);
		
		// 设置数据库方言
		gen.setDialect(new MysqlDialect());
		
		// 设置是否生成字段备注
		gen.setGenerateRemarks(true);
		
		// 添加不需要生成的表名到黑名单
		gen.addBlacklist(blacklist);
		
		// 设置是否在 Model 中生成 dao 对象
		gen.setGenerateDaoInModel(false);
		
		// 设置是否生成字典文件
		gen.setGenerateDataDictionary(false);
		
		// 设置需要被移除的表名前缀用于生成modelName。例如表名 "osc_user"，移除前缀 "osc_"后生成的model名为 "User"而非 OscUser
		// gernerator.setRemovedTableNamePrefixes("t_");
		
		// 将 mysql 8 以及其它原因之下生成 jdk 8 日期类型映射为 java.util.Date，便于兼容老项目，也便于习惯使用 java.util.Date 的同学
        TypeMapping tm = new TypeMapping();
        tm.addMapping(LocalDateTime.class, Date.class);
        tm.addMapping(LocalDate.class, Date.class);
        // tm.addMapping(LocalTime.class, LocalTime.class);		// LocalTime 暂时不变
        gen.setTypeMapping(tm);
        
		// 生成
		gen.generate();
	}
}





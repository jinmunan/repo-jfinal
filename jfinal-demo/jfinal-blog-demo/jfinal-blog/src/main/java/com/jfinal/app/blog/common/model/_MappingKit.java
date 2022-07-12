package com.jfinal.app.blog.common.model;

import com.jfinal.plugin.activerecord.ActiveRecordPlugin;

/**
 * Generated by JFinal, do not modify this file.
 * <pre>
 * Example:
 * public void configPlugin(Plugins me) {
 *     ActiveRecordPlugin arp = new ActiveRecordPlugin(...);
 *     _MappingKit.mapping(arp);
 *     me.add(arp);
 * }
 * </pre>
 */
public class _MappingKit {
	
	public static void mapping(ActiveRecordPlugin arp) {
		arp.addMapping("account", "id", Account.class);
		arp.addMapping("blog", "id", Blog.class);
		arp.addMapping("image", "id", Image.class);
		arp.addMapping("session", "id", Session.class);
	}
}



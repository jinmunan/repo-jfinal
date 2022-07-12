package com.jfinal.app.blog.common.model;

import com.jfinal.app.blog.common.kit.JsoupFilter;
import com.jfinal.app.blog.common.model.base.BaseAccount;

/**
 * Account
 */
@SuppressWarnings("serial")
public class Account extends BaseAccount<Account> {
	
	public static final String AVATAR_NO_AVATAR = "x.jpg";    // 刚注册时使用默认头像
	
	public static final int STATE_LOCK = -1;		// 锁定账号，无法做任何事情
	public static final int STATE_REG = 0;			// 注册、未激活
	public static final int STATE_OK = 1;			// 正常、已激活
	
	public boolean isStateOk() {
		return getState() == STATE_OK;
	}
	
	public boolean isStateReg() {
		return getState() == STATE_REG;
	}
	
	public boolean isStateLock() {
		return getState() == STATE_LOCK;
	}
	
	/**
	 * 过滤掉 nickName 中的 html 标记，恶意脚本
	 */
	protected void filter(int filterBy) {
		JsoupFilter.filterAccountNickName(this);
	}
	
	public Account removeSensitiveInfo() {
		remove("password", "salt");
		return this;
	}
}








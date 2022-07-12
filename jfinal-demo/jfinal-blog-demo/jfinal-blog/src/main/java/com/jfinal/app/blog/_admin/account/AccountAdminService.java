/**
 * 本项目采用《咖啡授权协议》，保护知识产权，就是在保护我们自己身处的行业。
 * 
 * Copyright (c) 2011-2021, jfinal.com
 */

package com.jfinal.app.blog._admin.account;

import java.util.Date;
import com.jfinal.app.blog.common.model.Account;
import com.jfinal.kit.HashKit;
import com.jfinal.kit.Ret;
import com.jfinal.kit.StrKit;
import com.jfinal.plugin.activerecord.Db;
import com.jfinal.plugin.activerecord.Page;

/**
 * 账户管理业务层
 */
public class AccountAdminService {
	
	private static int pageSize = 25;
	private Account dao = new Account().dao();
	
	/**
	 * 分页
	 */
	public Page<Account> paginate(int pageNumber) {
		return dao.paginate(pageNumber, pageSize, "select *", "from account order by updated desc");
	}
	
	/**
	 * 搜索。使用同一个 key 用于匹配 nickName 与 userName
	 */
	public Page<Account> search(String key, int pageNumber) {
		String sql = "select * from account "
						+ "where nickName like concat('%', #para(0), '%') "
						+ "or    userName like concat('%', #para(1), '%') "
						+ "order by updated desc";
		return dao.templateByString(sql, key, key).paginate(pageNumber, pageSize);
	}
	
	/**
	 * 创建
	 */
	public Ret save(Account acc) {
		preProccess(acc);
		
		Ret ret = validate(acc, true);
		if (ret != null) {
			return ret;
		}
		
		// userName 存小写到数据库（不区分大小写）
		acc.setUserName(acc.getUserName().trim().toLowerCase());
		passwordSaltAndHash(acc);
		acc.setState(Account.STATE_LOCK);
		acc.setCreated(new Date());
		acc.setUpdated(acc.getCreated());
		acc.setAvatar(Account.AVATAR_NO_AVATAR);	// 注册时设置默认头像
		acc.save();
		
		return Ret.ok("msg", "创建成功");
	}
	
	// 密码加盐 hash
	public void passwordSaltAndHash(Account acc) {
		if (StrKit.isBlank(acc.getPassword())) {
			throw new RuntimeException("密码不能为空");
		}
		
		String salt = HashKit.generateSaltForSha256();
		String pwd = acc.getPassword();
		pwd = HashKit.sha256(salt + pwd);
		
		acc.setPassword(pwd);
		acc.setSalt(salt);
	}
	
	/**
	 * 预处理
	 * 1：去除前后空白字符
	 * 2：登录名 userName 转换成小写字符（不区分大小写）
	 * 3：预防 mass assignment 攻击，只保留以下必要字段
	 *     https://jfinal.com/feedback/4677
	 *     https://www.oschina.net/question/260040_46570
	 */
	private void preProccess(Account a) {
		// 只保留必要字段，预防 mass assignment 攻击
		a.keep("id", "userName", "password", "nickName");
		
		// 移除所有空值属性，避免 account.update() 将某些字段置为 null
		a.removeNullValueAttrs();
		
		// userName 转换为小写字母（不区分大小写）
		if (a.getUserName() != null) {
			a.setUserName(a.getUserName().trim().toLowerCase());
		}
		
		// password 去除前后空白
		if (a.getPassword() != null) {
			a.setPassword(a.getPassword().trim());
		}
		
		// password 去除前后空白
		if (a.getNickName() != null) {
			a.setNickName(a.getNickName().trim());
		}
	}
	
	/**
	 * 验证
	 * @param create true 表示验证创建 account，否则表示验证更新 account
	 */
	private Ret validate(Account account, boolean create) {
		String userName = account.getUserName();
		String password = account.getPassword();
		String nickName = account.getNickName();
		
		if (StrKit.isBlank(userName)) {
			return Ret.fail("用户名不能为空");
		}
		
		// 创建
		if (create) {
			if (StrKit.isBlank(password)) {
				return Ret.fail("密码不能为空");
			}
			if (password.trim().length() < 6) {
				return Ret.fail("密码长度至少为 6");
			}
		}
		// 更新
		else {
			if (StrKit.notBlank(password) && password.trim().length() < 6) {
				return Ret.fail("密码长度至少为 6");
			}
		}
		
		if (StrKit.isBlank(nickName)) {
			return Ret.fail("昵称不能为空");
		}
		
		if (userName.contains(" ") || userName.contains("　")) { // 检测是否包含半角或全角空格
			return Ret.fail("用户名不能包含空格");
		}
		if (nickName.contains(" ") || nickName.contains("　")) { // 检测是否包含半角或全角空格
			return Ret.fail("昵称不能包含空格");
		}
		
		// 创建时的 id 为 -1，用于 isUserNameExists、isNickNameExists 方法中使用
		int id = create ? -1 : account.getId();
		if (isUserNameExists(userName, id)) {
			return Ret.fail("用户名已被注册，请换一个用户名");
		}
		if (isNickNameExists(nickName, id)) {
			return Ret.fail("昵称已被注册，请换一个昵称");
		}
		
		return null;
	}
	
	/**
	 * 用户名是否已被注册
	 * 注意：userName 被转换为小写字母后存入数据库
	 */
	private boolean isUserNameExists(String userName, int selfId) {
		userName = userName.toLowerCase().trim();
		return Db.queryInt("select id from account where userName = ? and id != ? limit 1", userName, selfId) != null;
	}
	
	/**
	 * 昵称是否已被注册，昵称不区分大小写，以免存在多个用户昵称看起来一个样的情况
	 *
	 *  mysql 的 where 字句与 order by 子句默认不区分大小写，区分大小写需要在
	 *  字段名或字段值前面使用 binary 关键字例如：
	 *  where nickName = binary "jfinal" 或者 where binary nickName = "jfinal"，前者性能要高
	 *
	 *  为了避免不同的 mysql 配置破坏掉 mysql 的 where 不区分大小写的行为，这里在 sql 中使用
	 *  lower(...) 来处理，参数 nickName 也用 toLowerCase() 方法来处理，再次确保不区分大小写
	 */
	private boolean isNickNameExists(String nickName, int selfId) {
		nickName = nickName.toLowerCase().trim();
		return Db.queryInt("select id from account where lower(nickName) = ? and id != ? limit 1", nickName, selfId) != null;
	}
	
	/**
	 * 更新
	 */
	public Ret update(int loginAccountId, Account acc) {
		if (loginAccountId != 1 && loginAccountId != acc.getId()) {
			return Ret.fail("只能修改自己的账户(ID 为 1 的管理员除外)");
		}
		
		preProccess(acc);
		
		Ret ret = validate(acc, false);
		if (ret != null) {
			return ret;
		}
		
		// 密码不为空时，表明密码需要修改
		if (StrKit.notBlank(acc.getPassword())) {
			passwordSaltAndHash(acc);
		}
		
		acc.setUpdated(new Date());
		acc.update();
		
		return Ret.ok("msg", "更新成功"); 
	}
	
	/**
	 * 切换状态。在锁定与正常两个状态间切换
	 */
	public Ret switchState(int loginAccountId, int id, boolean checked) {
		if (loginAccountId == id) {
			return Ret.fail("不能锁定自己的账户");
		}
		if (id == 1) {
			return Ret.fail("id 值为 1 的账户不能锁定");
		}
		if (loginAccountId != 1) {
			return Ret.fail("只有账户 ID 值为 1 的管理员才有权操作");
		}
		
		int state = checked ? Account.STATE_OK : Account.STATE_LOCK;
		String sql = "update account set state = ? where id = ?";
		Db.update(sql, state, id);
		return Ret.ok();
	}
	
	/**
	 * 获取
	 */
	public Account getById(int id) {
		return dao.findById(id);
	}
	
	/**
	 * 删除
	 */
	public Ret deleteById(int loginAccountId, int id) {
		if (loginAccountId == id) {
			return Ret.fail("不能删除自己的账户");
		}
		if (id == 1) {
			return Ret.fail("id 值为 1 的账户不能删除");
		}
		dao.deleteById(id);
		return Ret.ok("msg", "删除成功");
	}
	
	/**
	 * 判断账户是否仍在使用默认密码 "111111"
	 */
	public boolean isDefaultPassword(Account acc) {
		String hashedPwd = HashKit.sha256(acc.getSalt() + "111111");
		return hashedPwd.equals(acc.getPassword());
	}
}






package cn.com.mwsn.mpm.service;

import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.mpm.entity.User;

public interface UserService extends TransactionalService{
	
	/**
	 * 根据用户名和密码验证合法性
	 * @param account
	 * @param pwd
	 * @return
	 * 	0：验证成功；
	 * 	1：用户名或者密码错误；
	 * 	2：用户名和密码正确，但是该账户不允许登陆；
	 * 	3: 无此账户；
	 */
	public int validateAccount(String account, String pwd);
	
	
	/**
	 * 通过账号查询用户信息
	 * @param account
	 * @return
	 * 	如果该账号存在，则返回User实体类，否则返回null
	 */
	public User queryUser(String account);

}

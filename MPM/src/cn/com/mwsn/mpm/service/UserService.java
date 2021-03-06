package cn.com.mwsn.mpm.service;

import java.util.List;

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
	
	
	/**
	 * 修改帐号密码
	 * @param user
	 * @return
	 * 	如果密码修改成功，则返回true，否则返回false
	 */
	public boolean changePassWord(User user);
	
	/**
	 * 查询User表和Doctor关联数据
	 * @param null
	 * @return
	 * 	根据staffNum关联关系返回List的泛型
	 */
	public <T> List<T> findUserAndDoctor();
	
	/**
	 * 查询User表和Nurse关联数据
	 * @param null
	 * @return
	 * 	根据staffNum关联关系返回List的泛型
	 */
	public <T> List<T> findUserAndNurse();

}

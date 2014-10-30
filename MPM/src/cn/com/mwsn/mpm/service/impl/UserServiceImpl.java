package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.apache.struts2.json.JSONUtil;
import org.cz.utils.gson.GsonUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.User;
import cn.com.mwsn.mpm.service.UserService;


@Service("userService")
@Transactional
public class UserServiceImpl extends TransactionalServiceImpl implements
		UserService {

	@Override
	public int validateAccount(String account, String pwd) {
//		QueryResult<List<Incident>> queryResult = this.find("select u from Incident u order by u.time",null,1,10);
		User user = this.findBy(User.class, "account", account);
		if (user == null ){
			return 3;
		}else{
			if (user.getPassword().equals(pwd)){
				if (user.getIsAllowLogin()){
					return 0;
				}
				return 2;
			}else{
				return 1;
			}
		}

	}

	@Override
	public User queryUser(String account) {
		
		return this.findBy(User.class, "account", account);
		
	}

	@Override
	public boolean changePassWord(User user) {
		try {
			this.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	

}

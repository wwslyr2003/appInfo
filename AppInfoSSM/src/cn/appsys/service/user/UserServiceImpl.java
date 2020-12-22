package cn.appsys.service.user;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.user.UserMapper;
import cn.appsys.pojo.User;

@Service
public class UserServiceImpl implements UserService {

	@Resource
	private UserMapper userMapper; 
	@Override
	public User getUserLogin(String devCode,String userPassword) { 
		User user = null;
		try {
			user = userMapper.getUserLogin(devCode);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		if(null!=user){
			if(!user.getUserPassword().equals(userPassword)){
				user = null;
			}
		}
		return user;
	}
	
}

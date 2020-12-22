package cn.appsys.service.user;
 

import org.springframework.stereotype.Service;

import cn.appsys.pojo.User; 
public interface UserService {
	public User getUserLogin(String devCode,String userPassword);

}

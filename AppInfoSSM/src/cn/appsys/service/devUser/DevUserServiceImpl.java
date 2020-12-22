package cn.appsys.service.devUser;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.devUser.DevUserMapper; 
import cn.appsys.pojo.Devuser; 
@Service
public class DevUserServiceImpl implements DevUserService {
	@Resource
	private DevUserMapper devUserMapper; 
 
	@Override
	public Devuser getUserLogin(String devCode, String pwd) {
		Devuser devuser = null;
		try {
			devuser = devUserMapper.getUserLogin(devCode);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		if(null!=devuser){
			if(!devuser.getDevPassword().equals(pwd)){
				devuser = null;
			}
		}
		return devuser;
	}

}

package cn.appsys.service.devUser;

import cn.appsys.pojo.Devuser;

public interface DevUserService {
	public Devuser  getUserLogin(String devCode,String pwd);
}

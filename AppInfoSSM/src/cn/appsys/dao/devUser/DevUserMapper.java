package cn.appsys.dao.devUser;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.Devuser; 

public interface DevUserMapper {
	//登录
	public Devuser  getUserLogin(@Param("devCode")String devCode)throws Exception;
}

package cn.appsys.dao.user;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.User;
 

public interface UserMapper {
	//登录
		public User getUserLogin(@Param("devCode")String devCode)throws Exception;
		
}

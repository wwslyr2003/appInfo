package cn.appsys.dao.version;

 
import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.Version;

public interface VersionMapper {
	public  Version  getById(@Param("vid")int vid)throws Exception;
	public  List<Version>  getListById(@Param("id")int id)throws Exception;

	public  int  del(@Param("id")int id)throws Exception;
	
	public  int add(Version version)throws Exception;
	
	public  int update(Version version)throws Exception;
	
	
	public int findIdByAid(@Param("id")int id)throws Exception;
	
	public String getLocPath(@Param("id") int id) throws Exception;
}

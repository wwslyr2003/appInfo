package cn.appsys.service.version;
 
import java.util.List; 

import cn.appsys.pojo.Version;

public interface VersionService {
	public  Version  getById( int vid) ;
	public  List<Version>  getListById(int id);
	public int del(int id);
	public int update(Version ver);
	public int add( Version ver);
	public int findIdByAid(int id);
	public String getLocPath(int id);
}

package cn.appsys.service.version;
 
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.version.VersionMapper;
import cn.appsys.pojo.Version;
@Service
public class VersionServiceImpl implements VersionService {
@Resource
private   VersionMapper versionMapper;
	@Override
	public  Version  getById(int vid) {
		 Version  list=null;
		try {
			list=versionMapper.getById(vid);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<Version> getListById(int id) { 
		 List<Version>  list=null;
			try {
				list=versionMapper.getListById(id);
			} catch (Exception e) { 
				e.printStackTrace();
			}
			return list;
	}
	@Override
	public int del(int id) {
		int  falg=0;
		try {
			falg=versionMapper.del(id);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return falg;
	}
	@Override
	public int update(Version ver) {
		int  falg=0;
		try {
			falg=versionMapper.update(ver);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return falg;
	}
	@Override
	public int add(Version ver) {
		int  falg=0;
		try {
			falg=versionMapper.add(ver);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return falg;
	}
	@Override
	public int findIdByAid(int id) {
		int  falg=0;
		try {
			falg=versionMapper.findIdByAid(id);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return falg;
	}
	@Override
	public String getLocPath(int id) {
		String  path=null;
		try {
			path=versionMapper.getLocPath(id);
		 } catch (Exception e) { 
			e.printStackTrace();
		}
		return path;
	}

}

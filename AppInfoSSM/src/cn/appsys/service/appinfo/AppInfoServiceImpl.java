package cn.appsys.service.appinfo;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.appinfo.AppInfoMapper;
import cn.appsys.pojo.Info;

@Service
public class AppInfoServiceImpl implements AppInfoService {
	@Resource
	private AppInfoMapper appInfoMapper;

	@Override
	public List<Info> getAppInfoList(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int currentPageNo, int pageSize) {
		List<Info> list = null;
		try {
			list = appInfoMapper.getAppInfoList(softwareName, flatformId,
					categoryLevel1, categoryLevel2, categoryLevel3,
					(currentPageNo-1)*pageSize, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int getAppInfoCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3) {
		int falg = 0;
		try {
			falg = appInfoMapper.getAppInfoCount(softwareName, flatformId,
					categoryLevel1, categoryLevel2, categoryLevel3);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public Info getAppInfoById(int aid) {
		Info info = null;
		try {
			info = appInfoMapper.getAppInfoById(aid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return info;
	}

	@Override
	public int updateAppInfoStatus(int id, int status) {
		int falg = 0;
		try {
			falg = appInfoMapper.updateAppInfoStatus(id, status);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public List<Info> devgetAppInfoList(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int status, int devId, int currentPageNo, int pageSize) {
		List<Info> list = null;
		try {
			list = appInfoMapper.devgetAppInfoList(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, (currentPageNo-1)*pageSize, status, devId, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int devgetAppInfoCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int status, int devId) {
		int falg = 0;
		try {
			falg = appInfoMapper.devgetAppInfoCount(softwareName, flatformId, categoryLevel1, categoryLevel2, categoryLevel3, status, devId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int del(int id) {
		int falg = 0;
		try {
			falg = appInfoMapper.del(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int add(Info info) {
		int falg = 0;
		try {
			falg = appInfoMapper.add(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int getAPKcount(String ApkName) {
		int falg = 0;
		try {
			falg = appInfoMapper.getAPKcount(ApkName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int update(Info info) {
		int falg = 0;
		try {
			falg = appInfoMapper.update(info);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public int updateversionId(int versionId, int id) {
		int falg = 0;
		try {
			falg = appInfoMapper.updateversionId(versionId, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return falg;
	}

	@Override
	public String getLocPath(int id) {
		String  path=null;
		try {
			path=appInfoMapper.getLocPath(id);
		 } catch (Exception e) { 
			e.printStackTrace();
		}
		return path;
	}

	 
}

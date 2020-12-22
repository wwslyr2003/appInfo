package cn.appsys.service.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.Info;

public interface AppInfoService {
	/**
	 * 
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exceptionint
	 */
	public List<Info> getAppInfoList(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,
			int currentPageNo, int pageSize);

	/**
	 * 查询总记录数
	 * 
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 * @throws Exception
	 */
	public int getAppInfoCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3);
	
	
	public List<Info> devgetAppInfoList(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,int  status, int devId,
			int currentPageNo, int pageSize);
	
	
	public int devgetAppInfoCount(String softwareName, int flatformId,
			int categoryLevel1, int categoryLevel2, int categoryLevel3,int  status, int devId);
	
	/**
	 * 
	 * @param aid
	 * @return
	 */
	public Info getAppInfoById(int aid);
	/**
	 * 
	 * @param id
	 * @param status
	 * @return
	 */
	public int updateAppInfoStatus(int id, int status);

	public int del(int id);
	

	public  int add(Info info);
	public  int update(Info info);
	public int getAPKcount(String ApkName);
	public int updateversionId(	 int versionId, int id );
	public String getLocPath(int id);
}

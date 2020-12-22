package cn.appsys.dao.appinfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.Info;

public interface AppInfoMapper {
	/**
	 * 查询AppInfo列表
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	public List<Info> getAppInfoList(
			@Param("softwareName")String softwareName,
			@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,
			@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3,
			@Param("currentPageNo")int currentPageNo,
			
			@Param("pageSize")int pageSize 
			)throws Exception;
	/**
	 * 查询总记录数
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public int getAppInfoCount(
			@Param("softwareName")String softwareName,
			@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,
			@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3)throws Exception;
	
	
	
	
	/**
	 * 查询AppInfo列表
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 */
	public List<Info> devgetAppInfoList(
			@Param("softwareName")String softwareName,
			@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,
			@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3,
			@Param("currentPageNo")int currentPageNo,
			@Param("status")int status,
			@Param("devId")int devId, 
			@Param("pageSize")int pageSize 
			)throws Exception;
	/**
	 * 查询总记录数
	 * @param softwareName
	 * @param flatformId
	 * @param categoryLevel1
	 * @param categoryLevel2
	 * @param categoryLevel3
	 * @return
	 */
	public int devgetAppInfoCount(
			@Param("softwareName")String softwareName,
			@Param("flatformId")int flatformId,
			@Param("categoryLevel1")int categoryLevel1,
			@Param("categoryLevel2")int categoryLevel2,
			@Param("categoryLevel3")int categoryLevel3,
			@Param("status")int status,
			@Param("devId")int devId )throws Exception;
	public Info getAppInfoById(	@Param("aid")int aid )throws Exception;
	public int updateAppInfoStatus(	@Param("id")int id,@Param("status")int status )throws Exception;
	
	public int updateversionId(	@Param("versionId")int versionId,@Param("id")int id )throws Exception;
	 

	public  int  del(@Param("id")int id)throws Exception;
	public  int add(Info info);
	public  int update(Info info);
	public int getAPKcount(@Param("ApkName")String ApkName);

	public String getLocPath(@Param("id") int id) throws Exception;
	
}

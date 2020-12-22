package cn.appsys.dao.dataDictionary;

import java.util.List;

import cn.appsys.pojo.DataDictionary;

public interface DataDictionaryMapper {
	public List<DataDictionary> getFlatFormName()throws Exception;
	public List<DataDictionary> getStatusName()throws Exception;
}

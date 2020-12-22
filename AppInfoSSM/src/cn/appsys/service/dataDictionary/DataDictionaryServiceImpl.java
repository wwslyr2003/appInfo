package cn.appsys.service.dataDictionary;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.dataDictionary.DataDictionaryMapper;
import cn.appsys.pojo.DataDictionary;
@Service
public class DataDictionaryServiceImpl implements DataDictionaryService {

	@Resource
	private DataDictionaryMapper dataDictionaryMapper;
	@Override
	public List<DataDictionary> getFlatFormName() { 
		List<DataDictionary> list=null;
		try {
			list=dataDictionaryMapper.getFlatFormName();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return list;
	}
	@Override
	public List<DataDictionary> getStatusName() {
		List<DataDictionary> list=null;
		try {
			list=dataDictionaryMapper.getStatusName();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return list;
	}

}

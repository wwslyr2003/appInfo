package cn.appsys.dao.category;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appsys.pojo.Category;

public interface CategoryMapper {
	public List<Category> getCategory1List()throws Exception;
	
	public List<Category> getCategoryOtherList(@Param("preantId") int preantId)throws Exception;
}

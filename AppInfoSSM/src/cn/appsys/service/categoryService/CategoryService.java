package cn.appsys.service.categoryService;

import java.util.List;
 

import cn.appsys.pojo.Category;

public interface CategoryService {

	public List<Category> getCategory1List();

	public List<Category> getCategoryOtherList(int preantId);
}

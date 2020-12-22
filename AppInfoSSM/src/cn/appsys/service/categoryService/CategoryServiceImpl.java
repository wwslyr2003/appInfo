package cn.appsys.service.categoryService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appsys.dao.category.CategoryMapper;
import cn.appsys.pojo.Category;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Resource
	private CategoryMapper categoryMapper; 
	@Override
	public List<Category> getCategory1List() {
		List<Category> list=null;
		try {
			list=categoryMapper.getCategory1List();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Category> getCategoryOtherList(int preantId) {
		List<Category> list=null;
		try {
			list=categoryMapper.getCategoryOtherList(preantId);
		} catch (Exception e) { 
			e.printStackTrace();
		}
		return list;
	}

}

package cn.dao.appcategory;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.pojo.AppCategory;

public interface AppCategoryMapper {
	
	/**
	 * ��ѯ���࣬���ݸ���id
	 * @param parentId
	 * @return
	 */
	public List<AppCategory> getAppCategoryListByParentId(@Param("parentId")Integer parentId);
	
}

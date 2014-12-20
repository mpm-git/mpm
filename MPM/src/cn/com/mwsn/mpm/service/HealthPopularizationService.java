package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.mpm.entity.HealthContent;

public interface HealthPopularizationService {
	/**
	 * 保存设计的网页
	 * @param htmlContent
	 */
	public void saveContent(HealthContent htmlContent);
	
	/**
	 * 查询所有保存的页面
	 */
	public List<HealthContent> getAllPages();
	
	/**
	 * 删除页面根据id
	 */
	public void deleteHealthPageById(int id);
	
	/**
	 * 根据id查询内容
	 */
	public HealthContent findHealthPageByid(int id);
	
	/**
	 * 设定字段为columnName为value的page为isUsed
	 */
	public void setPageIsUsed(String columnName,String value, int isUsed);
	
	/**
	 * 更新修改页面内容
	 */
	public void updateHealthContent(HealthContent content);
}

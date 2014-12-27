package cn.com.mwsn.mpm.service;

import java.util.List;

import cn.com.mwsn.mpm.entity.HealthContent;
import cn.com.mwsn.mpm.entity.HealthType;

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
	
	/**
	 * 获取所有健康类型
	 */
	public List<HealthType> getHealthTypes();
	
	/**
	 * 删除健康类型
	 */
	public void deleteHealthType(String typeName);
	
	/**
	 * 添加健康类型
	 */
	public void addHealthType(String typeName);
	
	/**
	 * 查询健康类型
	 */
	public HealthType findHealthType(String healthType);
}

package cn.com.mwsn.frame.service;

import java.util.List;
import java.util.Map;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.frame.exception.ServiceException;



public interface TransactionalService {
	
	public void flush();

	public <T> T merge(T entity) throws ServiceException;

	public void persist(Object entity) throws ServiceException;

	public void save(Object object) throws ServiceException;

	public <T> T remove(Class<T> clazz, Object id) throws ServiceException;

	public void remove(Object entity) throws ServiceException;

	public int update(String queryString, Object... params)
			throws ServiceException;

	public void clear() throws ServiceException;

	public <T> T find(Class<T> entityClass, Object primaryKey);

	public <T> List<T> find(String queryString, Object... params);

	public <T> List<T> findMaxRow(String queryString, int maxRow,
			Object... params);

	public <T> T findBy(Class<T> clazz, String fieldName, Object value);
	/**
	 * 检测是否存在 
	 * @param clazz
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public boolean checkExist(Class clazz,String fieldName,Object value);
	/**
	 * add by tiandw ,find by the fieldName and value,return List
	 * @param <T>
	 * @param entityClass
	 * @param fieldName
	 * @param value
	 * @return
	 */
	public <T>List<T> findByField(Class<T> entityClass, String fieldName, Object value) ;

	public <T> List<T> findAll(Class<T> clazz);

	public <T> List<T> findNamed(String namedQuery, Object... params);

	public <T> T findNamedSingle(String namedQuery, Object... params);

	public <T> T findSingle(String queryString, Object... params);

	public <T> List<T> query(Integer rowSize, String q, Object... params);

	public Integer findRowCount(String namedQuery, Object... params);

	public Integer count(Class entityClass);

	// 查询一个表中最大的Id 
	public Integer findMaxId(Class entityClass);

	// 查询一个表中最大的Seq //add by yangzy
	public int findMaxSeq(String entityClass);

	// 根据一个ID，查询此ID对应的实体类中的Seq //add by yangzy
	public int findMaxSeq(String hsql, Object... params);

	// 根据一个ID，当Parent查询此ID对应的实体类中的Seq add by yangzy
	public int findMaxSeq4ParentNull(String hsql);
	
	// 根据一个ID，当Parent查询此ID对应的实体类中的Seq add by yangzy
	public int findMaxSeq4ParentNull(String hsql, Object... params);	
	
	//检查数据库中是否存在指定的记录    add by yangzy
	public Boolean checkExist(String className, String code, Integer id, String value); 
	
	//检查数据库中是否存在指定的记录     add by yangzy	 
	public Boolean checkExist(String className, String key, Integer id, Integer rootId, String value);
	/**
	 * 查找包含distinct的行数
	 * @param sql
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	public Integer distictCount(String sql,Object... objs) throws Exception;
	/**
	 * 
	 * @param clazz Class<? extends IdEntity>
	 * @param ids List<Integer>
	 * @return List<? extends IdEntity>
	 */
	public List findByIds(Class<? extends IdEntity> clazz, List<Integer> ids);

	/**分页查询
	 * @param queryString
	 * @param map
	 * @param page
	 * @param rows
	 * @return
	 */
//	<T> List<T> find(String queryString, Map<String, Object> map, int page, int rows);
	<T> QueryResult<List<T>> find(String queryString, Map<String, Object> map, int page, int rows);

}

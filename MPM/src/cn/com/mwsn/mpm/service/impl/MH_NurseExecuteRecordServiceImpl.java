package cn.com.mwsn.mpm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.entity.IdEntity;
import cn.com.mwsn.frame.exception.ServiceException;
import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_nurse;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_patient;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.service.MH_NurseExecuteRecordService;
@Service("mH_NurseExecuteRecordService")
@Transactional
public class MH_NurseExecuteRecordServiceImpl extends TransactionalServiceImpl implements
		MH_NurseExecuteRecordService {

	@Override
	public QueryResult<List<MH_NurseExecuteRecord>> find(
			Map<String, Object> map, int pageIndex, int pageSize) {
		String jpql="select m from MH_NurseExecuteRecord m where 1=1 :conditions: order by :order_by: :order:";
		
		jpql = jpql.replaceAll(":conditions:", map.get("conditions") == null?" ":" and "+map.get("conditions").toString());
		jpql = jpql.replaceAll(":order_by:", map.get("order_by") == null?"id":map.get("order_by").toString());
		jpql = jpql.replaceAll(":order:", map.get("order") == null?"desc":map.get("order").toString());
		jpql=jpql.replaceAll(":.*:", "");
		System.out.println(jpql);
		return this.find(jpql, null, pageIndex, pageSize);
	}
	@Override
	public QueryResult<List<MH_NurseExecuteRecord_nurse>> find_nurse(
			Map<String, Object> map, int pageIndex, int pageSize) {
		String jpql="select m from MH_NurseExecuteRecord_nurse m where 1=1 :conditions: order by :order_by: :order:";
		
		jpql = jpql.replaceAll(":conditions:", map.get("conditions") == null?" ":" and "+map.get("conditions").toString());
		jpql = jpql.replaceAll(":order_by:", map.get("order_by") == null?"id":map.get("order_by").toString());
		jpql = jpql.replaceAll(":order:", map.get("order") == null?"desc":map.get("order").toString());
		jpql=jpql.replaceAll(":.*:", "");
		System.out.println(jpql);
		return this.find(jpql, null, pageIndex, pageSize);
	}
	@Override
	public QueryResult<List<MH_NurseExecuteRecord_patient>> find_patient(
			Map<String, Object> m, int pageIndex, int pageSize) {
		String jpql="select m from MH_NurseExecuteRecord_patient m where 1=1 :name: :clinicNo: :execute: :min_start: :haoShi: :ciShu: order by :order_by: :order:";
		  //1:deadTime=null;2:deadTime="null";3:deadTime="not null";
		jpql = jpql.replaceAll(":conditions:", m.get("conditions") == null?" ":" and "+m.get("conditions").toString());
		  jpql=jpql.replaceAll(":execute:", m.get("execute")==null?"":" and execute "+"'%"+m.get("execute")+"%'");
		  jpql=jpql.replaceAll(":name:", m.get("name")==null?"":" and name like "+"'%"+m.get("name")+"%'");
//		  jpql=jpql.replaceAll(":name:", m.get("name")==null?"":" and expiry like "+"'%"+m.get("name")+"%'");
		  /*--->questionable*/
//		  jpql = jpql.replaceAll(":total", m.get("total") == null?"":" and total in (:total)");
//		  jpql = jpql.replaceAll(":used", m.get("used") == null?"":" and used in (:used)");
		  jpql = jpql.replaceAll(":order_by:", m.get("order_by") == null?"id":m.get("order_by").toString());
		  jpql = jpql.replaceAll(":order:", m.get("order") == null?"desc":m.get("order").toString());
		  jpql=jpql.replaceAll(":.*:", "");
		  System.out.println(jpql);
		return this.find(jpql, null, pageIndex, pageSize);
	}
	@Override
	public QueryResult<List<MH_NurseExecuteRecord_nurse>> find_level(
			Map<String, Object> map, int page_index, int page_size) {
		String jpql="select m from MH_NurseExecuteRecord_patient m where 1=1 :conditions: order by :order_by: :order:";
		
		jpql = jpql.replaceAll(":conditions:", map.get("conditions") == null?" ":" and "+map.get("conditions").toString());
		jpql = jpql.replaceAll(":order_by:", map.get("order_by") == null?"id":map.get("order_by").toString());
		jpql = jpql.replaceAll(":order:", map.get("order") == null?"desc":map.get("order").toString());
		jpql=jpql.replaceAll(":.*:", "");
		System.out.println(jpql);
		return this.find(jpql, null, page_index, page_size);
	}
	@Override
	public QueryResult<List<MH_NurseExecuteRecord_view>> find_view(
			Map<String, Object> map, int page_index, int page_size) {
		String jpql="select m from MH_NurseExecuteRecord_view m where 1=1 :conditions: order by :order_by: :order:";
		
		String conditions = map.get("conditions")==null?null:map.get("conditions").toString().trim();
		conditions=conditions.replaceAll("^and", " ");
		conditions=conditions.replaceAll("^or", " ");
		conditions=conditions.replaceAll("and$", " ");
		conditions=conditions.replaceAll("or$", " ");
		jpql = jpql.replaceAll(":conditions:", conditions == null||conditions.toString().trim()
				.equals("")?" ":" and "+conditions.toString());
		jpql = jpql.replaceAll(":order_by:", map.get("order_by") == null?"id":map.get("order_by").toString());
		jpql = jpql.replaceAll(":order:", map.get("order") == null?"desc":map.get("order").toString());
		jpql=jpql.replaceAll(":.*:", "");
		System.out.println(jpql);
		return this.find(jpql, null, page_index, page_size);
	}

}

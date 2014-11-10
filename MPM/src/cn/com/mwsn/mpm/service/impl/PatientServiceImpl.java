package cn.com.mwsn.mpm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.frame.service.QueryResult;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.entity.MH_NurseExecuteRecord_view;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.entity.Patient_view;
import cn.com.mwsn.mpm.service.PatientService;

@Service("patientService")
@Transactional
public class PatientServiceImpl extends TransactionalServiceImpl implements
		PatientService {

	@Override
	public Patient queryPatient(String empNO) {
		return this.findBy(Patient.class, "patientNo", empNO);
	}
	@Override
	public QueryResult<List<Patient_view>> find(
			Map<String, Object> map, int page_index, int page_size) {
		if(map==null)
			map=new HashMap<String, Object>();
		String jpql="select m from Patient_view m where 1=1 :conditions: order by :order_by: :order:";
		
		String conditions = map.get("conditions")==null?null:map.get("conditions").toString().trim();
		if(conditions!=null)
		{
			conditions=conditions.replaceAll("^and", " ");
			conditions=conditions.replaceAll("^or", " ");
			conditions=conditions.replaceAll("and$", " ");
			conditions=conditions.replaceAll("or$", " ");
		}
		jpql = jpql.replaceAll(":conditions:", conditions == null||conditions.toString().trim()
				.equals("")?" ":" and "+conditions.toString());
		jpql = jpql.replaceAll(":order_by:", map.get("order_by") == null?"id":map.get("order_by").toString());
		jpql = jpql.replaceAll(":order:", map.get("order") == null?"desc":map.get("order").toString());
		jpql=jpql.replaceAll(":.*:", "");
		System.out.println(jpql);
		return this.find(jpql, null, page_index, page_size);
	}
}

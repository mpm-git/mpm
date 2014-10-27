package cn.com.mwsn.mpm.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.mpm.entity.ActionReport;
import cn.com.mwsn.mpm.entity.ActionReportStat;
import cn.com.mwsn.mpm.service.RealTimePathService;

@Service
@Transactional
public class RealTimePathServiceImpl implements RealTimePathService {
	/**
	 * 持久化对象声明EntityManager
	 */
	@PersistenceContext(unitName = "punit")
	EntityManager entityManager;
	

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}


	@SuppressWarnings("unchecked")
	@Override

	public List<ActionReportStat> queryAllPath(String userCode,Long startTime,Long endTime) {

//		String sql = "SELECT DISTINCT " +
//				"cp.name," +
//				"cp.rfid," +
//				"cp.x," +
//				"cp.y," +
//				"IF((cp.x >= inpcell.x AND cp.x <= inpcell.x+inpcell.width AND cp.y >= inpcell.y AND cp.y <= inpcell.y+inpcell.high),inpcell.curroomCode,NULL) AS curroomCode " +
//				"FROM currentposition cp " +
//				"JOIN inpatientarea inparea " +
//				"ON cp.inpatientAreaNum = inparea.id " +
//				"JOIN inpatientcell inpcell " +
//				"ON inparea.id = inpcell.inpatientArea_id";
		String sql = "select ar.*,ic.* from actionreport ar  JOIN inpatientcell ic on  ar.ACTIONPLACE_CODE = ic.name"
				+ " where  ar.USER_CODE = "+userCode ;
		if(startTime!=null&&!"".equals(startTime)){
			sql = sql +" and ACTION_TIME>="+startTime;
		}
		if(endTime!=null&&!"".equals(endTime)){
			sql = sql +" and ACTION_TIME<="+endTime;
		}
		sql += "  order by ic.x asc ";
		
		List<ActionReportStat> list  = new ArrayList<ActionReportStat>();
		ActionReportStat actionReportStat = new ActionReportStat();
		 List<Object[]> result=entityManager.createNativeQuery(sql).getResultList();
	     Iterator<Object[]> it = result.iterator();  
						 while(it.hasNext()){
							 Object[] o = it.next();
							 actionReportStat = new ActionReportStat();
							 actionReportStat.setUSER_CODE(o[3].toString());
							 actionReportStat.setUSER_DESC(o[4].toString());
							 actionReportStat.setACTION_TIME(Long.parseLong(o[5].toString())); 
     						 actionReportStat.setX(Integer.parseInt(o[15].toString().toString()));
							 actionReportStat.setY(Integer.parseInt(o[16].toString().toString()));
							 actionReportStat.setACTIONPLACE_CODE(o[7].toString());
							 list.add(actionReportStat);
						 }
					return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ActionReportStat querySpecificPath(String userCode, Long queryTime, String userType) {
		String sql = "select  ar.*,ic.* from actionreport ar  JOIN inpatientcell ic on  ar.ACTIONPLACE_CODE = ic.name where 1=1" ;
		if(userCode != null && !"".equals(userCode)){
			sql += " and ar.USER_CODE = "+userCode;
		}
		if(userType != null && !"".equals(userType)){
			sql += " and ar.ACTIONEVENT_CODE = '" + userType + "'";
		}
		sql = sql+" order by ACTION_TIME desc limit 1";
		List<ActionReportStat> list  = new ArrayList<ActionReportStat>();
		ActionReportStat actionReportStat = new ActionReportStat();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 
		List<Object[]> result=entityManager.createNativeQuery(sql).getResultList();
	     Iterator<Object[]> it = result.iterator();  
						 while(it.hasNext()){
							 Object[] o = it.next();
							 actionReportStat = new ActionReportStat();
							 actionReportStat.setUSER_CODE(o[3].toString());
							 actionReportStat.setUSER_DESC(o[4].toString());
							 actionReportStat.setACTION_TIME(Long.parseLong(o[5].toString())); 
							 actionReportStat.setACTIONEVENT_CODE(o[6].toString());
     						 actionReportStat.setX(Integer.parseInt(o[15].toString().toString()));
							 actionReportStat.setY(Integer.parseInt(o[16].toString().toString()));
							 actionReportStat.setACTIONPLACE_CODE(o[7].toString());
							 
							 cal.setTimeInMillis(Long.parseLong(o[5].toString()));
							 actionReportStat.setTimedesc(sdf2.format(cal.getTime()));
							 
							 list.add(actionReportStat);
						 }
		if(list.size() > 0){
			return list.get(0);
		}
		return null;
	}

}

package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.PatientAlert;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.PatientAlertService;


@Service
@Transactional
public class PatientAlertServiceImpl extends TransactionalServiceImpl
		implements PatientAlertService {
	private static final Logger log = Logger
			.getLogger(PatientAlertServiceImpl.class);
	
	
	@Override
	public List<PatientAlert> queryAllPatientAlert() {
		return this.findAll(PatientAlert.class);
	}

	@Override
	public void updatePatientAlert(Incident incident) {
		log.debug("进入PatientAlertServiceImpl");
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID", incident.getTagID());
		log.debug("测试1 ------- " + person);
		
		if(incident.getType() == Constant.STATUS_OUT && person.getRole().equals(Constant.ROLE.PATIENT)){
			//产生一条告警记录
			PatientAlert patientAlert = new PatientAlert();
			patientAlert.setName(person.getName());
			patientAlert.setStartTime(incident.getTime());
			patientAlert.setEndTime(null);
			patientAlert.setType("PAT");
			patientAlert.setWardName(incident.getWardName());
			patientAlert.setPatientNum(person.getEmpNO());
			
			log.debug("测试2 ------- " + patientAlert);
			this.save(patientAlert);
		}
		else if(incident.getType() == Constant.STATUS_IN && person.getRole().equals(Constant.ROLE.PATIENT))
		{
			//更新记录的结束时间
			log.debug("测试3 ------- " + "更新结束时间");
			List<PatientAlert> patientAlert = this.findByField(PatientAlert.class, "name", person.getName());
			System.out.println(person.getName());
			if(patientAlert == null){
				return;
			}
			else
			{
				for(PatientAlert pa : patientAlert){
					log.debug("已经遍历病人离开信息："+pa.getEndTime());
					if(pa.getEndTime()==null){
						pa.setEndTime(incident.getTime());
						this.merge(pa);
					}
				}
				
			}
		}
		
	}


}

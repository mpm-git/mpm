package cn.com.mwsn.mpm.service.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.Constant.ROLE;
import cn.com.mwsn.mpm.entity.ContactBetweenNurseAndPatient;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.ContactBetweenNurseAndPatientService;

@Service
@Transactional
public class ContactBetweenNurseAndPatientServiceImpl extends
		TransactionalServiceImpl implements
		ContactBetweenNurseAndPatientService {

	private static final Logger log = Logger
			.getLogger(ContactBetweenNurseAndPatientServiceImpl.class);
	
	@Override
	public void updateContactBetweenNurseAndPatient(Incident incident) {
		log.debug("进入ContactBetweenNurseAndPatientServiceImpl");
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID",
				incident.getTagID());
		if (incident.getType() == Constant.STATUS_IN
				&& person.getRole() == ROLE.NURSE
				&& incident.getWardName().charAt(0) == 'w') {
			// 新产生一条护士和病人接触的记录
			ContactBetweenNurseAndPatient curcontact = new ContactBetweenNurseAndPatient();
			curcontact.setInTime(new Date(incident.getTime()));
			curcontact.setName(person.getName());
			curcontact.setOutTime(null);
			curcontact.setWardName(incident.getWardName());
			this.save(curcontact);
		} else if (incident.getType() == Constant.STATUS_OUT
				&& person.getRole() == ROLE.NURSE
				&& incident.getWardName().charAt(0) == 'w') {
			
			System.out.println("wujian");
			// 更新之前的接触记录
			List<ContactBetweenNurseAndPatient> precontactList = this
					.findByField(ContactBetweenNurseAndPatient.class, "name",
							person.getName());
			if(precontactList!=null){
				Iterator<ContactBetweenNurseAndPatient> it = precontactList.iterator();
				while (it.hasNext()) {
					ContactBetweenNurseAndPatient precontact = (ContactBetweenNurseAndPatient) it
							.next();
					if (precontact.getOutTime().equals(null)) {
						precontact.setOutTime(new Date(incident.getTime()));
						this.merge(precontact);
						break;
					}
				}
			}

		}

	}

}

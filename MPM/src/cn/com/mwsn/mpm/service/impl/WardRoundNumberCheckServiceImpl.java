package cn.com.mwsn.mpm.service.impl;

import java.sql.Date;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.entity.WardRoundRecord;
import cn.com.mwsn.mpm.service.WardRoundNumberCheckService;

@Service
@Transactional
public class WardRoundNumberCheckServiceImpl extends TransactionalServiceImpl
		implements WardRoundNumberCheckService {

	private static final Logger log = Logger
			.getLogger(WardRoundNumberCheckServiceImpl.class);

	@Override
	public void updateWardRoundRecord(Incident incident) {

		log.debug("进入WardRoundNumberCheckServiceImpl");
		InpatientCell cell = this.findBy(InpatientCell.class, "name",
				incident.getWardName());
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID",
				incident.getTagID());
		if (person.getRole().equals(Constant.ROLE.DOCTOR)
				|| person.getRole().equals(Constant.ROLE.NURSE)) {
			// 查找缓存
			boolean flag = false;
			Iterator it = Constant.buffer.iterator();
			while (it.hasNext()) {
				WardRoundRecord wardRoundRecord = (WardRoundRecord) it.next();
				if (wardRoundRecord.getName().equals(person.getName())) {
					if ((incident.getType() == Constant.STATUS_OUT)
							&& (Constant.minCheckWardTime <= Math.abs((incident
									.getTime() - wardRoundRecord.getStartTime()
									.getTime())))) {
						wardRoundRecord
								.setEndTime(new Date(incident.getTime()));
						this.save(wardRoundRecord);
						Constant.buffer.remove(wardRoundRecord);
					}
					flag = true;
				}
			}
			if (flag == false) {
				// buffer中木有
				WardRoundRecord record = new WardRoundRecord();
				record.setName(person.getName());
				record.setRole(person.getRole());
				record.setEndTime(null);
				record.setEmpID(person.getEmpNO());
				record.setWard(incident.getWardName());
				if (incident.getType() == Constant.STATUS_IN) {
					record.setStartTime(new Date(incident.getTime()));
					Constant.buffer.add(record);
				}
			}
		}

	}

}

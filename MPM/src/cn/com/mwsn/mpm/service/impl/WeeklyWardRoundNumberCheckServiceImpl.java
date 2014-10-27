package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.WardRounds;
import cn.com.mwsn.mpm.data.IOTSend2CP;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.entity.WardRoundNumberByDay;
import cn.com.mwsn.mpm.entity.WardRoundNumberByWeek;
import cn.com.mwsn.mpm.service.WeeklyWardRoundNumberCheckService;


@Service
@Transactional
public class WeeklyWardRoundNumberCheckServiceImpl extends TransactionalServiceImpl
		implements WeeklyWardRoundNumberCheckService {
	
	private static final Logger log = Logger
			.getLogger(WeeklyWardRoundNumberCheckServiceImpl.class);
	
	@Override
	/**
	 * 查询所有医护人员本周的查房信息
	 * @return
	 */
	@Transactional(propagation = Propagation.NOT_SUPPORTED,readOnly = true)
	public List<WardRoundNumberByWeek> queryAllWeeklyWardRoundNumber() {
		return findAll(WardRoundNumberByWeek.class);
	}

	@Override
	/**
	 * 更新所有医护人员本周的查房信息
	 */
	public void updateWeeklyWardRoundNumber(Incident incident) {
		log.debug("进入WeeklyWardRoundNumberCheckServiceImpl");
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID",
				incident.getTagID());
		/*if (person.getRole().equals(Constant.ROLE.DOCTOR)) {
			WardRoundNumberByWeek wardRoundNumberByWeek = this.findBy(
					WardRoundNumberByWeek.class, "name", person.getName());
			if (incident.getType() == Constant.STATUS_OUT) {
				if (wardRoundNumberByWeek == null) {
					wardRoundNumberByWeek = new WardRoundNumberByWeek();
					wardRoundNumberByWeek.setNumreal(1);
					wardRoundNumberByWeek.setNumorder(Constant.doctorOrder);
					wardRoundNumberByWeek.setName(person.getName());
					wardRoundNumberByWeek.setType(person
							.getRole());
					this.save(wardRoundNumberByWeek);
				} else {
					wardRoundNumberByWeek.setNumreal(wardRoundNumberByWeek
							.getNumreal() + 1);
					this.merge(wardRoundNumberByWeek);
				}
			} else if (incident.getType() == Constant.STATUS_IN) {
				if (wardRoundNumberByWeek == null) {
					wardRoundNumberByWeek = new WardRoundNumberByWeek();
					wardRoundNumberByWeek.setNumreal(1);
					wardRoundNumberByWeek.setName(person.getName());
					wardRoundNumberByWeek.setType(person
							.getRole());
					this.save(wardRoundNumberByWeek);
				} else {

				}
			} else {

			}
		}
*/
		
		if (person.getRole().equals(Constant.ROLE.DOCTOR)) {
			if (incident.getType() == Constant.STATUS_OUT) {
				if (WardRounds.isWardRound(person, incident.getTime(),
						incident.getWardName())) {
					// 是一次查房
					//发送给曼荼罗
					IOTSend2CP iotSend2CP = new IOTSend2CP();
					RFIDHistory rfidHistory = new RFIDHistory();
					rfidHistory.setValues(incident);
					iotSend2CP.loadRFID(rfidHistory);
					////////////////////
					WardRoundNumberByWeek wardRoundNumberByWeek = this.findBy(
							WardRoundNumberByWeek.class, "name",
							person.getName());
					if (wardRoundNumberByWeek == null) {
						wardRoundNumberByWeek = new WardRoundNumberByWeek();
						wardRoundNumberByWeek.setNumreal(1);
						wardRoundNumberByWeek.setNumorder(Constant.nurseOrder);
						wardRoundNumberByWeek.setName(person.getName());
						wardRoundNumberByWeek.setType(person.getRole());
						this.save(wardRoundNumberByWeek);
					} else {
						wardRoundNumberByWeek.setNumreal(wardRoundNumberByWeek
								.getNumreal() + 1);
						this.merge(wardRoundNumberByWeek);
					}
				}
			} else {
				WardRounds.insert(person, incident.getTime(),
						incident.getWardName());
			}
		}
		
	}

}

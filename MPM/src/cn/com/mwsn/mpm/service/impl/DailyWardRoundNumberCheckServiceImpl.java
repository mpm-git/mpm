package cn.com.mwsn.mpm.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.WardRounds;
import cn.com.mwsn.mpm.data.IOTSend2CP;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.entity.WardRoundNumberByDay;
import cn.com.mwsn.mpm.service.DailyWardRoundNumberCheckService;

@Service
@Transactional
public class DailyWardRoundNumberCheckServiceImpl extends
		TransactionalServiceImpl implements DailyWardRoundNumberCheckService {

	private static final Logger log = Logger
			.getLogger(DailyWardRoundNumberCheckServiceImpl.class);

	@Override
	/**
	 * 查询所有医护人员本日的查房信息
	 * @return
	 */
	public List<WardRoundNumberByDay> queryAllDailyWardRoundNumber() {
		return this.findAll(WardRoundNumberByDay.class);
	}

	@Override
	/**
	 * 更新医护人员日查房记录
	 */
	public void updateDailyWardRoundNumber(Incident incident) {
		log.debug("进入DailyWardRoundNumberCheckServiceImpl");
		RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID",
				incident.getTagID());
		if (person.getRole().equals(Constant.ROLE.NURSE)) {
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
					WardRoundNumberByDay wardRoundNumberByDay = this.findBy(
							WardRoundNumberByDay.class, "name",
							person.getName());
					if (wardRoundNumberByDay == null) {
						wardRoundNumberByDay = new WardRoundNumberByDay();
						wardRoundNumberByDay.setNumreal(1);
						wardRoundNumberByDay.setNumorder(Constant.nurseOrder);
						wardRoundNumberByDay.setName(person.getName());
						wardRoundNumberByDay.setType(person.getRole());
						this.save(wardRoundNumberByDay);
					} else {
						wardRoundNumberByDay.setNumreal(wardRoundNumberByDay
								.getNumreal() + 1);
						this.merge(wardRoundNumberByDay);
					}
				}
			} else {
				WardRounds.insert(person, incident.getTime(),
						incident.getWardName());
			}
		}

	}

}

package cn.com.mwsn.mpm.service.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.Constant.RECORD_STATE;
import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.entity.RFIDHistory;
import cn.com.mwsn.mpm.service.RFIDHistoryService;

@Service("rfidHistoryService")
@Transactional
public class RFIDHistoryServiceImpl extends TransactionalServiceImpl implements
		RFIDHistoryService {

	private static final Logger log = Logger
			.getLogger(RFIDHistoryServiceImpl.class);

	@Override
	public void insertRFIDHistory(Incident incident) {
		if (incident != null) {
			InpatientCell inpatientCell = this.findBy(InpatientCell.class,
					"name", incident.getWardName());
			RFIDHistory rfidHistory = new RFIDHistory();
			rfidHistory.setAction_time(incident.getTime());
			rfidHistory.setActionplace_code(incident.getWardName());
			rfidHistory.setActionplace_code(incident.getWardName());
			rfidHistory.setActionplace_desc(inpatientCell.getCurroomDesc());
			if (incident.getType() == 0) {
				rfidHistory.setActionstate_code(Constant.ACTION_IN_CODE);
				rfidHistory.setActionstate_desc(incident.getTagID() + "进入"
						+ inpatientCell.getCurroomDesc());
			} else if (incident.getType() == 1) {
				rfidHistory.setActionstate_code(Constant.ACTION_OUT_CODE);
				rfidHistory.setActionstate_desc(incident.getTagID() + "走出"
						+ inpatientCell.getCurroomDesc());
			} else {
				rfidHistory.setActionstate_code(Constant.ACTION_LOBBY_CODE);
				rfidHistory.setActionstate_desc(incident.getTagID() + "停留在"
						+ inpatientCell.getCurroomDesc());
			}
			rfidHistory.setRfid_id(incident.getTagID());
			rfidHistory.setIs_stop(RECORD_STATE.START);
			rfidHistory.setIs_update(false);
			this.save(rfidHistory);
		}

	}

}

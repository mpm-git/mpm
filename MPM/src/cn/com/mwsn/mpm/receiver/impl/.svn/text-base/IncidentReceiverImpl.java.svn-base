package cn.com.mwsn.mpm.receiver.impl;

import javax.jws.WebService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.mpm.receiver.IncidentReceiver;
import cn.com.mwsn.mpm.service.ActionReportService;
import cn.com.mwsn.mpm.service.ContactBetweenNurseAndPatientService;
import cn.com.mwsn.mpm.service.CurrentPositionService;
import cn.com.mwsn.mpm.service.DailyWardRoundNumberCheckService;
import cn.com.mwsn.mpm.service.PatientAlertService;
import cn.com.mwsn.mpm.service.WardRoundNumberCheckService;
import cn.com.mwsn.mpm.service.WeeklyWardRoundNumberCheckService;



@WebService(endpointInterface = "cn.com.mwsn.mpm.receiver.IncidentReceiver")
public class IncidentReceiverImpl extends SpringBeanAutowiringSupport implements IncidentReceiver {
	
	private static final Logger log = Logger.getLogger(IncidentReceiverImpl.class);
	
	@Autowired
	private CurrentPositionService currentPositionService;
//	@Autowired
//	private WardRoundNumberCheckService wardRoundNumberCheckService;
	@Autowired
	private WeeklyWardRoundNumberCheckService weeklyWardRoundNumberCheckService;
	@Autowired
	private DailyWardRoundNumberCheckService dailyWardRoundNumberCheckService;
	@Autowired
	private PatientAlertService patientAlertService;
	@Autowired
	private ContactBetweenNurseAndPatientService contactBetweenNurseAndPatientService;
	@Autowired
	private ActionReportService actionReportService;

	@Override
	public void handle(Incident incident) {
		log.debug("产生进出门事件!");
		currentPositionService.save(incident);
		currentPositionService.updateCurrentPos(incident);
//		wardRoundNumberCheckService.updateWardRoundRecord(incident);
		weeklyWardRoundNumberCheckService.updateWeeklyWardRoundNumber(incident);
		dailyWardRoundNumberCheckService.updateDailyWardRoundNumber(incident);
		patientAlertService.updatePatientAlert(incident);
		contactBetweenNurseAndPatientService.updateContactBetweenNurseAndPatient(incident);
		
		actionReportService.updateActionReport(incident);
	}

	@Override
	public void say(String str) {
		log.debug("**************************"+str);
		
	}

}

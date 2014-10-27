package cn.com.mwsn.mpm.action.system;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import cn.com.mwsn.frame.web.action.Struts2Utils;
import cn.com.mwsn.mpm.common.Constant.ROLE;
import cn.com.mwsn.mpm.entity.Doctor;
import cn.com.mwsn.mpm.entity.Nurse;
import cn.com.mwsn.mpm.entity.Patient;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.DoctorService;
import cn.com.mwsn.mpm.service.NurseService;
import cn.com.mwsn.mpm.service.PatientService;
import cn.com.mwsn.mpm.service.RFIDCardPersonService;

import com.opensymphony.xwork2.ActionSupport;

@Results({ @Result(name = "success", location = "/error/error.jsp"),
	@Result(name = "error", location = "/error/error.jsp") })
public class AddRfidAction extends ActionSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5031276066409434355L;
	
	@Autowired
	private RFIDCardPersonService rfidCardPersonService;
	@Autowired
	private DoctorService doctorService;
	@Autowired
	private NurseService nurseService;
	@Autowired
	private PatientService patientService;

	@Override
	public String execute() throws Exception {
		
		HttpServletRequest request = Struts2Utils.getRequest();
		String cardNo = request.getParameter("gonghao");
		RFIDCardPerson person = rfidCardPersonService.findBy(RFIDCardPerson.class, "empNO", cardNo);
		if(!person.equals(null)) {
			if(person.getRole().equals(ROLE.DOCTOR)) {
				Doctor doctor = doctorService.findBy(Doctor.class, "doctorId", cardNo);
				request.setAttribute("name", doctor.getName());
				request.setAttribute("sex", doctor.getSex());
				request.setAttribute("role", doctor.getType());
			}
			else if(person.getRole().equals(ROLE.NURSE)) {
				Nurse nurse = nurseService.findBy(Nurse.class, "nurseId", cardNo);
				request.setAttribute("name", nurse.getName());
				request.setAttribute("sex", nurse.getSex());
				request.setAttribute("role", nurse.getType());
			}
			else if(person.getRole().equals(ROLE.PATIENT)) {
				Patient patient = patientService.findBy(Patient.class, "clinicNo", cardNo);
				request.setAttribute("name", patient.getName());
				request.setAttribute("sex", patient.getSex());
				request.setAttribute("role", patient.getType());
			}
		}
		return this.SUCCESS;
	}

}

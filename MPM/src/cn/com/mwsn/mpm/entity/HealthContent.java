package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;
import javax.persistence.Lob;

import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class HealthContent  extends IdEntity {
	private static final long serialVersionUID = -588618421825231329L;
	
	@Lob
	private String jspContent;

	public String getJspContent() {
		return jspContent;
	}

	public void setJspContent(String jspContent) {
		this.jspContent = jspContent;
	}

	public HealthContent(String jspContent) {
		super();
		this.jspContent = jspContent;
	}

	public HealthContent() {
		super();
	}
	
}

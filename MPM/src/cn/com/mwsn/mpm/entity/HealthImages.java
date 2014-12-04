package cn.com.mwsn.mpm.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class HealthImages extends IdEntity{
	
	private String imagePath;
	
	@OneToOne(cascade={CascadeType.REFRESH,CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE},fetch=FetchType.LAZY)
	private HealthContent healthContent;

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public HealthContent getHealthContent() {
		return healthContent;
	}

	public void setHealthContent(HealthContent healthContent) {
		this.healthContent = healthContent;
	}

}

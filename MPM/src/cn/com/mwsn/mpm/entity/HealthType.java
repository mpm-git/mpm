package cn.com.mwsn.mpm.entity;

import javax.persistence.Entity;

import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class HealthType extends IdEntity{
	
	private static final long serialVersionUID = -263692427925926814L;
	private String typeName;

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	
	public HealthType() {
		super();
	}

	public HealthType(String typeName) {
		super();
		this.typeName = typeName;
	}
	

}

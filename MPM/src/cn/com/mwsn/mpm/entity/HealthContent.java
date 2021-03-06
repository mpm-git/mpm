package cn.com.mwsn.mpm.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import cn.com.mwsn.frame.entity.IdEntity;

@Entity
public class HealthContent  extends IdEntity {
	private static final long serialVersionUID = -588618421825231329L;
	
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(columnDefinition = "TEXT")
	private String jspContent;  //html 文章内容
	
	private String type;   //保存类型
	
	private String title;  //标题
	
	private int isUsed;    //标记页面或者图片是否被使用中，0  未使用  1 使用中
	
	private String imagePath;  //图片路径
	
	private String descr;
	
	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getIsUsed() {
		return isUsed;
	}

	public void setIsUsed(int isUsed) {
		this.isUsed = isUsed;
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getJspContent() {
		return jspContent;
	}

	public void setJspContent(String jspContent) {
		this.jspContent = jspContent;
	}
	
}

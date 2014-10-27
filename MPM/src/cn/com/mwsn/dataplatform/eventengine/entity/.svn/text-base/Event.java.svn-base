package cn.com.mwsn.dataplatform.eventengine.entity;


import cn.com.mwsn.frame.entity.IdEntity;
import javax.persistence.Entity;

/**
 * 事件实体类
 * 当前固定为：进出门事件
 * @author zhangke
 *
 */
@Entity
public class Event extends IdEntity{

	private static final long serialVersionUID = -4031450209424479657L;
	private String tagID;                  //RFID id
	private String antennaID;              //天线
	private Long time;                     //事件发生的时间
	private Integer status;                    //进出状态             
	
	public String getTagID() {
		return tagID;
	}

	public void setTagID(String tagID) {
		this.tagID = tagID;
	}

	public Long getTime() {
		return time;
	}
	public void setTime(Long time) {
		this.time = time;
	}
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAntennaID() {
		return antennaID;
	}

	public void setAntennaID(String antennaID) {
		this.antennaID = antennaID;
	}
	
	
	
//	@OneToOne(cascade = CascadeType.ALL)  
//	@JoinColumn(name = "id")   
//	public RFIDAntenna getAntenna() {
//		return antenna;
//	}
//
//	public void setAntenna(RFIDAntenna antenna) {
//		this.antenna = antenna;
//	}
}


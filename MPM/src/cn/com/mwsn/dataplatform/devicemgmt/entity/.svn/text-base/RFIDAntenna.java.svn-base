package cn.com.mwsn.dataplatform.devicemgmt.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cn.com.mwsn.frame.entity.IdEntity;


/**
 * RFID天线类
 * @author zhangke
 *
 */
@Entity
public class RFIDAntenna extends IdEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6423720152455150200L;
	
	
	private RFIDReader reader;
	private Integer antennaPort;                   //天线端口号

	public Integer getAntennaPort() {
		return antennaPort;
	}


	public void setAntennaPort(Integer antennaPort) {
		this.antennaPort = antennaPort;
	}

	@ManyToOne
	public RFIDReader getReader() {
		return reader;
	}


	public void setReader(RFIDReader reader) {
		this.reader = reader;
	}
}

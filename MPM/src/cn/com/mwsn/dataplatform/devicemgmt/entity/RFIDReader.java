package cn.com.mwsn.dataplatform.devicemgmt.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import cn.com.mwsn.frame.entity.IdEntity;
/**
 * RFID 读写器实体类
 * @author zhangke
 *
 */
@Entity
public class RFIDReader extends IdEntity {

	private static final long serialVersionUID = 1598882836786766601L;
	
	private Set<RFIDAntenna> antennas;          //天线集合
	private String readerID;

	
	@OneToMany(mappedBy="reader") 
	public Set<RFIDAntenna> getAntennas() {
		return antennas;
	}
	public void setAntennas(Set<RFIDAntenna> antennas) {
		this.antennas = antennas;
	}
	public String getReaderID() {
		return readerID;
	}
	public void setReaderID(String readerID) {
		this.readerID = readerID;
	}
}

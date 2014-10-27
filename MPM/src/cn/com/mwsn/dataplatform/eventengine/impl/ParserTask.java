package cn.com.mwsn.dataplatform.eventengine.impl;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.Callable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDAntenna;
import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDCard;
import cn.com.mwsn.dataplatform.devicemgmt.entity.RFIDReader;
import cn.com.mwsn.dataplatform.eventengine.EventEngine;
import cn.com.mwsn.dataplatform.eventengine.EventHandler;
import cn.com.mwsn.dataplatform.eventengine.common.Status;
import cn.com.mwsn.dataplatform.eventengine.entity.Event;
import cn.com.mwsn.frame.bean.BeanFactory;
import cn.com.mwsn.frame.service.TransactionalService;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.receiver.EventReceiver;

/**
 * 数据解析类
 * 
 * @author zhangke
 * 
 */
@Transactional
public class ParserTask extends TransactionalServiceImpl implements Runnable {
	
	
	private static final Logger log = Logger.getLogger(ParserTask.class);

	private String messageType;
	private short messageLen;
	private String readerID;
	private int antennaPort;
	private String tagID;
	private long time;
	private char status;
	private ByteBuffer receivedMsg;
	private Status shareStatus;
	private Hashtable<String, List<Event>> eventHash;
	private EntityManager em;
	


	public ParserTask(ByteBuffer receivedMsg) {
		this.receivedMsg = receivedMsg;
		
	}

	@Override
	public void run() {
		// 解析数据和插入
		parseData(this.receivedMsg);
	}

	public ByteBuffer getReceivedMsg() {
		return receivedMsg;
	}

	public void parseData(ByteBuffer recervedMsg) {
		
		if(Status.buffer.size() != 0){
			//取出buffer中的字节
			for(int i = 0; i < Status.buffer.size(); i++){
				recervedMsg.put((Byte)Status.buffer.get(i));
			}
			Status.buffer.clear();
		}
		
		
		RFIDAntenna antenna;
		
		this.messageType = "" + recervedMsg.get(0);
		this.messageLen = (short) ((recervedMsg.get(1) & 0x0FF) * Math.sqrt(16) + (recervedMsg
				.get(2) & 0x0FF));
		//if (recervedMsg.limit() / 161 < 2) {
			this.tagID = readBytes(3, 130, recervedMsg);
			this.readerID = readBytes(131, 150, recervedMsg);
			this.antennaPort = Integer.parseInt("" + recervedMsg.get(151));
			recervedMsg.position(152);
			this.time = recervedMsg.getLong();
			this.status = ("" + recervedMsg.get(160)).charAt(0);
			
			antenna = new RFIDAntenna();
			antenna.setAntennaPort(this.antennaPort);
			RFIDReader reader = new RFIDReader();
			reader.setReaderID(readerID);
			antenna.setReader(reader);
		
			//更新Event表
			Event event = new Event();
			event.setTagID(tagID);
			event.setTime(time);
			event.setAntennaID(readerID + antennaPort);
			if(status == '0')
			{
				event.setStatus(Status.STATUS_OUT);
			}
			else
			{
				event.setStatus(Status.STATUS_IN);
			}
			
			EventHandlerImpl eventHandlerImpl = (EventHandlerImpl)BeanFactory.getBean(Status.BEAN_EVENT_HANDLER);
			eventHandlerImpl.handle(event);
			
			
	//	} else {
			if(recervedMsg.limit() > 161){
			// 出现了粘包现象
			recervedMsg.position(161);
			for(int i = 161; i < recervedMsg.limit(); i++){
				Status.buffer.add(recervedMsg.get());
//			}
			}

		}
	}

	private String readBytes(int i, int j, ByteBuffer bb) {
		String str = "";
		for (int k = i; k < j + 1; k++) {
			char c = (char) bb.get(k);
			if (c != '#') {
				str += c;
			} else {
				return str;
			}
		}
		return str;
	}
}

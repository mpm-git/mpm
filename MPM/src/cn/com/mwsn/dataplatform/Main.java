package cn.com.mwsn.dataplatform;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.xml.ws.Endpoint;

import org.apache.log4j.Logger;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mwsn.dataplatform.eventengine.impl.EventHandlerImpl;
import cn.com.mwsn.dataplatform.eventengine.impl.ParseDataAdapter;
import cn.com.mwsn.frame.bean.BeanFactory;
import cn.com.mwsn.mpm.receiver.EventReceiver;
import cn.com.mwsn.mpm.receiver.IncidentReceiver;



/**
 * 数据平台启动
 * @author haiyanzhihun
 *
 */
public class Main {
	
	
	private static final int PORT = 8084;
	
	private static final Logger log = Logger.getLogger(Main.class);

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		
		log.debug("starting server...");
		log.debug("starting mima...");
		NioSocketAcceptor acceptor = new NioSocketAcceptor(Runtime.getRuntime()
				.availableProcessors() + 1);
		acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		acceptor.setHandler(new ParseDataAdapter());
		acceptor.getSessionConfig().setReadBufferSize(2048);
		acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
		acceptor.bind(new InetSocketAddress(PORT));			
	}

}

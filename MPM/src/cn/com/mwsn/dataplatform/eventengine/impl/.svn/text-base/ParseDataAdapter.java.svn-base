package cn.com.mwsn.dataplatform.eventengine.impl;

import java.nio.ByteBuffer;
import java.util.Hashtable;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;

import cn.com.mwsn.dataplatform.eventengine.common.Status;
import cn.com.mwsn.dataplatform.eventengine.entity.Event;

public class ParseDataAdapter extends IoHandlerAdapter implements IoHandler {

	private ThreadPoolExecutor executor;
	private LinkedBlockingQueue<Runnable> taskQueue; // 共享任务队列，动态增长

	public ParseDataAdapter() {
		
		taskQueue = new LinkedBlockingQueue<Runnable>();
		executor = new ThreadPoolExecutor(Status.MIN_THREAD_NUM,
				Status.MAX_THREAD_NUM, Status.KEEPALIVETIME,
				TimeUnit.MILLISECONDS, taskQueue);
	}

	public void exceptionCaught(IoSession session, Throwable cause)
			throws Exception {
		cause.printStackTrace();
	}

	public void messageReceived(IoSession session, Object message)
			throws Exception {

		IoBuffer ib = (IoBuffer) message;
		ByteBuffer receivedMsg = ib.buf();
		executor.execute(new ParserTask(receivedMsg));

	}

	@Override
	public void sessionCreated(IoSession session) throws Exception {
	}

	@Override
	public void sessionIdle(IoSession session, IdleStatus status)
			throws Exception {
	}
}

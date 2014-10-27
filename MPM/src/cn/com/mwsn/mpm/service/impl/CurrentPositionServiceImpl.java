package cn.com.mwsn.mpm.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.service.impl.TransactionalServiceImpl;
import cn.com.mwsn.mpm.common.Constant;
import cn.com.mwsn.mpm.common.GraphList;
import cn.com.mwsn.mpm.common.GraphList.Graph;
import cn.com.mwsn.mpm.entity.CurrentPosition;
import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.entity.RFIDCardPerson;
import cn.com.mwsn.mpm.service.CurrentPositionService;

@Service("currentPositionService")
@Transactional
public class CurrentPositionServiceImpl extends TransactionalServiceImpl
		implements CurrentPositionService {

	private static final Logger log = Logger
			.getLogger(CurrentPositionServiceImpl.class);

	
	@Override
	public void updateCurrentPos(Incident incident) {
		log.debug("进入CurrentPositionServiceImpl");

		GraphList mGraph = new GraphList();
		List<Graph> listGraph = mGraph.getGraph();
		if (incident.getType() == Constant.STATUS_OUT) {
			// 人出去时，将门口的天线赋值给她
			log.debug("人出去！");
			RFIDCardPerson person = this.findBy(RFIDCardPerson.class, "tagID",
					incident.getTagID());
			CurrentPosition curPosition = this.findBy(CurrentPosition.class,
					"name", person.getName());
			String s = incident.getWardName().replace('w', 'e');
//			int num = Integer.parseInt(String.valueOf(1));
			InpatientCell cell = this.findBy(InpatientCell.class, "name", s);
			if (!cell.equals(null) && curPosition!=null) {
				curPosition.setX0(curPosition.getX());
				curPosition.setY0(curPosition.getY());
				curPosition.setX(cell.getX() + (int) (Math.random() * 100));
				curPosition.setY(cell.getY() + (int) (Math.random() * 50));
				this.merge(curPosition);
			}
			else if(curPosition==null) {
				curPosition = new CurrentPosition();
				curPosition.setX0(-1);
				curPosition.setY0(-1);
				curPosition.setX(cell.getX() + (int) (Math.random() * 100));
				curPosition.setY(cell.getY() + (int) (Math.random() * 50));
				this.save(curPosition);
			}
			mGraph.deleteRfid(incident.getTagID(), incident.getWardName());
		} else {
			
			mGraph.addRfid(incident.getTagID(), incident.getWardName());
			List<String> inWardList = mGraph.getPersons(incident.getWardName());
			int n = inWardList.size();
			log.debug(incident.getWardName() + "  屋里有：  " + inWardList.size());
			

			if (n > 0) {
				log.debug(incident.getWardName());
				InpatientCell cell = this.findBy(InpatientCell.class, "name",
						incident.getWardName());
				if (cell == null) {
					return;
				}
				int[][] XY = searchPersonXY(cell.getX(), cell.getY(),
						cell.getWidth(), cell.getHigh(), n);
				for (int j = 0; j < XY.length; j++) {
					log.debug("(" + XY[j][0] + "," + XY[j][1] + ")");
				}

				for (int i = 0; i < XY.length; i++) {
					CurrentPosition currentPosition = this.findBy(
							CurrentPosition.class, "rfid", inWardList.get(i));
					if (currentPosition == null) {
						// 新来的人
						log.debug("tagID" + inWardList.get(i));
						RFIDCardPerson person = this.findBy(
								RFIDCardPerson.class, "tagID",
								inWardList.get(i));
						currentPosition = new CurrentPosition();
						currentPosition.setInpatientAreaNum(0);
						currentPosition.setName(person.getName());
						currentPosition.setRfid(person.getTagID());
						currentPosition.setRole(person.getRole());
						currentPosition.setX(XY[i][0]);
						currentPosition.setY(XY[i][1]);
						currentPosition.setX0(-1);
						currentPosition.setY0(-1);
						this.save(currentPosition);
					} else {
						// 屋里呆着的人
						currentPosition.setX0(currentPosition.getX());
						currentPosition.setY0(currentPosition.getY());
						currentPosition.setX(XY[i][0]);
						currentPosition.setY(XY[i][1]);
						this.merge(currentPosition);
					}

				}

			}
		}

	}

	public int[][] searchPersonXY(int x, int y, int width, int high, int n) {
		int[][] array = new int[n][2];
		int maxX = x + width;
		int maxY = y + high;
		int k = maxY / Constant.ICON_Y; // 一列有k个
		int i = maxX / Constant.ICON_X; // 一排有i个
		if (n > k * i) {
			System.out.println("设置的图标过大");
		} else {
			for (int mm = 0; mm < n; mm++) {
				array[mm][0] = x + (mm % i) * Constant.ICON_X;
				array[mm][1] = y + (mm / i) * Constant.ICON_Y;
			}
		}
		return array;
	}

}

package cn.com.mwsn.mpm.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.mwsn.mpm.entity.InpatientCell;
import cn.com.mwsn.mpm.service.CurrentPositionService;

public class GraphList {
	
	//地图的存储
	private static List<Graph> graphList = new ArrayList<Graph>();
	
	private void init() {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("applicationContext.xml");
		CurrentPositionService currentPositionService = (CurrentPositionService)applicationContext.getBean("currentPositionService");
		List<InpatientCell> list = currentPositionService.findAll(InpatientCell.class);
		Iterator<InpatientCell> it = list.iterator();
		while(it.hasNext()) {
			InpatientCell cell = it.next();
			if(!cell.getName().equals("") && cell.getName().charAt(0) == 'w') {
				this.graphList.add(new Graph(cell.getInpatientArea().getName(), cell.getName()));
			}
		}
		
	}
	
	public List<Graph> getGraph() {
		if(this.graphList.size() == 0){
			init();
		}
		return this.graphList;
	}
	
	public String getWard(String Rfid) {
		Iterator<Graph> it = this.graphList.iterator();
		while(it.hasNext()) {
			Graph graph = it.next();
			if(graph.getInWardList().contains(Rfid)) {
				System.out.println(Rfid + "在房间里");
				return graph.getRoom();
			}
		}
		return "";
	}

	public List<String> getPersons(String ward) {
		Iterator<Graph> it = this.graphList.iterator();
		while(it.hasNext()) {
			Graph graph = it.next();
			if(graph.getRoom().equals(ward)) {
				return graph.getInWardList();
			}
		}
		return null;
	}
	
	public void addRfid(String rfid, String ward) {
		
		for(int i = 0; i < this.graphList.size(); i++) {
			if(this.graphList.get(i).getRoom().equals(ward)) {
				if(!this.graphList.get(i).getInWardList().contains(rfid)) {
					this.graphList.get(i).getInWardList().add(rfid);
					break;
				}
			}
		}
	}
	
	public void deleteRfid(String rfid, String ward) {
		for(int i = 0; i < this.graphList.size(); i++) {
			if(this.graphList.get(i).getRoom().equals(ward)) {
				if(this.graphList.get(i).getInWardList().contains(rfid)) {
					this.graphList.get(i).getInWardList().remove(rfid);
					break;
				}
			}
		}
	}
	public class Graph {
		public String area;
		public String room;
		public List<String> inWardList;
		public Graph(String area, String room) {
			this.area = area;
			this.room = room;
			this.inWardList = new ArrayList<String>();
		}
		public String getArea() {
			return area;
		}
		public void setArea(String area) {
			this.area = area;
		}
		public String getRoom() {
			return room;
		}
		public void setRoom(String room) {
			this.room = room;
		}
		public List<String> getInWardList() {
			return inWardList;
		}
		public void setInWardList(List<String> inWardList) {
			this.inWardList = inWardList;
		}
		
		
	}
}

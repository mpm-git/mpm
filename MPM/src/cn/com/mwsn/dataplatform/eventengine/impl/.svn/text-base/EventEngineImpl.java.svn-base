package cn.com.mwsn.dataplatform.eventengine.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;


import cn.com.mwsn.dataplatform.eventengine.EventEngine;
import cn.com.mwsn.dataplatform.eventengine.common.Status;
import cn.com.mwsn.dataplatform.eventengine.entity.Event;
import cn.com.mwsn.dataplatform.eventengine.entity.Incident;
import cn.com.mwsn.frame.bean.BeanFactory;
import cn.com.mwsn.mpm.receiver.EventReceiver;
import cn.com.mwsn.mpm.receiver.IncidentReceiver;


@Service
public class EventEngineImpl implements EventEngine {
	
	private static final Logger log = Logger.getLogger(EventEngineImpl.class);
	private static List<Rule> rules;
	private static List<SelectedRule> selectedRules = new ArrayList<SelectedRule>();
	private EventHandlerImpl eventHandlerImpl;
	
	private static void parseRuleFile(){
		try {
            File f = new File("./src/rule.xml");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = (Document) builder.parse(f);
            
            rules = new ArrayList<Rule>();
            
            synchronized(rules){
            	NodeList nodelist1=doc.getElementsByTagName("in");
                for(int i=0;i<nodelist1.getLength();i++)
                {
                	Rule rule = new Rule();
                	rule.source = (String) nodelist1.item(i).getAttributes().getNamedItem("source").getNodeValue();
                	rule.target = (String) nodelist1.item(i).getAttributes().getNamedItem("target").getNodeValue();
                	rule.ward = (String) nodelist1.item(i).getAttributes().getNamedItem("ward").getNodeValue();
                	rule.id = (String) nodelist1.item(i).getAttributes().getNamedItem("id").getNodeValue();
                	rule.type = "0";
                	
                	rules.add(rule);
                }

                NodeList nodelist2=doc.getElementsByTagName("out");
                for(int i=0;i<nodelist2.getLength();i++)
                {
                	Rule rule = new Rule();
                	rule.source = (String) nodelist2.item(i).getAttributes().getNamedItem("source").getNodeValue();
                	rule.target = (String) nodelist2.item(i).getAttributes().getNamedItem("target").getNodeValue();
                	rule.ward = (String) nodelist2.item(i).getAttributes().getNamedItem("ward").getNodeValue();
                	rule.id = (String) nodelist2.item(i).getAttributes().getNamedItem("id").getNodeValue();
                	rule.type = "1";
                	
                	rules.add(rule);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }	
	}
	@Override
	public synchronized void handle(Event event) {
		
		IncidentReceiver ir = (IncidentReceiver)BeanFactory.getBean("wsIcidentReceiver");
		if (rules == null){
			this.parseRuleFile();
		}
		if (rules.size() == 0){
			return;
		}else{
			if (selectedRules.size() > 0){
				Iterator it = selectedRules.iterator();
				while(it.hasNext()){
					SelectedRule sr = (SelectedRule)it.next();
						if(event.getStatus() == 1){
						if (sr.tagId.equals(event.getTagID())){
							if (sr.target.equals(event.getAntennaID())){
								Incident incident = new Incident();
								if (sr.type.equals("0")){
									log.debug("产生进门的incident");
									incident.setTagID(event.getTagID());
									incident.setTime(event.getTime());
									incident.setType(0);
									incident.setWardName(sr.ward);
									it.remove();
									System.out.println("匹配成功，删除规则   " + sr.source);
									
								}else if (sr.type.equals("1")){
									log.debug("产生出门的incident");
									incident.setTagID(event.getTagID());
									incident.setTime(event.getTime());
									incident.setType(1);
									incident.setWardName(sr.ward);
									it.remove();
									System.out.println("匹配成功，删除规则   " + sr.source);
								}
								if(!Status.tagID.equals(incident.getTagID()) || Status.time != incident.getTime()) {
									ir.handle(incident);
									Status.tagID = incident.getTagID();
									Status.time = incident.getTime();
								}
								
								/*if(!Status.bufferType.equals(incident.getType()+"") || Status.bufferType == "") {
									ir.handle(incident);
									log.debug("存incident");
								}
								Status.bufferType = incident.getType() + "";*/
							}
						}
					}
				}	
			}
			
			synchronized(selectedRules){
				for (Rule rule : rules){
					if (rule.source.equals(event.getAntennaID())){
						SelectedRule sr = new SelectedRule();
						sr.id = rule.id;
						sr.source = rule.source;
						sr.target = rule.target;
						sr.type = rule.type;
						sr.ward = rule.ward;
						sr.tagId = event.getTagID();
						
						if(!selectedRules.contains(sr)){
							selectedRules.add(sr);
							System.out.println("加入规则   " + sr.source);
						}
					}
				}
			}
			
		}
		
	}
	
	private static class Rule {
		
		public String id;
		public String source;
		public String target;
		public String ward;
		public String type;

	}
	
	private static class SelectedRule extends Rule{
		public String tagId;
	}
	

}

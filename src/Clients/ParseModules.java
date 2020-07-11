package question5;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.xmlpull.v1.*;

public class ParseModules {
	boolean incars = false;
	boolean incar = false;
	boolean inId = false;
	boolean inName = false;
	boolean inModel = false;
	boolean inRegarea = false;
	
	car currentModule = new car();
	List<car> currentModuleList = new ArrayList<car>();
	
	public List<car> doParseModules(String s) {
		try {
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			XmlPullParser pullParser = factory.newPullParser();
			pullParser.setInput(new StringReader(s));
			processDocument(pullParser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return currentModuleList;
	}
	
	public void processDocument(XmlPullParser pullParser) throws XmlPullParserException, IOException {
		int eventType = pullParser.getEventType();
		do {
			if (eventType == XmlPullParser.START_DOCUMENT) {
				System.out.println("Start Document");
			} 
			else if (eventType == XmlPullParser.END_DOCUMENT) {
				System.out.println("End Document");
			} 
			else if (eventType == XmlPullParser.START_TAG) {
				processStartElement(pullParser);
			} 
			else if (eventType == XmlPullParser.END_TAG) {
				processEndElement(pullParser);
			} 
			else if (eventType == XmlPullParser.TEXT) {
				processText(pullParser);
			}
			eventType = pullParser.next();
		} while (eventType != XmlPullParser.END_DOCUMENT);
	}
	
	public void processStartElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("cars")) {
			incars = true;
			currentModuleList = new ArrayList<car>();
		}
		else if (name.equals("car")) {
			incar = true;
			currentModule = new car();
		} 
		else if (name.equals("id")) {
			inId = true;
		} 
		else if (name.equals("name")) {
			inName = true;
		} 
		else if (name.equals("model")) {
			inModel = true;
		} 
		else if (name.equals("regarea")) {
			inRegarea = true;
		}
	}
	
	public void processEndElement(XmlPullParser event) {
		String name = event.getName();
		if (name.equals("cars")) {
			incars = false;
		}
		else if (name.equals("car")) {
			incar = false;
			currentModuleList.add(currentModule);
			
		} 
		
		else if (name.equals("id")) {
			inId = false;
		} 
		else if (name.equals("name")) {
			inName = false;
		} 
		else if (name.equals("model")) {
			inModel = false;
		} 
		else if (name.equals("regarea")) {
			inRegarea = false;
		}
	}

	public void processText(XmlPullParser event) throws XmlPullParserException {
		if(inId) {
			String s = event.getText();
			currentModule.setId(Integer.parseInt(s));
		}
		if(inName) {
			String s = event.getText();
			currentModule.setName(s);;
		}
		if(inModel) {
			String s = event.getText();
			currentModule.setModel(s);
		}
		if(inRegarea) {
			String s = event.getText();
			currentModule.setRegarea(s);
		}
	}
}

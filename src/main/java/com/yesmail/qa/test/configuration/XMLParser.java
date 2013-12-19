package com.yesmail.qa.test.configuration;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/***
 * This class contains methods for reading componenet.xml file
 * 
 * @author rahull
 * 
 */
public class XMLParser {
	private static Document document;

	static {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		try {
			DocumentBuilder db = dbf.newDocumentBuilder();
			document = db.parse(XMLParser.class.getResource("/Component.xml")
					.getPath());
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException se) {
			se.printStackTrace();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}

	/**
	 * Method to traverse the XML document returns the value for specified
	 * String
	 * 
	 * @param argLocator
	 * @return
	 */
	public static String readComponentValueFromXML(String argLocator) {
		String[] componentLocator = argLocator.split("\\.");
		String uiObjectAttName = componentLocator[0];
		String locatorAttrName = componentLocator[1];
		Element rootElement = document.getDocumentElement();
		String uiObjectLocator = null;
		NodeList nodeList = rootElement.getElementsByTagName("page");
		if (nodeList != null && nodeList.getLength() > 0) {
			for (int i = 0; i < nodeList.getLength(); i++) {
				Element element = (Element) nodeList.item(i);
				if (element.getAttribute("name").equals(uiObjectAttName)) {
					uiObjectLocator = getTextValue(element, locatorAttrName);
					break;
				}
			}
		}
		return uiObjectLocator;
	}

	/**
	 * I take a xml element and the tag name, look for the tag and get the text
	 * content i.e for <employee><name>John</name></employee> xml snippet if the
	 * Element points to employee node and tagName is name I will return John
	 * 
	 * @param ele
	 * @param tagName
	 * @return
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nodeList = ele.getElementsByTagName(tagName);
		if (nodeList != null && nodeList.getLength() > 0) {
			Element el = (Element) nodeList.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}
		return textVal;
	}

}

package CoreAPIBackup;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import CoreAPI.APICore;

public class APICoreBackup {
	
	
	//EXTRACT VALUE FROM XML
	//-----------------------------------------------------------------------------------
	public static String extractValueFromXml(String xml, String xpath, String attributeName) {
		try {
			Document doc= APICore.StringToDocument(xml);
			if (doc==null) return null;

			XPathFactory xPathfactory = XPathFactory.newInstance();
			XPath xpathQuery = xPathfactory.newXPath();
			XPathExpression expr = xpathQuery.compile(xpath);
			NodeList nodes = (NodeList) expr.evaluate(doc, XPathConstants.NODESET);
			if (nodes==null || nodes.getLength()==0) {
				System.err.println("Node is null or zero length : "+(nodes==null ? "null" : ""+nodes.getLength()));
				return null;
			}
			Node node=nodes.item(0);
			if (attributeName.toLowerCase().equals("text")) return node.getTextContent();
			return node.getAttributes().getNamedItem(attributeName).getNodeValue();
		} catch (Exception e) {
				e.printStackTrace();
				return null;
		}

	}
	//-----------------------------------------------------------------------------------
	
	
	
    //ASSERT EQUALS & CONTAINS AND NVL JAVA
	//-----------------------------------------------------------------------------------    
    //ASSERT EQUALS
	public static void AssertEquals(String value1, String value2) {
		
		if (!APICoreBackup.nvl(value1, "").equals(APICoreBackup.nvl(value2, ""))) {
			System.out.println("Problem: ["+value1+"] & ["+value2+"]");
		}
		
		System.out.println("AssertEquals Checking: ["+value1+"] & ["+value2+"]");
	}
	
	//ASSERT CONTAINS
	public static void AssertContains(String value1, String value2) {
		
		if (!APICoreBackup.nvl(value1, "").contains(APICoreBackup.nvl(value2, ""))) {
			System.out.println("Problem: ["+value1+"] & ["+value2+"]");
		}
		
		System.out.println("AssertEquals Checking: ["+value1+"] & ["+value2+"]");
	}
    
	//NVL JAVA
	public static String nvl(String checkValue, String valueIfNull) {
		return (checkValue==null || checkValue.length()==0) ?  valueIfNull : checkValue;
	}
	//-----------------------------------------------------------------------------------

	
	
}
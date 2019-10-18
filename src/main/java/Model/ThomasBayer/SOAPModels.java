package Model.ThomasBayer;

import CoreAPI.APICore;
import CoreAPI.SOAPRequest;

public class SOAPModels {
	
	String WSHeader;
	String WSBody;
	
	public String getWSHeader() {return WSHeader;}
	public void setWSHeader(String wSHeader) {WSHeader = wSHeader;}

	public String getWSBody() {return WSBody;}
	public void setWSBody(String wSBody) {WSBody = wSBody;}


	//HEADER Default
	//-----------------------------------------------------------------
	public static SOAPModels HeaderDefault() {
		SOAPModels header=new SOAPModels();	
		return header;
	}
	//-----------------------------------------------------------------
	
	
	//HEADER Template
	//-----------------------------------------------------------------
	public static String HeaderTemplate() {
		return 
				APICore.FormatXML(
									"<soapenv:Header/>\r\n" + 
									""
									);
	}
	//-----------------------------------------------------------------
	

	//SOAP CountryInfoService Request
	//-----------------------------------------------------------------
	public String RequestTemplate() {
		return
				APICore.FormatXML(
									"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:blz=\"http://thomas-bayer.com/blz/\">\r\n" + 
									""+WSHeader+"\r\n" + 
									"<soapenv:Header/>\r\n" + 
									"<soapenv:Body>\r\n" + 
									""+WSBody+"\r\n" + 
									"</soapenv:Body>\r\n" + 
									"</soapenv:Envelope>\r\n" + 
									""
									);
	}
	//-----------------------------------------------------------------
	
	
	//BLZServiceSOAP11Binding SOAP Request Template RAW
	//-----------------------------------------------------------------
	public static SOAPRequest RequestRAWTemplate(SOAPModels SOAPMessage) {

		String WSEndPoint="http://www.thomas-bayer.com/axis2/services/BLZService";
			
		SOAPRequest request=new SOAPRequest(WSEndPoint, SOAPRequest.POST, SOAPMessage.RequestTemplate(), 0);
		request.addInputHeaderParameter("Content-Type", "text/xml;charset=UTF-8");
		request.addInputHeaderParameter("Host", "www.thomas-bayer.com");
		request.addInputHeaderParameter("Connection", "Keep-Alive");
		request.addInputHeaderParameter("User-Agent", "Apache-HttpClient/4.1.1 (java 1.5)");
		request.addInputHeaderParameter("SOAPAction", "");
		request.addInputHeaderParameter("Content-Length", "279");
		request.addInputHeaderParameter("Accept-Encoding", "gzip,deflate");

		return request;
	}
	//-----------------------------------------------------------------
	
	
	//SET SOAP WS OPERATIONS
	//-----------------------------------------------------------------
	public static void soapResult(String soapOperation) {
		
		SOAPModels BLZServiceSOAP11Binding = new SOAPModels();
		BLZServiceSOAP11Binding.setWSBody(soapOperation);

		SOAPRequest sOAPRequest = SOAPModels.RequestRAWTemplate(BLZServiceSOAP11Binding);
		
		boolean Control = APICore.sendSoapRequest(sOAPRequest);

		if (!Control) {
			System.out.println("Response Code  : " + sOAPRequest.getResponseCode());
			System.out.println("Error Message  : " + sOAPRequest.getErrorMessage());
			System.out.println("WS Output XML  : " + sOAPRequest.getOutputXML());
		} else {
			System.out.println("Response Code: " + sOAPRequest.getResponseCode()+"\n");
			System.out.println(""+APICore.BLUE+"Response Output"+"\n---------------\n"+ sOAPRequest.getOutputXML());
		}
	}
	//-----------------------------------------------------------------
	
	
	//RESPONSE CONTROL
	//-----------------------------------------------------------------
	public static void responseControl(String soapOperation, String searchValue) {
		
		SOAPModels BLZServiceSOAP11Binding = new SOAPModels();
		BLZServiceSOAP11Binding.setWSBody(soapOperation);

		SOAPRequest sOAPRequest = SOAPModels.RequestRAWTemplate(BLZServiceSOAP11Binding);
		
		boolean Control = APICore.sendSoapRequest(sOAPRequest);

		if (!Control) {
			System.out.println("Response Code  : " + sOAPRequest.getResponseCode());
			System.out.println("Error Message  : " + sOAPRequest.getErrorMessage());
			System.out.println("WS Output XML  : " + sOAPRequest.getOutputXML());
		} else {
			Boolean found = sOAPRequest.getOutputXML().contains(searchValue);
			
			if(found) {
				System.out.println("["+searchValue+"]");
				System.out.println(""+APICore.GREEN+"is in the Response [TRUE]");
			}
			else {
				System.out.println("["+searchValue+"]");
				System.out.println(""+APICore.RED+"is NOT in the Response [FALSE]");
			}
		}
	}
	//-----------------------------------------------------------------

}
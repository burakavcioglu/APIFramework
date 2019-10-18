package CoreAPI;

import java.util.ArrayList;
import org.w3c.dom.Document;

public class SOAPRequest {
	
	//WEB SERVICE OBJECTS
	public static final String GET="GET";
	public static final String POST="POST";
	public static final String PUT="PUT";
	public static final String DELETE="DELETE";
	
	Document outputDocument;
	String endPoint;
	String method;
	String inputXML;
	String errorMessage;
	
	int responseCode;
	int timeout=0;
	
	
	String outputXML;
	ArrayList<KeyVal> inputHederParams=new ArrayList<KeyVal>();
	ArrayList<KeyVal> outputHederParams=new ArrayList<KeyVal>();

	
	public SOAPRequest(String endPoint, String method, String inputXML, int timeout) {
		super();
		this.endPoint = endPoint;
		this.method = method;
		this.inputXML = inputXML;
		this.timeout = timeout;
	}
		
	public Document getOutputDocument() {return outputDocument;}
	public void setOutputDocument(Document outputDocument) {this.outputDocument = outputDocument;}

	public int getTimeout() {return timeout;}
	public void setTimeout(int timeout) {this.timeout = timeout;}

	public String getEndPoint() {return endPoint;}
	public void setEndPoint(String endPoint) {this.endPoint = endPoint;}
	
	public String getMethod() {return method;}
	public void setMethod(String method) {this.method = method;}
	
	public String getInputXML() {return APICore.FormatXML(inputXML);}
	public void setInputXML(String inputXML) {this.inputXML = inputXML;}
	
	public String getOutputXML() {return APICore.FormatXML(outputXML);}
	public void setOutputXML(String outputXML) {this.outputXML = outputXML;}
	
	public ArrayList<KeyVal> getInputHederParams() {return inputHederParams;}
	public void setInputHederParams(ArrayList<KeyVal> inputHederParams) {this.inputHederParams = inputHederParams;}
	
	public void addInputHeaderParameter(String key, String val) {this.inputHederParams.add(new KeyVal(key, val));}
	public ArrayList<KeyVal> getOutputHederParams() {return outputHederParams;}
	
	public void setOutputHederParams(ArrayList<KeyVal> outputHederParams) {this.outputHederParams = outputHederParams;}
	public void addOutputHeaderParameter(String key, String val) {this.outputHederParams.add(new KeyVal(key, val));}
	
	public int getResponseCode() {return responseCode;}
	public void setResponseCode(int responseCode) {this.responseCode = responseCode;}
	
	public String getErrorMessage() {return errorMessage;}
	public void setErrorMessage(String errorMessage) {this.errorMessage = errorMessage;}
	
}
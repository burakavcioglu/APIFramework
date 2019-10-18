package CoreAPI;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

public abstract class APICore {
	
	@Before
	public abstract void WSSetup(); //All Firstly Method Before Test and Fixed
	
	@Test
	public abstract void WSRun(); //Test

	//@After Annotation Excluded
	//public abstract void WSFinished();

	//Web Services Topic
	//Indent XML Generated by Transformer 
	//Usage of javax.xml.transform.OutputKeys.OutputKeys.INDENT
    
	
	//COLORS
	public static final String RED = "\033[31m";
	public static final String GREEN = "\033[32m";
	public static final String BLUE = "\033[34m";
	
    
	//FORMAT XML
	//-----------------------------------------------------------------------------------
	public static String FormatXML(String XML) {
		try 
		{Document Doc=StringToDocument(XML);
			if (Doc==null) return XML;
			return XMLFormat(Doc);
		}catch (Exception error) {
			return ExceptionToString(error);
		}
	}
	//-----------------------------------------------------------------------------------
	
	
	
	//STRING TO DOCUMENT
	//-----------------------------------------------------------------------------------
	public static Document StringToDocument(String XML) {
		if(XML == null) return null;
		Document Doc=null;
		try {
			DocumentBuilderFactory Factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder Builder = Factory.newDocumentBuilder();
			Doc=Builder.parse(new ByteArrayInputStream(XML.getBytes("UTF-8")));
		} catch(Exception error){
			error.printStackTrace();
			return null;
		}		
		return Doc;
	}
	//-----------------------------------------------------------------------------------
	
	
	
	//XML FORMAT
	//-----------------------------------------------------------------------------------
	public static String XMLFormat(Document Doc) {
		try {
			Transformer transformer = TransformerFactory.newInstance().newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
			transformer.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC,"yes"); //Add DOCTYPE_PUBLIC
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
			
			StreamResult Result = new StreamResult(new StringWriter());
			DOMSource Source = new DOMSource(Doc);
			transformer.transform(Source, Result);
			
			return Result.getWriter().toString();
		} catch(Exception error){return Doc.toString();}
	}
	//-----------------------------------------------------------------------------------
	
	
	
	//Exception To String
	//-----------------------------------------------------------------------------------
	public static String ExceptionToString(Exception error) {
		StringBuilder SB=new StringBuilder();
		SB.append(error.getMessage());
		for(StackTraceElement el : error.getStackTrace()) {
			SB.append("\n"+el.toString());
		}
		return SB.toString();
	}
	//-----------------------------------------------------------------------------------
	
	

    //SEND SOAP WEB SERVICES REQUEST
	//-----------------------------------------------------------------------------------
    public static boolean sendSoapRequest(SOAPRequest request) {
		
		boolean returnvariable = true;
		
		URL url=null;
		URLConnection connection=null;
		HttpURLConnection httpConn=null;
		OutputStream out = null;
		InputStreamReader isr;
		ByteArrayOutputStream bout = new ByteArrayOutputStream();
		BufferedReader in=null;

		try{
			url=new URL(request.getEndPoint());
			connection = url.openConnection();
			httpConn = (HttpURLConnection) connection;

			byte[] buffer = new byte[request.getInputXML().length()];
			buffer = request.getInputXML().getBytes();
			bout.write(buffer);
			byte[] b = bout.toByteArray();
			httpConn.setRequestProperty("Content-Length",String.valueOf(b.length));
			

			httpConn.setRequestMethod(request.getMethod());
			for (KeyVal param : request.getInputHederParams()) httpConn.setRequestProperty(param.getKey(),param.getVal());
			
			if (request.getTimeout()>0) {
				httpConn.setConnectTimeout(request.getTimeout());
				httpConn.setReadTimeout(request.getTimeout());
				
			}
			
			httpConn.setDoOutput(true);
			httpConn.setDoInput(true);
			
			out = httpConn.getOutputStream();
			out.write(b);
			out.close();
			
			int responseCode=httpConn.getResponseCode();
			request.setResponseCode(responseCode);
			
			
			
			} catch(Exception e) {
				try{
					int responseCode=httpConn.getResponseCode();
					request.setResponseCode(responseCode);
				} catch(Exception ex) {}	
			e.printStackTrace();
			request.setErrorMessage(ExceptionToString(e));
			returnvariable = false;
		} finally {
			
			
			try {
				if (httpConn.getResponseCode()==200) 
					isr =new InputStreamReader(httpConn.getInputStream());
				else {
					isr =new InputStreamReader(httpConn.getErrorStream());
					returnvariable = false;
				}
					
				
				in = new BufferedReader(isr);
				
				StringBuilder outputString=new StringBuilder();
				String responseString;
				while ((responseString = in.readLine()) != null) outputString.append(responseString);			
				
				request.setOutputXML(outputString.toString());
				
				
				if (outputString.length()>0) {
					try {
						DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
						DocumentBuilder builder = factory.newDocumentBuilder();
						request.setOutputDocument(builder.parse(new ByteArrayInputStream(outputString.toString().getBytes("UTF-8"))));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			} catch(Exception e) {
				e.printStackTrace();
				request.setErrorMessage(ExceptionToString(e));
			} finally {
				try{out.close();} catch(Exception ex) {}
				try{in.close();} catch(Exception ex) {}
			}
		}
		
		
		return returnvariable;
	}
	//-----------------------------------------------------------------------------------

    
    
	//REST CALL SERVICES
	//-----------------------------------------------------------------------------------------------
	public void restServicesCall(String endPoint) {
		  try {

			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed REST: HTTP Error Code: "
						+ conn.getResponseCode());
			}

			BufferedReader br = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));

			String restOutput;
			System.out.println("Rest API Response\n\n");
			while ((restOutput = br.readLine()) != null) {
				System.out.println(""+APICore.GREEN+"Response Output"+"\n---------------\n"+ restOutput);
			}

			conn.disconnect();}
		  
		  	catch (MalformedURLException e) {e.printStackTrace();}
		    catch (IOException e) {e.printStackTrace();}
	}
	//-----------------------------------------------------------------------------------------------
	
	
	//REST RESPONSE CONTROL
	//-----------------------------------------------------------------------------------------------
	public void restResponseControl(String endPoint, String searchValue) {
		  try {

			URL url = new URL(endPoint);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");

			if (conn.getResponseCode() != 200) {
				throw new RuntimeException("Failed REST: HTTP Error Code: "
						+ conn.getResponseCode());
			}

			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
				(conn.getInputStream())));
			
			String restOutput;

			if (searchValue == null) {
				System.out.println("Rest API Response\n\n");
				while ((restOutput = bufferedReader.readLine()) != null) {
					System.out.println(""+APICore.BLUE+"Response Output"+"\n---------------\n"+ restOutput);
				}
			}
			else {
				
				while ((restOutput = bufferedReader.readLine()) != null) {
					Boolean found = restOutput.contains(searchValue);
					
					if(found) {
						System.out.println(""+APICore.GREEN+"["+searchValue+"] == is in the Response [Done]");
					}
					else {
						System.out.println("["+searchValue+"]");
						System.out.println(""+APICore.RED+"is NOT in the Response [FALSE]");
					}
				}
			}
			
			conn.disconnect();}
		  
		  	catch (MalformedURLException e) {e.printStackTrace();} 
		    catch (IOException e) {e.printStackTrace();}
	}
	//-----------------------------------------------------------------------------------------------

}
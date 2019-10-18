package Model.ThomasBayer;

public class GetBank{
	
	//Service Objects
	public static final String BankCode = "37050198";
	public static final String GET_BANK = "getBank";
	public static final String BUNDES_BANK = "10000000";
	public static final String DEUTSCHE_BANK = "38070059";
	public static final String SPARDA_BANK_HESSEN  = "50090500";
	public static final String WESTERWALD_BANK = "57391800";

		
	//Request
	String blz;
	SOAPModels Header;

	public String getsBlz() {return blz;}
	public void setsBlz(String blz) {this.blz = blz;}
	
	public SOAPModels getHeader() {return Header;}
	public void setHeader(SOAPModels header) {Header = header;}
	
	
	//Build GetBank Default
	//--------------------------------------------------------------------------
	public static GetBank BuildGetBank() {
		GetBank wsquery=new GetBank();
		wsquery.setHeader(SOAPModels.HeaderDefault());
		return wsquery;	
	}
	//--------------------------------------------------------------------------
	
	
	//Body Template (<soapenv:Body> - </soapenv:Body>)
	//--------------------------------------------------------------------------
	public String BodyXML(String OperationName) {
		return "<blz:"+OperationName+">\r\n" + 
				(blz==null ? "" : " <blz:blz>"+blz+"</blz:blz>\r\n") + 
			   "</blz:"+OperationName+">\r\n" + ""
		;
	}
	//--------------------------------------------------------------------------
	
}
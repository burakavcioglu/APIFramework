package Test.ThomasBayer;

import static Model.ThomasBayer.GetBank.*;
import CoreAPI.APICore;
import CoreAPI.SOAPRequest;
import Model.ThomasBayer.GetBank;
import Model.ThomasBayer.SOAPModels;

public class SparkasseKolnBonnDefault extends APICore{

	@Override
	public void WSSetup() {}

	
	@Override
	public void WSRun() {
		
		//Request
		GetBank getBank = GetBank.BuildGetBank();
		getBank.setsBlz(BankCode);
		
		SOAPModels BLZServiceSOAP11Binding = new SOAPModels();
		BLZServiceSOAP11Binding.setWSBody(getBank.BodyXML(GET_BANK));
		
		
		//Result------------------------------------------------------------------------------
		SOAPRequest soapRequest = SOAPModels.RequestRAWTemplate(BLZServiceSOAP11Binding);
		
		boolean Control = APICore.sendSoapRequest(soapRequest);

		if (!Control) {
			System.out.println("Response Code  : " + soapRequest.getResponseCode());
			System.out.println("Error Message  : " + soapRequest.getErrorMessage());
			System.out.println("WS Output XML  : " + soapRequest.getOutputXML());
		} else {
			System.out.println("Response Code: " + soapRequest.getResponseCode()+"\n\n");
			System.out.println("WS Response Output XML:"+"\n\n"+ soapRequest.getOutputXML());
		}
		//------------------------------------------------------------------------------------
	}

}
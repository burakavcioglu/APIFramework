package Test.ThomasBayer;

import static Model.ThomasBayer.GetBank.*;
import CoreAPI.APICore;
import Model.ThomasBayer.GetBank;
import Model.ThomasBayer.SOAPModels;

public class GetBankNull extends APICore{

	@Override
	public void WSSetup() {}
	
	@Override
	public void WSRun() {
		
		//SERVICE REQUEST
		//-------------------------------------------
		GetBank getBank = GetBank.BuildGetBank();
		getBank.setsBlz("");
		//-------------------------------------------
		
		//GET WS OPERATIONS
		//-------------------------------------------
		SOAPModels.soapResult(getBank.BodyXML(GET_BANK));
		//-------------------------------------------

	}

}
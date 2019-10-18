package Test.ThomasBayer;

import static Model.ThomasBayer.GetBank.*;
import CoreAPI.APICore;
import Model.ThomasBayer.GetBank;
import Model.ThomasBayer.SOAPModels;

public class BundesbankPlzControl extends APICore{

	@Override
	public void WSSetup() {}
	
	@Override
	public void WSRun() {
		
		//SERVICE REQUEST
		//-------------------------------------------
		GetBank getBank = GetBank.BuildGetBank();
		getBank.setsBlz(BUNDES_BANK);
		//-------------------------------------------
		
		//RESPONSE CONTROL
		//-------------------------------------------
		SOAPModels.responseControl(getBank.BodyXML(GET_BANK), "10591");
		//-------------------------------------------

	}
	
}
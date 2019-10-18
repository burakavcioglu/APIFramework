package Rest.Capital;

import CoreAPI.APICore;
import Model.Rest.Capital.CapitalCommonLib;
import Model.Rest.Capital.RESTModels;

public class AnkaraTest3 extends APICore{

	@Override
	public void WSSetup() {}

	@Override
	public void WSRun() {
		

		RESTModels rest = new RESTModels();
		rest.restCallServices(CapitalCommonLib.ANKARA, CapitalCommonLib.REST_NAME_TRANSLATIONS);
		

		
		
		
		
	}

}
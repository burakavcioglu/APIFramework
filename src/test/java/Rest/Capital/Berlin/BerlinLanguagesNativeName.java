package Rest.Capital.Berlin;

import CoreAPI.APICore;
import Model.Rest.Capital.CapitalCommonLib;
import Model.Rest.Capital.RESTModels;

public class BerlinLanguagesNativeName extends APICore{

	@Override
	public void WSSetup() {}

	@Override
	public void WSRun() {
		

		RESTModels rest = new RESTModels();
		rest.restCallServices(CapitalCommonLib.BERLIN, CapitalCommonLib.REST_BERLIN_FIELDS_SIMPLE_1, "Deutsch");
				
	}

}
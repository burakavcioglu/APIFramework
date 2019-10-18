package Rest.Capital.Ankara.Languages;

import CoreAPI.APICore;
import Model.Rest.Capital.CapitalCommonLib;
import Model.Rest.Capital.RESTModels;

public class AnkaraIso6391Control extends APICore{

	@Override
	public void WSSetup() {}

	@Override
	public void WSRun() {
		

		RESTModels rest = new RESTModels();
		rest.restCallServices(CapitalCommonLib.ANKARA, CapitalCommonLib.REST_NAME_LANGUAGES, "tr");
		

		
	}

}
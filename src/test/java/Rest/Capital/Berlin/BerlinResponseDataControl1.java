package Rest.Capital.Berlin;

import CoreAPI.APICore;
import static Model.Rest.Capital.CapitalCommonLib.*;

import java.util.ArrayList;

public class BerlinResponseDataControl1 extends APICore{

	@Override
	public void WSSetup() {}

	@Override
	public void WSRun() {
		
		ArrayList<String> responseValue = new ArrayList<String>();
		responseValue.add("Deutsch");
		responseValue.add("European Union");
		responseValue.add("German");
		responseValue.add("Euro");
	    

	    for (int i = 0; i < responseValue.size(); i++) {
			restResponseControl(REST_CAPITAL_SAMPLE_URI_1, null, responseValue.get(i));
	    }
		
	}

}
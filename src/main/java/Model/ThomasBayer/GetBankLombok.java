package Model.ThomasBayer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetBankLombok {
	
	@Getter @Setter String blz;
	public String bezeichnung;
	public String bic;
	public String ort;
    public String plz;
	
    

}
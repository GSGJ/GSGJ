package function;

import java.io.Serializable;
import java.util.Date;

public class VenueInfoTable implements Serializable {
	public VenueTable venueTable = null;
	public Date dt = null;
	
	
	public void setData(VenueTable venue){
		this.venueTable = venue;
		dt = new Date();
	}
	
	
}

package org.SBPSWar.util;

public class BillStatusUtility {
	
	public static String getStringStatus(Integer status){
		
		switch(status.intValue()){
		
		case 1:
			
			return "Assigned";
			
		case 2:
			
			return "Approved";
		
		case 3:
			
			return "Funds Transferred";
		case 4:
			
			return "Disputed";
		default:
			
			return " No Status";
			
		}
	}

}

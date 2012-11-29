package org.SBPSWar.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SBPSFormAndBeanUtility {
	
	public static boolean validateString (String s){
		boolean valid =true;
		
		if(s!=null){
			
			if(s.length()<=0 || s.trim().equals("")){
				valid = false;
			}
		}else{
			
			valid = false;
		}
		
		return valid;
			
	}

	public static boolean validateLength(String s){
		boolean valid =true;
		
		if(s.trim().length()< 6){
			
			valid = false;
	
		}else{
			
			valid = true;
		}
		
		return valid;
		
	}
	public static boolean validateEmail(String s){
		boolean valid =true;
		
		
		if(!s.trim().contains("@") || !s.trim().contains(".")){
			
			valid = false;
	
		}else{
			
			valid = true;
		}
		
		return valid;
		
	}

	public static boolean validateNumeric(Double amount) {
		boolean valid =true;
		
		if(amount==null){
			valid = false;
		}else if(amount <=0){
			valid = false;
		}
		
		return valid;
	}

	public static boolean validateDate(Date issueDate) {
		
		boolean valid =true;
		
		if(issueDate==null){
			valid = false;
		}
		
		return valid;
	}
	
	public static boolean validateDates(Date start, Date end){
		
		boolean valid =true;
		
		//end has to be after start
		if(end.before(start)){
			valid = false;
		}
		
		return valid;
	}
	
	public static Date truncateDate(Date longDate){
		
		if (longDate!=null) {
			DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
			String formattedDate = df.format(longDate);
			Date issueDate = new Date(formattedDate);
			return issueDate;
		}
		return longDate;
	}
}

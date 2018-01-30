package com.util;

public class TaxCalculation {
	
	public static double TaxCal(int s){
		 int d=0;  
		 double tax=0,r=0;  
		  
		    if(s<=3500){r=0;d=0;}  
		    else  
		        if(s>3500&s<=5000){r=0.03;d=0;}  
		        else  
		            if(5000<s&&s<=8000){r=0.10;d=105;}  
		            else  
		                if(8000<s&&s<=12500){r=0.20;  
		                d=555;  
		                }  
		                else  
		                    if(12500<s&&s<=38500){r=0.25;d=1005;}  
		                    else  
		                        if(38500<s&&s<=58500){ r=0.30;d=2755;}  
		                        else  
		                            if(58500<s&&s<=83500){r=0.35;d=5505;}  
		                            else  
		                                if(83500<s){r=0.45;d=13505;}  
		      tax=r*(s-3500)-d;  
		      return tax;
		      
	}
	
}

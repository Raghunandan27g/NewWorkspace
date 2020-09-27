package com.in.commons.util;

import com.in.commons.dao.UserTableDetailsModel;

public class SimpleGenerationUtilities {
	
	public static String ulBasicDesign(UserTableDetailsModel userTabObj) {
		return "" + 
				"			<li><a href=\""+userTabObj.getTxtSection()+"/"+userTabObj.getTxtUrl()+"\" target=\"_blank\">"+ userTabObj.getTxtName()+" </a></li>\r\n" + 
				"";
	}
	public static String oneFileliDesign(UserTableDetailsModel userTabObj) {
		return "<li style=\"display: none;\">$"+userTabObj.getTxtSection()+"_One$</li>"
				+ ulBasicDesign(userTabObj);
	}
}

package com.in.commons.util;

import com.in.commons.dao.UserTableDetailsModel;

public class SimpleGenerationUtilities {
	
	public static String ulBasicDesign(UserTableDetailsModel userTabObj) {
		return "" + 
				"			<li><a href=\""+userTabObj.getTxtSection()+"/"+userTabObj.getTxtUrl()+"\" target=\"_blank\">"+ userTabObj.getTxtName()+" </a></li>\r\n" + 
				"";
	}
	public static String oneFileliDesign() {
		return "<li style=\"display: none;\">$admitCard_One$</li>"
				+ "<li>New Row</li>";
	}
}

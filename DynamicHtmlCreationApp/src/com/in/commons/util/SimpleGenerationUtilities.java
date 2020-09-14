package com.in.commons.util;

import com.in.commons.dao.UserTableDetailsModel;

public class SimpleGenerationUtilities {
	
	public static String ulBasicDesign(UserTableDetailsModel userTabObj) {
		return "<ul>" + 
				"			<li><a href=\""+userTabObj.getSection()+"/"+userTabObj.getURL()+"\" target=\"_blank\">"+ userTabObj.getName()+" </a></li>\r\n" + 
				"	</ul>\n";
	}
	
}

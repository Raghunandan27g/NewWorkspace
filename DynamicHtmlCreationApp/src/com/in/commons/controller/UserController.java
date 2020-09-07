package com.in.commons.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import com.in.commons.util.UserConstants;
import com.in.commons.util.fetchDataFromTable;
import com.in.commons.util.fileGeneration;

public class UserController {
	
	public static boolean stepwiseGenerationController(HttpServletRequest request) {
		Connection conn=null;
		boolean statusFlag=false;
		try {
			conn=DbResource.getConnection();
			String generationType=request.getParameter("generationType");
			System.out.println(generationType);
			if(!(generationType.equals(null) || generationType.equals(""))){
				if(generationType.equals(UserConstants.COMPLETE_GENERATION)) {
					
				}
				else if(generationType.equals(UserConstants.FLAG_FILES_GENERATION )) {
					
				}
				else if(generationType.equals(UserConstants.HOMEPAGE_GENERATION)) {
					Map userTabMap=fetchDataFromTable.DetailsTableFetch(conn);
					
					fileGeneration.indexTemplateFileManipulate(UserConstants.FILE_PATH,userTabMap);
//					for(int i=1;i<=userTabMap.size();i++) {
//						System.out.println(userTabMap.get(i).toString());
//					}
				}
				else if(generationType.equals(UserConstants.NEW_FILE_GENERATION)) {
					//Insert
				}
				else if(generationType.equals(UserConstants.EDIT_FILE)) {
					//Update
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return statusFlag;
	}
}

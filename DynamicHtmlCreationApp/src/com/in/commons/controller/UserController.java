package com.in.commons.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.commons.config.DAOConfig;
import com.in.commons.dao.UserTableDetailsModel;
import com.in.commons.util.UserConstants;
import com.in.commons.util.CRUDOperationOnTable;
import com.in.commons.util.fileGeneration;


public class UserController {
	
	public static Map stepwiseGenerationController(HttpServletRequest request) {
		Connection conn=null;
		boolean statusFlag=false;
		Map returningMap=null;
		try {
			
			conn=DbResource.getConnection();
			ObjectMapper objMap=new ObjectMapper();
	        UserTableDetailsModel obj=DAOConfig.getValueFromRequestConvertToPOJO(request);
	        Map mapOfQueries=DAOConfig.createQueries(obj);
			String generationType=request.getParameter(UserConstants.GENERATION_TYPE);
			System.out.println(generationType);
			if(!(generationType.equals(null) || generationType.equals(""))){
				if(generationType.equals(UserConstants.HOMEPAGE_GENERATION)) {
					
				}
				else if(generationType.equals(UserConstants.FLAG_FILES_GENERATION )) {
					
				}
				else if(generationType.equals(UserConstants.COMPLETE_GENERATION)) {
					Map userTabMap=CRUDOperationOnTable.DetailsTableFetch(conn,request,mapOfQueries);
//					JSONObject json=new JSONObject(userTabMap);
//					System.out.println(json);
					fileGeneration.indexTemplateFileManipulate(UserConstants.FILE_PATH,userTabMap);
				}
				else if(generationType.equals(UserConstants.NEW_FILE_GENERATION)) {
					//Insert
					boolean insertStatus=CRUDOperationOnTable.DetailsTableInsert(conn, mapOfQueries);
					if(insertStatus)
						System.out.println("Insert Successful");
//						use of top li will be done for both index as well as single file
					else
						throw new SQLException("Insert Failed");
				}
				else if(generationType.equals(UserConstants.EDIT_FILE)) {
					//Update
					String whereCond=DAOConfig.whereClauseCondition(request);
					boolean updateStatus=CRUDOperationOnTable.DetailsTableUpdate(conn, request, mapOfQueries, whereCond);
					if(updateStatus) {
						returningMap=CRUDOperationOnTable.DetailsTableFetch(conn,request,mapOfQueries);
//						no change in index file. Only singlePageTemplateFileManipulate
						
					}
					else
						throw new SQLException("Update Failed");
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
		return returningMap;
	}
	
	
}


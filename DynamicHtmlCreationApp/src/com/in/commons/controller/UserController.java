package com.in.commons.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.commons.config.DAOConfig;
import com.in.commons.dao.UserTableDetailsModel;
import com.in.commons.util.UserConstants;
import com.in.commons.util.CRUDOperationOnTable;
import com.in.commons.util.ImageFileCustom;
import com.in.commons.util.fileGeneration;


public class UserController {
	public static Map stepwiseGenerationController(HttpServletRequest request) {
		Connection conn=null;
		boolean statusFlag=false;
		Map returningMap=new HashMap();
		try {
			
			conn=DbResource.getConnection();
			
			ObjectMapper objMap=new ObjectMapper();
	        UserTableDetailsModel objUserTab=DAOConfig.getValueFromRequestConvertToPOJO(request);
	        //Image Upload Code here.
	        String objImageString = request.getParameter("imgBlobData");
	        if(objImageString!=null && !objImageString.isEmpty()){
	        	System.out.println("inside image");
				Map map = objMap.readValue(objImageString, Map.class);
				ImageFileCustom.storeImageOnServerMap(map,objUserTab);
	        }
			
	        Map mapOfQueries=DAOConfig.createQueries(objUserTab);
	        
			String generationType=request.getParameter(UserConstants.GENERATION_TYPE);
			System.out.println(generationType);
			if(!(generationType.equals(null) || generationType.equals(""))){
				if(generationType.equals(UserConstants.HOMEPAGE_GENERATION)) {
					boolean completeGenFlag=false;
					Map userTabMap=CRUDOperationOnTable.DetailsTableFetch(conn,request,mapOfQueries);
					returningMap.put(UserConstants.HOMEPAGE_STATUS_KEY,fileGeneration.homePageFileManipulate(UserConstants.FILE_PATH,userTabMap,completeGenFlag));
				}
				else if(generationType.equals(UserConstants.FLAG_FILES_GENERATION )) {
					
				}
				else if(generationType.equals(UserConstants.COMPLETE_GENERATION)) {
					boolean completeGenFlag=true;
					Map userTabMap=CRUDOperationOnTable.DetailsTableFetch(conn,request,mapOfQueries);
//					JSONObject json=new JSONObject(userTabMap);
//					System.out.println(json);
					returningMap.put(UserConstants.COMPLETE_STATUS_KEY,fileGeneration.homePageFileManipulate(UserConstants.FILE_PATH,userTabMap,completeGenFlag));
				}
				else if(generationType.equals(UserConstants.NEW_FILE_GENERATION)) {
					//Insert
					boolean homePageInsertStatus=false;
					boolean insertStatus=CRUDOperationOnTable.DetailsTableInsert(conn, mapOfQueries);
					if(insertStatus) {
						System.out.println("Insert Successfully in DB");
						homePageInsertStatus=fileGeneration.insertFileManipulateHome(UserConstants.FILE_PATH, objUserTab);
						if(homePageInsertStatus) {
							returningMap.put(UserConstants.INSERT_STATUS_KEY,fileGeneration.multiPageTemplateFileManipulate(UserConstants.FILE_PATH, objUserTab));
							returningMap.put(UserConstants.FETCH_COMPLETE_DATA, CRUDOperationOnTable.DetailsTableFetch(conn, request, mapOfQueries));
						}
						else {
							returningMap.put(UserConstants.INSERT_STATUS_KEY,false);
							throw new SQLException("Home page does not exist");
						}
					}
					else {
						returningMap.put(UserConstants.INSERT_STATUS_KEY,false);
						throw new SQLException("Insert Failed");
					}
				}
				else if(generationType.equals(UserConstants.EDIT_FILE)) {
//					Update
					String whereCond=DAOConfig.whereClauseCondition(request);
					boolean updateStatus=CRUDOperationOnTable.DetailsTableUpdate(conn, request, mapOfQueries, whereCond);
					if(updateStatus) {
						returningMap.put(UserConstants.UPDATE_STATUS_KEY, fileGeneration.multiPageTemplateFileManipulate(UserConstants.FILE_PATH, objUserTab));
						returningMap.put(UserConstants.FETCH_COMPLETE_DATA, CRUDOperationOnTable.DetailsTableFetch(conn, request, mapOfQueries));
//						no change in index file. Only singlePageTemplateFileManipulate
					}
					else {
						returningMap.put(UserConstants.UPDATE_STATUS_KEY,false);
						throw new SQLException("Update Failed");
					}
				}
				else if(generationType.equals(UserConstants.FETCH_COMPLETE_DATA)) {
//					mapOfQueries.put(UserConstants.SELECT_QUERY,mapOfQueries.get(UserConstants.VIEW_SELECT_QUERY));
					returningMap.put(UserConstants.FETCH_COMPLETE_DATA, CRUDOperationOnTable.DetailsTableFetch(conn, request, mapOfQueries));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return returningMap;
	}
}


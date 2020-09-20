package com.in.commons.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.commons.dao.UserTableDetailsModel;
import com.in.commons.util.UserConstants;

public class DAOConfig {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static UserTableDetailsModel getValueFromRequestConvertToPOJO(HttpServletRequest request) {
        UserTableDetailsModel pojo=null;
		try {
			Map<String, String[]> allMap = request.getParameterMap();
			Map mapToConvert=new HashMap();
			StringBuilder mapString=null;
			for (String key : allMap.keySet()) {
			    String[] strArr = allMap.get(key);
			    mapString=new StringBuilder();
			    for( int i = 0; i < strArr.length; i++)
			    {
			    	if(i!=0) {
			    		mapString.append(",");
			    	}
			        String element = strArr[i].replace("<", "").replace(">", "");
			        System.out.println(key+":"+mapString.append(element));
			    }
			    mapToConvert.put(key,mapString);
			}
			ObjectMapper newObj=new ObjectMapper();
			pojo=newObj.convertValue(mapToConvert, UserTableDetailsModel.class);
//		Same can be achieved via @JsonIgnoreProperties(ignoreUnknown = true)
//		newObj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pojo;
	}
	@SuppressWarnings("unchecked")
	public static Map createQueries(UserTableDetailsModel pojo) {
		Map crudMap=null;
		int count=0;
		StringBuilder updateString=new StringBuilder();
		StringBuilder insertString=new StringBuilder();
		StringBuilder insertStringColumns=new StringBuilder();
		StringBuilder insertStringValues=new StringBuilder();
		StringBuilder selectString=new StringBuilder();
		ObjectMapper objMap=new ObjectMapper();
			try {
				String jsonStr = objMap.writeValueAsString(pojo);
				crudMap=objMap.readValue(jsonStr, Map.class);
				insertStringColumns.append("(");
				insertStringValues.append("(");
				for (Object key : crudMap.keySet()) {
					if(count!=0) {
						updateString.append(",");
						insertStringColumns.append(",");
						insertStringValues.append(",");
						selectString.append(",");
			    	}
					updateString.append(key+"='"+crudMap.get(key)+"'");
					insertStringColumns.append(key);
					insertStringValues.append("'"+crudMap.get(key)+"'");
					selectString.append(key);
					count++;
				}
				insertStringColumns.append(")");
				insertStringValues.append(")");
				insertString.append(insertStringColumns+" values "+insertStringValues);
				crudMap.put(UserConstants.UPDATE_QUERY, updateString);
				crudMap.put(UserConstants.INSERT_QUERY, insertString);
				crudMap.put(UserConstants.SELECT_QUERY, selectString);
			} catch (Exception e) {
				// TODO: handle exception
			}
		return crudMap;
	}
	public static String whereClauseCondition(HttpServletRequest request) {
		return "where "+UserConstants.WHERE_CONDITION+"='"+request.getParameter(UserConstants.WHERE_CONDITION)+"'";
	}
}

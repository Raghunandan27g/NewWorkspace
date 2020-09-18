package com.in.commons.config;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.in.commons.dao.UserTableDetailsModel;

public class DAOConfig {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static UserTableDetailsModel getValueFromRequestConvertToPOJO(HttpServletRequest request) {
        Map<String, String[]> allMap = request.getParameterMap();
        Map mapToConvert=new HashMap();
        StringBuilder mapString=null;
        UserTableDetailsModel pojo=null;
        for (String key : allMap.keySet()) {
            String[] strArr = allMap.get(key);
            mapString=new StringBuilder();
            for( int i = 0; i < strArr.length; i++)
            {
            	if(i!=0) {
            		mapString.append(",");
            	}
                String element = strArr[i];
                System.out.println(key+":"+mapString.append(element));
            }
            mapToConvert.put(key,mapString);
        }
        
		ObjectMapper newObj=new ObjectMapper();
		pojo=newObj.convertValue(mapToConvert, UserTableDetailsModel.class);
//		Same can be achieved via @JsonIgnoreProperties(ignoreUnknown = true)
//		newObj.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		return pojo;
	}
}

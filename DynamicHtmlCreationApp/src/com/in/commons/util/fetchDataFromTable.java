package com.in.commons.util;

import java.sql.*;
import java.util.Map;

import org.apache.commons.collections4.*;
import org.apache.commons.collections4.map.MultiValueMap;
import org.json.JSONObject;

import com.in.commons.dao.UserTableDetailsModel;

public class fetchDataFromTable {
	
	@SuppressWarnings({ "unchecked", "deprecation" })
	public static Map DetailsTableFetch(Connection conn) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserTableDetailsModel userTabObj=null;

		MultiMap multiMap = new MultiValueMap();

		try {
			String query="select * from details order by section";
			ps=conn.prepareStatement(query);
			//ps.setInt(parameterIndex, x);
			rs=ps.executeQuery();
			while(rs.next()) {
				userTabObj=new UserTableDetailsModel();
				
				userTabObj.setSno(rs.getInt("SNo"));
				userTabObj.setName(rs.getString("Name"));
				userTabObj.setKeyword(rs.getString("Keyword"));
				userTabObj.setURL(rs.getString("URL"));
				userTabObj.setSection(rs.getString("section"));
				userTabObj.setContent(rs.getString("content"));
				
				multiMap.put(rs.getString("section"),userTabObj);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 	multiMap;
	}
}

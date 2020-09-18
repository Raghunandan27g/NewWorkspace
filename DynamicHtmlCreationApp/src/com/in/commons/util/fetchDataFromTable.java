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
			String query="select * from details order by created_at desc";
			ps=conn.prepareStatement(query);
			//ps.setInt(parameterIndex, x);
			rs=ps.executeQuery();
			while(rs.next()) {
				userTabObj=new UserTableDetailsModel();
				
				userTabObj.setTxtName(rs.getString("Name"));
				userTabObj.setTxtKeyword(rs.getString("Keyword"));
				userTabObj.setTxtUrl(rs.getString("URL"));
				userTabObj.setTxtSection(rs.getString("section"));
				userTabObj.setTxtContent(rs.getString("content"));
				
				multiMap.put(rs.getString("section"),userTabObj);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 	multiMap;
	}
}

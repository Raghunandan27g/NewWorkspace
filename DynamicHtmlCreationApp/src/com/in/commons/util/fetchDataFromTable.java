package com.in.commons.util;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import com.in.commons.dao.UserTableDetailsModel;

public class fetchDataFromTable {
	
	public static Map DetailsTableFetch(Connection conn) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserTableDetailsModel userTabObj=null;
		Map userTabMap=new HashMap<Integer,UserTableDetailsModel>();;
		
		try {
			String query="select * from details order by sno desc";
			ps=conn.prepareStatement(query);
			//ps.setInt(parameterIndex, x);
			rs=ps.executeQuery();
			while(rs.next()) {
				userTabObj=new UserTableDetailsModel();
				userTabObj.setSno(rs.getInt("SNo"));
				userTabObj.setName(rs.getString("Name"));
				userTabObj.setKeyword(rs.getString("Keyword"));
				userTabObj.setURL(rs.getString("URL"));
				
				userTabMap.put(rs.getInt("SNo"), userTabObj);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 	userTabMap;
	}
}

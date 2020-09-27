package com.in.commons.util;

import java.lang.reflect.*;
import java.sql.*;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections4.*;
import org.apache.commons.collections4.map.MultiValueMap;
import org.json.JSONObject;

import com.in.commons.dao.UserTableDetailsModel;

public class CRUDOperationOnTable	{
	
	@SuppressWarnings({ "unchecked", "deprecation", "rawtypes" })
	public static Map DetailsTableFetch(Connection conn,HttpServletRequest request,Map mapOfQueries) {
		PreparedStatement ps=null;
		ResultSet rs=null;
		UserTableDetailsModel userTabObj=null;
		String[] columnArr = (mapOfQueries.get(UserConstants.SELECT_QUERY).toString()).split(","); 
		MultiMap multiMap = new MultiValueMap();

		try {
			String query="select "+mapOfQueries.get(UserConstants.SELECT_QUERY)+" from "+UserConstants.TABLE_NAME+" order by created_at desc";
			ps=conn.prepareStatement(query);
			System.out.println(ps);
			rs=ps.executeQuery();
			while(rs.next()) {
				userTabObj=new UserTableDetailsModel();
				for (String column:columnArr) {
					Method m=UserTableDetailsModel.class.getMethod("set"+column.substring(0, 1).toUpperCase() + column.substring(1),UserTableDetailsModel.class.getDeclaredField(column).getType());
					m.invoke(userTabObj, rs.getString(column));
//					Field field = UserTableDetailsModel.class.getDeclaredField(column);
//					field.setAccessible(true); 
//			        field.set(userTabObj, rs.getString(column)); 
				}
				multiMap.put(rs.getString(UserConstants.MAP_COLUMN_KEY),userTabObj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return 	multiMap;
	}
	public static boolean DetailsTableUpdate(Connection conn,HttpServletRequest request,Map mapOfQueries,String whereCond) {
		PreparedStatement ps=null;
		int rowsEffected=0;
		try {
			String query="update "+UserConstants.TABLE_NAME+" set "+mapOfQueries.get(UserConstants.UPDATE_QUERY)+" "+whereCond;
			ps=conn.prepareStatement(query);
			System.out.println(ps);
			rowsEffected=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (rowsEffected>0) ? true:false;
	}
	public static boolean DetailsTableInsert(Connection conn,Map mapOfQueries) {
		PreparedStatement ps=null;
		int rowsEffected=0;
		try {
			String query="insert into "+UserConstants.TABLE_NAME+" "+mapOfQueries.get(UserConstants.INSERT_QUERY);
			ps=conn.prepareStatement(query);
			System.out.println(ps);
			rowsEffected=ps.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		finally {
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return (rowsEffected>0) ? true:false;
	}
}

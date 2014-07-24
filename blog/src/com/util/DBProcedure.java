package com.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 * 存储过程
 * @author Cloud
 * */
public class DBProcedure {

	//log
	private static Logger log = Logger.getLogger(DBHandle.class);
	
	Connection conn = null;
	
	public DBProcedure(DBHandle db){
		this.conn = db.getConn();
	}
	
	public String[] returnOption(String sql){
		String[] call = null;
		try {
			CallableStatement cs = conn.prepareCall(sql);
			ResultSet rs = cs.executeQuery(sql);
			call = new String[30];
			while(rs.next()){
				for(int i = 0; i < call.length; i++){
					call[i] = rs.getString(i+1);
				}
			}
		}catch(Exception e){
			log.error("DBProcedure.returnOption 发生异常:"+e.getMessage());
		}
		return call;
	}
	
	public String[] returnInstituteOptionAttitude(String sw_institute_id){
		return returnOption("CALL institute_opinion_attitude('"+sw_institute_id+"')");
	}
	
	public String[] returnInstituteOptionTechnic(String sw_institute_id){
		return returnOption("CALL institute_opinion_technic('"+sw_institute_id+"')");
	}
	
	public String[] returnSalonOptionAttitude(String sw_salon_id){
		return returnOption("CALL salon_opinion_attitude('"+sw_salon_id+"')");
	}
	
	public String[] returnSalonOptionTechnic(String sw_salon_id){
		return returnOption("CALL salon_opinion_technic('"+sw_salon_id+"')");
	}
	
	public void changeServiceSort(String sw_sort1,String sw_sort2,String sw_type_id) throws SQLException{
		CallableStatement cs = conn.prepareCall("CALL exchangeservicesort("+sw_sort1+","+sw_sort2+",'"+sw_type_id+"')");
		cs.executeUpdate();
	}
	
	public void changeServiceTypeSort(String sw_sort1,String sw_sort2,String sw_salon_id) throws SQLException{
		CallableStatement cs = conn.prepareCall("CALL exchangeservicetypesort("+sw_sort1+","+sw_sort2+",'"+sw_salon_id+"')");
		cs.executeUpdate();
	}
}

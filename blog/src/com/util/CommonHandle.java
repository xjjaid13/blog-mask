package com.util;

public class CommonHandle {

	/**
	 * 获得页数
	 * @param recordSum 记录总数
	 * @param recordNum 每页记录数
	 * @return 页数
	 */
	public static int returnPageCount(int recordSum,int recordNum){
		if(recordSum % recordNum == 0){
			return recordSum / recordNum;
		}else{
			return recordSum / recordNum + 1;
		}
	}
	
}

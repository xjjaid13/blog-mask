package com.util;

public class SqlHandle {

	public char openQuote() {
		return '[';
	}

	public char closeQuote() {
		return ']';
	}

	private static String removeSelectOrderBy(String sql) {
		int orderByIndex = sql.toLowerCase().lastIndexOf("order by");
		if (orderByIndex > 0) {
			//LogWriter.debug("[dblib] removeSelectOrderBy :" + sql,FormatSQL.class);
			return sql.substring(0, orderByIndex);
		}
		return sql;
	}

	public static String getCountSql(String sql) {

		int formIndex = sql.toLowerCase().lastIndexOf(" from ");
		String tmpSql = "";
		if (formIndex > 0) {
			//LogWriter.debug("[dblib] getCountSql :" + sql, FormatSQL.class);
			tmpSql += "SELECT COUNT(*) ";
			tmpSql += sql.substring(formIndex, sql.length()).trim();
		}
		return tmpSql;
	}

	/**
	 * 根据SQL返回查询总记录数的语句
	 * 
	 * @param sql
	 * @param f
	 *            boolean f true=组织sql方式 / false=临时表方式
	 * @return
	 */
	public static String createTotalCountSql(String sql, boolean f) {

		// 可以过滤掉查询语句中的order by
		sql = removeSelectOrderBy(sql);
		if (f) {
			// LogWriter.debug("[dblib] createTotalCountSql :" +
			// getCountSql(sql),
			// FormatSQL.class);
			return getCountSql(sql);
		} else {
			// 如果需要可以根据不同数据库来组织不同SQL
			StringBuffer strBuf = new StringBuffer();
			strBuf.append("SELECT COUNT(*) FROM ( ");
			strBuf.append(sql);
			strBuf.append(") TMP_");
			return strBuf.toString();
		}
	}

	public static String createTotalCountSql(String sql) {
		return createTotalCountSql(sql, true);
	}

	/**
	 * 分页查询返回指定位置和数量的结果集(oracle)
	 * 
	 * @param sql
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public static String createOraclePagingSql(String sql, int pageSize,
			int pageIndex) {
		int m = (pageIndex - 1) * pageSize;
		int n = m + pageSize;
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer strBuf = new StringBuffer(sql.length() + 100);
		strBuf.append("SELECT * FROM ( SELECT ROW_.*, ROWNUM rn_ FROM ( ");
		strBuf.append(sql);
		strBuf.append(" ) ROW_ WHERE ROWNUM <= ");
		strBuf.append(n);
		strBuf.append(") WHERE rn_ > ");
		strBuf.append(m);
		if (isForUpdate) {
			strBuf.append(" for update");
		}
		return strBuf.toString();
	}
	
	/**
	 * 分页查询返回指定位置和数量的结果集(oracle)
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static String createOraclePagingSql_SE(String sql, int start,
			int end) {
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update")) {
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer strBuf = new StringBuffer(sql.length() + 100);
		int indexFrom = sql.indexOf("FROM");
		if(indexFrom < 0){
			return sql;
		}
		String param = sql.substring(6, indexFrom).replaceAll("a\\.", "ROW_.")
							.replaceAll("b\\.", "ROW_.");
		strBuf.append("SELECT "+param+" FROM (SELECT ROW_.*,ROWNUM rn_ FROM ( ");
		strBuf.append("SELECT * FROM "+sql.substring(indexFrom+4,sql.length()));
		strBuf.append(" ) ROW_ WHERE ROWNUM <= ");
		strBuf.append(end);
		strBuf.append(") ROW_ WHERE rn_ > ");
		strBuf.append(start);
		if (isForUpdate) {
			strBuf.append(" for update");
		}
		return strBuf.toString();
	}
	/**
	 * 分页查询返回指定位置和数量的结果集(mysql)
	 * 
	 * @param sql
	 * @param pageSize
	 * @param pageIndex
	 * @return
	 */
	public static String createMysqlPagingSql(String sql, int pageSize,
			int pageIndex) {
		sql = sql.trim();
		int m = (pageIndex - 1) * pageSize;
		return sql + " LIMIT " + m + "," + pageSize;
	}
	/**
	 * 分页查询返回指定位置和数量的结果集(mysql)
	 * 
	 * @param sql
	 * @param start
	 * @param end
	 * @return
	 */
	public static String createMysqlPagingSql_SE(String sql, int start,
			int end) {
		sql = sql.trim();
		return sql + " LIMIT " + start + "," + (end - start );
	}

	@SuppressWarnings("unused")
	private static boolean hasDistinct(String sql) {
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	@SuppressWarnings("unused")
	private static String getRowNumber(String sql) {
		StringBuffer rownumber = new StringBuffer(50)
				.append("ROWNUMBER() OVER(");
		rownumber.append(") AS rn_ ");
		return rownumber.toString();
	}
	
}

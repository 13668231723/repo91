package com.yc.bbs.dao;

import java.sql.SQLException;
import java.util.List;

import com.yc.common.util.DBHelper;

public class BoardDao {
	
	public List<?> query(){
		
		String sql = "SELECT a.*,b.*,c.title,\n" +
				"c.uid,c.publishtime\n" +
				" FROM tbl_board a \n" +
				"LEFT JOIN\n" +
				"(SELECT boardid,COUNT(*) cnt\n" +
				" FROM tbl_topic \n" +
				"GROUP BY \n" +
				"boardid) b ON a.id = b.boardid\n" +
				"LEFT JOIN(\n" +
				"SELECT\n" +
				"*\n" +
				"FROM\n" +
				"tbl_topic a\n" +
				"WHERE\n" +
				"a.id in(\n" +
				"SELECT\n" +
				"max(id)\n" +
				"FROM\n" +
				"tbl_topic\n" +
				"GROUP BY\n" +
				"boardid\n" +
				")\n" +
				")c ON a.id = c.boardid";
		try {
			return DBHelper.selectListMap(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}

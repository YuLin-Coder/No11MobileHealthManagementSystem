package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
 
import com.myweb.domain.NewsType;
import com.myweb.utils.DBManager;

public class NewsTypeDao {
	
	 
	public void addNewsType(NewsType newsType) {
	 
		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into NEWSTYPE (TYPENAME,CREATEUSER) values (?, ?)";

		List<Object> params = new ArrayList<Object>(); 

		params.add(newsType.getTypename());

		params.add(newsType.getCreateuser());
	 	
		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

		 

		} catch (SQLException e) {

			e.printStackTrace();
		}
		 
	}

	 
	public List<Map<String, Object>> getNewsTypeList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select * from NEWSTYPE where id!=-1  order by CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}
	
	 
	public NewsType getNewsTypeById(String id) {

		NewsType newsType = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from NewsType where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			newsType = (NewsType) db.findSimpleRefResult(sql, list,
					NewsType.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return newsType;
	}

	
	public void delNewsTypeById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from NEWSTYPE where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

			 
		} catch (SQLException e) {

			e.printStackTrace();
		}

		
	}
	
	 
	public boolean updateNewsType(NewsType newsType) {

		boolean flag=false;
		
		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update NEWSTYPE  set  TYPENAME=?  where id="+String.valueOf(newsType.getId());

		List<Object> params = new ArrayList<Object>();
        
		params.add(newsType.getTypename());		 
		 
	 	
		try {
		    flag = db.updateByPreparedStatement(sql, params);

		 

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
}

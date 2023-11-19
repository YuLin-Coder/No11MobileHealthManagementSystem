package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Admin;
import com.myweb.domain.News2;
import com.myweb.utils.DBManager;

public class News2Dao {
	// 添加新闻
	public int addNews2(News2 news) {

		int id = 0;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into NEWS2 (ID, TITLE,IMGPATH,CONTENT,CREATEUSER,CREATETIME) values (?, ?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		id = db.getMaxId("NEWS2");

		params.add(id);

		params.add(news.getTitle());

		params.add(news.getImgpath());

		params.add(news.getContent());

		params.add(news.getCreateuser());

		params.add(news.getCreatetime());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id;
	}

	// 修改新闻
	public boolean updateNews2(News2 news) {

		boolean flag=false;
		
		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update NEWS2  set  TITLE=?,IMGPATH=?,CONTENT=? where id="+String.valueOf(news.getId());

		List<Object> params = new ArrayList<Object>();
        
		params.add(news.getTitle());
		 
		params.add(news.getImgpath());
		
		params.add(news.getContent());

		try {
		    flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}

	
	// 查询所有新闻公告
	public List<Map<String, Object>> getNews2List() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select * from NEWS2  order by CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	// 删除新闻
	public void delNews2ById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from News2 where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	// 查询新闻
	public News2 getNews2ById(String id) {

		News2 news = null;
		
		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from News2 where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			news = (News2) db.findSimpleRefResult(sql, list, News2.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return news;
	}
}

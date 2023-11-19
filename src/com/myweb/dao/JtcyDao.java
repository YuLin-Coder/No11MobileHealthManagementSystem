package com.myweb.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.myweb.domain.Jtcy;
import com.myweb.utils.DBManager;

public class JtcyDao {

	public void addJtcy(Jtcy jtcy) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into JTCY ( chenghu,xingming,xinbie,nianlin,userid) values (?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		params.add(jtcy.getChenghu());

		params.add(jtcy.getXingming());

		params.add(jtcy.getXinbie());

		params.add(jtcy.getNianlin());

		params.add(jtcy.getUserid());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Map<String, Object>> getJtcyList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select t1.id, t2.username,t1.chenghu,t1.xingming,t1.xinbie,t1.nianlin,t1.userid from JTCY t1,user t2 where t1.userid=t2.id  order by t1.id desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}


	public List<Map<String, Object>> getMyJtcyList(String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select * from JTCY  where userid='"+userid+"' order by id desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}
	public Jtcy getJtcyById(String id) {

		Jtcy jtcy = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from jtcy where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			jtcy = (Jtcy) db.findSimpleRefResult(sql, list, Jtcy.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return jtcy;
	}

	public void delJtcyById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from JTCY where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean updateJtcy(Jtcy jtcy) {

		boolean flag = false;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update jtcy  set  chenghu=?,xingming=? ,xinbie=?,nianlin=? where id="
				+ String.valueOf(jtcy.getId());

		List<Object> params = new ArrayList<Object>();

		params.add(jtcy.getChenghu());

		params.add(jtcy.getXingming());

		params.add(jtcy.getXinbie());

		params.add(jtcy.getNianlin());

		try {
			flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
}

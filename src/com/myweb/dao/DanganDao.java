package com.myweb.dao;

import com.myweb.domain.Dangan;
import com.myweb.utils.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DanganDao {

	public void addDangan(Dangan dangan) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into DANGAN ( tizhong,xinming,xueya,xinlu,cyid,userid,riqi) values (?, ?, ?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		params.add(dangan.getTizhong());

		params.add(dangan.getXinming());

		params.add(dangan.getXueya());

		params.add(dangan.getXinlu());

		params.add(dangan.getCyid());

		params.add(dangan.getUserid());

		params.add(dangan.getRiqi());

		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public List<Map<String, Object>> getDanganList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TIZHONG,T2.XINGMING ,T1.XUEYA,T1.XINLU,T1.RIQI,T1.CYID,T1.USERID from DANGAN t1,JTCY T2 where  T1.CYID=T2.ID order by t1.id desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public List<Map<String, Object>> getMyDanganList(String userid) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select T1.ID,T1.TIZHONG,T2.XINGMING AS XINMING,T1.XUEYA,T1.XINLU,T1.RIQI,T1.CYID,T1.USERID from DANGAN t1,JTCY T2  where t1.userid='" + userid
				+ "' AND T1.CYID=T2.ID order by id desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}

	public Dangan getDanganById(String id) {

		Dangan dangan = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select  T1.ID,T1.TIZHONG,T2.XINGMING AS XINMING,T1.XUEYA,T1.XINLU,T1.RIQI,T1.CYID,T1.USERID from DANGAN t1,JTCY T2  where t1.cyid=t2.id and  t1.id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			dangan = (Dangan) db.findSimpleRefResult(sql, list, Dangan.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return dangan;
	}

	public void delDanganById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from DANGAN where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public boolean updateDangan(Dangan dangan) {

		boolean flag = false;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update dangan  set  tizhong=?,xinlu=? ,xueya=? where id="
				+ String.valueOf(dangan.getId());

		List<Object> params = new ArrayList<Object>();

		params.add(dangan.getTizhong());

		params.add(dangan.getXinlu());

		params.add(dangan.getXueya());

		try {
			flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
}

package com.myweb.dao;

import com.myweb.domain.Admin;
import com.myweb.utils.DBManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AdminDao {

	
	// 查询管理员
	public Admin getAdminByUsernameAndPwd(String username,String password) {

		Admin admin = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from admin where loginname=(?) and loginpsw=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(username);
		
		list.add(password);

		try {
			admin = (Admin) db.findSimpleRefResult(sql, list,
					Admin.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return admin;
	}
	
	// 添加管理员账号
	public int addAdmin(Admin admin) {

		int id = 0;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "insert into ADMIN (ID, LOGINNAME,LOGINPSW,USERNAME,CREATETIME) values (?, ?, ?, ?, ?)";

		List<Object> params = new ArrayList<Object>();

		id = db.getMaxId("ADMIN");

		params.add(id);

		params.add(admin.getLoginname());

		params.add(admin.getLoginpsw());

		params.add(admin.getUsername());
		
		params.add(admin.getCreatetime());
		
		try {
			boolean flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return id;
	}

	// 查询所有管理员
	public List<Map<String, Object>> getAdminList() {

		DBManager db = new DBManager();

		db.getConnection();

		String sql2 = "select * from ADMIN  order by CREATETIME desc";

		List<Map<String, Object>> list = null;

		try {
			list = db.findModeResult(sql2, null);

		} catch (Exception ex) {

			ex.printStackTrace();
		}
		return list;

	}
	
	 
	public Admin getAdminById(String id) {

		Admin admin = null;

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "select *  from admin where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			admin = (Admin) db.findSimpleRefResult(sql, list,
					Admin.class);

		} catch (Exception e) {

			e.printStackTrace();
		}
		return admin;
	}

	// 删除管理员
	public void delAdminById(String id) {

		DBManager db = new DBManager();

		db.getConnection();

		String sql = "delete from ADMIN where id=(?)";

		List<Object> list = new ArrayList<Object>();

		list.add(id);

		try {
			boolean flag = db.updateByPreparedStatement(sql, list);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}

		
	}
	
	 
	public boolean updateAdmin(Admin admin) {

		boolean flag=false;
		
		DBManager db = new DBManager();

		db.getConnection();

		String sql = "update admin  set  loginpsw=?,username=? where id="+String.valueOf(admin.getId());

		List<Object> params = new ArrayList<Object>();
        
		params.add(admin.getLoginpsw());		 
		 
		params.add(admin.getUsername());
		
		try {
		    flag = db.updateByPreparedStatement(sql, params);

			System.out.println(flag);

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return flag;
	}
}

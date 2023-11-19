package com.myweb.utils;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.*;

public class DBManager {

	/**
	 * @param args
	 */
	static String driver;
	static String url;
	static String username;
	static String password;
	private Connection connection;
	private PreparedStatement pstmt;
	private ResultSet resultSet;

	public DBManager() {
		InputStream in = DBManager.class.getClassLoader().getResourceAsStream(
				"db.properties");

		Properties pro = new Properties();

		try {
			pro.load(in);

		} catch (IOException e) {

			e.printStackTrace();
		}

		driver = pro.getProperty("driver");

		url = pro.getProperty("url");

		username = pro.getProperty("username");

		password = pro.getProperty("password");

		try {
			Class.forName(driver);

			System.out.println("数据库连接成功！");

		} catch (Exception e) {

		}
	}

	/**
	 * 获得数据库的连接
	 * 
	 * @return
	 */
	public Connection getConnection() {
		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}

	/**
	 * 获得最大ID
	 * 
	 * @param tableName
	 * @return
	 * @throws SQLException
	 */
	public int getMaxId(String tableName) {
		Statement state = null;
		ResultSet rs = null;
		int maxId = 0;
		try {
			state = connection.createStatement();
			String sql = "select max(id) maxId from " + tableName;
			rs = state.executeQuery(sql);
			// 从resultset对象中将数据取出
			if (rs.next()) {
				maxId = rs.getInt("maxId");
			}
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}

		return ++maxId;
	}

	/**
	 * 增加、删除、改
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public boolean updateByPreparedStatement(String sql, List<Object> params)
			throws SQLException {
		boolean flag = false;
		int result = -1;
		pstmt = connection.prepareStatement(sql);
		int index = 1;
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		result = pstmt.executeUpdate();
		flag = result > 0 ? true : false;
		return flag;
	}

	/**
	 * 查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public Map<String, Object> findSimpleResult(String sql, List<Object> params)
			throws SQLException {
		Map<String, Object> map = new HashMap<String, Object>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();// 返回查询结果
		ResultSetMetaData metaData = resultSet.getMetaData();
		int col_len = metaData.getColumnCount();
		while (resultSet.next()) {
			for (int i = 0; i < col_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
		}
		return map;
	}

	/**
	 * 查询多条记录
	 * 
	 * @param sql
	 * @param params
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findModeResult(String sql,
			List<Object> params) throws SQLException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			Map<String, Object> map = new HashMap<String, Object>();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				map.put(cols_name, cols_value);
			}
			list.add(map);
		}

		return list;
	}

	/**
	 * 通过反射机制查询单条记录
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> T findSimpleRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		T resultObject = null;
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// 通过反射机制创建一个实例
			resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				int type = metaData.getColumnType(i + 1);
				if (cols_value == null) {
					if (type == Types.INTEGER) {
						cols_value = 0;
					} else {
						cols_value = "";
					}
				}
				Field field = cls.getDeclaredField(cols_name.toLowerCase());
				field.setAccessible(true); // 打开javabean的访问权限
				if (type == Types.TIMESTAMP) {
					 field.set(resultObject, String.valueOf(cols_value));
				} else {
				   field.set(resultObject, cols_value);
				}
			}
		}
		return resultObject;

	}

	/**
	 * 通过反射机制查询多条记录
	 * 
	 * @param sql
	 * @param params
	 * @param cls
	 * @return
	 * @throws Exception
	 */
	public <T> List<T> findMoreRefResult(String sql, List<Object> params,
			Class<T> cls) throws Exception {
		List<T> list = new ArrayList<T>();
		int index = 1;
		pstmt = connection.prepareStatement(sql);
		if (params != null && !params.isEmpty()) {
			for (int i = 0; i < params.size(); i++) {
				pstmt.setObject(index++, params.get(i));
			}
		}
		resultSet = pstmt.executeQuery();
		ResultSetMetaData metaData = resultSet.getMetaData();
		int cols_len = metaData.getColumnCount();
		while (resultSet.next()) {
			// 通过反射机制创建一个实例
			T resultObject = cls.newInstance();
			for (int i = 0; i < cols_len; i++) {
				String cols_name = metaData.getColumnName(i + 1);
				Object cols_value = resultSet.getObject(cols_name);
				if (cols_value == null) {
					cols_value = "";
				}
				Field field = cls.getDeclaredField(cols_name);
				field.setAccessible(true); // 打开javabean的访问权限
				field.set(resultObject, cols_value);
			}
			list.add(resultObject);
		}
		return list;
	}

	/**
	 * 释放数据库连接
	 */
	public void releaseConn() {
		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		// JdbcUtils jdbcUtils = new JdbcUtils();
		// jdbcUtils.getConnection();

		/******************* 增 *********************/
		/*
		 * String sql =
		 * "insert into userinfo (username, pswd) values (?, ?), (?, ?), (?, ?)"
		 * ; List<Object> params = new ArrayList<Object>(); params.add("小明");
		 * params.add("123xiaoming"); params.add("张三"); params.add("zhangsan");
		 * params.add("李四"); params.add("lisi000"); try { boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 * System.out.println(flag); } catch (SQLException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		/******************* 删 *********************/
		// 删除名字为张三的记录
		/*
		 * String sql = "delete from userinfo where username = ?"; List<Object>
		 * params = new ArrayList<Object>(); params.add("小明"); boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 */

		/******************* 改 *********************/
		// 将名字为李四的密码改了
		/*
		 * String sql = "update userinfo set pswd = ? where username = ? ";
		 * List<Object> params = new ArrayList<Object>();
		 * params.add("lisi88888"); params.add("李四"); boolean flag =
		 * jdbcUtils.updateByPreparedStatement(sql, params);
		 * System.out.println(flag);
		 */

		/******************* 查 *********************/
		// 不利用反射查询多个记录
		/*
		 * String sql2 = "select * from userinfo "; List<Map<String, Object>>
		 * list = jdbcUtils.findModeResult(sql2, null);
		 * System.out.println(list);
		 */

		// 利用反射查询 单条记录
		/*
		 * String sql = "select * from userinfo where username = ? ";
		 * List<Object> params = new ArrayList<Object>(); params.add("李四");
		 * UserInfo userInfo; try { userInfo =
		 * jdbcUtils.findSimpleRefResult(sql, params, UserInfo.class);
		 * System.out.print(userInfo); } catch (Exception e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } Class clcs =
		 * UserInfo.class; try { Object obj = clcs.newInstance(); Method f =
		 * clcs.getDeclaredMethod("setUsername", String.class); f.invoke(obj,
		 * "yan123"); Method f2 = clcs.getDeclaredMethod("getUsername", null);
		 * Object name = f2.invoke(obj, null); System.out.println("反射得到的名字 = " +
		 * name);
		 * 
		 * } catch (InstantiationException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } catch (IllegalAccessException e) { //
		 * TODO Auto-generated catch block e.printStackTrace(); } catch
		 * (NoSuchMethodException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (SecurityException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } catch
		 * (IllegalArgumentException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } catch (InvocationTargetException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

	}
}

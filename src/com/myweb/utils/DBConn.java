package com.myweb.utils;

import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.sql.Statement;
  
public class DBConn {  

	private static Connection conn = null;

	private PreparedStatement pstm;

	public static Connection getConn() {
		if (conn == null) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
				try {
					conn = DriverManager
							.getConnection("jdbc:mysql://localhost:3308/znzp?user=root&password=root&useUnicode=true&characterEncoding=UTF-8");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return conn;

	}

	public static void realse(ResultSet rs, PreparedStatement pstmt) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void doPstm(String sql, Object[] params) {
		if (sql != null && !sql.equals("")) {
			if (params == null)
				params = new Object[0];

			getConn();
			if (conn != null) {
				try {
					System.out.println(sql);
					pstm = conn.prepareStatement(sql,
							ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					for (int i = 0; i < params.length; i++) {
						pstm.setObject(i + 1, params[i]);
					}
					pstm.execute();
				} catch (SQLException e) {
					System.out.println("doPstm()方法出错！");
					e.printStackTrace();
				}
			}
		}
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
		getConn();
		if (conn != null) {
			try {
				state = conn.createStatement();
				String sql = "select max(id) maxId from " + tableName;
				rs = state.executeQuery(sql);
				// 从resultset对象中将数据取出
				if (rs.next()) {
					if(rs.getObject("maxId")!=null&&!rs.getObject("maxId").toString().equals(""))
					{
					   maxId = rs.getInt("maxId");
					}
				}
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		}
		return ++maxId;

	}

	public ResultSet getRs() throws SQLException {
		return pstm.getResultSet();
	}

	public int getCount() throws SQLException {
		return pstm.getUpdateCount();
	}

	public void closed() {
		try {
			if (pstm != null)
				pstm.close();
		} catch (SQLException e) {
			System.out.println("关闭pstm对象失败！");
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭con对象失败！");
			e.printStackTrace();
		}
	}
  
}  

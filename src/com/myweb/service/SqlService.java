package com.myweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myweb.utils.DBConn;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class SqlService extends HttpServlet {

	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	String sql = "";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		action = _request.getParameter("action").toString();

		sql = _request.getParameter("sql").toString();

	//	sql = new String(sql.getBytes("ISO8859-1"), "UTF-8");

		if (sql != null) {
			if (action.equals("add")) {

				add();

			} else if (action.equals("edit")) {

				edit();

			} else if (action.equals("query")) {

				query();

			} else if (action.equals("delete")) {

				delete();
			}
			else if (action.equals("getid")) {

				getid();
			}
		}
	}

	public void add() throws ServletException, IOException {

		String result = "";// 返回结果集

		DBConn mydb = new DBConn();

		try {
			mydb.doPstm(sql, null);

			result = "ok";

		} catch (Exception e) {

			System.out.println("新增失败！");

			e.printStackTrace();

		} finally {

			/* 返回数据 */
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}

	}

	public void edit() throws ServletException, IOException {

		String result = "";// 返回结果集

		DBConn mydb = new DBConn();

		mydb.doPstm(sql, null);

		result = "ok";

		/* 返回数据 */
		_response.setCharacterEncoding("UTF-8");

		_response.setHeader("content-type", "text/html;charset=UTF-8");

		PrintWriter pw = _response.getWriter();

		pw.write(result);

		pw.flush();

		pw.close();
	}

	public void getid() throws ServletException, IOException {

		String result = "";// 返回结果集

		DBConn mydb = new DBConn();
		
		try {
			 
			result=String.valueOf(mydb.getMaxId(sql));
			 

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			/* 返回数据 */
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}

	}

	public void delete() throws ServletException, IOException {

		String result = "";// 返回结果集

		DBConn mydb = new DBConn();

		mydb.doPstm(sql, null);

		result = "ok";

		/* 返回数据 */
		_response.setCharacterEncoding("UTF-8");

		_response.setHeader("content-type", "text/html;charset=UTF-8");

		PrintWriter pw = _response.getWriter();

		pw.write(result);

		pw.flush();

		pw.close();
	}
	
	public void query() throws ServletException, IOException {

		String result = "";// 返回结果集

		DBConn mydb = new DBConn();
		try {
			mydb.doPstm(sql, null);

			ResultSet rs = mydb.getRs();

			if (rs.next()) {

				result = this.ResultSetToJsonArray(rs).toJSONString();
			}

			rs.close();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			/* 返回数据 */
			_response.setCharacterEncoding("UTF-8");

			_response.setHeader("content-type", "text/html;charset=UTF-8");

			PrintWriter pw = _response.getWriter();

			pw.write(result);

			pw.flush();

			pw.close();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public JSONArray ResultSetToJsonArray(ResultSet rs) {

		JSONObject element = null;

		JSONArray ja = new JSONArray();

		ResultSetMetaData rsmd = null;

		String columnName, columnValue = null;

		try {

			rs.beforeFirst();

			rsmd = rs.getMetaData();

			while (rs.next()) {

				element = new JSONObject();

				for (int i = 0; i < rsmd.getColumnCount(); i++) {

					columnName = rsmd.getColumnName(i + 1);

					columnValue = rs.getString(columnName);

					element.put(columnName, columnValue);

				}
				ja.add(element);
			}
		} catch (SQLException e) {

			e.printStackTrace();

		}
		return ja;
	}

	public JSONObject ResultSetToJsonObject(ResultSet rs) {

		JSONObject element = null;

		JSONArray ja = new JSONArray();

		JSONObject jo = new JSONObject();

		ResultSetMetaData rsmd = null;

		String columnName, columnValue = null;
		try {
			rsmd = rs.getMetaData();

			while (rs.next()) {

				element = new JSONObject();

				for (int i = 0; i < rsmd.getColumnCount(); i++) {

					columnName = rsmd.getColumnName(i + 1);

					columnValue = rs.getString(columnName);

					element.put(columnName, columnValue);
				}
				ja.add(element);
			}

			jo.put("result", ja);

		} catch (SQLException e) {

			e.printStackTrace();

		}
		return jo;
	}

	// 将resultset转换为json
	public String ResultSetToJsonString(ResultSet rs) {

		return ResultSetToJsonObject(rs).toString();
	}

}

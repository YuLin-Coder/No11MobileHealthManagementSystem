package com.myweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.JtcyDao;
import com.myweb.domain.Jtcy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JtcyService extends HttpServlet {
	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		action = _request.getParameter("action").toString();

		if (action.equals("add")) {

			addJtcy();
		} else if (action.equals("editinit")) {

			editinitJtcy();
		}
		else if (action.equals("editsave")) {

			editSaveJtcy();
			
		}else if (action.equals("mylist")) {

			mylistJtcy();
		} else if (action.equals("delete")) {

			deleteJtcy();
		}
	}

	public void mylistJtcy() throws ServletException, IOException {

		String result = "";// 返回结果集

		String userid = _request.getParameter("userid").toString();
		try {

			JtcyDao jtcyDao = new JtcyDao();

			List<Map<String, Object>> jtcyList = new ArrayList<Map<String, Object>>();

			jtcyList = jtcyDao.getMyJtcyList(userid);

			if (jtcyList != null) {

				String json = JSONArray.toJSONString(jtcyList);

				result = json.toString();

			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

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

	public void editinitJtcy() throws ServletException, IOException {

		String result = "";// 返回结果集

		String jtcyid = _request.getParameter("jtcyid").toString();
		try {

			JtcyDao jtcyDao = new JtcyDao();

			Jtcy jtcy = jtcyDao.getJtcyById(jtcyid);

			if (jtcy != null) {

				result = JSONObject.toJSONString(jtcy);

			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

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

	public void editSaveJtcy() throws ServletException, IOException {

		String result = "";// 返回结果集

		String jtcy = _request.getParameter("jtcy").toString();

		//jtcy = new String(jtcy.getBytes("ISO8859-1"), "UTF-8");

		try {

			Jtcy jtcyObj = JSONObject.parseObject(jtcy, Jtcy.class);

			JtcyDao jtcyDAO = new JtcyDao();

			jtcyDAO.updateJtcy(jtcyObj);

			result = "ok";

		} catch (Exception e) {

			System.out.print(e.getMessage());

			result = "error";

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

	public void deleteJtcy() throws ServletException, IOException {

		String result = "";// 返回结果集

		String jtcyid = _request.getParameter("jtcyid").toString();

		try {

			JtcyDao jtcyDAO = new JtcyDao();

			jtcyDAO.delJtcyById(jtcyid);

			result = "ok";

		} catch (Exception e) {

			System.out.print(e.getMessage());

			result = "error";

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

	public void addJtcy() throws ServletException, IOException {

		String result = "";// 返回结果集

		String jtcy = _request.getParameter("jtcy").toString();

	//	jtcy = new String(jtcy.getBytes("ISO8859-1"), "UTF-8");

		try {

			Jtcy jtcyObj = JSONObject.parseObject(jtcy, Jtcy.class);

			JtcyDao jtcyDAO = new JtcyDao();

			jtcyDAO.addJtcy(jtcyObj);

			result = "ok";

		} catch (Exception e) {

			result = "error";

			System.out.print(e.getMessage());

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

}

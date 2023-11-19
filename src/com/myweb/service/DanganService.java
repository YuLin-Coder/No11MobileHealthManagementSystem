package com.myweb.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.DanganDao;
import com.myweb.domain.Dangan;

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

public class DanganService extends HttpServlet {
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

			addDangan();
		} else if (action.equals("editinit")) {

			editinitDangan();
		}
		else if (action.equals("editsave")) {

			editSaveDangan();
			
		}else if (action.equals("mylist")) {

			mylistDangan();
		} else if (action.equals("delete")) {

			deleteDangan();
		}
	}

	public void mylistDangan() throws ServletException, IOException {

		String result = "";// 返回结果集

		String userid = _request.getParameter("userid").toString();
		try {

			DanganDao danganDao = new DanganDao();

			List<Map<String, Object>> danganList = new ArrayList<Map<String, Object>>();

			danganList = danganDao.getMyDanganList(userid);

			if (danganList != null) {

				String json = JSONArray.toJSONString(danganList);

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

	public void editinitDangan() throws ServletException, IOException {

		String result = "";// 返回结果集

		String danganid = _request.getParameter("danganid").toString();
		try {

			DanganDao danganDao = new DanganDao();

			Dangan dangan = danganDao.getDanganById(danganid);

			if (dangan != null) {

				result = JSONObject.toJSONString(dangan);

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

	public void editSaveDangan() throws ServletException, IOException {

		String result = "";// 返回结果集

		String dangan = _request.getParameter("dangan").toString();

	//	dangan = new String(dangan.getBytes("ISO8859-1"), "UTF-8");

		try {

			Dangan danganObj = JSONObject.parseObject(dangan, Dangan.class);

			DanganDao danganDAO = new DanganDao();

			danganDAO.updateDangan(danganObj);

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

	public void deleteDangan() throws ServletException, IOException {

		String result = "";// 返回结果集

		String danganid = _request.getParameter("danganid").toString();

		try {

			DanganDao danganDAO = new DanganDao();

			danganDAO.delDanganById(danganid);

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

	public void addDangan() throws ServletException, IOException {

		String result = "";// 返回结果集

		String dangan = _request.getParameter("dangan").toString();

		//dangan = new String(dangan.getBytes("ISO8859-1"), "UTF-8");

		try {

			Dangan danganObj = JSONObject.parseObject(dangan, Dangan.class);

			DanganDao danganDAO = new DanganDao();

			danganDAO.addDangan(danganObj);

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

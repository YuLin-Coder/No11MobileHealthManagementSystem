package com.myweb.service;

import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.UserDao;
import com.myweb.domain.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class UserService extends HttpServlet {
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

		if (action.equals("reg")) {

			userReg();
		}
		else if (action.equals("vali")) {

			vali();
		}
		else if(action.equals("login"))
		{
			login();
		}
		else if(action.equals("editsave"))
		{
			editSaveUser();
		}
	}

	public void vali() throws ServletException, IOException {

		String result = "";// 返回结果集

		String user = _request.getParameter("user").toString();
		
		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();
		 
			result = userDAO.getUserByLoginName(userObj.getLoginname());

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
	
	public void login() throws ServletException, IOException {

		String result = "";// 返回结果集

		String user = _request.getParameter("user").toString();
		
		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			User user1 = new User();

			user1 = userDAO.getUserByLoginNameAndPassword(userObj
					.getLoginname(), userObj.getLoginpsw());

			if (user1 != null) {

				result = JSONObject.toJSONString(user1);

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

 
	 
	public void editSaveUser() throws ServletException, IOException {

		String result = "";// 返回结果集

		String user = _request.getParameter("user").toString();

		//user = new String(user.getBytes("ISO8859-1"), "UTF-8");
		
		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			userDAO.updateUser(userObj);

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

	// 用户注册
	public void userReg() throws ServletException, IOException {

		String result = "";// 返回结果集


		String user = _request.getParameter("user").toString();

		//user = new String(user.getBytes("ISO8859-1"), "UTF-8");

		
		try {

			User userObj = JSONObject.parseObject(user, User.class);

			UserDao userDAO = new UserDao();

			int rel = userDAO.addUser(userObj);

			if (rel > -1) {

				result = "ok";
			}

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

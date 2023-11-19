package com.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myweb.dao.AdminDao;
import com.myweb.dao.UserDao;
import com.myweb.domain.Admin;
import com.myweb.domain.User;

public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		action = request.getParameter("action").toString();

		if (action.equals("myinfo")) {

			myinfo();

		} else if (action.equals("myinfosave")) {

			myinfoSave();

		} else if (action.equals("list")) {

			listAdmin();

		} else if (action.equals("add")) {

			addAdmin();

		}  else if (action.equals("delete")) {

			delAdmin();

		} else if (action.equals("edit")) {

			editAdmin();

		} else if (action.equals("editSave")) {

			editSaveAdmin();

		} 

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 修改个人信息
	public void myinfo() throws ServletException, IOException {

		User user = new User();

		UserDao dao = new UserDao();

		if (_session.getAttribute("user") != null) {
			try {
				user = dao.getUserById(String.valueOf(((User) _session
						.getAttribute("user")).getId()));

				_request.setAttribute("user", user);

			} catch (Exception ex) {

				_request.setAttribute("alertNote", "0");
			}
		}
		_request.getRequestDispatcher("/user/myInfo.jsp").forward(_request,
				_response);

	}

	// 保存个人信息
	public void myinfoSave() throws ServletException, IOException {

		String id = _request.getParameter("id");

		String loginpsw = _request.getParameter("loginpsw");

		String interests = _request.getParameter("interests");

		String job = _request.getParameter("job");

		String concern = _request.getParameter("concern");

		String email = _request.getParameter("email");

		String tel = _request.getParameter("tel");

		UserDao dao = new UserDao();

		User user = dao.getUserById(id);

		user.setLoginpsw(loginpsw);

		 

		user.setEmail(email);

		user.setTel(tel);

		user.setJob(job);

		try {
			dao.updateUser(user);

			_request.setAttribute("user", user);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/user/myInfo.jsp").forward(_request,
				_response);

	}

	 
	// 添加用户信息
	public void addAdmin() throws ServletException, IOException {

		String loginname = _request.getParameter("loginname");

		String loginpsw = _request.getParameter("loginpsw");

		String username = _request.getParameter("username");
		
		Admin admin = new Admin();

		admin.setLoginname(loginname);

		admin.setLoginpsw(loginpsw);
		
		admin.setUsername(username);
		
		 Date date = new Date();
		 
		admin.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));

		AdminDao dao = new AdminDao();
		try {
			dao.addAdmin(admin);
			
			_request.setAttribute("alertNote", "1");
			
		} catch (Exception ex) {
			
			_request.setAttribute("alertNote", "0");
		}
		 
		_request.getRequestDispatcher("/admin/admin_Add.jsp").forward(_request,
				_response);

	}
	
	// 编辑用户信息
	public void editAdmin() throws ServletException, IOException {

		String id = _request.getParameter("id");

		AdminDao dao = new AdminDao();

		Admin admin = null;

		try {
			admin = dao.getAdminById(id);

			_request.setAttribute("admin", admin);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/admin_Edit.jsp").forward(_request,
				_response);

	}

	// 保存用户信息
	public void editSaveAdmin() throws ServletException, IOException {

		String loginpsw = _request.getParameter("loginpsw");

		String username =  _request.getParameter("username");
		
		String id =  _request.getParameter("id");

		AdminDao dao = new AdminDao();

		Admin admin = null;

		try {
			admin = dao.getAdminById(id);

			admin.setLoginpsw(loginpsw);

			admin.setUsername(username);
			
			boolean flag = dao.updateAdmin(admin);
			
			 _request.setAttribute("admin", admin);
			 
			if (flag) {
				 _request.setAttribute("alertNote", "1");
			} else {
				 _request.setAttribute("alertNote", "0");
			}
		} catch (Exception ex) {
			 _request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/admin_Edit.jsp").forward(_request,
				_response);

	}

	// 用户列表
	public void listAdmin() throws ServletException, IOException {

		AdminDao dao = new AdminDao();

		try {

			List<Map<String, Object>> list = dao.getAdminList();

			_request.setAttribute("AdminList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/admin_List.jsp").forward(_request,
				_response);

	}

	// 删除用户
	public void delAdmin() throws ServletException, IOException {

		String id = _request.getParameter("id");

		AdminDao dao = new AdminDao();

		try {
			dao.delAdminById(id);

			List<Map<String, Object>> list = dao.getAdminList();

			_request.setAttribute("AdminList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/admin_List.jsp").forward(_request,
				_response);
	}

}

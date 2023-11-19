package com.myweb.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 
import com.myweb.dao.UserDao;
 
import com.myweb.domain.User;

public class UserServlet extends HttpServlet {

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

			listUser();

		}else if (action.equals("select")) {

			selectUser();

		} else if (action.equals("delete")) {

			delUser();

		} else if (action.equals("edit")) {

			editUser();

		} else if (action.equals("editSave")) {

			editSaveUser();

		} else if (action.equals("reg")) {

			userReg();

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

		String username = _request.getParameter("username");

		String job = _request.getParameter("job");

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

	// 用户注册
	public void userReg() throws ServletException, IOException {

		String loginname = _request.getParameter("loginname");

		String loginpsw = _request.getParameter("loginpsw");

		String loginpsw1 = _request.getParameter("loginpsw1");

		String interests = _request.getParameter("interests");

		String job = _request.getParameter("job");

		String concern = _request.getParameter("concern");

		String email = _request.getParameter("email");

		String tel = _request.getParameter("tel");

		User user = new User();

		user.setLoginname(loginname);

		user.setLoginpsw(loginpsw);

		user.setEmail(email);

		user.setTel(tel);

		user.setJob(job);

		UserDao dao = new UserDao();

		try {
			dao.addUser(user);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/user_Reg.jsp").forward(_request,
				_response);
	}

	// 编辑用户信息
	public void editUser() throws ServletException, IOException {

		String id = _request.getParameter("id");

		UserDao dao = new UserDao();

		User user = null;

		try {
			user = dao.getUserById(id);

			_request.setAttribute("user", user);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/user_Edit.jsp").forward(_request,
				_response);

	}

	// 保存用户信息
	public void editSaveUser() throws ServletException, IOException {

		String id = _request.getParameter("id");

		String loginpsw = _request.getParameter("loginpsw");

		String job = _request.getParameter("job");

		String email = _request.getParameter("email");

		String tel = _request.getParameter("tel");

		String username=_request.getParameter("username");
		
		
		UserDao dao = new UserDao();

		User user = dao.getUserById(id);

		user.setLoginpsw(loginpsw);

		user.setEmail(email);
		
		user.setUsername(username);

		user.setTel(tel);

		user.setJob(job);

		try {
			dao.updateUser(user);

			_request.setAttribute("user", user);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/user_Edit.jsp").forward(_request,
				_response);

	}

	 
	public void selectUser() throws ServletException, IOException {

		UserDao dao = new UserDao();

		try {

			List<Map<String, Object>> list = dao.getUserList();

			_request.setAttribute("UserList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/selectUser.jsp").forward(_request,
				_response);

	}
	
	// 用户列表
	public void listUser() throws ServletException, IOException {

		UserDao dao = new UserDao();

		try {

			List<Map<String, Object>> list = dao.getUserList();

			_request.setAttribute("UserList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/user_List.jsp").forward(_request,
				_response);

	}

	// 删除用户
	public void delUser() throws ServletException, IOException {

		String id = _request.getParameter("id");

		UserDao dao = new UserDao();

		try {
			dao.delUserById(id);

			List<Map<String, Object>> list = dao.getUserList();

			_request.setAttribute("UserList", list);
			
			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
			
			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/user_List.jsp").forward(_request,
				_response);
	}

}

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

import com.myweb.dao.BoardDao;
import com.myweb.dao.NewsTypeDao;
import com.myweb.domain.Admin;
import com.myweb.domain.Board;
import com.myweb.domain.NewsType;
import com.myweb.domain.User;

public class NewsTypeServlet extends HttpServlet {

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

		if (action.equals("add")) {

			addNewsType();

		} else if (action.equals("list")) {

			listNewsType();

		} else if (action.equals("delete")) {

			delNewsType();

		} else if (action.equals("edit")) {

			editNewsType();

		} else if (action.equals("editSave")) {

			editSaveNewsType();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 添加类型
	public void addNewsType() throws ServletException, IOException {

		String typename = _request.getParameter("typename");

		NewsType newsType = new NewsType();

		newsType.setTypename(typename);

		NewsTypeDao dao = new NewsTypeDao();

		try {
			dao.addNewsType(newsType);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/newsType_Add.jsp").forward(
				_request, _response);

	}

	// 编辑类型
	public void editNewsType() throws ServletException, IOException {

		String id = _request.getParameter("id");

		NewsTypeDao dao = new NewsTypeDao();

		NewsType newsType = null;

		try {
			newsType = dao.getNewsTypeById(id);

			_request.setAttribute("newsType", newsType);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("admin/newsType_Edit.jsp").forward(
				_request, _response);

	}

	// 保存编辑类型
	public void editSaveNewsType() throws ServletException, IOException {

		String id = _request.getParameter("id");

		String typename = _request.getParameter("typename");

		NewsTypeDao dao = new NewsTypeDao();

		NewsType newsType = null;

		try {
			newsType = dao.getNewsTypeById(id);

			newsType.setTypename(typename);

			boolean flag = dao.updateNewsType(newsType);

			_request.setAttribute("newsType", newsType);

			if (flag) {

				_request.setAttribute("alertNote", "1");

			} else {

				_request.setAttribute("alertNote", "0");

			}
		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/newsType_Edit.jsp").forward(
				_request, _response);

	}

	// 类型列表
	public void listNewsType() throws ServletException, IOException {
		NewsTypeDao dao = new NewsTypeDao();
		try {

			List<Map<String, Object>> list = dao.getNewsTypeList();

			_request.setAttribute("newsTypeList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/newsType_List.jsp").forward(_request,
				_response);
	}

	// 删除类型
	public void delNewsType() throws ServletException, IOException {

		String id = _request.getParameter("id");

		NewsTypeDao dao = new NewsTypeDao();
		try {
			dao.delNewsTypeById(id);
			
			_request.setAttribute("alertNote", "1");
 
		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
			
			ex.printStackTrace();
		}

		_request.getRequestDispatcher("NewsTypeServlet?action=list").forward(_request,
				_response);
	}

}

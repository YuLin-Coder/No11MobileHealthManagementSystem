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


 
import com.myweb.dao.News2Dao;

 
import com.myweb.domain.News2;

public class News2Servlet extends HttpServlet {

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

			addNews2();

		} else if (action.equals("list")) {

			listNews2();

		} else if (action.equals("edit")) {

			editNews2();

		} else if (action.equals("editSave")) {

			editSaveNews2();

		} else if (action.equals("delete")) {
             deleteNews2();
		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 删除新闻
	public void deleteNews2() throws ServletException, IOException {
		String id = _request.getParameter("id");

		News2Dao dao = new News2Dao();

		try {
			dao.delNews2ById(id);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("News2Servlet?action=list").forward(_request,
				_response);
	}
	
	// 添加新闻
	public void addNews2() throws ServletException, IOException {

		String title = _request.getParameter("title");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		News2 news2 = new News2();

		news2.setTitle(title);

		news2.setImgpath(imgpath);

		news2.setContent(content);

		news2.setCreateuser("cc");

		Date date = new Date();

		news2.setCreatetime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
				.format(date));

		News2Dao dao = new News2Dao();

		try {
			dao.addNews2(news2);

			_request.setAttribute("alertNote", "1");

		} catch (Exception ex) {

			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/news2_Add.jsp").forward(_request,
				_response);

	}

	// 编辑新闻
	public void editNews2() throws ServletException, IOException {

		String id = _request.getParameter("id");

		News2Dao dao = new News2Dao();

		News2 news2 = null;

		try {
			news2 = dao.getNews2ById(id);

			_request.setAttribute("news2", news2);

		} catch (Exception ex) {

		}

		_request.getRequestDispatcher("/admin/news2_Edit.jsp").forward(_request,
				_response);
	}

	// 保存编辑新闻
	public void editSaveNews2() throws ServletException, IOException {
		
		String title = _request.getParameter("title");

		String imgpath = _request.getParameter("imgpath");

		String content = _request.getParameter("content");

		String id = _request.getParameter("id");

		News2Dao dao = new News2Dao();

		News2 news2 = null;

		try {
			news2 = dao.getNews2ById(id);

			news2.setTitle(title);

			news2.setImgpath(imgpath);

			news2.setContent(content);

			boolean flag = dao.updateNews2(news2);
			
			_request.setAttribute("news2", news2);

			if (flag) {
				_request.setAttribute("alertNote", "1");
			} else {
				_request.setAttribute("alertNote", "0");
			}
		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/news2_Edit.jsp").forward(_request,
				_response);
	}

	// 新闻列表
	public void listNews2() throws ServletException, IOException {

		News2Dao dao = new News2Dao();

		try {

			List<Map<String, Object>> list = dao.getNews2List();

			_request.setAttribute("news2List", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/news2_List.jsp").forward(_request,
				_response);

	}

}

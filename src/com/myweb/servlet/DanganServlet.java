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

import com.myweb.dao.DanganDao;
import com.myweb.dao.UserDao;
import com.myweb.domain.Dangan;
import com.myweb.domain.User;

public class DanganServlet extends HttpServlet {
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

		if (action.equals("list")) {

			listDangan();

		} else if (action.equals("delete")) {

			delDangan();

		} else if (action.equals("edit")) {

			editDangan();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 编辑用户信息
	public void editDangan() throws ServletException, IOException {

		String id = _request.getParameter("id");

		DanganDao dao = new DanganDao();

		Dangan dangan = null;

		try {
			dangan = dao.getDanganById(id);

			_request.setAttribute("dangan", dangan);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/dangan_Edit.jsp").forward(_request,
				_response);

	}

	public void listDangan() throws ServletException, IOException {

		DanganDao dao = new DanganDao();

		try {

			List<Map<String, Object>> list = dao.getDanganList();

			_request.setAttribute("danganList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/dangan_List.jsp").forward(_request,
				_response);

	}

	public void delDangan() throws ServletException, IOException {

		String id = _request.getParameter("id");

		DanganDao dao = new DanganDao();

		try {
			dao.delDanganById(id);

			List<Map<String, Object>> list = dao.getDanganList();

			_request.setAttribute("danganList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/dangan_List.jsp").forward(_request,
				_response);
	}

}

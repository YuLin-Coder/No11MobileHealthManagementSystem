package com.myweb.servlet;

import com.myweb.dao.JtcyDao;
import com.myweb.domain.Jtcy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JtcyServlet extends HttpServlet {
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

			listJtcy();

		} else if (action.equals("delete")) {

			delJtcy();

		} else if (action.equals("edit")) {

			editJtcy();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 编辑用户信息
	public void editJtcy() throws ServletException, IOException {

		String id = _request.getParameter("id");

		JtcyDao dao = new JtcyDao();

		Jtcy jtcy = null;

		try {
			jtcy = dao.getJtcyById(id);

			_request.setAttribute("jtcy", jtcy);

		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}
		_request.getRequestDispatcher("/admin/jtcy_Edit.jsp").forward(_request,
				_response);

	}

	public void listJtcy() throws ServletException, IOException {

		JtcyDao dao = new JtcyDao();

		try {

			List<Map<String, Object>> list = dao.getJtcyList();
			List<Map<String, Object>> list2 =new ArrayList<>();

			for(Map map:list){

			    map.put("id",map.get("id").toString());
                list2.add(map);
            }

			_request.setAttribute("jtcyList", list);

		} catch (Exception ex) {

			ex.printStackTrace();

		}
		_request.getRequestDispatcher("/admin/jtcy_List.jsp").forward(_request,
				_response);

	}

	public void delJtcy() throws ServletException, IOException {

		String id = _request.getParameter("id");

		JtcyDao dao = new JtcyDao();

		try {
			dao.delJtcyById(id);

			List<Map<String, Object>> list = dao.getJtcyList();

			_request.setAttribute("JtcyList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/jtcy_List.jsp").forward(_request,
				_response);
	}

}

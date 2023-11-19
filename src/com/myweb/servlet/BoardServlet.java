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
 
import com.myweb.domain.User;

public class BoardServlet extends HttpServlet {

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

			addBoard();

		} else if (action.equals("list")) {

			listBoard();

		}  else if (action.equals("shenhe")) {

			shenpiBoard();

		} else if (action.equals("delete")) {

			delBoard();

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	// 添加留言
	public void addBoard() throws ServletException, IOException {

		String username = "";

		Board board = new Board();

		if (_session.getAttribute("logintype").equals("0")) {
			User user = (User) _session.getAttribute("user");

			username = user.getLoginname();
		} else if (_session.getAttribute("logintype").equals("1")) {
			Admin admin = (Admin) _session.getAttribute("admin");

			username = admin.getLoginname();
		}

		String content = _request.getParameter("content");

		board.setContent(content);

		board.setUsername(username);

		BoardDao dao = new BoardDao();
		try {
			dao.addBoard(board);

			List<Map<String, Object>> list = dao.getBoardListByNewsId(board
					.getNewsid());

			_request.setAttribute("boardList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/board_List.jsp").forward(
				_request, _response);
	}

	// 留言列表
	public void listBoard() throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		try {

			String id = _request.getParameter("id");

			List<Map<String, Object>> list = dao.getBoardListByNewsId(id);

			_request.setAttribute("boardList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/board_List.jsp").forward(
				_request, _response);
	}

	// 删除留言
	public void delBoard() throws ServletException, IOException {

		String id = _request.getParameter("id");

		String newsid = _request.getParameter("newsid");

		BoardDao dao = new BoardDao();
		try {
			dao.delBoardById(id);

			List<Map<String, Object>> list = dao.getBoardListByNewsId(newsid);

			_request.setAttribute("boardList", list);

		} catch (Exception ex) {

			ex.printStackTrace();
		}

		_request.getRequestDispatcher("/admin/board_List.jsp").forward(
				_request, _response);
	}

	public void shenpiBoard() throws ServletException, IOException {

		String id = _request.getParameter("id");

		String newsid = _request.getParameter("newsid");

		BoardDao dao = new BoardDao();

		try {

			boolean flag = dao.shengpi(id, "已审核");

			List<Map<String, Object>> list = dao.getBoardListByNewsId(newsid);

			_request.setAttribute("boardList", list);

			if (flag) {
				_request.setAttribute("alertNote", "1");
			} else {
				_request.setAttribute("alertNote", "0");
			}
		} catch (Exception ex) {
			_request.setAttribute("alertNote", "0");
		}

		_request.getRequestDispatcher("/admin/board_List.jsp").forward(
				_request, _response);
	}
}

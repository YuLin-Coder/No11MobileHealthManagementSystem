package com.myweb.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.myweb.dao.BoardDao;
 
 
import com.myweb.domain.Board;
 
 

public class BoardService extends HttpServlet {

	HttpSession _session;

	HttpServletRequest _request;

	HttpServletResponse _response;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		_session = request.getSession();

		_request = request;

		_response = response;

		String action = "";

		try {
			/* 读取数据 */
			BufferedReader br = new BufferedReader(new InputStreamReader(
					(ServletInputStream) _request.getInputStream(), "utf-8"));

			StringBuffer sb = new StringBuffer("");

			String temp;

			while ((temp = br.readLine()) != null) {
				sb.append(temp);
			}

			br.close();

			
			if (!sb.toString().equals("")) {

				JSONObject jsonObj = JSONObject.parseObject(sb.toString());

				action = jsonObj.getString("action");

				if (action.equals("list")) {

					
					listBoard(jsonObj.getString("newsid"));

				} else if (action.equals("add")) {

					addBoard(jsonObj.getString("board"));

				}
			}
		} catch (Exception e) {

			System.out.print(e.getMessage());

		}

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	public void listBoard(String newsid) throws ServletException, IOException {

		String result = "";// 返回结果集

		try {

			BoardDao boardDao = new BoardDao();

			List<Map<String, Object>> boardList = new ArrayList<Map<String, Object>>();

			boardList = boardDao.getBoardListByNewsIdService(newsid);

			if (boardList != null) {

				String json = JSONArray.toJSONString(boardList);

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

	public void addBoard(String board) throws ServletException, IOException {

		String result = "";// 返回结果集

		try {

			Board boardObj = JSONObject.parseObject(board, Board.class);

			BoardDao boardDao = new BoardDao();

			boardDao.addBoard(boardObj);

			result = "ok";

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

}

package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/hello")
public class HelloServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		String name = "김준일";
//		resp.setContentType("text/html");
//		resp.setCharacterEncoding("utf-8");
//		
//		resp.getWriter().println(""
//				+ "<html>"
//				+ "<head>"
//				+ "<title>Hello</title>"
//				+ "</head>"
//				+ "<body>"
//				+ "<h1>Hello Servlet</h1>"
//				+ "<h2>" + name + "</h2>"
//				+ "</body>"
//				+ "</html>");
//	} // 주소창에서 엔터를 치면 doGet 요청
//
//}
// 
//// ctrl + shift + o = 필요 없는 임폴트 지워주기

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age"); // 파라미터는 무조건 문자열로
		
		System.out.println(name);
		System.out.println(age);
		
		req.getRequestDispatcher("/WEB-INF/views/jjsspp/.jsp").forward(req, resp);
	}
}

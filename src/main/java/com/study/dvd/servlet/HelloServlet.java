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
//		String name = "������";
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
//	} // �ּ�â���� ���͸� ġ�� doGet ��û
//
//}
// 
//// ctrl + shift + o = �ʿ� ���� ����Ʈ �����ֱ�

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String name = req.getParameter("name");
		String age = req.getParameter("age"); // �Ķ���ʹ� ������ ���ڿ���
		
		System.out.println(name);
		System.out.println(age);
		
		req.getRequestDispatcher("/WEB-INF/views/jjsspp/.jsp").forward(req, resp);
	}
}

package com.study.dvd.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/producer/search") // 요청 걸기
public class SearchProducerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	@Override // 주소를 검색하면 doGet이 호출됨 / 요청 = 톰캣에 요청
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/search_producer.jsp").forward(req, resp); // forward = doGet을 호출할 때 톰캣한테 넘겨줘라
		// 주소가 없으면 404 / 주소가 없으면 405 / 주소도 있고 매핑주소도 있으면 200(OK)
	}
}


	/*
	 * 1. 요청 URL = /producer/search
	 * 2. dao.producerDao	=> searchProducerByProducerName()
	 * 3. entity.producer
	 * 4. search_producer.jsp
	 */
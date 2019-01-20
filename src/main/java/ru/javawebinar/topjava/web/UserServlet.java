package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.slf4j.LoggerFactory.getLogger;

public class UserServlet extends HttpServlet {
	private final Logger log = getLogger(getClass());
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.debug("redirect");
		response.sendRedirect("userList.jsp");
	}
}

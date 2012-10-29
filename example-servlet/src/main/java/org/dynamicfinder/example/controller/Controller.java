package org.dynamicfinder.example.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class Controller extends HttpServlet {

	private static final long serialVersionUID = -2231057974722481959L;

	private HttpServletRequest request;
	private HttpServletResponse response;

	/**
	 * Do whatever you want with the web.
	 */
	protected abstract void process();

	protected void addAttribute(final String key, final Object value) {
		this.request.setAttribute(key, value);
	}

	protected void setView(final String viewName) {
		final RequestDispatcher dispatcher = super.getServletContext().getRequestDispatcher(viewName);
		try {
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected void redirect(final String url) {
		try {
			this.response.sendRedirect(url);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected Map<String, String[]> getParameters() {
		return this.request.getParameterMap();
	}

	protected String getString(String param) {
		return this.request.getParameter(param);
	}

	protected Integer getInteger(String param) {
		final String s = this.request.getParameter(param);
		return s == null ? null : Integer.valueOf(s);
	}

	protected Date getDate(String param, String dateFormat) {
		final String s = this.request.getParameter(param);
		SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
		try {
			return sdf.parse(s);
		} catch (ParseException e) {
			return null;
		}
	}

	protected HttpServletRequest getRequest() {
		return request;
	}

	protected HttpServletResponse getResponse() {
		return response;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.initialize(request, response);
		process();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.initialize(request, response);
		process();
	}

	private void initialize(HttpServletRequest req, HttpServletResponse res) {
		this.request = req;
		this.response = res;
		this.request.setAttribute("_root", request.getContextPath());
	}
}

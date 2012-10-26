package org.dynamicfinder.example.servlet.controller;

import javax.servlet.annotation.WebServlet;

@WebServlet(value="/home")
public class HomeServlet extends Controller {

	private static final long serialVersionUID = 4192306771626436423L;

	@Override
	protected void process() {
		setView("/WEB-INF/views/home.jsp");
	}

}

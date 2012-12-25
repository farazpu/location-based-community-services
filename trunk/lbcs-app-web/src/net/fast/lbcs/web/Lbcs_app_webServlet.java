package net.fast.lbcs.web;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Lbcs_app_webServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}

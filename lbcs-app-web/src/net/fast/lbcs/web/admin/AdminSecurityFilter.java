package net.fast.lbcs.web.admin;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import net.fast.lbcs.HttpControllerFactory;
import net.fast.lbcs.admin.controller.AdminController;

/**
 * Servlet Filter implementation class AdminSecurityFilter
 */
public class AdminSecurityFilter implements Filter {

    private FilterConfig fConfig;

	/**
     * Default constructor. 
     */
    public AdminSecurityFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @throws IOException 
	 * @throws ServletException 
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	
	private void forward(String destination,ServletRequest request, ServletResponse response) throws ServletException, IOException{
		 RequestDispatcher rd = fConfig.getServletContext().getRequestDispatcher(destination);
	     rd.forward(request, response);
	        
	}
	
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		AdminController adminController = new HttpControllerFactory((HttpServletRequest) request).getAdminController();
		if(adminController.isLoggedIn()){
			chain.doFilter(request, response);			
		}
		else
		{
			String user = request.getParameter("user");
			String password = request.getParameter("password");
			if(adminController.login(user, password)){
				forward("/admin/welcome.jsp?pageNum=0", request, response);
			}
			else {
				forward("/admin/login.jsp", request, response);				
			}
				
			
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		this.fConfig = fConfig;
		
	}

}

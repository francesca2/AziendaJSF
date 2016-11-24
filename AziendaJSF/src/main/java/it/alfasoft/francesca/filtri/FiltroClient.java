package it.alfasoft.francesca.filtri;

import it.alfasoft.francesca.controller.LoginController;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class FiltroClient
 */
@WebFilter("/faces/portali/client/*")
public class FiltroClient implements Filter {

    /**
     * Default constructor. 
     */
    public FiltroClient() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		HttpServletRequest httpReq = (HttpServletRequest) request;
		HttpServletResponse httpRes = (HttpServletResponse) response;
		HttpSession session= httpReq.getSession();
		
		LoginController logController =(LoginController)session.getAttribute("loginController");
		
		//controlliamo se il logController non è stato creato quindi è la prima volta che cerco di entrare nella pagina
		//oppure l'attributo loggato è settato a false, facciamo un redirect alla pagina di login
		if(logController.getRuolo()!='c'){
			httpRes.sendRedirect(httpReq.getContextPath()+"/faces/login.xhtml");
		}

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}

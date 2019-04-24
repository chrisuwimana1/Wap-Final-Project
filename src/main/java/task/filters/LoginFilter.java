package task.filters;

import task.model.ApplicationUser;

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
 * Servlet Filter implementation class LoginFilter
 */
// @WebFilter("/*")
public class LoginFilter implements Filter {

    public LoginFilter() {
        // TODO Auto-generated constructor stub
    }

    public void destroy() {
        // TODO Auto-generated method stub
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse resp = (HttpServletResponse) response;
//        
//        
//        System.out.println("resp = " + resp);
//
//        HttpSession session = req.getSession();
//
//        String servletPath = req.getServletPath();
//
//        // if he is on login page ignore
//        if (servletPath.equals("/pages/")) {
//            chain.doFilter(request, response);
//            return;
//        }
//
//        ApplicationUser loginedUser = (ApplicationUser) session.getAttribute("currentUser");
//
//        if (loginedUser != null) {
//            // TODO
//            // he can now access the page he want
//            // in future He will only be able to acces the pages that he has permision to view
            chain.doFilter(request, response);
//            return;
//        }
//
//        if (loginedUser == null) {
//            // send him to the login page
//            resp.sendRedirect(req.getContextPath());
//        }
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}

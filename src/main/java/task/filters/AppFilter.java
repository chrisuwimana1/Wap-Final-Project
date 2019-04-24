/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.filters;

import java.io.IOException;
import javax.faces.FactoryFinder;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import task.model.ApplicationUser;

/**
 *
 * @author cmbuyamba
 */
@WebFilter(filterName = "AppFilter", urlPatterns = {"/pages/*"})
public class AppFilter implements Filter {

    public static final String LOGIN_PAGE = "/login.jsp";
    public static final String PROFIL_PAGE = "/pages/dashboard.jsp";

    @Override
    public void doFilter(ServletRequest servletRequest,
            ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        
        System.out.println("========================");

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        if (isAjax(request) && !request.isRequestedSessionIdValid()) {
            System.out.println("Session expiration during ajax request, partial redirect to login page");
            response.getWriter().print(xmlPartialRedirectToPage(request, LOGIN_PAGE));
            response.flushBuffer();
        } else {
            // managed bean name is exactly the session attribute name
            ApplicationUser user = (ApplicationUser) request.getSession().getAttribute("user");

            if (user != null) {
                if (true) {
                    System.out.println("user is logged in");
                    // user is logged in, continue request

                    servletResponse.setCharacterEncoding("UTF-8");
                    filterChain.doFilter(servletRequest, servletResponse);

                } else {
                    System.out.println("user is not logged in");
                    // user is not logged in, redirect to login page
                    response.sendRedirect(
                            request.getContextPath()
                            + LOGIN_PAGE);
                }
            } else {
                System.out.println("user not found");
                // user is not logged in, redirect to login page
                response.sendRedirect(request.getContextPath() + LOGIN_PAGE);
            }
        }
    }

    @Override
    public void init(FilterConfig arg) throws ServletException {
        System.out.println("LoginFilter initialized");
    }

    public FacesContext provideFacesContext(ServletRequest servletRequest, ServletResponse servletResponse) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        FacesContext facesContext = FacesContext.getCurrentInstance();

        // Check current FacesContext.
        if (facesContext == null) {

            // Create new Lifecycle.
            LifecycleFactory lifecycleFactory = (LifecycleFactory) FactoryFinder.getFactory(FactoryFinder.LIFECYCLE_FACTORY);
            Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory.DEFAULT_LIFECYCLE);

            // Create new FacesContext.
            FacesContextFactory contextFactory = (FacesContextFactory) FactoryFinder.getFactory(FactoryFinder.FACES_CONTEXT_FACTORY);
            facesContext = contextFactory.getFacesContext(request.getSession().getServletContext(), request, response, lifecycle);

//            // Create new View.
//            UIViewRoot view = facesContext.getApplication().getViewHandler().createView(facesContext, "");
//            facesContext.setViewRoot(view);
            return facesContext;
        } else {
            return facesContext;
        }
    }

    private boolean isAjax(HttpServletRequest request) {
        return "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
    }

    private String xmlPartialRedirectToPage(HttpServletRequest request, String page) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version='1.0' encoding='UTF-8'?>");
        sb.append("<partial-response><redirect url=\"").append(request.getContextPath()).append(request.getServletPath()).append(page).append("\"/></partial-response>");
        return sb.toString();
    }

    @Override
    public void destroy() {
        // close resources
    }
}

package task.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import task.model.UserRole;
import task.model.ApplicationUser;
import task.service.LoginService;

@WebServlet("/dashboard")
public class LoginServlet extends HttpServlet {

    static String LOGIN_PAGE_PATH = "/pages/login.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String baseURL = req.getContextPath();

        req.setAttribute("baseURL", baseURL);

        req.getRequestDispatcher(LOGIN_PAGE_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("uname");
        String password = req.getParameter("psw");


        System.out.println(username + "............" );
        ApplicationUser currentUser = LoginService.login(username, password);

        if (currentUser != null) {

            String baseURL = req.getContextPath();

            List<UserRole> currentUserRoles = currentUser.getUserRoleList();

            currentUserRoles.forEach( x-> System.out.println(x.toString()));

            // System.out.println(" ........." +);

            HttpSession session = req.getSession();

            session.setAttribute("currentUser", currentUser);
            // session.setAttribute("currentUserRoles", currentUserRoles);

            req.getRequestDispatcher("/dashboard.jsp").forward(req,resp);
            // System.out.print(currentUser.toString());
        } else {
            doGet(req, resp);
        }
    }
}

package task.controller;

import task.model.ApplicationUser;
import task.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete")
public class deleteUser  extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");

        if(id == null)
            req.getRequestDispatcher(req.getContextPath()+"/users").forward(req, resp);

        Integer delId = Integer.parseInt(id);
        ApplicationUser user = UserService.getUserById(delId);

        req.setAttribute("delUser", user);
        req.getRequestDispatcher("/deleteUser.jsp").forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id.equals(null)) {
            doGet(req, resp);
        }else{
            Integer delId = Integer.parseInt(id);

            System.out.println("Id to be Deleted: "+delId);
            UserService.deleteUserById(delId);
            //req.getRequestDispatcher("/users").forward(req,resp);
            resp.sendRedirect("/users");
        }
    }

}

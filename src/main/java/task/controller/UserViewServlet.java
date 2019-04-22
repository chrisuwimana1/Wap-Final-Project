package task.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import task.model.ApplicationUser;
import task.service.UserService;

@WebServlet("/users")
public class UserViewServlet extends HttpServlet {
	
	static String VIEWUSER_PAGE_PATH = "/pages/Users.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ApplicationUser> users = UserService.getAll();
		
		String currentURL = req.getContextPath() + "/users?id=";
		
		req.setAttribute("users", users);
		req.setAttribute("url", currentURL);
		req.getRequestDispatcher(VIEWUSER_PAGE_PATH).forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

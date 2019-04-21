package task.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.service.LoginService;

@WebServlet("/login")
public class LoginServlet extends HttpServlet{
	
	static String LOGIN_PAGE_PATH = "/pages/login.jsp";
	static String LOGIN_QUERY = "SELECT * FROM mydb.APPLICATION_USER WHERE username = '%s' AND password = '%'";
	static String DASHBOARD_PAGE_PATH = "/pages/dashboard.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ApplicationUser> users = new TaskMngtDao<ApplicationUser>().findAll(ApplicationUser.class);
		
		// users.forEach(x -> System.out.println(x.toString()));
		req.getRequestDispatcher(LOGIN_PAGE_PATH).forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter("uname");
		String password = req.getParameter("psw");
		
		List<Object> currentUser = LoginService.login(username, password);
		
		if(currentUser != null && !currentUser.isEmpty()) {
			
			String baseURL = req.getContextPath();
			
			resp.sendRedirect(baseURL);
			// System.out.print(currentUser.toString());
		}else {
			// System.out.println("User: "+ username +", pwd: "+ password +" is not registred");
		}
	}
}

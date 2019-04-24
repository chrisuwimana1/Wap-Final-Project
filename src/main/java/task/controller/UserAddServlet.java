package task.controller;

import task.model.ApplicationUser;
import task.service.UserService;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/adduser")
public class UserAddServlet extends HttpServlet {
	
	static String ADDUSER_PAGE_PATH = "/pages/addUser.jsp";

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(ADDUSER_PAGE_PATH).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String firstname = request.getParameter("firstname");
		String lastname = request.getParameter("lastname");
		String email = request.getParameter("email");
		String team = request.getParameter("team");
		String phone = request.getParameter("phone");
		String location = request.getParameter("location");

		System.out.println("Firstname == "+ firstname);
		System.out.println("username == "+ username);

		ApplicationUser user = new ApplicationUser();
		user.setEmail(email);
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setLastname(location);
		user.setPhone(phone);
		// create a new user to the DB
		UserService.addUser(user);

		response.sendRedirect(request.getContextPath() +"/users");
	}

}

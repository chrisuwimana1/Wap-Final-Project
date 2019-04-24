package task.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.PUT;

import com.google.gson.Gson;
import task.model.ApplicationUser;
import task.service.UserService;

@WebServlet("/users")
public class UserViewServlet extends HttpServlet {
	
	static String VIEWUSER_PAGE_PATH = "/viewUser.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String id = req.getParameter("id");

		if(id == null){
			// view all users
			List<ApplicationUser> users = UserService.getAll();
			req.setAttribute("users", users);
			req.getRequestDispatcher(VIEWUSER_PAGE_PATH).forward(req, resp);

		}else{
			// here we are going to edit one user
			Integer currId = Integer.parseInt(id);

			ApplicationUser editUserById = UserService.getUserById(currId);

			HttpSession session = req.getSession();

			session.setAttribute("editUser", editUserById);
			req.getRequestDispatcher("/editUser.jsp").forward(req, resp);
		}

	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();

		ApplicationUser currUser = (ApplicationUser) session.getAttribute("editUser");
		String username ="";
		if(currUser == null){
			username = req.getParameter("uname");
		}else{
			username = currUser.getUsername();
		}

		String password = req.getParameter("pwd");
		String firstname = req.getParameter("fname");
		String lastname = req.getParameter("lname");
		String email = req.getParameter("email");
		String phone = req.getParameter("phone");
		String location = req.getParameter("location");

		ApplicationUser editedUser = new ApplicationUser();
		editedUser.setUsername(username);
		editedUser.setPassword(password);
		editedUser.setId(currUser.getId());
		editedUser.setFirstname(firstname);
		editedUser.setEmail(email);
		editedUser.setPhone(phone);
		editedUser.setLastname(lastname);
		editedUser.setLocation(location);

		// edited user
		UserService.updateUser(editedUser);
		// redirect to users page
		doGet(req, resp);
	}

}

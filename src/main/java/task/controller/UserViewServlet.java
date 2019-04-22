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
import javax.ws.rs.PUT;

import com.google.gson.Gson;
import task.model.ApplicationUser;
import task.service.UserService;

@WebServlet("/users")
public class UserViewServlet extends HttpServlet {
	
	static String VIEWUSER_PAGE_PATH = "/Users.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<ApplicationUser> users = UserService.getAll();


		List<Map> finalList = new ArrayList<Map>();

		users.forEach(tr->{
			Map<String, Object> map = new HashMap<>();
			map.put("id", tr.getId());
			map.put("firstname", tr.getFirstname());
			map.put("lastname", tr.getLastname());
			map.put("email", tr.getEmail());
			map.put("username", tr.getUsername());
			map.put("password", tr.getPassword());
			
			finalList.add(map);
		});
		
		
		System.out.println("... users "+ users);

		String usersJsonString = new Gson().toJson(finalList);

		PrintWriter out = resp.getWriter();
		resp.setContentType("application/json");
		resp.setCharacterEncoding("UTF-8");
		out.print(usersJsonString);
		out.flush();
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

}

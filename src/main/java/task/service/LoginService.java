package task.service;

import java.util.List;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;

public class LoginService {

	
	// login method which return a list of user
	public static ApplicationUser login(String username, String password) {
		String loginQuery = "SELECT * FROM mydb.APPLICATION_USER " + "WHERE username = '" + username
				+ "' AND password = '" + password + "'";

		List<ApplicationUser> user = new TaskMngtDao<ApplicationUser>().executeNativeQuery(loginQuery,ApplicationUser.class);

                
		return user.get(0);
	}
	
	// logout which take user id and return void 
	public static void logout(int id) {
		
		// TODO later
	}
}

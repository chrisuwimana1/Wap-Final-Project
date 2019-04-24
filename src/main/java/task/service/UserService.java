package task.service;

import java.util.ArrayList;
import java.util.List;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.UserEnum;

public class UserService {

	public static void addUser(Integer id, String username, String password, String firstname, String lastname) {

	}

	public static List<ApplicationUser> getAll() {
		List<ApplicationUser> users = new ArrayList<>();
		TaskMngtDao<ApplicationUser> dao = new TaskMngtDao<>();
		users = dao.findAll(ApplicationUser.class);

		return users;
	}
	
	public void updateUser(ApplicationUser user) {
		TaskMngtDao<ApplicationUser> useDao = new TaskMngtDao<>();
		useDao.edit(user);
		// .....
	}

	public static List<ApplicationUser> getUsersbyRole(UserEnum role){

       String sql = "SELECT * FROM APPLICATION_USER ,USER_ROLE WHERE APPLICATION_USER.ID= USER_ROLE.APPLICATION_USER_ID and USER_ROLE.ROLE_TYPE="+role.getUserRole();

       return new TaskMngtDao<ApplicationUser>().executeNativeQuery(sql,ApplicationUser.class);

	}

}

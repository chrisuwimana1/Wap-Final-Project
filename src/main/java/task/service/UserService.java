package task.service;

import java.util.ArrayList;
import java.util.List;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;

public class UserService {

	public static List<ApplicationUser> getAll() {
		List<ApplicationUser> users = new ArrayList<>();
		TaskMngtDao<ApplicationUser> dao = new TaskMngtDao<>();
		users = dao.findAll(ApplicationUser.class);

		return users;
	}
	
	public static void updateUser(ApplicationUser user) {
		TaskMngtDao<ApplicationUser> useDao = new TaskMngtDao<>();
		useDao.edit(user);
		// .....
	}

	public static boolean addUser(ApplicationUser user){
		new TaskMngtDao<ApplicationUser>().create(user);

		return true;
	}


	public static ApplicationUser getUserById(Integer id){
		return (ApplicationUser) new TaskMngtDao<ApplicationUser>().find(ApplicationUser.class, id);
	}

//
//	public static ApplicationUser updateUser(ApplicationUser user){
//		return (ApplicationUser) new TaskMngtDao<ApplicationUser>().edit(user);
//	}
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.service;

import java.util.Date;
import java.util.List;
import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.Category;
import task.model.Task;

/**
 *
 * @author celem
 */
public class TaskService {

    public static void createNewTask(Integer ownerId, Integer managerId, Integer categoryId, Integer priority, String name, Date dueDate, String description) {
        Task task = new Task(priority, name, dueDate);
        task.setCreationDate(new Date());
        task.setProjectManagerId(new ApplicationUser(managerId));
        task.setTaskOwnerId(new ApplicationUser(ownerId));
        task.setCategoryId(new Category(categoryId));
        task.setDescription(description);
        System.out.println("Task created: " + task);
        new TaskMngtDao<>().create(task);
    }

    public static void updateTask(Integer taskId, Integer ownerId, Integer categoryId, Integer priority, String name, Date dueDate, String description, Integer status) {
        Task task = new TaskMngtDao<Task>().find(Task.class, taskId);
        task.setTaskOwnerId(new ApplicationUser(ownerId));
        task.setDescription(description);
        task.setDueDate(dueDate);
        task.setStatus(status);
        task.setPriority(priority);
        task.setName(name);
        task.setCategoryId(new Category(categoryId));
        System.out.println("Task updated: " + task);
        new TaskMngtDao<>().edit(task);
    }
    
    public static void deleteTask(Integer taskId) {
        Task task = new TaskMngtDao<Task>().find(Task.class, taskId);
        System.out.println("Task deleted: " + task);
        new TaskMngtDao<>().remove(task);
    }
    
    public static Task getTask(Integer taskId) {
        return new TaskMngtDao<Task>().find(Task.class, taskId);
    }

    public static List<Task> getAllTasks() {
        return new TaskMngtDao<Task>().findAll(Task.class);
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.Category;
import task.model.Task;
import task.model.UserRole;
import task.service.TaskService;

/**
 *
 * @author celem
 */
public class TaskServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        System.out.println("operation = " + operation);

        if ("list".equals(operation)) {
            List<Task> allTasks = TaskService.getAllTasks();

            List<Task> list = new TaskMngtDao<Task>().executeNativeQuery("select * from TASK", Task.class);

            List<Map> newList = new ArrayList<>();

            list.stream().map((task) -> {
                Map<String, Object> map = new HashMap();
                map.put("id", task.getId());
                map.put("name", task.getName());
                map.put("categoryId", task.getCategoryId().getId());
                map.put("categoryName", task.getCategoryId().getName());
                map.put("taskOwnerId", task.getTaskOwnerId().getId());
                map.put("taskOwnerName", task.getTaskOwnerId().getFirstname() + " " + task.getTaskOwnerId().getLastname());
                map.put("projectManagerId", task.getProjectManagerId().getFirstname() + " " + task.getProjectManagerId().getLastname());
                map.put("projectManagerName", task.getProjectManagerId().getUsername());
                map.put("priority", task.getPriority());
                map.put("status", task.getStatus());
                map.put("dueDate", task.getDueDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getDueDate()));
                map.put("description", task.getDescription());
                map.put("creationDate", task.getCreationDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getCreationDate()));
                return map;
            }).forEachOrdered((map) -> {
                newList.add(map);
            });

            System.out.println("allTasks = " + allTasks);
            String json = new Gson().toJson(newList);
            try (PrintWriter out = response.getWriter()) {
                response.setContentType("application/json");
                out.write(json);
            }
        } else if ("create".equals(operation) || "update".equals(operation)) {
            String taskName = request.getParameter("taskName");
            String taskId = request.getParameter("taskId");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String taskOwner = request.getParameter("taskOwner");
            String priority = request.getParameter("priority");
            String dueDate = request.getParameter("dueDate");

            ApplicationUser currentUser = (ApplicationUser) request.getSession().getAttribute("currentUser");

            int priorityInt = Integer.valueOf(priority);
            
            int categoryInt = Integer.valueOf(category);
            try {
                Date newDate = getNewDate(dueDate);
                if ("create".equals(operation)) {
                    TaskService.createNewTask(Integer.parseInt(taskOwner), currentUser.getId(), categoryInt, priorityInt, taskName, newDate, description);
                } else {
                    TaskService.updateTask(Integer.parseInt(taskId), Integer.parseInt(taskOwner), categoryInt, priorityInt, taskName, newDate, description);
                }

            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        }

    }

    public Date getNewDate(String date) throws Exception {
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    @Override
    public void init(ServletConfig config) throws ServletException {

        List<ApplicationUser> users = new TaskMngtDao<ApplicationUser>().findAll(ApplicationUser.class);
        List<ApplicationUser> devs = new ArrayList<>();

        users.forEach((user) -> {
            List<UserRole> userRoleList = user.getUserRoleList();
            userRoleList.stream().filter((userRole) -> (userRole.getRoleType() == 3)).forEachOrdered((_item) -> {
                devs.add(user);
            });
        });
        
        List<Category> categories = new TaskMngtDao<Category>().findAll(Category.class);       

        config.getServletContext().setAttribute("categories", categories);
        config.getServletContext().setAttribute("devs", devs);
    }

}

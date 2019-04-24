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
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        HttpSession session = request.getSession();
        
        ApplicationUser currentUser = (ApplicationUser) session.getAttribute("currentUser");
        
        session.setAttribute("PMDisabled", "disabled");
        
        List<UserRole> roles = currentUser.getUserRoleList();
        
        roles.stream().map((role) -> role.getRoleType()).map((roleType) -> {
            if (roleType == 1) {
                System.out.println("1 ========================> roleType = " + roleType);
            }
            return roleType;
        }).map((roleType) -> {
            if (roleType == 2) {
                System.out.println("2 +++++++++++++++++++++++++++roleType = " + roleType);
                session.setAttribute("PMDisabled", "");
            }
            return roleType;
        }).filter((roleType) -> (roleType == 3)).forEachOrdered((item) -> {
            System.out.println("3 ------------------------------roleType = " + item);
            session.setAttribute("DevDisabled", "");
        });
        
        if ("create".equals(operation) || "update".equals(operation) || "delete".equals(operation)) {
            String taskName = request.getParameter("taskName");
            String taskId = request.getParameter("taskId");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String taskOwner = request.getParameter("taskOwner");
            String priority = request.getParameter("priority");
            String status = request.getParameter("status");
            //String dueDate = request.getParameter("dueDate");
            String numberOfDays = request.getParameter("numberOfDays");
            
            try {
                //Date newDate = ;
                if (null != operation) {
                    switch (operation) {
                        case "create":
                            Calendar instance = Calendar.getInstance();
                            instance.add(Calendar.DATE, Integer.parseInt(numberOfDays) + 1);
                            TaskService.createNewTask(Integer.parseInt(taskOwner), currentUser.getId(), Integer.valueOf(category), Integer.valueOf(priority), taskName, instance.getTime(), description);
                            break;
                        case "update":
                            instance = Calendar.getInstance();
                            instance.add(Calendar.DATE, Integer.parseInt(numberOfDays) + 1);
                            TaskService.updateTask(Integer.parseInt(taskId), Integer.parseInt(taskOwner), Integer.valueOf(category), Integer.valueOf(priority), taskName, instance.getTime(), description, Integer.parseInt(status));
                            break;
                        case "delete":
                            TaskService.deleteTask(Integer.parseInt(taskId));
                            break;
                        default:
                            break;
                    }
                }
                
            } catch (Exception e) {
                e.printStackTrace(System.out);
            }
        } else if ("get".equals(operation)) {
            
            Task task = TaskService.getTask(Integer.parseInt(request.getParameter("taskId")));
            Map<String, Object> map = new HashMap();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("categoryId", task.getCategoryId().getId());
            map.put("categoryName", task.getCategoryId().getName());
            map.put("taskOwnerId", task.getTaskOwnerId().getId());
           map.put("team", task.getTaskOwnerId().getTeamId()==null?"":task.getTaskOwnerId().getTeamId().getName());
            map.put("taskOwnerName", task.getTaskOwnerId().getFirstname() + " " + task.getTaskOwnerId().getLastname());
            map.put("projectManagerId", task.getProjectManagerId().getFirstname() + " " + task.getProjectManagerId().getLastname());
            map.put("projectManagerName", task.getProjectManagerId().getUsername());
            map.put("priority", task.getPriority());
            map.put("status", task.getStatus());
            map.put("dueDate", task.getDueDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getDueDate()));
            map.put("description", task.getDescription());
            
            Date now = Calendar.getInstance().getTime();
            Date dueDate = task.getDueDate();
            
            long diffInMillies = dueDate.getTime() - now.getTime();
            long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            
            map.put("numberOfDays", numberOfDays);
            map.put("overDue", diffInMillies > 0 ? "No" : "Yes");
            
            map.put("creationDate", task.getCreationDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getCreationDate()));
           
            System.out.println("task = " + task);
            String json = new Gson().toJson(map);
            System.out.println("json = " + json);
            response.setContentType("application/json");
            try (PrintWriter out = response.getWriter()) {
                
                out.write(json);
                
            }
        }
        
        List<Task> allTasks = TaskService.getAllTasks();
        
        List<Map> newList = new ArrayList<>();
        
        allTasks.stream().map((task) -> {
            Map<String, Object> map = new HashMap();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("categoryId", task.getCategoryId().getId());
            map.put("categoryName", task.getCategoryId().getName());
            map.put("taskOwnerId", task.getTaskOwnerId().getId());
            map.put("team", task.getTaskOwnerId().getTeamId()==null?"":task.getTaskOwnerId().getTeamId().getName());
            map.put("taskOwnerName", task.getTaskOwnerId().getFirstname() + " " + task.getTaskOwnerId().getLastname());
            map.put("projectManagerId", task.getProjectManagerId().getFirstname() + " " + task.getProjectManagerId().getLastname());
            map.put("projectManagerName", task.getProjectManagerId().getUsername());
            map.put("priority", task.getPriority());
            map.put("status", task.getStatus());
            map.put("dueDate", task.getDueDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getDueDate()));
            map.put("description", task.getDescription());
            
            Date now = Calendar.getInstance().getTime();
            Date dueDate = task.getDueDate();
            
            long diffInMillies = dueDate.getTime() - now.getTime();
            long numberOfDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            
            map.put("numberOfDays", numberOfDays);
            map.put("overDue", diffInMillies > 0 ? "No" : "Yes");
            
            map.put("creationDate", task.getCreationDate() == null ? "" : new SimpleDateFormat("MM/dd/yyyy").format(task.getCreationDate()));
            return map;
        }).forEachOrdered((map) -> {
            newList.add(map);
        });
        
        request.setAttribute("tasks", newList);        
        
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
        loadConfig(config);
        
    }
    
    private void loadConfig(ServletConfig config) {
        List<ApplicationUser> users = new TaskMngtDao<ApplicationUser>().findAll(ApplicationUser.class);
        List<ApplicationUser> devs = new ArrayList<>();
        
        users.forEach((user) -> {
            List<UserRole> userRoleList = user.getUserRoleList();
            userRoleList.stream().filter((userRole) -> (userRole.getRoleType() == 3)).forEachOrdered((item) -> {
                devs.add(user);
            });
        });
        
        List<Category> categories = new TaskMngtDao<Category>().findAll(Category.class);
        
        config.getServletContext().setAttribute("categories", categories);
        config.getServletContext().setAttribute("devs", devs);
        
        System.out.println("categories = " + categories);
        System.out.println("devs = " + devs);
    }
}

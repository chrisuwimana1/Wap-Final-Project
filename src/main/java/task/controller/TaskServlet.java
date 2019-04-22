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
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import task.dao.TaskMngtDao;
import task.model.Task;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String operation = request.getParameter("operation");

        if ("list".equals(operation)) {
            List<Task> allTasks = TaskService.getAllTasks();

            List<Task> list = new TaskMngtDao<Task>().executeNativeQuery("select * from TASK", Task.class);

            List<Map> newList = new ArrayList<>();

            list.stream().map((task) -> {
                Map<String, Object> map = new HashMap();
                map.put("name", task.getName());
                map.put("id", task.getId());
                map.put("category", task.getCategoryId().getName());
                map.put("taskOwnerId", task.getCategoryId().getName());
                map.put("projectManagerId", task.getCategoryId().getName());
                map.put("priority", task.getPriority());
                map.put("status", task.getStatus());
                map.put("dueDate", task.getDueDate() == null ? "" : new SimpleDateFormat("MM-dd-yyyy").format(task.getDueDate()));
                map.put("description", task.getDescription());
                map.put("creationDate", task.getCreationDate() == null ? "" : new SimpleDateFormat("MM-dd-yyyy").format(task.getCreationDate()));
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
        } else if ("add".equals(operation)) {
            String taskName = request.getParameter("taskName");
            String description = request.getParameter("description");
            String category = request.getParameter("category");
            String taskOwner = request.getParameter("taskOwner");
            String priority = request.getParameter("priority");
            String dueDate = request.getParameter("dueDate");

            System.out.println(taskName);
            System.out.println(description);
            System.out.println(category);
            System.out.println(taskOwner);
            System.out.println(priority);
            System.out.println(dueDate);

            int priorityInt = Integer.valueOf(priority);
            int categoryInt = Integer.valueOf(category);
            try {
                Date newDate = getNewDate(dueDate);
                System.out.println(newDate);
                TaskService.createNewTask(1, 1, categoryInt, priorityInt, taskName, newDate, description);
                try (PrintWriter out = response.getWriter()) {
                    out.print("Sussess");
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

}

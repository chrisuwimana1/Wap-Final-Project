/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.controller;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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


        List<Task> allTasks = TaskService.getAllTasks();
        
        List<Task> list = new TaskMngtDao<Task>().executeNativeQuery("select * from TASK",Task.class);
        
        list.forEach(task->{
            task.getTaskOwnerId().setApplicationRoleList(null);
            System.out.println(task.getTaskOwnerId().getApplicationRoleList());
        });

        list.forEach(task->{
            task.getProjectManagerId().setApplicationRoleList(null);
            System.out.println(task.getProjectManagerId().getApplicationRoleList());
        });

        list.forEach(task->{
            System.out.println(task.getProjectManagerId().getApplicationRoleList());
        });
        
        //System.out.println("allTasks = " + allTasks);

        String json = new Gson().toJson(list);
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("application/json");
            out.write(json);
        }
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

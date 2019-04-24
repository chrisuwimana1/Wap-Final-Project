/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package task.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import task.model.ApplicationUser;
import task.model.Task;
import task.model.UserRole;
import task.service.TaskService;

/**
 *
 * @author celem
 */

public class NewTaskServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        }
    }
    
    

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
        //processRequest(request, response);
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
        List<Task> tasks = TaskService.getAllTasks();
        
        List<Map> newList = new ArrayList<>();

        tasks.stream().map((task) -> {
            Map<String, Object> map = new HashMap();
            map.put("id", task.getId());
            map.put("name", task.getName());
            map.put("categoryId", task.getCategoryId().getId());
            map.put("categoryName", task.getCategoryId().getName());
            map.put("team", task.getTaskOwnerId().getTeamId()==null?"":task.getTaskOwnerId().getTeamId().getName());
            map.put("taskOwnerId", task.getTaskOwnerId().getId());
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
        
        
        request.getRequestDispatcher("/newTasks.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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

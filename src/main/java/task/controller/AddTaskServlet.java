package task.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import task.model.ApplicationUser;
import task.model.Category;
import task.model.Task;
import java.util.*;
import java.text.SimpleDateFormat;
import task.service.TaskService;

@WebServlet("/AddTaskServlet")
public class AddTaskServlet extends HttpServlet{
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("Inside the servlet");
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
        try{
            Date newDate= getNewDate(dueDate);
            System.out.println(newDate);
            TaskService.createNewTask(1,1, categoryInt,priorityInt,taskName, newDate, description);
        }catch (Exception e){
            System.out.println(e);
        }



        //Get the owner id from the form
        //Get the Project Manager Id from the session

        //OwnerId

        //FIXING THE DATE FORMAT


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    public Date getNewDate(String date) throws Exception{
        return new SimpleDateFormat("yyyy-MM-dd").parse(date);
    }
}

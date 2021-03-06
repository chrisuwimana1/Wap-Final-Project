package task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import task.model.ApplicationUser;
import task.model.Team;
import task.model.UserEnum;
import task.service.TeamService;
import task.service.UserService;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;

@WebServlet(name="addteamservlet" ,urlPatterns = "/addteam")
public class AddTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        List<ApplicationUser> developers = UserService.getUsersbyRole(UserEnum.DEVELOPER);

            System.out.println("There is no id");
            List<ApplicationUser> devs =  developers.stream().filter(d->d.getTeamId()==null).collect(Collectors.toList());

            req.setAttribute("devs",devs);
            req.getRequestDispatcher("createTeam.jsp").forward(req,resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Enumeration parameterNames = req.getParameterNames();

        while(parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String teamelements = req.getParameter("members");



        if(teamelements.length()!=0 || teamelements != null){

            String[] teamArray = teamelements.split(",");
            List<Integer> teamMembers = new ArrayList<>();

            for(int i =0 ;i<teamArray.length;i++){
                int nId = Integer.parseInt(teamArray[i]);

                teamMembers.add(nId);
            }

            int newId = TeamService.createTeam(name,description);

            TeamService.addTeamMember(teamMembers,new Team(newId));
        }




    }


}

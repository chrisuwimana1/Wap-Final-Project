package task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import task.model.ApplicationUser;
import task.model.Team;
import task.model.UserEnum;
import task.service.TeamService;
import task.service.UserService;

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

@WebServlet(name="addservlet" ,urlPatterns = "/addservlet")
public class AddTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ApplicationUser> developers = UserService.getUsersbyRole(UserEnum.DEVELOPER);
        List<Map> devs = new ArrayList<>();

        developers.forEach(t->{

            Map<String,Object> map = new HashMap<>();
            map.put("id", t.getId());
            map.put("firstname", t.getFirstname());
            map.put("lastname",t.getLastname());

            devs.add(map);

        });


        ObjectMapper mapper = new ObjectMapper();
        String newJsonData = mapper.writeValueAsString(devs);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(newJsonData);
        out.close();
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

        String[] teamArray = teamelements.split(",");
        List<ApplicationUser> teamMembers = new ArrayList<>();

        for(int i =0 ;i<teamArray.length;i++){
            ApplicationUser member = new ApplicationUser(new Integer(teamArray[i]));
            teamMembers.add(member);
        }

        int newId = TeamService.createTeam(name,description);

        TeamService.addTeamMember(teamMembers,new Team(newId));


    }
}

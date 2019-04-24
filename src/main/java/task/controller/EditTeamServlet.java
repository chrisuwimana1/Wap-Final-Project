package task.controller;

import task.model.Team;
import task.service.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "editservlet",urlPatterns = "/editteam")
public class EditTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {



        Enumeration parameterNames = req.getParameterNames();

        System.out.println(parameterNames);
        while(parameterNames.hasMoreElements()){
            System.out.println(parameterNames.nextElement());
        }

        String name = req.getParameter("name");
        String description = req.getParameter("description");
        String teamelements = req.getParameter("members");
        String teamId = req.getParameter("teamId");
         System.out.println(teamId+" ...................id..........");

        Team team = new Team(name);
        team.setDescription(description);
        team.setId(Integer.parseInt(teamId));

        TeamService.editTeam(team);

        if(teamelements.length()!=0 || teamelements != null){
            String[] teamArray = teamelements.split(",");
            List<Integer> myteamMembers = new ArrayList<>();

            for(int i =0 ;i<teamArray.length;i++){
                int nId = Integer.parseInt(teamArray[i]);

                myteamMembers.add(nId);
            }
            TeamService.removeTeamMember(myteamMembers);
        }




    }
}

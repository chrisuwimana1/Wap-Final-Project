package task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import task.dao.TaskMngtDao;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "getTeam",urlPatterns = {"/getTeam"})
public class GetTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("teamId")==null){

            List<Team> teamList = TeamService.getTeamList();
            req.setAttribute("teams",teamList);

            req.getRequestDispatcher("viewTeamList.jsp").forward(req,resp);


        }else{
            int teamId = Integer.parseInt(req.getParameter("teamId"));

            Team team = TeamService.getTeambyId(teamId);


            List<ApplicationUser> developers = UserService.getUsersbyRole(UserEnum.DEVELOPER);

            List<ApplicationUser> devs = new ArrayList<>();

            if(!developers.isEmpty()) {

                for (ApplicationUser dev : developers) {

                    if (dev.getTeamId() != null) {
                        if (dev.getTeamId().getId() == team.getId()) {
                            devs.add(dev);
                        }
                    }

                }
            }
             req.setAttribute("team",team);
            if(devs.size() == 0)
                System.out.println("is zero ............");

            System.out.println(devs.toString()+" .f.....................");
             req.setAttribute("developers", devs);


             req.getRequestDispatcher("viewMyTeam.jsp").forward(req,resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getParameter("teamId")!=null){


        int id = Integer.parseInt(req.getParameter("teamId"));

        Team team = TeamService.getTeambyId(id);
        Map<String,Object> myT = new HashMap<>();
        myT.put("id", team.getId());
        myT.put("name",team.getName());
        myT.put("description",team.getDescription());

        List<Map> myteamList = new ArrayList<>();
        myteamList.add(myT);


        ObjectMapper mapper = new ObjectMapper();
        String newJsonData = mapper.writeValueAsString(myteamList);

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.print(newJsonData);
        out.close();

        }

    }
}

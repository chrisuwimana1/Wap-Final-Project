package task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import task.model.Team;
import task.service.TeamService;

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
            List<Map> myTeams = new ArrayList<>();

            teamList.forEach(t->{
                Map<String,Object> map = new HashMap<>();
                map.put("id",t.getId());
                map.put("name",t.getName());
                map.put("description",t.getDescription());
                myTeams.add(map);
            });

            ObjectMapper mapper = new ObjectMapper();
            String newJsonData = mapper.writeValueAsString(myTeams);

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(newJsonData);
            out.close();

        }else{
            int teamId = Integer.parseInt(req.getParameter("teamId"));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}

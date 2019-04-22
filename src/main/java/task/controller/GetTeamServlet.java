package task.controller;

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
import java.util.List;

@WebServlet(name = "getTeam",urlPatterns = {"/getTeam"})
public class GetTeamServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("teamId")==null){

            List<Team> teamMembers = TeamService.getTeamList();

            resp.setContentType("application/json");
            String gson = new Gson().toJson(teamMembers);
            PrintWriter out = resp.getWriter();
            out.print(gson);
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

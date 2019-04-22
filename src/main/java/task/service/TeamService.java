package task.service;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.Team;
import java.util.List;

import java.awt.*;

public class TeamService {

    public static void createTeam(String name){

        Team team = new Team(null,name);

        new TaskMngtDao<Team>().create(team);
    }

    public static Team editTeam(ApplicationUser team){



    }

    public static List<Team> getTeambyId(ApplicationUser id){

        String sql = "SELECT * FROM TEAM WHERE id ="+id;
        List<Object> teams = new TaskMngtDao<Team>().executeNativeQuery(sql);
        return (Team)teams;

    }

    public static List<Team> getAllTeams(){

        return null;

    }
}

package task.service;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.Team;
import java.util.List;

public class TeamService {

    public static void createTeam(String name) {

        Team team = new Team( name);

        new TaskMngtDao<Team>().create(team);
    }

    //public static Team editTeam(ApplicationUser team) {
//
//    }

    public static Team getTeambyId(Integer id) {
      
        return new TaskMngtDao<Team>().find(Team.class, id);

    }

    public static List<Team> getAllTeams() {

        return null;

    }
}

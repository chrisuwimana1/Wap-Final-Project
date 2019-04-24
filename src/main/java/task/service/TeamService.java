package task.service;

import task.dao.TaskMngtDao;
import task.model.ApplicationUser;
import task.model.Team;
import task.model.UserEnum;

import java.util.List;
import java.util.stream.Collectors;

public class TeamService {

    public static int createTeam(String name,String descr) {

        Team team = new Team( name);
        team.setDescription(descr);

        new TaskMngtDao<Team>().create(team);

        return getTeamMaxId();
    }

    public static void editTeam(Team team) {

        new TaskMngtDao<Team>().edit(team);
    }

    public static int getTeamMaxId(){

        String sql = "select ID from TEAM order by ID desc LIMIT 1";
        return new TaskMngtDao<Team>().executeNativeQuery(sql,Team.class).get(0).getId();
    }

    public static Team getTeambyId(Integer id) {
      
        return new TaskMngtDao<Team>().find(Team.class, id);

    }

    public static List<Team> getTeamList() {

        return new TaskMngtDao<Team>().findAll(Team.class);

    }

    //    Add one team member
    public static void addTeamMember(List<ApplicationUser> members,Team team){
        //check if it is a developer

        if(members!=null) {

            List<ApplicationUser> developers = members.stream()
                    .filter(m -> m.getUserRoleList().stream()
                            .anyMatch(r -> r.getId() == UserEnum.DEVELOPER.getUserRole()))
                    .filter(m -> m.getTeamId() == null)
                    .collect(Collectors.toList());


            for (ApplicationUser developer : developers) {

                developer.setTeamId(team);
                new TaskMngtDao<ApplicationUser>().edit(developer);

            }
        }

    }

//    Add one team member
    public static void addTeamMember(ApplicationUser member,Team team){
        //check if it is a developer

      if(member!=null){
          if(member.getUserRoleList().stream().anyMatch(r->r.getId()==UserEnum.DEVELOPER.getUserRole()) && member.getTeamId()==null){
              member.setTeamId(team);
              new TaskMngtDao<ApplicationUser>().edit(member);
              team.getApplicationUserList().add(member);
          }
      }

    }

    //    delete a team member from group
    public  static void removeTeamMember(ApplicationUser member,Team team){

        team.getApplicationUserList().remove(member);
        member.setTeamId(null);
        new TaskMngtDao<ApplicationUser>().edit(member);
    }

    public  static void removeTeamMember(List<ApplicationUser> members,Team team){

        for(ApplicationUser member:members){
            team.getApplicationUserList().remove(member);
            member.setTeamId(null);
            new TaskMngtDao<ApplicationUser>().edit(member);
        }

    }

    public static void deleteTeam(Team team){

        if(!team.getApplicationUserList().isEmpty()){
            team.getApplicationUserList().stream().forEach(member->{
                member.setTeamId(null);
                new TaskMngtDao<ApplicationUser>().edit(member);
            });
         team.getApplicationUserList().clear();
        }
        String sql = "DELETE FROM TEAM WHERE id="+team.getId();
        new TaskMngtDao<Team>().executeNativeQuery(sql,Team.class);
    }
}

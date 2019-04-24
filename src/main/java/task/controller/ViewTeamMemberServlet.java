package task.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import task.model.ApplicationUser;
import task.model.UserEnum;
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
import java.util.stream.Collectors;

@WebServlet(name = "viewTeamMember",urlPatterns = "/getteammember")
public class ViewTeamMemberServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ApplicationUser> developers = UserService.getUsersbyRole(UserEnum.DEVELOPER);
        String id = req.getParameter("teamId");

        if(id!=null){

            System.out.println("My id is  "+ id);

            int teamId = Integer.parseInt(id);

            List<ApplicationUser> devs = new ArrayList<>();

            List<Map> myList =new ArrayList<>();

            if(!developers.isEmpty()){

                for(ApplicationUser dev: developers){

                    if(dev.getTeamId()!=null){
                        if(dev.getTeamId().getId()==teamId){
                            devs.add(dev);
                        }
                    }

                }

                devs.forEach(t->{

                    Map<String,Object> map = new HashMap<>();
                    map.put("id", t.getId());
                    map.put("firstname", t.getFirstname());
                    map.put("lastname",t.getLastname());

                    myList.add(map);

                });
            }

            ObjectMapper mapper = new ObjectMapper();
            String newJsonData = mapper.writeValueAsString(myList);
            System.out.println(newJsonData+" my json here");

            resp.setContentType("application/json");
            PrintWriter out = resp.getWriter();
            out.print(newJsonData);
            out.close();
        }

    }
}

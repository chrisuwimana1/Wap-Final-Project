$(function () {


   $("#teamviewAllBar").click(function () {
        $(".teamlistTable").removeClass("active")
       $("#msform").removeClass("active")


       $.get("getTeam",{dataType:"application/json"})
           .done(function (data) {
                $("#teamListTableBody").empty();

                for (let i = 0; i<data.length ;i++){

                    $("#teamListTableBody").append(" <tr id='"+data[i].id+"' class=\"teamListTableItem\">\n" +
                        "            <td>"+data[i].id+"</td>\n" +
                        "            <td>"+data[i].name+"</td>\n" +
                        "            <td>"+data[i].description+"</td>\n" +
                        "        </tr>")
                }




           })
           .fail(function () {
               alert("failure")
           })
           .always(function () {

               $(".teamlistTable").addClass("active")
           });
   })



    $("#teamAddBar").click(function () {
        $(".teamlistTable").removeClass("active")

        $("#msform").addClass("active")


        
    })


    $("#goToTeamMemberSwitch").click(function () {

        $.get("addservlet",{dataType: "application/json"})
            .done(function (data) {

                $("#teamalluserlist").empty()
                for (let i =0; i<data.length;i++){
                    $("#teamalluserlist").append(" <li draggable=\"true\" id='"+data[i].id+"' ondragstart=\"drag(event)\">"+data[i].firstname+" "+data[i].lastname+"</li>")
                }

            })
            .fail(function () {

            })
            .always(function () {

            })
    })

    $("#getSelectedUsersBtn").click(function () {


        $(".teamconftitle.userList ul").empty();
        $(".teamconftitle.userList ul").append($("#teamnewmemberlist").children())


        $(".teamconftitle.teamname span").text("");
        $(".teamconftitle.teamname span").text($("#addTeamNameInput").val());


        $(".teamconftitle.teamdescr span").text("");
        $(".teamconftitle.teamdescr span").text($("#addTeamNameTextArea").val());
    })



})

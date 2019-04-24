 $(function () {
//
//
//    // $("#teamviewAllBar").click(function () {
//    //      $(".teamlistTable").removeClass("active")
//    //     $("#msform").removeClass("active")
//    //
//    //
//    //     $.get("getTeam",{dataType:"application/json"})
//    //         .done(function (data) {
//    //              $("#teamListTableBody").empty();
//    //
//    //              for (let i = 0; i<data.length ;i++){
//    //
//    //                  $("#teamListTableBody").append(" <tr draggable='false' id='"+data[i].id+"' class=\"teamListTableItem\">\n" +
//    //                      "            <td>"+data[i].id+"</td>\n" +
//    //                      "            <td>"+data[i].name+"</td>\n" +
//    //                      "            <td>"+data[i].description+"</td>\n" +
//    //                      "        </tr>")
//    //              }
//    //
//    //
//    //
//    //
//    //         })
//    //         .fail(function () {
//    //             alert("failure")
//    //         })
//    //         .always(function () {
//    //
//    //             $(".teamlistTable").addClass("active")
//    //         });
//    // })
//
//
//
//     $("#teamAddBar").click(function () {
//         $(".teamlistTable").removeClass("active")
//
//         $("#msform").addClass("active")
//
//         $("#goToTeamMemberSwitch").addClass("addnew")
//
//
//
//     })
//
//
//     $("#goToTeamMemberSwitch.addnew").click(function () {
//
//         $.get("addteamservlet",{dataType: "application/json"})
//             .done(function (data) {
//
//                 $("#teamalluserlist").empty()
//                 for (let i =0; i<data.length;i++){
//                     $("#teamalluserlist").append(" <li draggable=\"true\" id='"+data[i].id+"' ondragstart=\"drag(event)\">"+data[i].firstname+" "+data[i].lastname+"</li>")
//                 }
//
//             })
//             .fail(function () {
//
//             })
//             .always(function () {
//
//             })
//     })
//

//
//
//     $("#teamvieMineBar").click(function () {
//
//         $(".teamlistTable").removeClass("active")
//
//         $("#msform").addClass("active")
//
//         $("#goToTeamMemberSwitch").addClass("viewmyTeam")
//
//         // $.ajax("getTeam",{
//         //     method:'POST',
//         //     data:{
//         //         teamId:2
//         //     }
//         // }).done(function (data) {
//         //
//         //     alert(data)
//         //     $(".teamconftitle.teamname span").text(data[0].name)
//         //     $(".teamconftitle.teamdescr span").text(data[0].description)
//         //
//         //
//         //
//         // })
//         // .fail()
//         //
//         // .always()sidebarsubItem
//
//
//
//
//     })
//
//     $("#goToTeamMemberSwitch.viewmyTeam").click(function () {
//
//         $.get("addteamservlet",{dataType: "application/json"})
//             .done(function (data) {
//
//                 $("#teamalluserlist").empty()
//                 for (let i =0; i<data.length;i++){
//                     $("#teamalluserlist").append(" <li draggable=\"true\" id='"+data[i].id+"' ondragstart=\"drag(event)\">"+data[i].firstname+" "+data[i].lastname+"</li>")
//                 }
//
//             })
//             .fail(function () {
//
//             })
//             .always(function () {
//
//             })
//
//     })
//
//
//
     
     $(".teamListTableItem").click(function () {
        let teamId = $(this).attr("id")
         window.location = "getTeam?teamId="+teamId;


     })

     $("#getSelectedUsersBtn").click(function () {

        console.log("please")

         $(".teamconftitle.userList ul").empty();
         $(".teamconftitle.userList ul").append($("#teamnewmemberlist").children())


         $(".teamconftitle.teamname span").text("");
         $(".teamconftitle.teamname span").text($("#addTeamNameInput").val());


         $(".teamconftitle.teamdescr span").text("");
         $(".teamconftitle.teamdescr span").text($("#addTeamNameTextArea").val());
     })


     $("#createTeamSubmitBtn").click(function(){


    alert("ohhh please")

             var teamName =  $(".teamconftitle.teamname span").text();
             var teamDescr =  $(".teamconftitle.teamdescr span").text();
             var teamMember = getTeamList($(".teamconftitle.userList ul"))

             if (teamMember==null || teamMember.length==0){
                 alert("Can't create a team without Members")
                 window.location.reload();
             }

             alert(teamMember.toString())

             $.ajax("addteam",{

                 method:'POST',
                 data:{
                     name:teamName,
                     description:teamDescr,
                     members:teamMember.toString()
                 }
                      }).done(function (data) {

                          alert("okay")
                 window.location="getTeam"
                      })
                        .fail(function () {
                            alert("fail")
                        })
                        .always(function () {

                            alert("success")
                        })


     })

     $("#viewTeamSubmitBtn").click(function(){


         alert("ohhh please noooooooo")

         var teamName =  $(".teamconftitle.teamname span").text();
         var teamDescr =  $(".teamconftitle.teamdescr span").text();
         var teamMember = getTeamList($(".teamconftitle.userList ul"))
         var teamId  = $(".idOfMyTeam").attr("id");

         alert(teamId)

         $.ajax("editteam",{

             method:'POST',
             data:{
                 name:teamName,
                 description:teamDescr,
                 members:teamMember.toString(),
                 teamId:teamId
             }
                  }).done(function (data) {

                      alert("okay")
             window.location="getTeam"
                  })
                    .fail(function () {

                        alert("fail")
                    })
                    .always(function () {

                    })


     })




 })

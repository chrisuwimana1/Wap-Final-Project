$(function () {
    alert("hello me")
    $.get("getTeam",{dataType:"application/json"})
        .done(function (data) {
            $("#TeamListTableBody").empty();
             console.log(data)
            for (let i=0;i<data.length;i++){

               $("#TeamListTableBody").prepend("<tr>\n" +
                   "            <td>"+data[i].id+"</td>\n" +
                   "            <td>"+data[i].name+"</td>\n" +
                   "            <td>"+data[i].description+"</td>\n" +
                   "        </tr>")
            }
        })
        .error(function () {
            alert("failure")
        })
        .always();


})

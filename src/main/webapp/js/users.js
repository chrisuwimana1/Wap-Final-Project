// this codes deals with users

// add users and delete


$(document).ready(
    function () {
        var  func = $.get("users", {dataType: "application/json"})
            .done(function (data) {
                $("#table").empty();

                for (let i = 0; i < data.length; i++) {
                    let id_str = data[i].id;
                    $("#table").append("<tr><td>" + data[i].firstname + "</td><td>" + data[i].lastname + "</td><td>"
                        +data[i].username + "</td><td>" + data[i].email +
                        "</td><td>" + "<button id='id' style='color: green'>Edit</button>"+

                    "</td></tr>");
                }
            })
            .fail(function (err) {
                alert("error " + err)
            })
            .always();

        $("#profilebtn").click(function () {
            alert("it's working now show the profile");
        });

        $("#usersviewAllBar").click(function () {
            func();
            alert("it's working");
        });

    });
// this codes deals with users

// add users and delete


$(document).ready(
    function () {
        $("#usersviewAllBar").click(function () {
            alert("it's working");
        });


        $("#profilebtn").click(function () {
            alert("it's working now show the profile");
        })

        $.get("users", {dataType: "application/json"})
            .done(function (data) {
                $("#table").empty();

                for (let i = 0; i < data.length; i++) {
                    $("#table").append("<tr><td>" + data[i].firstname + "</td><td>" + data[i].lastname + "</td><td>"
                        +data[i].username + "</td><td>" + data[i].email + "</td></tr>");
                }
            })
            .fail(function (err) {
                alert("error " + err)
            })
            .always();
    });
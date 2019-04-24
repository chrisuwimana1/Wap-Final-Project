// this codes deals with users

// add users and delete


$(document).ready(
    function () {
        var  func = $.get("users", {dataType: "application/json"})
            .done(function (data) {
                $("#table").empty();

                for (let i = 0; i < data.length; i++) {
                    let id_str = data[i].id;
                    $("#table").append("<tr id='"+id_str+"' class='rows' ><td>" + data[i].firstname + "</td><td>"
                        + data[i].lastname + "</td><td>"
                        +data[i].username + "</td><td>" + data[i].email + "</td></tr>");
                }
            })
            .fail(function (err) {
                alert("error " + err)
            })
            .always();

        $("#profilebtn").click(function () {
            // alert("it's working now show the profile");
        });


        // edit one user when you click one the row
        $(document).on("click","tr", function(){
            var currId = $(this).attr('id');
            // alert(currId);

            $.getJSON("users?id="+ currId, function (data) {
                var items = [];
                $.each( data, function( id, value, ) {
                    // items.push( "<li id='" + id + "'>" + firstname +"</li>" );
                });

                $( "<ul/>", {
                    "class": "my-new-list",
                    html: items.join( "" )
                }).appendTo( "body" );
            });

            $.ajax("users?id="+ currId,{
                method : "GET",
                data : {
                    username: username,
                    password : password,
                    firstname : firstname,
                    lastname : lastname,
                    email : email,
                    team : team
                }
            }).done(function () {
//                alert("done");
            }).fail(function () {
                alert("fail");
            });
            // alert("curr Id" + currId);
        });



        $("#usersviewAllBar").click(function () {
            // cancel callling func
            // func;
        });


        // add user
        $("userAddBar").click(function () {
            alert("Add user bar");
        });


        // pop up for add user
        $("#addUserBtn").on("click", function(){
            $(".popup, .popup-content").addClass("active");
        });

        $(".close, .popup").on("click", function(){
            $(".popup, .popup-content").removeClass("active");
        });



        // Send data to the adduser servlet so that we can add to the database
        $("#addUser").click(function () {
            var erros = $("#errors");
            var  flag = true;
            erros.empty();

            var username = $("input[name='uname']").val();
            var password = $("input[name='psw']").val();
            var firstname = $("input[name='fname']").val();
            var lastname = $("input[name='lname']").val();
            var email = $("input[name='email']").val();
            var team = $("input[name='team']").val();

            alert( username + " " +password+ " "+ firstname +" "+ lastname+ " "+ email+ " "+ team);

            if(username === "" || password === "" || firstname === "" || lastname === "" || email === ""){
                flag = false;
                erros.append("Please fill in all the fields! ");
            }

            if(flag){
                let data = {
                    'username': username,
                    'password' : password,
                    'firstname' : firstname,
                    'lastname' : lastname,
                    'email' : email,
                    'team' : team
                };

                $.ajax("adduser", {
                    method : "POST",
                    data : {
                        username: username,
                        password : password,
                        firstname : firstname,
                        lastname : lastname,
                        email : email,
                        team : team
                    }
                }).done(function () {
                    alert("success!")
                }).fail(function () {
                    alert("fail!")
                });

                $.post()
            }
        });

    });
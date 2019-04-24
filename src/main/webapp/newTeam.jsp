<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Wap Project | Blue Group</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="stylesheet" href="css/createTeam.css" type="text/css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat" type="text/css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>
    <script src="js/createTeam.js"></script>

</head>
<body>

<!-- multistep form -->
<form id="msform" >
    <!-- progressbar -->
    <ul id="progressbar">
        <li class="active">Add Group Details</li>
        <li>Add Members</li>
        <li>Submit</li>
    </ul>
    <!-- fieldsets -->
    <fieldset>
        <h2 class="fs-title">Create A New Team</h2>
        <h3 class="fs-subtitle">This is step 1</h3>
        <input type="text" name="name" placeholder="Team Name" />
        <textarea cols="20" rows="10" placeholder="Add Description..."></textarea>
        <input type="button" name="next" class="next action-button" value="Next" />
    </fieldset>

    <fieldset>
        <h2 class="fs-title">Add Team Member</h2>
        <h3 class="fs-subtitle">This is Step 2</h3>
        <h6>Error here</h6>
        <div id="teamusercontainer">

          <div id="teamallusers">

              <p>Drag From Here</p>
              <ul id="teamalluserlist" ondrop="drop(event)" ondragover="allowDrop(event)">
                  <li draggable="true" id="1" ondragstart="drag(event)">Christophe Habin</li>
                  <li draggable="true" id="2" ondragstart="drag(event)">Celestin Mbuyang</li>
                  <li draggable="true" id="3" ondragstart="drag(event)">David Mmtbz</li>
                  <li draggable="true" id="4" ondragstart="drag(event)">Christian</li>
              </ul>

          </div>

            <div id="teamnewmembers" >
                 <p>Drag To Here</p>
                <ul id="teamnewmemberlist" ondrop="drop(event)" ondragover="allowDrop(event)">

                </ul>

            </div>
        </div>

        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="button" name="next" class="next action-button" value="Next" />
    </fieldset>

    <fieldset>
        <h2 class="fs-title">Confirm and Add</h2>
        <h3 class="fs-subtitle">This is Step 3</h3>
        <div id="teamconfcontainer">


            <div class="teamconftitle"><b>Group Name:</b> <span>Group B</span></div>
            <div class="teamconftitle"><b>Group Description:</b> <span>Group B</span></div>
            <div class="teamconftitle"><b>Group Members:</b>
            <ul>

                <li >Celestin Mbuyang</li>
                <li >David Mmtbz</li>
                <li >Christian</li>
        </ul>

        </div>

        </div>
        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="submit" name="submit" class="submit action-button" value="Submit" />
    </fieldset>
</form>

</body>
</html>


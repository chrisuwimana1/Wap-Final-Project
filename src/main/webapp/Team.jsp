<%--
  Created by IntelliJ IDEA.
  User: christophehabineza
  Date: 2019-04-22
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<link rel="stylesheet" href="css/createTeam.css" type="text/css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat" type="text/css">

<link rel="stylesheet" type="text/css" href="css/Team.css">

<div class="teamlistTable table-responsive-md">

    <table id="table_id" class="display table table-striped">

        <thead class="thead-dark">
        <tr>
            <th>Team Id</th>
            <th>Team Name</th>
            <th>Team Description</th>
        </tr>
        </thead>
        <tbody id="teamListTableBody">
        <tr id="" class="teamListTableItem">

        </tr>
        </tbody>


    </table>

</div>

<div style="width:100%;background: #eeeeee;height:auto;">

    <form id="msform" class="" >
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
        <input type="text" id="addTeamNameInput" name="name" placeholder="Team Name" required/>
        <textarea cols="5" rows="3" placeholder="Add Description..." id="addTeamNameTextArea" required></textarea>
        <input type="button" name="next" id="goToTeamMemberSwitch" class="next action-button" value="Next" />
    </fieldset>

    <fieldset>
        <h2 class="fs-title">Add Team Member</h2>
        <h3 class="fs-subtitle">This is Step 2</h3>
        <h6>Error here</h6>
        <div id="teamusercontainer">

            <div id="teamallusers">

                <p>Drag From Here</p>
                <ul id="teamalluserlist" ondrop="drop(event)" ondragover="allowDrop(event)">
<%--                    <li draggable="true" id="1" ondragstart="drag(event)">Christophe Habin</li>--%>
<%--                    <li draggable="true" id="2" ondragstart="drag(event)">Celestin Mbuyang</li>--%>
<%--                    <li draggable="true" id="3" ondragstart="drag(event)">David Mmtbz</li>--%>
<%--                    <li draggable="true" id="4" ondragstart="drag(event)">Christian</li>--%>
                </ul>

            </div>

            <div id="teamnewmembers" >
                <p>Drag To Here</p>
                <ul id="teamnewmemberlist" ondrop="drop(event)" ondragover="allowDrop(event)">

                </ul>

            </div>
        </div>

        <input type="button" name="previous" class="previous action-button" value="Previous" required/>
        <input type="button" name="next" id="getSelectedUsersBtn" class="next action-button" value="Next" required />
    </fieldset>

    <fieldset>
        <h2 class="fs-title">Confirm and Add</h2>
        <h3 class="fs-subtitle">This is Step 3</h3>
        <div id="teamconfcontainer">


            <div class="teamconftitle teamname"><b>Group Name:</b> <span>Group B</span></div>
            <div class="teamconftitle teamdescr"><b>Group Description:</b> <span>Group B</span></div>
            <div class="teamconftitle userList"><b>Group Members:</b>
                <ul>

<%--                    <li >Celestin Mbuyang</li>--%>
<%--                    <li >David Mmtbz</li>--%>
<%--                    <li >Christian</li>--%>
                </ul>

            </div>

        </div>
        <input type="button" name="previous" class="previous action-button" value="Previous" />
        <input type="button" name="submit" class="submit action-button" value="Submit" />
    </fieldset>
</form>

</div>


<%--
  Created by IntelliJ IDEA.
  User: celem
  Date: 4/19/2019
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="js/team.js"></script>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
    <%--         Javascript are at the bottom--%>
</head>
<body>
    
    <%@ include file="" %>
<table id="datatable" class="display">
    <thead>
    <tr>
        <th>Id</th>
        <th>Status</th>
        <th>Category</th>
        <th>Owner</th>
        <th>Project Manager</th>
        <th>Priority</th>
        <th>Task name</th>
        <th>Due Date</th>
    </tr>
    </thead>
    <tfoot>
    <tr>
        <th>Id</th>
        <th>Status</th>
        <th>Category</th>
        <th>Owner</th>
        <th>Project Manager</th>
        <th>Priority</th>
        <th>Task name</th>
        <th>Due Date</th>
    </tr>
    </tfoot>
</table>

<style type="text/css">
    /* The Modal (background) */


    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0, 0, 0); /* Fallback color */
        background-color: rgba(0, 0, 0, 0.4); /* Black w/ opacity */
        padding-top: 60px;
    }

    /* Modal Content/Box */
    .modal-content {
        background-color: #fefefe;
        margin: 5px auto; /* 15% from the top and centered */
        border: 1px solid #888;
        width: 80%; /* Could be more or less, depending on screen size */
    }

    /* The Close Button */
    .close {
        /* Position it in the top right corner outside of the modal */
        position: absolute;
        right: 25px;
        top: 0;
        color: #000;
        font-size: 35px;
        font-weight: bold;
    }

    /* Close button on hover */
    .close:hover,
    .close:focus {
        color: red;
        cursor: pointer;
    }

    /* Add Zoom Animation */
    .animate {
        -webkit-animation: animatezoom 0.6s;
        animation: animatezoom 0.6s
    }

    @-webkit-keyframes animatezoom {
        from {
            -webkit-transform: scale(0)
        }
        to {
            -webkit-transform: scale(1)
        }
    }

    @keyframes animatezoom {
        from {
            transform: scale(0)
        }
        to {
            transform: scale(1)
        }
    }

    #task-name-error, #task-owner-error{
        color: red;
    }


</style>

<script>
    // Get the modal
    var modal = document.getElementById('id01');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    }
</script>

<!-- Button to open the modal login form -->
<button onclick="document.getElementById('id01').style.display = 'block'">New Task</button>
<div id="id01" class="modal">
            <span onclick="document.getElementById('id01').style.display = 'none'"
                  class="close" title="Close Modal">&times;</span>

    <!-- Modal Content -->
    <form class="modal-content animate" method="post">
        <div class="container">
            <%--                    <label for="taskname"><b>Task Name: </b></label>--%>

            <p id ="task-name-error"></p>
            Task Name:
            <input id="taskname" type="text" placeholder="Enter Task name" name="taskname" required>
            <br><br>
            <label for="description"><b>Description: </b></label>
            <textarea id="description" rows="4" cols="50">
                    </textarea>
            <br><br>
            <label for="category">Category: </label>
            <select id="category">
                <option value="1">Personal</option>
                <option value="2">Business</option>
            </select>
            <br><br>
                <p id ="task-owner-error"></p>
            <label for="taskowner">Task Owner</label>
            <input id="taskowner" type="text" placeholder="Task Owner" name="taskowner" required>
            <br><br>
            <label for="priority">Task Priority: </label>
            <select id="priority">
                <option value="1">Low</option>
                <option value="2">Medium</option>
                <option value="3">High</option>
                >
            </select>
            <br><br>
            <label>Due Date: </label>
            <input id ="duedate" type="date" name="dueDate">
            <br><br>
            <button id = "add-task" type="submit">Add</button>
        </div>

        <div class="container" style="background-color:#f1f1f1">
            <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancelbtn">
                Cancel
            </button>
        </div>
    </form>
</div>

<%--        Java Script --%>
<script src="js/scripts/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="js/scripts/jquery.dataTables.min.js" type="text/javascript"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="application/javascript" src="js/task/add-task.js"></script>
</body>
</html>
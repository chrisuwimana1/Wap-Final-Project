<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="css/task/task.css" rel="stylesheet" type="text/css"/>

<div id = "task-main-container">
    <table id="datatable" class="display">
        <thead>
            <tr>
                <th>id</th>
                <th>Task name</th>
                <th>Category</th>
                <th>Status</th>
                <th>Owner</th>
                <th>Project Manager</th>
                <th>Priority</th>
                <th>Due Date</th>
            </tr>
        </thead>
        <tfoot>
            <tr>
                <th>id</th>
                <th>Task name</th>
                <th>Category</th>
                <th>Status</th>
                <th>Owner</th>
                <th>Project Manager</th>
                <th>Priority</th>
                <th>Due Date</th>
            </tr>
        </tfoot>
    </table>

    <button id="new-task">New >> Task</button>


    <div id="myModal" class="task-modal">
        <!-- Modal content -->
        <div class="task-modal-content">
            <div class="task-modal-header">
                <span id="task-close" class="task-close">&times;</span>
                <h3 id="taskform-title"></h3>
            </div>
            <div class="task-modal-body">
                <p>
                    <label for="description">Task Name: </label>
                    <input id="taskname" type="text" placeholder="Enter Task name" name="taskname" required>
                    <input id="taskId" type="hidden" name="taskId">
                </p>
                <p>
                    <label for="description">Description: </label>
                    <textarea id="description" rows="4" cols="50"></textarea>
                </p>
                <p>
                    <label for="category">Category: </label>
                    <select id="category">
                        <option value=""></option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>   
                    </select>
                </p>
                <p>
                    <label for="taskowner">Task Owner: </label>
                    <select id="taskowner">
                        <option value=""></option>
                        <c:forEach var="dev" items="${devs}">
                            <option value="${dev.id}">${dev.firstname} ${dev.lastname}</option>
                        </c:forEach>                   
                    </select>
                </p>
                <p>
                    <label for="priority">Task Priority: </label>
                    <select id="priority">
                        <option value=""></option>
                        <option value="1">Low</option>
                        <option value="2">Medium</option>
                        <option value="3">High</option>                    
                    </select>
                </p>
                <p>
                    <label for="duedate">Due Date: </label>
                    <input id ="duedate" type="date" name="dueDate">
                </p>
            </div>
            <div class="task-modal-footer">
                <button id="create-task">Create</button> <button id="update-task">Update</button> <button id="delete-task">Delete</button>
            </div>
        </div>
    </div>
</div>

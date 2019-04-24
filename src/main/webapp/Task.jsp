<link href="css/task/task.css" rel="stylesheet" type="text/css"/>

<div id = "task-main-container">
    <table id="datatable" class="display task-list-table">
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
                <th>Overdue</th>
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
                <th>Overdue</th>
            </tr>
        </tfoot>
    </table>

    <button id="new-task" ${sessionMap.newTask}>New Task</button>

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
                    <input id="taskname" type="text" placeholder="Enter Task name" name="taskname" required ${PMDisabled}>
                    <input id="taskId" type="hidden" name="taskId">
                    <input id="rowId" type="hidden" name="rowId">
                    <input id="creationDate" type="hidden" name="creationDate">
                </p>
                <p>
                    <label for="description">Description: </label>
                    <textarea id="description" rows="4" cols="50" ${PMDisabled}></textarea>
                </p>
                <p>
                    <label for="category">Category: </label>
                    <select id="category" ${PMDisabled}>
                        <option value=""></option>
                        <c:forEach var="category" items="${categories}">
                            <option value="${category.id}">${category.name}</option>
                        </c:forEach>   
                    </select>
                </p>
                <p>
                    <label for="taskowner">Task Owner: </label>
                    <select id="taskowner" ${PMDisabled}>
                        <option value=""></option>
                        <c:forEach var="dev" items="${devs}">
                            <option value="${dev.id}">${dev.firstname} ${dev.lastname}</option>
                        </c:forEach>                   
                    </select>
                </p>
                <p>
                    <label for="priority">Task Priority: </label>
                    <select id="priority" ${PMDisabled}>
                        <option value=""></option>
                        <option value="1">Low</option>
                        <option value="2">Medium</option>
                        <option value="3">High</option>                    
                    </select>
                </p>
                <p style="display: none">
                    <label for="duedate">Due Date: </label>
                    <input id ="duedate" type="date" name="dueDate">
                </p>
                <p>
                    <label for="status" >Status: </label>
                    <select id="status" ${PMDisabled}>
                        <option value=""></option>
                        <option value="0">Initialization</option>
                        <option value="1">In progress</option>
                        <option value="2">Completed</option>                    
                    </select>
                </p>
                <p>
                    <label for="numberOfDays">Number Of Days: </label>
                    <input id ="numberOfDays" type="number" name="numberOfDays" ${PMDisabled}>
                </p>
            </div>
            <div class="task-modal-footer">
                <button id="create-task" ${PMDisabled}>Create</button> <button id="update-task" ${PMDisabled}>Update</button> <button id="delete-task" ${PMDisabled}>Delete</button>
            </div>
        </div>
    </div>
</div>

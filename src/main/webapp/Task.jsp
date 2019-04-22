<div id = "main-container">
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
</div>
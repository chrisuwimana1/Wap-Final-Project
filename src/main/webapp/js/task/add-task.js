$(document).ready(function () {
    $('#add-task').click(function () {

        //validate task name & Task Owner

        if ($('#taskname').val() === ""){
            $('#task-name-error').text("The task Name field is required");
        }else if ($('#taskowner').val() === "") {
            $('#task-owner-error').text("The task Owner field is required");
        }else {
            $.post("AddTaskServlet",
                {
                    taskName: $('#taskname').val(),
                    description: $('#description').val(),
                    category: $('#category').val(),
                    taskOwner: $('#taskowner').val(),
                    priority: $('#priority').val(),
                    dueDate: $('#duedate').val(),
                }, function (data, status) {
                    alert("Data: " + data.taskName + "\nStatus: " + status);
                });
        }
    });
});


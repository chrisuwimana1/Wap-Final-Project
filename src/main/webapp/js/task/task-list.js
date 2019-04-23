$(document).ready(function () {

    $('#task-close').click(function () {
        $('#myModal').css('display', 'none');
    });


    $('#new-task').click(function () {
        $('#taskform-title').text('Create Task');
        $('input[type=date], input[type=text], select').val('');
        $('#create-task').css('display', '');
        $('#update-task').css('display', 'none');
        $('#delete-task').css('display', 'none');
        $('#myModal').css('display', 'block');
    });

    $('#create-task').click(function () {
        //validate task name & Task Owner

        if ($('#taskname').val() === "") {
            $('#task-name-error').text("The task Name field is required");
        } else if ($('#taskowner').val() === "") {
            $('#task-owner-error').text("The task Owner field is required");
        } else {
            $.ajax("TaskServlet", {
                method: "POST",
                data: {
                    taskName: $('#taskname').val(),
                    description: $('#description').val(),
                    category: $('#category').val(),
                    taskOwner: $('#taskowner').val(),
                    priority: $('#priority').val(),
                    dueDate: $('#duedate').val(),
                    operation: 'create'
                }
            }).done(function (data) {
                $('input[type=date], input[type=text], select').val('');
                $('textarea').val('');
                loadTasks();
            }).fail(function (a, b, c) {
                alert("Fail: " + a + " >> " + b + " >> " + c);
            }).always(function () {
                alert("Always");
            });
        }
    });

    $('#update-task').click(function () {
        //validate task name & Task Owner

        if ($('#taskname').val() === "") {
            $('#task-name-error').text("The task Name field is required");
        } else if ($('#taskowner').val() === "") {
            $('#task-owner-error').text("The task Owner field is required");
        } else {
            $.ajax("TaskServlet", {
                method: "POST",
                data: {
                    taskName: $('#taskname').val(),
                    taskId: $('#taskId').val(),
                    description: $('#description').val(),
                    category: $('#category').val(),
                    taskOwner: $('#taskowner').val(),
                    priority: $('#priority').val(),
                    dueDate: $('#duedate').val(),
                    operation: 'update'
                }
            }).done(function (data) {
                $('#myModal').css('display', 'none');
                loadTasks();
            }).fail(function (a, b, c) {
                alert("Fail: " + a + " >> " + b + " >> " + c);
            }).always(function () {
                alert("Always");
            });
        }
    });


    $('#taskViewAllBar').click(loadTasks);

    function loadTasks() {
        $("#datatable").css('display', 'none');
        $.ajax("TaskServlet",
                {
                    method: "POST",
                    dataType: "json",
                    data: {
                        operation: 'list'
                    }}).done(function (data) {
            console.log(data);
            $("#datatable").css('display', '');

            table = $("#datatable").addClass('nowrap').DataTable({
                data: data,
                responsive: true,
                columnDefs: [
                    {targets: [-1, -3], className: 'dt-body-right'}
                ],
                sort: true,
                searching: true,
                paging: true,
                aLengthMenu: [[2, 5, 10, -1], [2, 5, 10, "All"]],
                iDisplayLength: 10,
                columns: [
                    {'data': 'id'},
                    {'data': 'name'},
                    {'data': 'categoryName'},
                    {'data': 'status'},
                    {'data': 'taskOwnerName'},
                    {'data': 'projectManagerName'},
                    {'data': 'priority'},
                    {'data': 'dueDate'}
                ]
            });

            $('#datatable tbody').on('click', 'tr', function () {
                $('#taskname').empty();
                $('#taskname').val(table.row(this).data().name);
                $('#taskId').val(table.row(this).data().id);
                $('#description').val(table.row(this).data().description);

                var categoryName = table.row(this).data().categoryName.trim();
                $("#category option").filter(function () {
                    //may want to use $.trim in here
                    return $(this).text().trim() === categoryName.trim();
                }).prop('selected', true);

                var taskOwnerName = table.row(this).data().taskOwnerName.trim();

                $("#taskowner option").filter(function () {
                    //may want to use $.trim in here
                    return $(this).text().trim() === taskOwnerName.trim();
                }).prop('selected', true);

                var priority = table.row(this).data().priority;

                $("#priority option").filter(function () {
                    //may want to use $.trim in here
                    return $(this).val() == priority;
                }).prop('selected', true);

                $('#dueDate').val(new Date());
                $('#create-task').css('display', 'none');
                $('#update-task').css('display', '');
                $('#delete-task').css('display', '');
                $('#taskform-title').text('Task Details');
                $('#myModal').css('display', 'block');
            });

            $('#datatable tfoot').empty();
            // Setup - add a text input to each footer cell
            $('#datatable tfoot tr').clone(true).appendTo('#datatable tfoot');

            $('#datatable tfoot tr:eq(1) th').each(function (i) {
                title = $(this).text();
                $(this).html('<input  type="text" placeholder="Search ' + title + '" />');

                $('input', this).on('keyup change', function () {
                    if (table.column(i).search() !== this.value) {
                        table.column(i).search(this.value).draw();
                    }
                });
            });
        }).always(function () {
        }).fail(function () {
        });
    }
});
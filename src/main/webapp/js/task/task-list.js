$(document).ready(function () {

    $('#myTable').DataTable();

    $("#byOwner").on("keyup", function () {
        // Declare variables
        var filter, tr, td, i, txtValue;

        filter = this.value.toUpperCase();
        tr = $('#myTable tr');

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[6];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    });


    //filter by Priority
    $("#byPriority").on("keyup", function () {
        // Declare variables
        var filter, tr, td, i, txtValue;

        filter = this.value.toUpperCase();
        tr = $('#myTable tr');

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[4];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    });

    //filter by Team ID
    $("#byTeam").on("keyup", function () {
        // Declare variables
        var filter, tr, td, i, txtValue;

        filter = this.value.toUpperCase();
        tr = $('#myTable tr');

        // Loop through all table rows, and hide those who don't match the search query
        for (i = 0; i < tr.length; i++) {
            td = tr[i].getElementsByTagName("td")[5];
            if (td) {
                txtValue = td.textContent || td.innerText;
                if (txtValue.toUpperCase().indexOf(filter) > -1) {
                    tr[i].style.display = "";
                } else {
                    tr[i].style.display = "none";
                }
            }
        }
    });

    $("#myInput").on("keyup", function () {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function () {
            $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1);
        });
    });

    var table;

    $('#task-close').click(function () {
        $('#myModal').css('display', 'none');
    });

    $(".tskLstItm").dblclick(function () {

        $('.error-message').text('');

        let data = {
            taskId: $(this).attr("id"),
            operation: 'get'
        };
        $.ajax("TaskServlet", {
            data: data,
            method: "POST",
            dataType: "json"
        }).done(function (data) {
            $('#taskname').val(data.name);
            $('#taskId').val(data.id);
            //$('#rowId').val(table.row(this).id());
            $('#description').val(data.description);
            $('#numberOfDays').val(data.numberOfDays);

            var categoryName = data.categoryName.trim();
            $("#category option").filter(function () {
                //may want to use $.trim in here
                return $(this).text().trim() === categoryName.trim();
            }).prop('selected', true);

            var status = data.status;
            $("#status option").filter(function () {
                //may want to use $.trim in here
                return $(this).val() == status;
            }).prop('selected', true);

            var taskOwnerName = data.taskOwnerName.trim();

            $("#taskowner option").filter(function () {
                //may want to use $.trim in here
                return $(this).text().trim() === taskOwnerName.trim();
            }).prop('selected', true);

            var priority = data.priority;

            $("#priority option").filter(function () {
                //may want to use $.trim in here
                return $(this).val() == priority;
            }).prop('selected', true);

            $('#dueDate').val(new Date('12/04/2018'));

            //$('#dueDate').val(new Date());
            $('#create-task').css('display', 'none');
            $('#update-task').css('display', '');
            $('#delete-task').css('display', '');
            $('#taskform-title').text('Task Details');
            $('#myModal').css('display', 'block');
        });
    });


    $('#new-task').click(function () {
        $('.error-message').text('');
        $('#taskform-title').text('Create Task');
        $('input[type=date], input[type=text], select').val('');
        $('input[type=number]').val('0');
        $('#create-task').css('display', '');
        $('#update-task').css('display', 'none');
        $('#delete-task').css('display', 'none');
        $('#statusP').css('display', 'none');
        $('textarea').val('');
        $('#myModal').css('display', 'block');
    });

    $('#create-task').click(function (event) {
        //validate task name & Task Owner

        event.preventDefault();

        let category = $('#category option:selected').val();
        let taskowner = $('#taskowner option:selected').val();
        let priority = $('#priority option:selected').val();
        let taskname = $('#taskname').val();
        //let status = $('#status').val();
        let numberOfDays = $('#numberOfDays').val();
        let submit = true;

        $('.error-message').empty();

        if (category === "") {
            $('#category-error').html("Category name is required");
            submit = false;
        }

        if (taskowner === "") {
            $('#taskowner-error').html("Task owner is required");
            submit = false;
        }
        if (priority === "") {
            $('#priority-error').html("Priority is required");
            submit = false;
        }

        if (taskname === "") {
            $('#taskname-error').html("Task name is required");
            submit = false;
        }

        if (numberOfDays < 1) {
            $('#number-of-days-error').html("NOD must be > 0");
            submit = false;
        }

        if (submit) {
            $.ajax("TaskServlet", {
                method: "POST",
                data: {
                    taskName: $('#taskname').val(),
                    description: $('#description').val(),
                    category: $('#category').val(),
                    taskOwner: $('#taskowner').val(),
                    priority: $('#priority').val(),
                    dueDate: $('#duedate').val(),
                    numberOfDays: $('#numberOfDays').val(),
                    operation: 'create'
                }
            }).done(function (data) {
                $('input[type=number], input[type=text], select').val('');
                $('textarea').val('');
                //table.row.add(data);
            }).fail(function (a, b, c) {
                alert("Fail: " + a + " >> " + b + " >> " + c);
            }).always(function () {
                window.location = "tasks";
            });
        } else {

        }
    });

    $('#update-task').click(function () {
        //validate task name & Task Owner

        event.preventDefault();

        let category = $('#category option:selected').val().trim();
        let taskowner = $('#taskowner option:selected').val();
        let priority = $('#priority option:selected').val();
        let taskname = $('#taskname').val();
        let status = $('#status option:selected').val();
        let numberOfDays = $('#numberOfDays').val();
        let submit = true;

        if (status === "") {
            $('#category-error').html("Status is required");
            submit = false;
        }

        if (category === "") {
            $('#category-error').html("Category is required");
            submit = false;
        }

        if (taskowner === "") {
            $('#taskowner-error').html("Task owner is required");
            submit = false;
        }
        if (priority === "") {
            $('#priority-error').html("Priority is required");
            submit = false;
        }

        if (taskname === "") {
            $('#taskname-error').html("Task name is required");
            submit = false;
        }

        if (numberOfDays < 1) {
            $('#number-of-days-error').html("NOD must be > 0");
            submit = false;
        }

        if (submit) {
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
                    numberOfDays: $('#numberOfDays').val(),
                    status: $('#status').val(),
                    operation: 'update'
                }
            }).done(function (data) {
                $('#myModal').css('display', 'none');
                //$('#datatable').DataTable().ajax.reload();
            }).fail(function (a, b, c) {
                alert("Fail: " + a + " >> " + b + " >> " + c);
            }).always(function () {
                window.location = "tasks";
            });
        }
    });

    $('#delete-task').click(function () {
        $.ajax("TaskServlet", {
            method: "POST",
            data: {
                taskId: $('#taskId').val(),
                operation: 'delete'
            }
        }).done(function (data) {
            $('#myModal').css('display', 'none');
            //alert($('#rowId').val());
            //$('#datatable').data.reload();
            //$('#datatable').DataTable().draw();

            //$('#datatable').DataTable().ajax.reload();

            //;
        }).fail(function (a, b, c) {
            alert("Fail: " + a + " >> " + b + " >> " + c);
        }).always(function () {
            window.location = "tasks";
        });

    });

    $('#taskViewAllBar').click(loadTasks);

    function loadTasks() {

        $("#datatable").css('display', 'none');
        $.ajax("TaskServlet",
                {
                    method: "POST",
                    dataType: "application/json",
                    data: {
                        operation: 'list'
                    }}).done(function (data) {
            console.log(data);
            $("#datatable").css('display', '').addClass('nowrap');
            table = $("#datatable").DataTable({
                data: data,
                responsive: true,
                retrieve: true,
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
                    {'data': 'dueDate'},
                    {'data': 'overDue'}
                ]
            });


            $('#datatable tbody').on('dblclick', 'tr', function () {
                $('#taskname').empty();
                $('#taskname').val(table.row(this).data().name);
                $('#taskId').val(table.row(this).data().id);
                $('#rowId').val(table.row(this).id());
                $('#description').val(table.row(this).data().description);
                $('#numberOfDays').val(table.row(this).data().numberOfDays);

                var categoryName = table.row(this).data().categoryName.trim();
                $("#category option").filter(function () {
                    //may want to use $.trim in here
                    return $(this).text().trim() === categoryName.trim();
                }).prop('selected', true);

                var status = table.row(this).data().status;
                $("#status option").filter(function () {
                    //may want to use $.trim in here
                    return $(this).val() == status;
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

                $('#dueDate').val(new Date('12/04/2018'));

                //$('#dueDate').val(new Date());
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

            //
        }).always(function () {

        }).fail(function () {
        });
    }
});
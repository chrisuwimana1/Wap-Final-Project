$(document).ready(function () {

    $('#add-task').click(function () {
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
                    operation: 'add'
                }
            }).done(function (data) {
                alert("Success");
            }).fail(function (a, b, c) {
                alert("Fail: " + a + " >> " + b + " >> " + c);
            }).always(function () {
                alert("Always");
            });
        }
    });


    $('#taskViewAllBar').click(function () {
        $.ajax("TaskServlet",
                {
                    method: "GET",
                    dataType: "json",
                    data: {
                        operation: 'list'
                    }}).done(function (data) {
            console.log(data);
            table = $("#datatable").DataTable({
                data: data,
                sort: true,
                searching: true,
                paging: true,
                aLengthMenu: [[2, 5, 10, -1], [2, 5, 10, "All"]],
                iDisplayLength: 10,
                columns: [
                    {'data': 'id'},
                    {'data': 'name'},
                    {'data': 'category'},
                    {'data': 'status'},
                    {'data': 'taskOwnerId'},
                    {'data': 'projectManagerId'},
                    {'data': 'priority'},
                    {'data': 'dueDate'}
                ]
            });

            $('#datatable tbody').on('click', 'tr', function () {
                alert(table.row(this).data().id);
            });

            // Setup - add a text input to each footer cell
            $('#datatable tfoot tr').clone(true).appendTo('#datatable tfoot');
            $('#datatable tfoot tr:eq(1) th').each(function (i) {
                title = $(this).text();
                $(this).html('<input type="text" placeholder="Search ' + title + '" />');

                $('input', this).on('keyup change', function () {
                    if (table.column(i).search() !== this.value) {
                        table.column(i).search(this.value).draw();
                    }
                });
            });
        }).always(function () {
        }).fail(function () {
        });
    });
});
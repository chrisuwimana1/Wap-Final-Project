$(document).ready(function () {
    alert('hhhhhhhhhhhhhhhhh')
    $.ajax("TaskServlet",
        {
            method: "GET",
            dataType: "json"
        }).done(function (data) {
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
                {'data': 'status'},
                {'data': 'categoryId.name'},
                {'data': 'taskOwnerId.username'},
                {'data': 'projectManagerId.username'},
                {'data': 'priority'},
                {'data': 'name'},
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
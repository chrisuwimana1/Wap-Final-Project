<%--
  Created by IntelliJ IDEA.
  User: celem
  Date: 4/19/2019
  Time: 2:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Title</title>
        <link href="css/jquery.dataTables.min.css" rel="stylesheet" type="text/css"/>
        <script src="js/scripts/jquery-3.3.1.min.js" type="text/javascript"></script>
        <script src="js/scripts/jquery.dataTables.min.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $.ajax("http://jsonplaceholder.typicode.com/users",
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
                            {'data': 'name'},
                            {'data': 'username'},
                            {'data': 'email'},
                            {'data': 'phone'},
                            {'data': 'website'},
                            {'data': 'company.name'},
                            {'data': 'address.street'}
                        ]
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
        </script>
    </head>
    <body>
        <table id="datatable" class="display">
            <thead>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Web site</th>
                    <th>Company</th>
                    <th>Address</th>
                </tr>
            </thead>
            <tfoot>
                <tr>
                    <th>Id</th>
                    <th>Name</th>
                    <th>Username</th>
                    <th>Email</th>
                    <th>Phone</th>
                    <th>Web site</th>
                    <th>Company</th>
                    <th>Address</th>
                </tr>
            </tfoot>
        </table>

    </body>
</html>
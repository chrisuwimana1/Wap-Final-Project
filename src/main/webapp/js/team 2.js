$(function () {


   $("#teamviewAllBar").click(function () {
        $(".teamlistTable").hide()


       $.get("getTeam",{dataType:"application/json"})
           .done(function (data) {
               // $("#TeamListTableBody").empty();
               table = $("#table_id").DataTable({
                   data: data,
                   sort: true,
                   searching: true,
                   retrieve:true,
                   paging: true,
                   aLengthMenu: [[2, 5, 10, -1], [2, 5, 10, "All"]],
                   iDisplayLength: 10,
                   columns: [
                       {'data': 'id'},
                       {'data': 'name'},
                       {'data': 'description'}

                   ]
               });

               $('#table_id tbody').on('click', 'tr', function () {
                   alert(table.row(this).data().id);
               });

               // Setup - add a text input to each footer cell
               $('#table_id tfoot tr').clone(true).appendTo('#datatable tfoot');
               $('#table_id tfoot tr:eq(1) th').each(function (i) {
                   title = $(this).text();
                   $(this).html('<input type="text" placeholder="Search ' + title + '" />');

                   $('input', this).on('keyup change', function () {
                       if (table.column(i).search() !== this.value) {
                           table.column(i).search(this.value).draw();
                       }
                   });
               });
           })
           .fail(function () {
               alert("failure")
           })
           .always(function () {

                $(".teamlistTable").show();
           });
   })



    $("#teamAddBar").click(function () {
        
    })

})

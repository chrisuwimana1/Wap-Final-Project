<%--
  Created by IntelliJ IDEA.
  User: christophehabineza
  Date: 2019-04-22
  Time: 10:58
  To change this template use File | Settings | File Templates.
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div>
    <div class="">
        <!-- Content Wrapper. Contains page content -->
        <div class="">
            <!-- Content Header (Page header) -->
            <section class="content-header">
                <h1>
                    List of registered users
                    <small>| task management</small>
                </h1>
            </section>

            <!-- Main content -->
            <section class="">
                <div class="row">
                    <div class="col-xs-12">
                        <div class="box">
                            <!-- /.box-header -->
                            <div class="box-body">
                                <table class="table table-bordered table-hover">
                                    <thead>
                                    <tr>
                                        <th>First Name</th>
                                        <th>Last Name</th>
                                        <th>Username</th>
                                        <th>Email</th>
                                        <th>View / Edit</th>
                                    </tr>
                                    </thead>
                                    <tbody id="table">


                                    </tbody>
                                </table>
                            </div>
                            <!-- /.box-body -->
                        </div>

                    </div>
                    <!-- /.col -->
                </div>
                <!-- /.row -->
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <div class="control-sidebar-bg"></div>
    </div>
</div>
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
                <div class="popup-overlay col-md-6">
                    <!--Creates the popup content-->
                    <div class="popup-content">
                        <h2>Edit user -- name here</h2>

                        <!--popup's close button-->
                        <div id="errors"></div>

                        <form action="adduser" method="post" class="form-inline">
                            <div class="container">
                                <label for="uname"><b>Username</b></label>
                                <input id="uname" type="text" placeholder="Enter Username" name="uname" required>
                                <br>

                                <label for="psw"><b>Password</b></label>
                                <input id="psw" type="password" placeholder="Enter Password" name="psw" required>
                                <br>

                                <label for="fname"><b>Firstname</b></label>
                                <input id="fname" type="text" placeholder="Firstname" name="fname" required>
                                <br>

                                <label for="lname"><b>Lastname</b></label>
                                <input id="lname" type="text" placeholder="Lastname" name="lname" required>
                                <br>

                                <label for="email"><b>Email</b></label>
                                <input id="email" type="email" placeholder="Email" name="email" required>
                                <br>

                                <label for="team"><b>Team</b></label>
                                <input id="team" type="email" placeholder="Team" name="team" >
                                <br>

                                <select id="roles">
                                    <option name="role1" value="role1">Programmer</option>
                                    <option name="role2" value="role2">Developer</option>
                                </select>

                                <button id="addUser" type="button">Add user</button>
                            </div>
                        </form>

                        <button class="close">Close</button>
                    </div>
                </div>

                <button id="addUserBtn" class="btn-default">Add new user</button>
            </section>
            <!-- /.content -->
        </div>
        <!-- /.content-wrapper -->

        <div class="control-sidebar-bg"></div>
    </div>
</div>
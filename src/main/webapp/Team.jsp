<%--
  Created by IntelliJ IDEA.
  User: christophehabineza
  Date: 2019-04-22
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>

<div style="width: 100%;height: 200vh;background: lightgreen;color:white">
    <c:forEach items="${teamMembers}" var="member">
        <div><c:out value="${member.name}"> </c:out> </div>
    </c:forEach>

</div>
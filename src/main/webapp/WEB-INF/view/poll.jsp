<%@ page import="java.util.List" %>
<%@ page import="model.Poll" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<br>
<br>
<div style="text-align: center " ><h1><p style="font-size: 100px; color: #b8daff" >Welcome</p> </h1><br></div>
<br>
<h1>
    <div style="text-align: center ">
        <br><br>
        <%
            List<Poll> pollList = (List<Poll>) request.getAttribute("pollList");
            for (Poll poll : pollList) {
        %>
        <a href="${pageContext.request.contextPath}/poll/beginpoll?pollId=<%=poll.getId()%>">
            <br>
            <br>
            <%=poll.getName()%></a>
        <br/>
        <%
            }
        %>

    </div>
</h1>


</body>
</html>

<%@ page import="model.Result" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Hello World</title>
</head>
<body>

<%
    Result result = (Result) request.getAttribute("result");
%>
<h1><%=result.getExplanation()%></h1>
<br>
<a href="${pageContext.request.contextPath}/poll">к списку  ответам</a>
</body>
</html>

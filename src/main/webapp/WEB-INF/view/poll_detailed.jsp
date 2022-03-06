<%@ page import="java.util.List" %>

<%@ page import="model.Question" %>
<%@ page import="model.Answer" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World</title>
</head>
<body>
<br>
<br>
<div style="text-align: center " ><h3><p style="font-size: 100px; color: #b8daff" ></p> </h3><br></div>
<br>
<h1>
    <div style="text-align: center ">
        <br><br>
        <form action="${pageContext.request.contextPath}/poll/result" method="post">
            <%
                List<Question> questionList = (List<Question>) request.getAttribute("questionList");
                for (Question question : questionList) {
            %>
            <h1><%=question.getText()%></h1>
            <br/>
            <%
                for (Answer answer : question.getAnswers()) {
            %>
            <div style="display: flex">
                <label>
                    <input type="radio" name="<%=question.getId()%>_question" value="<%=answer.getId()%>"/>
                </label>
                <p><%=answer.getText()%></p>
            </div>
            <%
                    }
                }
            %>
            <br/>
            <input type="submit" value="Save"/>
        </form>
    </div>
</h1>

</body>
</html>

<%@ taglib prefix="C" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Todo's for ${name}</title>
    <link href="webjars/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container">
    <table class="table table-striped">
        <caption>Your Todos are</caption>
        <thead>
        <tr>
            <th>Description</th>
            <th>Target Date</th>
            <th>Is it done?</th>
            <th>Delete</th>
        </tr>
        </thead>
        <tbody>
        <C:forEach items="${todos}" var="todo">
            <tr>
                    <%--calls getter and setter method by standard naming convention--%>
                <td>${todo.desc}</td>
                <td>${todo.targetDate}</td>
                <td>${todo.done}</td>
                <td><a  href="/delete-todo?id=${todo.id}" type="button" class="btn btn-warning">Delete</a></td>
            </tr>
        </C:forEach>
        </tbody>
    </table>
    <div><a class="button" href="/add-todo">Add a Todo</a></div>
    <script src="webjars/jquery/1.9.1/jquery.min.js"></script>
    <script src="webjars/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</div>
</body>
</html>
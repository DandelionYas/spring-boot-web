<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
    <font color="red">${errorMessage}</font>
    <form method="post">
        Name: &nbsp&nbsp&nbsp&nbsp&nbsp <input type="text" name="name"/><br/><br/>
        Password: <input type="password" name="password"/><br/><br/>
        &nbsp&nbsp&nbsp&nbsp&nbsp <input type="submit"/>
    </form>
</div>
<%@ include file="common/footer.jspf" %>
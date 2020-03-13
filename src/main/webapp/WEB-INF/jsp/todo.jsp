<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>
<div class="container">
    <%-- modelAttribute is the name of Attribute added to model in old version was commandName --%>
    <form:form method="post" modelAttribute="todo">
        <fieldset class="form-group">
            <form:hidden path="id"/>
            <form:label path="desc">Description</form:label>
            <form:input path="desc" class="form-control" type="text" required="required"/>
            <form:errors path="desc" cssClass="text-warning"/>
        </fieldset>
        <fieldset class="form-group">
            <form:label path="targetDate">Date</form:label>
            <form:input path="targetDate" class="form-control" type="text" required="required"/>
            <form:errors path="targetDate" cssClass="alert-warning"/>
        </fieldset>
        <button type="submit" class="btn btn-success">Add</button>
    </form:form>
</div>
<%@ include file="common/footer.jspf"%>
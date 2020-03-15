<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container tab-content">
    <p class="bg-danger h3">An exception occurred! please contact support.</p>
    <p class="bg-warning h4">
        error: ${error}<br/>
        status: ${status}<br/>
        exception: ${exception}<br/>
        url: ${url}<br/>
    </p>
</div>
<%@ include file="common/footer.jspf" %>
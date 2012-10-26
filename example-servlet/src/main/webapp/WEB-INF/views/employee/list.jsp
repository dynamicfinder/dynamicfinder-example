<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
<jsp:attribute name="pageTitle">Finder4J Servlet Example</jsp:attribute>
<jsp:attribute name="pageContent">
    <legend>Employee Data</legend>

    <form class="form-horizontal">
        <span class="form-group">
            <label class="form-label" for="firstName">First Name</label>
            <input id="firstName" name="firstName" type="text" value="${firstName}" /><br/>
        </span>

        <span class="form-group">
            <label class="form-label" for="lastName">Last Name</label>
            <input id="lastName" name="lastName" type="text"  value="${lastName}" /><br/>
        </span>

        <button type="submit" class="btn">Search</button>
    </form>

    <table class="table table-hover table-bordered">
    <tr>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Gender</th>
    <tr>
    <c:forEach items="${employees}" var="employee">
    <tr>
        <td>${employee.firstName}</td>
        <td>${employee.lastName}</td>
        <td>${employee.gender == 'M' ? 'Male' : 'Female'}</td>
    </tr>
    </c:forEach>
    </table>
    <div class="pagination">
        <ul>
        <c:forEach items="${pages}" var="page">
            <c:choose>
            <c:when test="${currentPage == page}">
            <li class="disabled"><span>${page}</span></li>
            </c:when>
            <c:otherwise>
            <li>
            <a href="${_root}/employee/list?page=${page}&firstName=${firstName}&lastName=${lastName}">
                ${page}
            </a>
            </li>
            </c:otherwise>
            </c:choose>
        </c:forEach>
        </ul>
    </div>
</jsp:attribute>
</t:template>
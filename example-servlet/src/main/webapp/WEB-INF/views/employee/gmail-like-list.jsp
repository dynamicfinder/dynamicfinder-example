<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
<jsp:attribute name="pageTitle">Dynamic Finder Example Servlet - Simple List</jsp:attribute>
<jsp:attribute name="pageContent">
    <legend>Gmail Like Search - Employee Data</legend>

    <div class="alert">
        Another use case for dynamicfinder usage. Use 'and' for criteria/restriction logic and 
        <code>firstName:&lt;some_first_name&gt; lastName:&lt;some_last_name&gt; gender:&lt;'M'_or_'F'&gt;</code>, 
        format 'Search Criteria' input. (Not the support for parse
        <code>field1:&lt;value1&gt; field2:&lt;value2&gt; etc</code> string is not 
        part of dynamicfinder. Feel free to add feature request in our issues if 
        you found this is a big advantages for you an or community).
    </div>

    <form class="form-horizontal">
        <div class="input-append">
        <input class="span2" id="appendedInputButton" name="keyword" size="30" 
            type="text" placeholder="Search Criteria" value="${keyword}">
        <button class="btn" type="submit">Search</button>
        <button class="btn" type="button" onclick="javsacript:go('${_root}/employee/gmail-like-list');">Clear</button>
        </div>
    </form>

    <span class="badge badge-info">Generated Query: <code>${queryStringLog}</code></span><br/>
    <span class="badge badge-info">Generated Count Query: <code>${countQueryStringLog}</code></span>
    <br/><br/>

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
            <a href="${_root}/employee/gmail-like-list?page=${page}&keyword=${keyword}">
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
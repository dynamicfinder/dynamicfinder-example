<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:template>
<jsp:attribute name="pageTitle">Dynamic Finder Example Servlet - Simple List</jsp:attribute>
<jsp:attribute name="pageContent">
    <legend>Simple Search - Employee Data</legend>

    <div class="alert">
        Basic use case for dynamicfinder usage. Use 'or' for criteria/restriction  
        logic.
    </div>

    <form class="form-horizontal">
        <span class="control-group">
            <label class="control-label" for="firstName">First Name</label>
            <div class="controls">
                <input id="firstName" name="firstName" type="text" value="${firstName}" />
            </div>
        </span>

        <span class="control-group">
            <label class="control-label" for="lastName">Last Name</label>
            <div class="controls">
                <input id="lastName" name="lastName" type="text"  value="${lastName}" />
            </div>
        </span>

        <span class="control-group">
            <label class="control-label" for="gender">Gender</label>
            <div class="controls">
	            <select id="gender" name="gender">
	                <option${gender == '' ? ' selected="selected"' : ''} value="">- - any - -</option>
	                <option${gender == 'M' ? ' selected="selected"' : ''} value="M">Male</option>
	                <option${gender == 'F' ? ' selected="selected"' : ''} value="F">Female</option>
	            </select>
	            <br/><br/>
	            <button type="submit" class="btn">Search</button> &nbsp; 
                <button type="button" class="btn" onclick="javsacript:go('${_root}/employee/simple-list');">Clear</button>
            </div>
        </span>
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
            <a href="${_root}/employee/simple-list?page=${page}&firstName=${firstName}&lastName=${lastName}&gender=${gender}">
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
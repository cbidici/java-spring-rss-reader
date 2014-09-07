<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<h1>${user.username}</h1>

<c:forEach items="${user.blogs}" var="blog">
	<h1>${blog.name}</h1>
	<p>${blog.url}</p>

	<table class="table table-bordered table-hover table-striped">
		<thead>
			<tr>
				<th><spring:message code="page.table.label.userdetail.title" /></th>
				<th><spring:message code="page.table.label.userdetail.link" /></th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${blog.items}" var="item">
				<tr>
					<td>${item.title}</td>
					<td>${item.link}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>

</c:forEach>
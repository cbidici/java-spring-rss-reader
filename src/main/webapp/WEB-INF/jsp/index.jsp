<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th><spring:message code="page.table.label.index.date" /></th>
			<th><spring:message code="page.table.label.index.description" /></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${items}" var="item">
			<tr>
				<td><c:out value="${item.publishDate}" /></td>
				<td>
					<strong>
						<a href="<c:out value='${item.link}' />" target="_blank">
							<c:out value="${item.title}" />
						</a>
					</strong>
					<br/>
					${item.description}
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

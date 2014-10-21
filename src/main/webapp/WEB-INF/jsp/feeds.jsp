<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<h1>${user.username}</h1>

<!-- Nav tabs -->
<ul class="nav nav-tabs" role="tablist">
	<c:forEach items="${user.blogs}" var="blog">
		<li><a href="#blog_${blog.id}" role="tab" data-toggle="tab">${blog.name}</a></li>
	</c:forEach>
</ul>

<!-- Tab panes -->
<div class="tab-content">
	<c:forEach items="${user.blogs}" var="blog">
		<div class="tab-pane" id="blog_${blog.id}">
			<h1><c:out value="${blog.name}" /></h1>
			<p><c:out value="${blog.url}" /></p>
		
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th><spring:message code="page.table.label.feeds.date" /></th>
						<th><spring:message code="page.table.label.feeds.description" /></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${blog.items}" var="item">
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
		</div>
	</c:forEach>
</div>

<script type="text/javascript">
$(document).ready(
	function(){
		$('.nav-tabs a:first').tab('show') // Select first tab
	}		
);
</script>
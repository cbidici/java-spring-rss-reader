<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp" %>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th><spring:message code="page.table.label.users.username" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${users}" var="user">
			<tr>
				<td><a href="<spring:url value="/users/${user.id}.html" />"><c:out value="${user.username}" /></a></td>
				<td><a href='<spring:url value="/users/remove/${user.id}.html" />' class="btn btn-danger triggerRemove"><spring:message code="page.button.label.users.deluser" /></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<div class="modal fade" id="modalConfirmRemove" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal">
					<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
				</button>
				<h4 class="modal-title" id="myModalLabel"><spring:message code="page.common.confirm" /></h4>
			</div>
			<div class="modal-body">
				<spring:message code="page.common.askconfirm" />
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="page.common.cancel" /></button>
				<a href="" class="btn btn-danger doRemove"><spring:message code="page.common.yes" /></a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	$(document).ready(
		function(){
			$('.triggerRemove').click(
				function(e){
					e.preventDefault();
					$('#modalConfirmRemove .doRemove').attr('href', $(this).attr('href'));
					$('#modalConfirmRemove').modal();
				}
			);
		}	
	);
</script>
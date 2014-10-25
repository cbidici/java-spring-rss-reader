<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<h1>${user.username}</h1>


<!-- Button trigger modal -->
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#addFeedModal">
	<spring:message code="page.button.label.account.addfeed" />
</button>

<br/>
<br/>

<table class="table table-bordered table-hover table-striped">
	<thead>
		<tr>
			<th><spring:message code="page.table.label.account.feedname" /></th>
			<th><spring:message code="page.table.label.account.feedurl" /></th>
			<th></th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${user.feeds}" var="feed">
			<tr>
				<td><c:out value="${feed.name}" /></td>
				<td><c:out value="${feed.url}" /></td>
				<td><a href='<spring:url value="/user/feed/remove/${feed.id}.html" />' class="btn btn-danger triggerRemove"><spring:message code="page.button.label.account.delfeed" /></a></td>
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

<form:form id="addFeedForm" commandName="feed" cssClass="form-horizontal">
	<!-- Modal -->
	<div class="modal fade" id="addFeedModal" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">
						<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">
						<spring:message code="page.modal.title.addfeed" />
					</h4>
				</div>
				<div class="modal-body">
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">
							<spring:message code="page.form.label.account.addfeed.name" /> :
						</label>
						<div class="col-sm-10">
							<form:input path="name" cssClass="form-control" />
							<form:errors path="name" />
						</div>
					</div>
					<div class="form-group">
						<label for="url" class="col-sm-2 control-label">
							<spring:message code="page.form.label.account.addfeed.url" /> :
						</label>
						<div class="col-sm-10">
							<form:input path="url" cssClass="form-control" />
							<form:errors path="url" />
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="page.button.label.account.addfeed.close" /></button>
					<input type="submit" class="btn btn-primary" value="<spring:message code="page.button.label.account.addfeed.save" />"/>
				</div>
			</div>
		</div>
	</div>
</form:form>

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
			$("#addFeedForm").validate(
				{
					rules : {
						name : {
							required : true,
							minlength : 1,
							maxlength : 256
						},
						url : {
							required : true,
							minlength : 1,
							url : true
						}
					},
					messages : {
						name : "<spring:message code='com.cbstd.rssr.entity.feed.name.size' arguments='name,32,5' />", 
						url : "<spring:message code='com.cbstd.rssr.entity.feed.url.url' />",
					},
					highlight : function(element){
						$(element).closest(".form-group").removeClass("has-success").addClass("has-error");
					},
					unhighlight : function(element){
						$(element).closest(".form-group").removeClass("has-error").addClass("has-success");
					},
					errorElement : "div"
				}
			);
		}	
	);
</script>
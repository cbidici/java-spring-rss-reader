<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<c:if test="${register eq 'success'}">
	<div class="alert alert-success">
		<spring:message code="page.alert.register.success" />
	</div>
</c:if>

<form:form id="registerationForm" commandName="user" cssClass="form-horizontal">

	<div class="form-group">
		<label for="username" class="col-sm-2 control-label">
			<spring:message code="page.form.label.register.username" />
		</label>
		<div class="col-sm-10">
			<form:input path="username" cssClass="form-control" />
			<form:errors path="username" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label">
			<spring:message code="page.form.label.register.email" />
		</label>
		<div class="col-sm-10">
			<form:input path="email" cssClass="form-control" />
			<form:errors path="email" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">
			<spring:message code="page.form.label.register.password" />
		</label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
			<form:errors path="password" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label">
			<spring:message code="page.form.label.register.passwordagain" />
		</label>
		<div class="col-sm-10">
			<input type="password" id="passwordAgain" name="passwordAgain" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<div class="col-sm-offset-2 col-sm-10">
			<button type="submit" class="btn btn-large btn-primary">
				<spring:message code="page.form.label.register.signup" />
			</button>
		</div>
	</div>

</form:form>

<script type="text/javascript">
$(document).ready(
	function(){
		$("#registerationForm").validate(
			{
				rules : {
					username : {
						required : true,
						minlength : 5,
						maxlength : 32,
						remote : {
							url : "<spring:url value='/register/available.html' />",
							type : "get",
							data : {
								username : function(){
									return $("#username").val();
								}
							}
						}
					},
					email : {
						required : true,
						minlength : 1,
						email : true
					},
					password : {
						required : true,
						minlength : 5,
						maxlength : 256
					},
					passwordAgain : {
						required : true,
						minlength : 5,
						maxlength : 256,
						equalTo : "#password"
					}
				},
				messages : {
					username : {
						required : "<spring:message code='com.cbstd.rssr.entity.User.username.size' arguments='username,32,5' />",
						minlength : "<spring:message code='com.cbstd.rssr.entity.User.username.size' arguments='username,32,5' />",
						maxlength : "<spring:message code='com.cbstd.rssr.entity.User.username.size' arguments='username,32,5' />",
						remote : "<spring:message code='com.cbstd.rssr.entity.User.username.exists' />"
					}, 
					email : "<spring:message code='com.cbstd.rssr.entity.User.email.email' />",
					password : "<spring:message code='com.cbstd.rssr.entity.User.password.size' arguments='password,256,5' />",
					passwordAgain : "<spring:message code='com.cbstd.rssr.entity.User.password.again' />"
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
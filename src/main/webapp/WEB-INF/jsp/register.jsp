<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<form:form commandName="user" cssClass="form-horizontal">
	<div class="form-group">
		<label for="username" class="col-sm-2 control-label"><spring:message
				code="page.form.label.register.username" /></label>
		<div class="col-sm-10">
			<form:input path="username" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="email" class="col-sm-2 control-label"><spring:message
				code="page.form.label.register.email" /></label>
		<div class="col-sm-10">
			<form:input path="email" cssClass="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label for="password" class="col-sm-2 control-label"><spring:message
				code="page.form.label.register.password" /></label>
		<div class="col-sm-10">
			<form:password path="password" cssClass="form-control" />
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
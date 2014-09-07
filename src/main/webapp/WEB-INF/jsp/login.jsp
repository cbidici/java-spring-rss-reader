<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<style>
body {
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading, .form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

<form class="form-signin" role="form" action='/j_spring_security_check'
	method='POST'>
	<h2 class="form-signin-heading"><spring:message code="page.form.info.loginrequired" /></h2>
	<input type="text" class="form-control" name="j_username"
		placeholder="<spring:message code="page.form.label.login.username" />" required autofocus> <input
		type="password" class="form-control" placeholder="<spring:message code="page.form.label.login.password" />"
		name="j_password" required>
	<button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="page.form.label.login.login" /></button>
</form>

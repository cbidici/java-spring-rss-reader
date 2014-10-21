<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles-extras"
	prefix="tilesx"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>

<%@ include file="/WEB-INF/layout/taglibs.jsp"%>

<tilesx:useAttribute name="pageId" />

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
	
<link rel="stylesheet" href="/css/jquery.sidr.light.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>

<script
	src="http://ajax.aspnetcdn.com/ajax/jquery.validate/1.13.0/jquery.validate.min.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title><spring:message code="page.title.${pageId}" /></title>
</head>
<body>

	<div class="container">

		<!-- Static navbar -->
		<div class="navbar navbar-default" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target=".navbar-collapse">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href='<spring:url value="/" />'>RSSR</a>
				</div>
				<div class="navbar-collapse collapse">
					<ul class="nav navbar-nav">
						<li class="${pageId == 'index' ? 'active' : ''}">
							<a href='<spring:url value="/" />'>
								<spring:message code="page.navigation.home" />
							</a>
						</li>
						<security:authorize access="hasRole('ROLE_ADMIN')">
							<li class="${pageId == 'users' || pageId == 'userdetail' ? 'active' : ''}">
								<a href="<spring:url value="/users.html" />">
									<spring:message code="page.navigation.users" />
								</a>
							</li>
						</security:authorize>
						<security:authorize access="!isAuthenticated()">
							<li class="${pageId == 'register' ? 'active' : ''}">
								<a href="<spring:url value="/register.html" />">
									<spring:message code="page.navigation.register" />
								</a>
							</li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="${pageId == 'feeds' ? 'active' : ''}">
								<a href="<spring:url value="/user/feeds.html" />">
									<spring:message code="page.navigation.feeds" />
								</a>
							</li>
							<li class="${pageId == 'account' ? 'active' : ''}">
								<a href="<spring:url value="/user/account.html" />">
									<spring:message code="page.navigation.account" />
								</a>
							</li>
						</security:authorize>
						<security:authorize access="!isAuthenticated()">
							<li class="${pageId == 'login' ? 'active' : ''}">
								<a href="<spring:url value="/login.html" />">
									<spring:message code="page.navigation.login" />
								</a>
							</li>
						</security:authorize>
						<security:authorize access="isAuthenticated()">
							<li class="">
								<a href="<spring:url value="/logout.html" />">
									<spring:message code="page.navigation.logout" />
								</a>
							</li>
						</security:authorize>
					</ul>
					<div class="navbar-right">
						<a href="?lang=tr_TR">TR</a> | <a href="?lang=en_US">EN</a>
					</div>
				</div>
				<!--/.nav-collapse -->
			</div>
			<!--/.container-fluid -->
		</div>


		<tiles:insertAttribute name="body" />
		<br /> <br />
		<tiles:insertAttribute name="footer" />

	</div>
</body>
</html>
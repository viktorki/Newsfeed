<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<title><spring:message code="title.login" /></title>
</head>
<body>
	<div class="container">
		<form action="login.do" method="post">
			<c:if test="${not empty registered }">
				<div class="alert alert-success">
					<spring:message code="message.registered" />
					"
				</div>
			</c:if>
			<c:if test="${not empty error }">
				<div class="alert alert-danger">
					<spring:message code="error.wrong.username.or.password" />
				</div>
			</c:if>
			<div class="row">
				<div class="col-md-2 col-md-offset-4">
					<label for="username"><spring:message code="label.username" /></label>
				</div>
				<div class="col-md-2">
					<input name="username" id="username" class="form-control" required />
				</div>
			</div>
			<div class="row">
				<div class="col-md-2 col-md-offset-4">
					<label for="password"><spring:message code="label.password" /></label>
				</div>
				<div class="col-md-2">
					<input type="password" name="password" id="password"
						class="form-control" required />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4 col-md-offset-4">
					<a href="profile/new"><spring:message code="label.register" />è</a>
					<button class="btn btn-primary pull-right" name="submit"
						type="submit">
						<span class="glyphicon glyphicon-log-in" aria-hidden="true"></span>
						<spring:message code="label.login" />
					</button>
				</div>
			</div>
		</form>
	</div>
</body>
</html>
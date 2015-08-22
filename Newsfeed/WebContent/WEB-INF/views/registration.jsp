<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
<title><spring:message code="title.registration" /></title>
</head>
<body>
	<div class="container">
		<div class="alert alert-danger hide" id="passwordsNotMatch">
			<span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>
			<spring:message code="error.passwords.does.not.match" />
		</div>
		<form:form method="post" commandName="user"
			enctype="multipart/form-data" id="frm">
			<div class="page-header">
				<h1>
					<spring:message code="title.registration" />
				</h1>
			</div>
			<div class="row">
				<div class="col-md-3">
					<form:label path="username">
						<spring:message code="label.username" />
					</form:label>
					<form:input path="username" class="form-control"
						required="required" />
				</div>
				<div class="col-md-3">
					<form:label path="password">
						<spring:message code="label.password" />
					</form:label>
					<form:input type="password" path="password" id="password"
						class="form-control" required="required" />
				</div>
				<div class="col-md-3">
					<label for="confirmPassword"><spring:message
							code="label.confirm.password" /></label> <input type="password"
						id="confirmPassword" class="form-control" required="required" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-3">
					<form:label path="firstName">
						<spring:message code="label.first.name" />
					</form:label>
					<form:input path="firstName" class="form-control"
						required="required" />
				</div>
				<div class="col-md-3">
					<form:label path="lastName">
						<spring:message code="label.last.name" />
					</form:label>
					<form:input path="lastName" class="form-control"
						required="required" />
				</div>
			</div>
			<div class="row">
				<div class="col-md-4">
					<form:label path="avatarFile">
						<spring:message code="label.avatar" />
					</form:label>
					<form:input type="file" path="avatarFile" class="form-control"
						required="required" />
				</div>
			</div>
			<a href="${pageContext.request.contextPath }/"
				class="btn btn-default"><spring:message code="label.cancel" /></a>
			<button type="submit" class="btn btn-primary pull-right">
				<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
				<spring:message code="label.save.profile" />
			</button>
		</form:form>
	</div>
</body>
</html>

<script>
$("#frm").on("submit", function(e) {
	if($("#password").val() != $("#confirmPassword").val()) {
		$("#passwordsNotMatch").removeClass("hide");
		e.preventDefault();
	}
});
</script>
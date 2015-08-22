<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<title><spring:message code="title.edit.profile" /></title>
</head>
<body>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<c:if test="${not empty success }">
			<div class="alert alert-success">
				<spring:message code="message.profile.updated" />
			</div>
		</c:if>
		<form:form method="post" commandName="user"
			enctype="multipart/form-data">
			<form:hidden path="id" />
			<form:hidden path="password" />
			<div class="page-header">
				<h1>
					<spring:message code="title.edit.profile" />
				</h1>
			</div>
			<div class="row">
				<div class="col-md-3">
					<form:label path="username">
						<spring:message code="label.username" />
					</form:label>
					<form:input path="username" class="form-control" readonly="true" />
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
					<img class="thumbnail" width="360"
						src="data:image/jpg;base64,${user.avatar }" />
					<form:input type="file" path="avatarFile" class="form-control" />
				</div>
			</div>
			<a href="${pageContext.request.contextPath }/"
				class="btn btn-default">Cancel</a>
			<button type="submit" class="btn btn-primary pull-right">
				<span class="glyphicon glyphicon-save" aria-hidden="true"></span>
				<spring:message code="label.save.profile" />
			</button>
		</form:form>
	</div>
</body>
</html>
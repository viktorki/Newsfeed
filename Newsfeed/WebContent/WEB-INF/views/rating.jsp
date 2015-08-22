<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
	<head>
		<link rel="stylesheet"
			href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
		<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
		<script
			src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
		<title><spring:message code="title.rating" /></title>
	</head>
	<body>
		<div class="container">
			<jsp:include page="menu.jsp" />
			<div class="page-header">
				<h1><spring:message code="title.rating" /></h1>
			</div>
			<div class="container-fluid">
				<table class="table">
					<tr>
						<th><spring:message code="title.number" /></th>
						<th><spring:message code="title.user" /></th>
						<th><spring:message code="title.rating" /></th>
					</tr>
					<c:forEach items="${userList }" var="user" varStatus="status">
						<tr>
							<td>${status.index + 1 }</td>
							<td><img src="data:image/jpg;base64,${user.avatar }"
								class="img-thumbnail" width="50" /> ${user.firstName }
								${user.lastName }</td>
							<td>
								<c:choose>
									<c:when test="${user.bansCount == 0}">
										${user.likesCount == 0 ? "<spring:message code="message.not.applicable" />" : "<spring:message code="message.max" />" }
									</c:when>
									<c:otherwise>
										<fmt:formatNumber value="${user.rating }" maxFractionDigits="2" />
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</body>
</html>

<script>
$(function() {
	$("#rating").addClass("active");
});
</script>
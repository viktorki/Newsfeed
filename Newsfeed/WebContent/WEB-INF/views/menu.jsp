<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#navbar-collapse">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="${pageContext.request.contextPath }/"><spring:message
					code="title.newsfeed" /></a>
		</div>
		<div class="collapse navbar-collapse" id="navbar-collapse">
			<ul class="nav navbar-nav">
				<li id="messages"><a
					href="${pageContext.request.contextPath }/"><spring:message
							code="title.messages" /></a></li>
				<li id="rating"><a
					href="${pageContext.request.contextPath }/rating/"><spring:message
							code="title.rating" /></a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="${pageContext.request.contextPath }/profile/edit"><img
						src="data:image/jpg;base64,${currentUser.avatar }" height="18" />
						${currentUser.firstName } ${currentUser.lastName }</a></li>
				<li><a href="${pageContext.request.contextPath }/logout"><span
						class="glyphicon glyphicon-log-out" aria-hidden="true"></span> <spring:message
							code="label.logout" /></a></li>
			</ul>
		</div>
	</div>
</nav>
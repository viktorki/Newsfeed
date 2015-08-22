<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link rel="stylesheet"
	href="${pageContext.request.contextPath }/css/bootstrap.min.css" />
<script
	src="${pageContext.request.contextPath }/js/jquery-1.11.2.min.js"></script>
<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
<title><spring:message code="title.messages" /></title>
</head>
<body>
	<div class="container">
		<jsp:include page="menu.jsp" />
		<c:if test="${not empty bannedUser }">
			<div class="alert alert-info">
				<spring:message code="message.user.banned"
					arguments="${bannedUser }" />
			</div>
		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<button id="previous" class="btn btn-default">
					<span class="glyphicon glyphicon-backward" aria-hidden="true"></span>
					<spring:message code="label.previous.messages" />
				</button>
				<button id="last" class="btn btn-default pull-right">
					<spring:message code="label.latest.messages" />
					<span class="glyphicon glyphicon-forward" aria-hidden="true"></span>
				</button>
			</div>
			<div id="messageList"></div>
			<div class="input-group panel-footer">
				<input id="text" class="form-control" /> <span
					class="input-group-btn"> <a id="send" href="#"
					class="btn btn-primary"><spring:message code="label.send" /></a>
				</span>
			</div>
		</div>
	</div>
</body>
</html>

<script>
$(function() {
	$("#messages").addClass("active");

	$("#last").trigger("click");
	
	setInterval(function() {
		showMessageList(page);
	}, 1000);

	$("#text").focus();
});

$("#previous").on("click", function(e) {
	page += 1;
	showMessageList(page);

	$("#last").show();
	
	e.preventDefault();
});

$("#last").on("click", function(e) {
	page = 0;
	showMessageList(0);
	$(this).hide();
	
	e.preventDefault();
});

$("#send").on("click", function(e) {
	var text = $("#text").val();

	if (text != "") {
		$.ajax({
			url: "${pageContext.request.contextPath}/message/write",
			type: "post",
			data: {
				text: text
			}
		});

		$("#text").val("");
	}

	$("#last").trigger("click");

	e.preventDefault();
});

function showMessageList(page) {
	$.ajax({
		url: "${pageContext.request.contextPath}/message/list/" + page,
		success: function(data) {
			$("#messageList").html(data);
		}
	});
}

$(document).keypress(function(e) {
    if (e.which == 13) {
        $("#send").trigger("click");
    }
});
</script>
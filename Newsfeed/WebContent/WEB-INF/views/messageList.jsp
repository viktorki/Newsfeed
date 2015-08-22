<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<table class="table borderless">
	<c:forEach items="${messageList }" var="message">
		<tr ${message.likedByCurrentUser ? "class='success'" : "" }>
			<td><img src="data:image/jpg;base64,${message.author.avatar }"
				class="img-thumbnail" width="40" /></td>
			<td class="col-md-10"><small>${message.author.firstName }
					${message.author.lastName }</small> <c:if
					test="${currentUser.id != message.author.id }">
					<a
						href="${pageContext.request.contextPath}/message/ban/${message.author.id }"
						class="btn btn-danger btn-sm"><span
						class="glyphicon glyphicon-minus-sign" aria-hidden="true"></span></a>
				</c:if> <br /> ${message.text }</td>
			<td>
				<button class="btn btn-success btn-lg like"
					${message.likedByCurrentUser ? "disabled='disabled'" : "" }
					data-id="${message.id }" data-likes="${message.likesCount }">${message.likesCount }
					<span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span>
				</button>
			</td>
		</tr>
	</c:forEach>
</table>

<script>
$(function() {
	<c:choose>
		<c:when test="${moreMessages }">
			$("#previous").removeAttr("disabled");
		</c:when>
		<c:otherwise>
			$("#previous").attr("disabled", "disabled");
		</c:otherwise>
	</c:choose>
});

$(".like").on("click", function(e) {
	var id = $(this).data("id");
	var likesCount = $(this).data("likes") + 1;
	
	$.ajax({
		url: "${pageContext.request.contextPath}/message/like/" + id
	});

	$(this).html(likesCount + " <span class=\"glyphicon glyphicon-thumbs-up\" aria-hidden=\"true\"></span>");
	$(this).parent().parent().addClass("success");
	$(this).attr("disabled", "disabled");

	e.preventDefault();
});
</script>
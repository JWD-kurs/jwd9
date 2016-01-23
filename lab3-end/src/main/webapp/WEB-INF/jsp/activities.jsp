<%@include file="/WEB-INF/jsp/layout/header.jsp" %> 

	<h1>Activities</h1>
	
	<table class="table">
		<thead>
			<tr>
				<th>ID</th><th>Name</th><th>Action</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${activitiesModel}" var="activity">
			<tr>
				<td><c:out value="${activity.id }"></c:out> </td>
				<td><c:out value="${activity.name }"></c:out></td>
				<td>
					<a href="/activities/remove/${activity.id }">remove</a>
					<a href="/activities/edit/${activity.id }">edit</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	
	<a href="/activities/add">Add new activity</a>

<%@include file="/WEB-INF/jsp/layout/footer.jsp" %> 
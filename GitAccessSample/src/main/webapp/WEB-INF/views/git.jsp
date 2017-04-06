<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<title>Git Access</title>
<link rel='stylesheet' href='<c:url value="/resources/css/style.css" />'
	type='text/css' media='all' />
</head>
<body>
	<form:form id="gitForm" method="post"
		class="bs-example form-horizontal" commandName="user">
		<div class="form-group">
			<label for="userNameInput" class="col-lg-3 control-label required">Email
				Address</label>
			<div class="col-lg-8">
				<form:input type="text" class="form-control" path="userName"
					id="userNameInput" placeholder="User Name" maxlength="50" />
				<form:errors path="userName" cssClass="error" />
			</div>
		</div>
		
		
		
		<div class="col-lg-9 col-lg-offset-3">
			<button class="btn btn-primary" id="submit">Submit</button>
		</div>
	</form:form>
	
	<br/>
	<br/>
	<c:if test="${formSubmitted == true && repoData != null}">
			<table border="2">
			<thead>
				<tr>
					<td>Name</td>
					<td>URL</td>
				</tr>
				</thead>
				<tbody>
				 <c:forEach var="data" items="${repoData}">
				 <tr>
				 	<td>${data.repoName}</td>
				 	<td>${data.url}</td>
				 </tr>
				 </c:forEach>
				 </tbody>
			</table>
		</c:if>
</body>
</html>
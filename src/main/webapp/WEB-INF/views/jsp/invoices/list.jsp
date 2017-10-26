<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">


<body>

	<div class="container">

		<c:if test="${not empty msg}">
			<div class="alert alert-${css} alert-dismissible" role="alert">
				<button type="button" class="close" data-dismiss="alert" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
				<strong>${msg}</strong>
			</div>
		</c:if>


		<table class="table table-striped">
			<thead>
				<tr>
					
					<th>Name</th>
					<th>Email</th>
					<th>Due Date</th>
					<th>Description</th>
					<th>Amount</th>
				</tr>
			</thead>

	
			<c:set var="total"/>
			<c:forEach var="invoice" items="${invoices}">
				<tr>
					
					<td>${invoice.name}</td>
					<td>${invoice.email}</td>
					<td>${invoice.dueDate}</td>
					<td>${invoice.description}</td>
					<td>${invoice.amount}</td>
				</tr>
				
				<c:set var="total" value="${total + invoice.amount}" />
				
			</c:forEach>
		</table>
		
		<h2>Total : $ <c:out value="${total}"/>	</h2>			

		<spring:url value="/invoices/add" var="urlAddInvoice" />
		<button class="btn btn-primary" onclick="location.href='${urlAddInvoice}'">+(ADD ITEM)</button>
		
	</div>


</body>
</html>
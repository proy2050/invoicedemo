<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html lang="en">

<div class="container">

	<br />

	<spring:url value="/invoices" var="invoiceActionUrl" />

	<form:form class="form-horizontal" method="post" modelAttribute="invoiceForm" action="${invoiceActionUrl}">

		<form:hidden path="invoiceId" />

		<spring:bind path="name">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Name</label>
					<form:input path="name" type="text" class="form-control " id="name"  />
					<form:errors path="name" class="control-label" />
			</div>
		</spring:bind>

		<spring:bind path="email">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Email</label>
					<form:input path="email" class="form-control" id="email" />
					<form:errors path="email" class="control-label" />
			</div>
		</spring:bind>
		
		<spring:bind path="dueDate">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Due Date</label>
					<form:input path="dueDate" class="form-control" id="dueDate"  />
					<form:errors path="dueDate" class="control-label" />
			</div>
		</spring:bind>
		
		<br><br>
		
		<spring:bind path="description">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Description</label>
				<div class="col-sm-10">
					<form:input path="description" class="form-control" id="description"  />
					<form:errors path="description" class="control-label" />
				</div>
			</div>
		</spring:bind>
		
		<br>
		
		<spring:bind path="amount">
			<div class="form-group ${status.error ? 'has-error' : ''}">
				<label class="col-sm-2 control-label">Amount</label>
				<div class="col-sm-10">
					<form:input path="amount" class="form-control" id="amount"  />
					<form:errors path="amount" class="control-label" />
				</div>
			</div>
		</spring:bind>

		<br>
		
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn-lg btn-primary pull-right">SEND</button>
			</div>
		</div>
	</form:form>

</div>


</body>
</html>
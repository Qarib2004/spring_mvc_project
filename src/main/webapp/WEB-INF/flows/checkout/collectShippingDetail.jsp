<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
<title>Shipping</title>
</head>
<body>

<section class="container">
<form:form modelAttribute="order.shippingDetail" class="form-horizontal">
<fieldset>
<legend>Shipping Details</legend>
<div class="form-group">
<label class="control-label col-lg-2" for="name">Name</label>
<div class="col-lg-10">
<form:input id="name" path="name" type="text" class="form-control" />
</div>
</div>
<div class="form-group">
<label class="control-label col-lg-2" for="shippingDate">Shipping Date (dd/mm/yyyy)</label>
<div class="col-lg-10">
<form:input id="shippingDate" path="shippingDate" type="text" class="form-control" />
</div>
</div>
<div class="form-group">
<label class="control-label col-lg-2" for="doorNo">Door No</label>
<div class="col-lg-10">
<form:input id="doorNo" path="shippingAddress.doorNo" type="text" class="form-control" />
</div>
</div>
<!-- Similarly, add input tags for the remaining fields of the shippingAddress domain object. -->
<input type="hidden" name="_flowExecutionKey" value="${flowExecutionKey}"/>
<div class="form-group">
<div class="col-lg-offset-2 col-lg-10">
<button id="back" class="btn btn-default" name="_eventId_backToCollectCustomerInfo">Back</button>
<input type="submit" id="btnAdd" class="btn btn-primary" value="Add" name="_eventId_shippingDetailCollected"/>
<button id="btnCancel" class="btn btn-default" name="_eventId_cancel">Cancel</button>
</div>
</div>
</fieldset>
</form:form>
</section>
</body>
</html>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Products</title>
</head>
<body>

<section class="container">
<c:if test="${result.hasErrors()}">
        <div class="alert alert-danger">
            <form:errors path="*" cssClass="error" />
        </div>
    </c:if>
    <!-- Добавлен атрибут enctype="multipart/form-data" -->
    <form:form modelAttribute="newProduct" class="form-horizontal" action="${pageContext.request.contextPath}/products/add" enctype="multipart/form-data">
        <fieldset>
            <legend><spring:message code="addProduct.form.title" /></legend>

            <!-- Global errors -->
            <form:errors path="*" cssClass="alert alert-danger" element="div"/>

            <div class="form-group">
                <label class="control-label col-lg-2" for="productId">
                    <spring:message code="addProduct.form.productId.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productId" path="productId" type="text" class="form-control"/>
                    <form:errors path="productId" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="name">
                    <spring:message code="addProduct.form.name.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="name" path="name" type="text" class="form-control"/>
                    <form:errors path="name" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitPrice">
                    <spring:message code="addProduct.form.unitPrice.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="unitPrice" path="unitPrice" type="text" class="form-control"/>
                    <form:errors path="unitPrice" cssClass="text-danger"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="manufacturer">
                    <spring:message code="addProduct.form.manufacturer.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="manufacturer" path="manufacturer" type="text" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="category">
                    <spring:message code="addProduct.form.category.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="category" path="category" type="text" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInStock">
                    <spring:message code="addProduct.form.unitsInStock.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="unitsInStock" path="unitsInStock" type="text" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="unitsInOrder">
                    <spring:message code="addProduct.form.unitsInOrder.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="unitsInOrder" path="unitsInOrder" type="text" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="description">
                    <spring:message code="addProduct.form.description.label"/>
                </label>
                <div class="col-lg-10">
                    <form:textarea id="description" path="description" rows="2" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="discontinued">
                    <spring:message code="addProduct.form.discontinued.label"/>
                </label>
                <div class="col-lg-10">
                    <form:checkbox id="discontinued" path="discontinued"/>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-lg-2" for="condition">
                    <spring:message code="addProduct.form.condition.label"/>
                </label>
                <div class="col-lg-10">
                    <form:radiobutton path="condition" value="New"/> <spring:message code="addProduct.form.condition.new"/>
                    <form:radiobutton path="condition" value="Old"/> <spring:message code="addProduct.form.condition.old"/>
                    <form:radiobutton path="condition" value="Refurbished"/> <spring:message code="addProduct.form.condition.refurbished"/>
                </div>
            </div>

            <!-- Добавлено поле для загрузки изображения продукта -->
            <div class="form-group">
                <label class="control-label col-lg-2" for="productImage">
                    <spring:message code="addProduct.form.productImage.label"/>
                </label>
                <div class="col-lg-10">
                    <form:input id="productImage" path="productImage" type="file" class="form-control"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-lg-offset-2 col-lg-10">
                    <input type="submit" id="btnAdd" class="btn btn-primary" value="<spring:message code='addProduct.form.submit.label'/>"/>
                </div>
            </div>

            <div class="pull-right" style="padding-right:50px">
                <a href="?language=messages" >English</a>|<a href="?language=nl" >Dutch</a>
            </div>
        </fieldset>
    </form:form>
</section>
</body>
</html>

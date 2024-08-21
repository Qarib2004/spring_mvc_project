<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.1/angular.min.js"></script>
    <script src="/resource/js/controllers.js"></script>
    <title>Product Details</title>
</head>
<body>

<section class="container" ng-app="cartApp">
    <div class="thumbnail">
        <img src="<c:url value='/resourcesImg/images/${product.productId}.png'/>" alt="image" style="width:60%"/>
        <div class="caption">
            <h3>${product.name}</h3>
            <p>${product.description}</p>
            <p>
                <strong>Item Code : </strong>
                <span class="label label-warning">${product.productId}</span>
            </p>
            <p>
                <strong>Manufacturer:</strong> ${product.manufacturer}
            </p>
            <p>
                <strong>Category:</strong> ${product.category}
            </p>
            <p>
                <strong>Available units in stock:</strong> ${product.unitsInStock}
            </p>
            <h4>${product.unitPrice} USD</h4>
            <p>
                <a href="<spring:url value='/cart' />"class="btn btn-warning btn-large" ng-click="addToCart('${product.productId}')">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Order Now
                </a>
                <a href="<spring:url value='/cart' />" class="btn btn-default" ng-click="viewCart()">
                    <span class="glyphicon glyphicon-hand-right"></span> View Cart
                </a>
            </p>
        </div>
    </div>
</section>
</body>
</html>

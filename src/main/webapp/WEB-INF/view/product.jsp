<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>Product Details</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>Product Details</h1>
        </div>
    </div>
</section>
<section class="container">
    <div class="thumbnail">
        <!-- Добавьте изображение продукта -->
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
                <a href="#" class="btn btn-warning btn-large">
                    <span class="glyphicon glyphicon-shopping-cart"></span> Order Now
                </a>
            </p>
        </div>
    </div>
</section>
</body>
</html>

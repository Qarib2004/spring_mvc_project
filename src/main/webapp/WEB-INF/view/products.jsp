<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css">
    <title>All Products</title>
</head>
<body>
<section>
    <div class="jumbotron">
        <div class="container">
            <h1>All Products</h1>
        </div>
    </div>
</section>
<section class="container">
    <div class="row">
        <c:forEach var="product" items="${products}">
            <div class="col-md-4">
                <div class="thumbnail">
                    <img src="<c:url value='/resourcesImg/images/${product.productId}.png'/>" alt="image" style="width:100%">
                    <div class="caption">
                        <h3>${product.name}</h3>
                        <p>${product.description}</p>
                        <p><a href="<c:url value='/products/details?id=${product.productId}'/>" class="btn btn-primary" role="button">Details</a></p>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>
</body>
</html>

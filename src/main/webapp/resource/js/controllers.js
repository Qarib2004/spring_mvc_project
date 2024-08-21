var cartApp = angular.module('cartApp', []);

cartApp.controller('cartCtrl', function ($scope, $http) {
    $scope.refreshCart = function(cartId) {
        $http.get('/products/rest/cart/' + cartId)
            .then(function(response) {
                $scope.cart = response.data;
            }, function(error) {
                console.error('Error refreshing cart:', error);
            });
    };

    $scope.clearCart = function() {
        $http.delete('/products/rest/cart/' + $scope.cartId)
            .then(function() {
                $scope.refreshCart($scope.cartId);
            }, function(error) {
                console.error('Error clearing cart:', error);
            });
    };

    $scope.initCartId = function(cartId) {
        $scope.cartId = cartId;
        $scope.refreshCart($scope.cartId);
    };

    $scope.addToCart = function(productId) {
        $http.put('/products/rest/cart/add/' + productId)
            .then(function() {
                $scope.refreshCart($scope.cartId);
                alert("Product Successfully added to the Cart!");
            }, function(error) {
                console.error('Error adding to cart:', error);
            });
    };

    $scope.removeFromCart = function(productId) {
        $http.put('/products/rest/cart/remove/' + productId)
            .then(function() {
                $scope.refreshCart($scope.cartId);
            }, function(error) {
                console.error('Error removing from cart:', error);
            });
    };
});

app.controller("productCtrl",function ($scope,mainApiHandler,$rootScope){

    $scope.newProductList = [];
    $scope.popularProduction = [];

    $scope.getNewProductData=()=>{
        $rootScope.page_ = "Product";
        mainApiHandler.callGet("product/newProduct/"
            ,(response)=>{
            $scope.newProductList = response.datalist;
        })
    }
    $scope.getpopularProductData=()=>{
        mainApiHandler.callGet("product/popularProduct/"
            ,(response)=>{
                $scope.popularProduction = response.datalist;
            })
    }

    $scope.getNewProductData();
    $scope.getpopularProductData();

    $scope.getTime = (date)=>{
        let Time = date.substr(0,10);
        return Time;
    }
});

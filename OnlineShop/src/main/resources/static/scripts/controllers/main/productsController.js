app.controller("productsCtrl",function ($scope,mainApiHandler,$rootScope){

    $rootScope.page_ = "Products";
    $scope.categoryList=[];
    $scope.productsList=[];
    $scope.getCategoryList=()=>{
        mainApiHandler.callGet("productcategory"
            ,(response)=>{
                $scope.categoryList = response.datalist;
            })
    };

    $scope.getCategoryList();


    $scope.getNewProductData=()=>{
        $rootScope.page_ = "Product";
        mainApiHandler.callGet("product/newProduct/"
            ,(response)=>{
                $scope.productsList = response.datalist;
            })
    }
    $scope.getpopularProductData=()=>{
        mainApiHandler.callGet("product/popularProduct/"
            ,(response)=>{
                $scope.productsList = response.datalist;
            })
    }
    $scope.getCheapestProductData=()=>{
        mainApiHandler.callGet("product/cheapestProduct/"
            ,(response)=>{
                $scope.productsList = response.datalist;
            })
    }
    $scope.getExpensiveProductData=()=>{
        mainApiHandler.callGet("product/expensiveProduct/"
            ,(response)=>{
                $scope.productsList = response.datalist;
            })
    }



    $scope.selectedFilter = 'Popular';
    $scope.changeSelected=(selectitem)=>{
        $scope.selectedFilter = selectitem;
        switch (selectitem){
            case 'Popular':
                $scope.getpopularProductData();
                break;
            case 'New':
                $scope.getNewProductData();
                break;
            case 'Cheapest':
                $scope.getCheapestProductData();
                break;
            case 'Expensive':
                $scope.getExpensiveProductData();
                break;
        }
    };

    $scope.changeSelected('Popular');
});
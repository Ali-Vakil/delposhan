app.controller("productCategoryCtrl",function ($scope,mainApiHandler,$rootScope){

    $rootScope.page_ = "Product";
    $scope.dataId = 0
    $scope.dataList =[];
    $scope.categoryTitle= "none"
    $scope.qurey = {
        pageSize:12,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;



    $scope.getdatalist = () => {
        let url = 'product/getAll/'+$scope.dataId +'?pageSize=' + $scope.qurey.pageSize + '&pageNumber=' + $scope.qurey.pageNumber;

        mainApiHandler.callGet(url, (response) => {
            $scope.dataList = response.datalist;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.qurey.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.qurey.pageSize > 0)
                $scope.pageCount++;
        });

        mainApiHandler.callGet("productcategory/info/"+ $scope.dataId, (response) => {
            $scope.categoryTitle = response.datalist[0].title;
        });
    }

    $scope.init = (id)=>{
        $scope.dataId = id;
        $scope.getdatalist()
    }
    $scope.getTime = (date)=>{
        let Time = date //.substr(0,10);
        return Time;
    }

    $scope.changPage = (pageNumber) => {
        $scope.qurey.pageNumber = pageNumber;
        $scope.getdatalist();
    }

    $scope.range = (max) => {
        return new Array(max);
    }
});
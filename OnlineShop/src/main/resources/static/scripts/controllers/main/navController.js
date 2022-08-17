app.controller("navCtrl",function ($scope,mainApiHandler,$rootScope){

    $scope.navList = [];
    $scope.searchKey="";
    $scope.page_ = $rootScope.page_;
    $scope.getNavData=()=>{
        mainApiHandler.callGet("Nav/"
            ,(response)=>{
            $scope.navList = response.datalist;
            })
    }

    $scope.search=()=>{
        location.href = "product/find?key="+$scope.searchKey;
    }
    $scope.getNavData();

});
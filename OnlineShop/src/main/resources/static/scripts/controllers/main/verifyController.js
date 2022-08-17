app.controller("verifyCtrl",function ($scope,mainApiHandler,$rootScope,$cookies){

    $rootScope.page_ = "verify";

    $scope.init = ()=>{
        $cookies.remove("basket");
    }

    $scope.init();
});
app.controller('contentAddCtrl',['$scope','ApiHandler','textAngularManager', function ($scope,ApiHandler,textAngularManager){

    $scope.data = {};
    $scope.addData=()=>{
        if($scope.data.key === undefined ||$scope.data.key ==null || $scope.data.key === "")
        {
            Swal.fire('Pleas Insert key');
            return;
        }if($scope.data.value === undefined ||$scope.data.value ==null || $scope.data.value === "")
        {
            Swal.fire('Pleas Insert value');
            return;
        }

        ApiHandler.callPost("Content/",$scope.data,(response)=>{
            debugger;
            $scope.changeMenu("content-list")
        },(error)={

        },true)
    }

}]);
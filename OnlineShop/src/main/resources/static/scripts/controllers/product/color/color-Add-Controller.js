app.controller('colorAddCtrl',function ($scope,ApiHandler){

    $scope.data = {};
    $scope.addData=()=>{
        if($scope.data.name === undefined ||$scope.data.name ==null || $scope.data.name === "")
        {
            Swal.fire('Pleas Insert name');
            return;
        }if($scope.data.value === undefined ||$scope.data.value ==null || $scope.data.value === "")
        {
            Swal.fire('Pleas Insert value');
            return;
        }

        ApiHandler.callPost("color/",$scope.data,(response)=>{
            debugger;
            $scope.changeMenu("color-list")
        },(error)={

        },true)
    }

});
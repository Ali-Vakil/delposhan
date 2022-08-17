app.controller('userAddCtrl',function ($scope,ApiHandler){

    $scope.data = {};
    $scope.addData=()=>{

        if($scope.data.lastName === undefined ||$scope.data.lastName ==null || $scope.data.lastName === "")
        {
            Swal.fire('Pleas Insert lastName');
            return;
        }if($scope.data.username === undefined ||$scope.data.username ==null || $scope.data.username === ""){
            Swal.fire('Pleas Insert username');
            return;
        }if($scope.data.password === undefined ||$scope.data.password ==null || $scope.data.password === ""){
            Swal.fire('Pleas Insert password');
            return;
        }if($scope.data.role === undefined ||$scope.data.role ==null || $scope.data.role === ""){
            Swal.fire('Pleas Insert role');
            return;
        }if($scope.data.enable === undefined ||$scope.data.enable ==null || $scope.data.enable === "")
        {
            Swal.fire('Pleas Choose enable');
            return;
        }

        ApiHandler.callPost("user/",$scope.data,(response)=>{
            debugger;
            $scope.changeMenu("user-list")
        },(error)={

        },true)
    }

});
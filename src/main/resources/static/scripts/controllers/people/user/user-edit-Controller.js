app.controller('userEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EditData=()=>{
        if($scope.data.lastName === undefined ||$scope.data.lastName ==null || $scope.data.lastName === "")
        {
            Swal.fire('Pleas Insert lastName');
            return;
        }if($scope.data.role === undefined ||$scope.data.role ==null || $scope.data.role === ""){
            Swal.fire('Pleas Insert role');
            return;
        }if($scope.data.enable === undefined ||$scope.data.enable ==null || $scope.data.enable === "")
        {
            Swal.fire('Pleas Choose enable');
            return;
        }

        ApiHandler.callPut("user/",$scope.data,(response)=>{
            $scope.changeMenu("user-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('user/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
});
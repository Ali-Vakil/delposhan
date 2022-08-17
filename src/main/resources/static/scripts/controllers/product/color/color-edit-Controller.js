app.controller('colorEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EditData=()=>{
        if($scope.data.name === undefined ||$scope.data.name ==null || $scope.data.name === "")
        {
            Swal.fire('Pleas Insert name');
            return;
        }if($scope.data.value === undefined ||$scope.data.value ==null || $scope.data.value === "")
        {
            Swal.fire('Pleas Insert value');
            return;
        }

        ApiHandler.callPut("color/",$scope.data,(response)=>{
            $scope.changeMenu("color-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('color/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
});
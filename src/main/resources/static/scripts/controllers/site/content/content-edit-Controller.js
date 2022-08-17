app.controller('contentEditCtrl',['$scope','ApiHandler','$rootScope','textAngularManager',function ($scope,ApiHandler,$rootScope,textAngularManager){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EditData=()=>{
        if($scope.data.key === undefined ||$scope.data.key ==null || $scope.data.key === "")
        {
            Swal.fire('Pleas Insert key');
            return;
        }if($scope.data.value === undefined ||$scope.data.value ==null || $scope.data.value === "")
        {
            Swal.fire('Pleas Insert value');
            return;
        }

        ApiHandler.callPut("Content/",$scope.data,(response)=>{
            $scope.changeMenu("content-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('Content/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
}]);
app.controller('sizeEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EditData=()=>{
        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert title');
            return;
        }

        ApiHandler.callPut("size/",$scope.data,(response)=>{
            $scope.changeMenu("size-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('size/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
});
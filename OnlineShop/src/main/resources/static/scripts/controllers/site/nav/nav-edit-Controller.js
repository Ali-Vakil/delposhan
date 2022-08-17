app.controller('navEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EditData=()=>{
        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert Title');
            return;
        }if($scope.data.link === undefined ||$scope.data.link ==null || $scope.data.link === "")
        {
            Swal.fire('Pleas Insert link');
            return;
        }if($scope.data.enable === undefined ||$scope.data.enable ==null || $scope.data.enable === "")
        {
            Swal.fire('Pleas Choose enable');
            return;
        }

        ApiHandler.callPut("Nav/",$scope.data,(response)=>{
            $scope.changeMenu("nav-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('Nav/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
});
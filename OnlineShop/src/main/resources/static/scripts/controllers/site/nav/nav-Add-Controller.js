app.controller('navAddCtrl',function ($scope,ApiHandler){

    $scope.data = {};
    $scope.addData=()=>{
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

        ApiHandler.callPost("Nav/",$scope.data,(response)=>{
            debugger;
            $scope.changeMenu("nav-list")
        },(error)={

        },true)
    }

});
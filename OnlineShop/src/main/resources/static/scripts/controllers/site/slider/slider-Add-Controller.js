app.controller('sliderAddCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.addData=()=>{

        $scope.data.image = $rootScope.uploadFile

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
        }if($scope.data.image === undefined ||$scope.data.image ==null || $scope.data.image === "")
        {
            Swal.fire('Pleas Upload Image');
            return;
        }

        ApiHandler.callPost("slider/",$scope.data,(response)=>{
            $scope.changeMenu("slider-list")
        },(error)={

        },true)
    }

});
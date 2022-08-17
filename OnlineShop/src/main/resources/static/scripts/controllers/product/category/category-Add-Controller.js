app.controller('categoryAddCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.addData=()=>{

        $scope.data.image = $rootScope.uploadFile

        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert Title');
            return;
        }if($scope.data.description === undefined ||$scope.data.description ==null || $scope.data.description === "")
        {
            Swal.fire('Pleas Insert description');
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

        ApiHandler.callPost("productcategory/",$scope.data,(response)=>{
            $scope.changeMenu("category-list")
        },(error)={

        },true)
    }

});
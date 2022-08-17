app.controller('blogAddCtrl',['$scope','$rootScope','ApiHandler','textAngularManager', function ($scope,$rootScope,ApiHandler,textAngularManager){

    $scope.data = {};
    $scope.addData=()=>{

        $scope.data.image = $rootScope.uploadFile

        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert key');
            return;
        }if($scope.data.subtitle === undefined ||$scope.data.subtitle ==null || $scope.data.subtitle === "")
        {
            Swal.fire('Pleas Insert key');
            return;
        }if($scope.data.description === undefined ||$scope.data.description ==null || $scope.data.description === "")
        {
            Swal.fire('Pleas Insert description');
            return;
        }if($scope.data.status === undefined ||$scope.data.status ==null || $scope.data.status === "")
        {
            Swal.fire('Pleas Insert status');
            return;
        }if($scope.data.image === undefined ||$scope.data.image ==null || $scope.data.image === "")
        {
            Swal.fire('Pleas Insert image');
            return;
        }


        ApiHandler.callPost("blog/",$scope.data,(response)=>{
            $scope.changeMenu("blog-list")
        },(error)={

        },true)
    }

}]);
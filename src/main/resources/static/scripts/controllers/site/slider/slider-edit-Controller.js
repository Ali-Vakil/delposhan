app.controller('SliderEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EnableFlage = false
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
        if($scope.data.description === undefined ||$scope.data.description ==null || $scope.data.description === "")
        {
            Swal.fire('Pleas enter description');
            return;
        }

        if($scope.EnableFlage === true){

            if($rootScope.uploadFile === null || $rootScope.uploadFile === undefined)
                $rootScope.uploadFile = $scope.data.image;
            $scope.data.image = $rootScope.uploadFile;
        }


        ApiHandler.callPut("slider/",$scope.data,(response)=>{

            $scope.changeMenu("slider-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('slider/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];

            },(error)=>{}
        ,true)
    }
    $scope.GetData();
    $scope.changeEnableFlag= ()=>{
        if($scope.EnableFlage)
            $scope.EnableFlage = false;
        else
            $scope.EnableFlage = true;
    }
});
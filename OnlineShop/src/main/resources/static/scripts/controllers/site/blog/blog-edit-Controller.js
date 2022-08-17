app.controller('blogEditCtrl',function ($scope,ApiHandler,$rootScope){

    $scope.data = {};
    $scope.id = $rootScope.dataId;
    $scope.EnableFlage = false ;
    $scope.Time;
    $scope.EditData=()=>{

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
        }
        if($scope.EnableFlage === true){

            if($rootScope.uploadFile === null || $rootScope.uploadFile === undefined)
                $rootScope.uploadFile = $scope.data.image;
            $scope.data.image = $rootScope.uploadFile;
        }


        ApiHandler.callPut("blog/",$scope.data,(response)=>{

            $scope.changeMenu("blog-list")
        },(error)={
        },true)
    }
    $scope.GetData=()=>{
        ApiHandler.callGet('blog/'+$scope.id,(response)=>{
            $scope.data = response.datalist[0];
            $scope.Time = $scope.ConvertJsonDateToDateTime($scope.data.publishDate);

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

    $scope.ConvertJsonDateToDateTime = (date)=>{
        // var parsedDate = new Date(parseInt(date.substr(6)));
        // var newDate = new Date(parsedDate);
        var year = parseInt(date.substr(0,4));
        var month = parseInt(date.substr(6,2));
        var day = parseInt(date.substr(9,2));
        return new Date(year, month, day)
    }


});
app.controller('sizeAddCtrl',function ($scope,ApiHandler){

    $scope.data = {};
    $scope.sizesList =[];
    $scope.addData=()=>{
        if($scope.data.title === undefined ||$scope.data.title ==null || $scope.data.title === "")
        {
            Swal.fire('Pleas Insert title');
            return;
        }


        ApiHandler.callGet("size/",(response)=>{
            $scope.sizesList =  response.datalist;
        })
        for (item in $scope.sizesList)
        {
            if(data.title =$scope.sizesList[item].title)
            {
                Swal.fire('Size is exist right now !');
                return;
            }
        }

        ApiHandler.callPost("size/",$scope.data,(response)=>{
            $scope.changeMenu("size-list")
        },(error)={

        },true)
    }

});
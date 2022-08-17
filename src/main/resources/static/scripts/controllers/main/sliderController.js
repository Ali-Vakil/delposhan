app.controller("sliderCtrl",function ($scope,mainApiHandler){

    $scope.sliderList = [];

    $scope.getsliderData=()=>{
        mainApiHandler.callGet("slider/"
            ,(response)=>{
            $scope.sliderList = response.datalist;
            })
    }

    $scope.getsliderData();

});

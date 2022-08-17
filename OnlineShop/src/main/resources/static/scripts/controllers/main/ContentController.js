app.controller("contentCtrl",function ($scope,mainApiHandler){

    $scope.contentList = [];

    $scope.getcontentData=()=>{
        mainApiHandler.callGet("Content/getAllData"
            ,(response)=>{
            $scope.contentList = response.datalist;
            })
    }

    $scope.getcontentData();

    $scope.getContent=(key)=>{
        for(let i =0 ; i < $scope.contentList.length;i++)
        {
            if( $scope.contentList[i].key == key)
            {
                return  $scope.contentList[i].value;
            }
        }
    };
});
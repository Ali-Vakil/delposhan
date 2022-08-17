app.controller("blogInfoCtrl",function ($scope,mainApiHandler,$rootScope){

    $rootScope.page_ = "Blog";
    $scope.dataId = 0
    $scope.data ={};

    $scope.getblogData = () => {
        let url = 'blog/info/'+ $scope.dataId;

        mainApiHandler.callGet(url, (response) => {
            $scope.data = response.datalist[0];
        });
    }

    $scope.init = (id)=>{
        $scope.dataId = id;
        $scope.getblogData()
    }
    $scope.getTime = (date)=>{
        let Time = date.substr(0,10);
        return Time;
    }
});
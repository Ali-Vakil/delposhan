app.controller("blogCtrl",function ($scope,mainApiHandler,$rootScope){

    $rootScope.page_ = "Blog";
    $scope.qurey = {
        pageSize:10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;

    $scope.blogList = [];

    $scope.getblogData = () => {
        let url = 'blog/getAllData?pageSize=' + $scope.qurey.pageSize + '&pageNumber=' + $scope.qurey.pageNumber;

        mainApiHandler.callGet(url, (response) => {
            $scope.blogList = response.datalist;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.qurey.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.qurey.pageSize > 0)
                $scope.pageCount++;

        });
    }

    $scope.changPage = (pageNumber) => {
        $scope.qurey.pageNumber = pageNumber;
        $scope.getdatalist();
    }

    $scope.range = (max) => {
        return new Array(max);
    }

    $scope.getblogData();

    $scope.getTime = (date)=>{
        let Time = date.substr(0,10);
        return Time;
    }
});
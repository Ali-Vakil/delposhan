app.controller('colorListCtrl', function ($scope, ApiHandler, $rootScope) {

    $scope.qurey = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.dataList = [];
    $scope.getdatalist = () => {
        let url = 'color/getAll?pageSize=' + $scope.qurey.pageSize + '&pageNumber=' + $scope.qurey.pageNumber;

        ApiHandler.callGet(url, (response) => {
            $scope.dataList = response.datalist;
            $scope.totalCount = response.totalCount;
            $scope.pageCount = $scope.totalCount / $scope.qurey.pageSize;
            $scope.pageCount = parseInt($scope.pageCount);
            if ($scope.totalCount % $scope.qurey.pageSize > 0)
                $scope.pageCount++;

        }, (err) => {

        }, true);
    }
    $scope.changPage = (pageNumber) => {
        $scope.qurey.pageNumber = pageNumber;
        $scope.getdatalist();
    }
    $scope.range = (max) => {
        return new Array(max);
    }
    $scope.editItem = (id) => {
        $rootScope.dataId = id;
        $scope.changeMenu('color-edit');

    }
    $scope.getdatalist();


});
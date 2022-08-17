app.controller('sliderListCtrl', function ($scope, ApiHandler, $rootScope) {

    $scope.qurey = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.dataList = [];

    $scope.getdatalist = () => {
        let url = 'slider/getAll?pageSize=' + $scope.qurey.pageSize + '&pageNumber=' + $scope.qurey.pageNumber;

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
        $scope.changeMenu('slider-edit');

    }
    $scope.getdatalist();

    $scope.DeleteItem = (id) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, delete it!'
        }).then((result) => {
            if (result.isConfirmed) {
                ApiHandler.callDelete('slider/' + id, (response) => {
                    Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                    )
                    $scope.getdatalist();

                }, (error) => {

                }, true)

            }
        })
    }

    $scope.changeOrder = (id, sliderigationId) => {
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't be able to revert this!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, Do it!'
        }).then((result) => {
                if (result.isConfirmed) {
                    ApiHandler.callPost('slider/updateOrder/' + id + '/' + sliderigationId,null, (response) => {
                        Swal.fire(
                            'Do it!',
                            'Your file has been Moved.',
                            'success'
                        )
                        $scope.getdatalist();
                    }, (error) => {

                    }, true)
                }
            }
        )
    }

});
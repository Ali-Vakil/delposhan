app.controller('categoryListCtrl', function ($scope, ApiHandler, $rootScope) {

    $scope.qurey = {
        pageSize: 10,
        pageNumber: 0
    };
    $scope.totalCount = 0;
    $scope.pageCount = 0;
    $scope.dataList = [];
    $scope.getdatalist = () => {
        let url = 'productcategory/getAll?pageSize=' + $scope.qurey.pageSize + '&pageNumber=' + $scope.qurey.pageNumber;

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
        $scope.changeMenu('category-edit');

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
                ApiHandler.callDelete('productcategory/' + id, (response) => {
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

    $scope.ShowProducts = (data)=>{
        $rootScope.Category = data;
        $scope.changeMenu('product-list');
    }
});
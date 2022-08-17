app.controller("basketCtrl",function ($scope,mainApiHandler,$rootScope,$cookies){

    $rootScope.page_ = "Basket";
    // $scope.getaboutData();

    $scope.dataList=[];
    $scope.totoalCount=0;
    $scope.totalPrice=0;


    $scope.filldataList = ()=>{
        if($cookies.get("basket") == undefined ||
            $cookies.get("basket") == null){
            $scope.dataList = [];
            return;
        }
        $scope.dataList = JSON.parse($cookies.get("basket"));
        for(let i =0 ; i< $scope.dataList.length;i++){
            $scope.totoalCount += parseInt($scope.dataList[i].count);
            $scope.totalPrice += $scope.dataList[i].price * $scope.dataList[i].count;
        }
    };

    $scope.filldataList();


    $scope.DeleteItem = (data)=>{
        Swal.fire({
            title: 'Are you sure?',
            text: "You won't remove this Item from your basket ?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Yes, remove it!'
        }).then((result) => {
            if (result.isConfirmed) {
               for(let i = 0 ;i<$scope.dataList.length ;i++){
                   if($scope.dataList[i].id == data.id){
                       $scope.dataList.splice(i,1);
                   }
               }
               $cookies.put("basket", JSON.stringify($scope.dataList),{path: '/'});
                location.href = location.href;
            }
        })
    }
});